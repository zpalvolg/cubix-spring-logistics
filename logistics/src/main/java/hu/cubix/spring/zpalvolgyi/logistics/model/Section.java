package hu.cubix.spring.zpalvolgyi.logistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private Long id;
    private int sectionOrderNumber;
    @ManyToOne
    private Milestone startMilestone;
    @ManyToOne
    private Milestone endMilestone;

    @ManyToOne
    private TransportPlan transportPlan;

    public Section() {
    }

    public Section(int sectionOrderNumber, Milestone startMilestone, Milestone endMilestone) {
        this.sectionOrderNumber = sectionOrderNumber;
        this.startMilestone = startMilestone;
        this.endMilestone = endMilestone;
    }

    public Section(int sectionOrderNumber, Milestone startMilestone, Milestone endMilestone, TransportPlan transportPlan) {
        this.sectionOrderNumber = sectionOrderNumber;
        this.startMilestone = startMilestone;
        this.endMilestone = endMilestone;
        this.transportPlan = transportPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSectionOrderNumber() {
        return sectionOrderNumber;
    }

    public void setSectionOrderNumber(int sectionOrderNumber) {
        this.sectionOrderNumber = sectionOrderNumber;
    }

    public Milestone getStartMilestone() {
        return startMilestone;
    }

    public void setStartMilestone(Milestone startMilestone) {
        this.startMilestone = startMilestone;
    }

    public Milestone getEndMilestone() {
        return endMilestone;
    }

    public void setEndMilestone(Milestone endMilestone) {
        this.endMilestone = endMilestone;
    }

    public TransportPlan getTransportPlan() {
        return transportPlan;
    }

    public void setTransportPlan(TransportPlan transportPlan) {
        this.transportPlan = transportPlan;
    }
}
