package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.dto.AddressDto;
import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import hu.cubix.spring.zpalvolgyi.logistics.repository.AddressRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.MilestoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @PreAuthorize("hasAuthority('AddressManager')")
    public Address save(Address address){
        return addressRepository.save(address);
    }

    public Boolean validRequest(AddressDto address, String action, Long id){
        if(address == null)
            return false;

        if(action.equals("Create")){
            if(address.getId() != null)
                return false;
        }

        if(action.equals("Update")){
            if(!address.getId().equals(id))
                return false;
        }

        if(address.getCountry() == null || address.getCity() == null
                || address.getStreet() == null || address.getZipCode() == null || address.getHouseNumber() == null)
            return false;

        if(address.getCountry().isEmpty() || address.getCity().isEmpty()
                || address.getStreet().isEmpty()  || address.getZipCode().isEmpty()
                || address.getHouseNumber().isEmpty() )
            return false;

        return true;
    }

    public Address findById(long id){
        return addressRepository.findById(id).orElse(null);
    }

    @Transactional
    @PreAuthorize("hasAuthority('AddressManager')")
    public void deleteById(long id){
        //remove address from milestone to not violate foreign key constraint
        List<Milestone> milestones = milestoneRepository.findByAddressId(id);
        milestones.forEach(m -> m.setAddress(null));
        milestoneRepository.saveAll(milestones);

        addressRepository.deleteById(id);
    }

    public Page<Address> findByExample(Address example, Pageable pageable) {
        String country = example.getCountry() == null ? "" : example.getCountry();
        String city = example.getCity() == null ? "" : example.getCity();
        String street = example.getStreet() == null ? "" : example.getStreet() ;
        String zipCode = example.getZipCode() == null ? "" : example.getZipCode();

        Specification<Address> spec = Specification.where(null);

        if(!country.isEmpty())
            spec = spec.and(AddressSpecifications.hasCountry(country));

        if(!city.isEmpty())
            spec = spec.and(AddressSpecifications.hasCity(city));

        if(!street.isEmpty())
            spec = spec.and(AddressSpecifications.hasStreet(street));

        if(!zipCode.isEmpty())
            spec = spec.and(AddressSpecifications.hasZipCode(zipCode));

        return addressRepository.findAll(spec, pageable);
    }
}
