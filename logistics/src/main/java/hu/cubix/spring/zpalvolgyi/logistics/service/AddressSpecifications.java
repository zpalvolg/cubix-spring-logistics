package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import hu.cubix.spring.zpalvolgyi.logistics.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {
    public static Specification<Address> hasCountry(String country){
        return (root, cq, cb) -> cb.equal(root.get(Address_.country), country);
    }

    public static Specification<Address> hasZipCode(String zipCode){
        return (root, cq, cb) -> cb.equal(root.get(Address_.zipCode), zipCode);
    }

    public static Specification<Address> hasCity(String city){
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), city.toLowerCase() + "%");
    }

    public static Specification<Address> hasStreet(String street){
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), street.toLowerCase() + "%");
    }
}
