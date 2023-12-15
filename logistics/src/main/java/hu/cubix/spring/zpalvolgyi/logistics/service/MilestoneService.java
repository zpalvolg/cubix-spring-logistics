package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import hu.cubix.spring.zpalvolgyi.logistics.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilestoneService {
    @Autowired
    private MilestoneRepository milestoneRepository;

    public Milestone findById(long id) {
        return milestoneRepository.findById(id).orElse(null);
    }

}