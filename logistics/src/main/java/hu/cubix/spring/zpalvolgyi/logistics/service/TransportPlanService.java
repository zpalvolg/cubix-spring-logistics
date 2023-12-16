package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.config.DelayConfigurationProperties;
import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import hu.cubix.spring.zpalvolgyi.logistics.model.Section;
import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import hu.cubix.spring.zpalvolgyi.logistics.repository.MilestoneRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.SectionRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransportPlanService {
    @Autowired
    private TransportPlanRepository transportPlanRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private DelayConfigurationProperties config;

    public TransportPlan findById(long id){
        return transportPlanRepository.findById(id).orElse(null);
    }

    @Transactional
    public void handleDelay(Section section,long milestoneId, int duration){
        //Add the delay to the planned time of the milestone
        Milestone milestone = milestoneRepository.findById(milestoneId).orElse(null);

        if(milestone != null){
            increasePlannedTime(milestone,duration);

            //If milestone was a start milestone of a section, add the delay to the planned time of the end milestone of the section as well
            if(milestoneId == section.getStartMilestone().getId()){
                increasePlannedTime(section.getEndMilestone(),duration);
            }

            //If the milestone was an end milestone of a section, add the delay to the planned time of the start milestone of the next section
            if(milestoneId == section.getEndMilestone().getId()){
                Section nextSection = sectionRepository.findByOrderNumberAndTransportPlan(section.getSectionOrderNumber() + 1 , section.getTransportPlan().getId());

                if(nextSection != null){
                    increasePlannedTime(nextSection.getStartMilestone(),duration);
                }
            }

            //The expected income of the transport plan should be decreased by a few percents
            TransportPlan transportPlan = transportPlanRepository.findById(section.getTransportPlan().getId()).orElse(null);
            if (transportPlan != null){
                //The percentage value depends on the length of the delay, and can be defined via properties
                int decreasePercentage = getDecreasePercentage(duration);
                double expectedIncome = transportPlan.getExpectedIncome();
                expectedIncome = expectedIncome * ((double) (100 - decreasePercentage)/100);
                transportPlan.setExpectedIncome(expectedIncome);
                transportPlanRepository.save(transportPlan);
            }
        }
    }

    public void increasePlannedTime(Milestone milestone, int duration){
        LocalDateTime plannedTime = milestone.getPlannedTime();
        plannedTime = plannedTime.plusMinutes(duration);
        milestone.setPlannedTime(plannedTime);
        milestoneRepository.save(milestone);
    }

    public int getDecreasePercentage(int duration){
        int decreasePercentage = 0;

        List<Integer> limits = config.getLimits();
        List<Integer> percentages = config.getPercentages();

        for(int i = 0; i < limits.size(); i++){
            if(duration >= limits.get(i)){
                decreasePercentage = percentages.get(i);
                break;
            }
        }

        return decreasePercentage;
    }
}
