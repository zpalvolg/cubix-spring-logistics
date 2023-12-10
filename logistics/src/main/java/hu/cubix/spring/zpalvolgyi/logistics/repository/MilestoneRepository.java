package hu.cubix.spring.zpalvolgyi.logistics.repository;

import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone,Long> {
}
