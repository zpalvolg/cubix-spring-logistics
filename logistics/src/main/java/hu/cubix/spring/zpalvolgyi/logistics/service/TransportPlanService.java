package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import hu.cubix.spring.zpalvolgyi.logistics.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportPlanService {
    @Autowired
    private TransportPlanRepository transportPlanRepository;

    public TransportPlan findById(long id){
        return transportPlanRepository.findById(id).orElse(null);
    }
}
