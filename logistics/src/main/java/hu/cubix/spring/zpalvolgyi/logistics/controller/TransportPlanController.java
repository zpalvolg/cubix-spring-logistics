package hu.cubix.spring.zpalvolgyi.logistics.controller;

import hu.cubix.spring.zpalvolgyi.logistics.dto.DelayDto;
import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
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

        TransportPlan transportPlan = transportPlanService.findById(transportPlanId);
        if(transportPlan == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Milestone milestone = milestoneService.findById(milestoneId);
        if(milestone == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if(sectionService.findByMilestoneAndTransportPlan(milestoneId,transportPlanId) == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
