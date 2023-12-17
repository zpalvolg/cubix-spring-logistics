package hu.cubix.spring.zpalvolgyi.logistics.controller;

import hu.cubix.spring.zpalvolgyi.logistics.dto.DelayDto;
import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import hu.cubix.spring.zpalvolgyi.logistics.model.Section;
import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import hu.cubix.spring.zpalvolgyi.logistics.service.MilestoneService;
import hu.cubix.spring.zpalvolgyi.logistics.service.SectionService;
import hu.cubix.spring.zpalvolgyi.logistics.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

    @Autowired
    private TransportPlanService transportPlanService;

    @Autowired
    private MilestoneService milestoneService;

    @Autowired
    private SectionService sectionService;

    @PostMapping(value = "/{id}/delay")
    public void delay(@RequestBody DelayDto delayDto, @PathVariable long id){
        long milestoneId = delayDto.getMilestoneId();
        long transportPlanId = id;
        int duration = delayDto.getDuration();

        TransportPlan transportPlan = transportPlanService.findById(transportPlanId);

        //In case of non-existing transport plan or milestone
        if(transportPlan == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Milestone milestone = milestoneService.findById(milestoneId);
        if(milestone == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //if the milestone with the specified id is not part of any sections of the given transport plan
        Section section = sectionService.findByMilestoneAndTransportPlan(milestoneId,transportPlanId);
        if(section == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //response should be 200 OK otherwise
        transportPlanService.handleDelay(section, milestoneId ,duration);
    }
}
