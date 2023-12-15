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

        Address address5 = addressRepository.save(new Address("CA", "Toronto", "Yonge Street", "M5B 1R4", "789", 43.6532, -79.3832));
        Address address6 = addressRepository.save(new Address("AU", "Sydney", "George Street", "2000", "456", -33.8688, 151.2093));
        Address address7 = addressRepository.save(new Address("DE", "Berlin", "Friedrichstraße", "10117", "101", 52.5200, 13.4050));
        Address address8 = addressRepository.save(new Address("BR", "São Paulo", "Avenida Paulista", "01310-100", "123", -23.5505, -46.6333));


        Milestone milestone1 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(1), address1));
        Milestone milestone2 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(2), address2));
        Milestone milestone3 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(3), address3));
        Milestone milestone4 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(4), address4));

        Milestone milestone5 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(1), address5));
        Milestone milestone6 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(2), address6));
        Milestone milestone7 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(3), address7));
        Milestone milestone8 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusDays(4), address8));

        TransportPlan transportPlan1 = transportPlanRepository.save(new TransportPlan(123.456));
        TransportPlan transportPlan2 = transportPlanRepository.save(new TransportPlan(456.123));

        Section section1 = sectionRepository.save(new Section(1,milestone1,milestone2,transportPlan1));
        Section section2 = sectionRepository.save(new Section(2,milestone3,milestone4,transportPlan1));

        Section section3 = sectionRepository.save(new Section(1,milestone5,milestone6,transportPlan2));
        Section section4 = sectionRepository.save(new Section(2,milestone7,milestone8,transportPlan2));

    }
}
