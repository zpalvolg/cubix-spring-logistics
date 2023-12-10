package hu.cubix.spring.zpalvolgyi.logistics.service;

import hu.cubix.spring.zpalvolgyi.logistics.model.Address;
import hu.cubix.spring.zpalvolgyi.logistics.model.Milestone;
import hu.cubix.spring.zpalvolgyi.logistics.model.Section;
import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import hu.cubix.spring.zpalvolgyi.logistics.repository.AddressRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.MilestoneRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.SectionRepository;
import hu.cubix.spring.zpalvolgyi.logistics.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDbService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TransportPlanRepository transportPlanRepository;

    public void clearDb(){
        sectionRepository.deleteAll();
        transportPlanRepository.deleteAll();
        milestoneRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Transactional
    public void insertTestData(){

        Address address1 = addressRepository.save(new Address("US", "New York", "Broadway", "10001", "123", 40.7128, -74.0060));
        Address address2 = addressRepository.save(new Address("UK", "London", "Oxford Street", "SW1A 1AA", "456", 51.5074, -0.1278));
        Address address3 = addressRepository.save(new Address("FR", "Paris", "Champs-Élysées", "75008", "789", 48.8566, 2.3522));
        Address address4 = addressRepository.save(new Address("JP", "Tokyo", "Shibuya", "150-0041", "101", 35.6895, 139.6917));

        Milestone milestone1 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(1), address1));
        Milestone milestone2 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(2), address2));
        Milestone milestone3 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(3), address3));
        Milestone milestone4 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(4), address4));

        TransportPlan transportPlan1 = transportPlanRepository.save(new TransportPlan(123.456));

        Section section1 = sectionRepository.save(new Section(1,milestone1,milestone2,transportPlan1));
        Section section2 = sectionRepository.save(new Section(2,milestone3,milestone4,transportPlan1));

    }
}
