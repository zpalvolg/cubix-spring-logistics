package hu.cubix.spring.zpalvolgyi.logistics.repository;

import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportPlanRepository extends JpaRepository<TransportPlan,Long> {
}
