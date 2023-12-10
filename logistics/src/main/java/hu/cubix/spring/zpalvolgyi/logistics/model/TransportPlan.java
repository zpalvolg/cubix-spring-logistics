package hu.cubix.spring.zpalvolgyi.logistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TransportPlan {
    @Id
    @GeneratedValue
    private Long id;
    private double expectedIncome;
    @OneToMany(mappedBy = "transportPlan")
    private List<Section> sections;

    public TransportPlan() {
    }

    public TransportPlan(double expectedIncome, List<Section> sections) {
        this.expectedIncome = expectedIncome;
        this.sections = sections;
    }

    public TransportPlan(double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
