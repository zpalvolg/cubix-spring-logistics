package hu.cubix.spring.zpalvolgyi.logistics.repository;

import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
