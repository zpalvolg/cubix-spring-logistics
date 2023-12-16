package hu.cubix.spring.zpalvolgyi.logistics.repository;

import hu.cubix.spring.zpalvolgyi.logistics.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section,Long> {

    @Query("SELECT s FROM Section s WHERE (s.startMilestone.id = :milestoneId OR s.endMilestone.id = :milestoneId) AND s.transportPlan.id = :transportPlanId")
    Section findByMilestoneAndTransportPlan(long milestoneId, long transportPlanId);

    @Query("SELECT s FROM Section s WHERE s.sectionOrderNumber = :orderNumber AND s.transportPlan.id = :transportPlanId")
    Section findByOrderNumberAndTransportPlan(int orderNumber, long transportPlanId);
}
