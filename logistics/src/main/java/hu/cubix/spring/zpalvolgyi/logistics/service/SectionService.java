package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public int findByMilestoneAndTransportPlan(long milestoneId, long transportPlanId){
        return sectionRepository.findByMilestoneAndTransportPlan(milestoneId,transportPlanId);
    }
}
