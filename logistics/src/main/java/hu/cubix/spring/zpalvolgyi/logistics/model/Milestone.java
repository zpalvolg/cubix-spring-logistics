package hu.cubix.spring.zpalvolgyi.logistics.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Milestone {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime plannedTime;
    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "startMilestone")
    private Set<Section> sectionsStart;

    @OneToMany(mappedBy = "endMilestone")
    private Set<Section> sectionsEnds;

    public Milestone() {
    }

    public Milestone(LocalDateTime plannedTime, Address address) {
        this.plannedTime = plannedTime;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Section> getSectionsStart() {
        return sectionsStart;
    }

    public void setSectionsStart(Set<Section> sectionsStart) {
        this.sectionsStart = sectionsStart;
    }

    public Set<Section> getSectionsEnds() {
        return sectionsEnds;
    }

    public void setSectionsEnds(Set<Section> sectionsEnds) {
        this.sectionsEnds = sectionsEnds;
    }
}
