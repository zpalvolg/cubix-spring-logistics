package hu.cubix.spring.zpalvolgyi.logistics.dto;

public class DelayDto {
    Long milestoneId;
    int duration;

    public DelayDto() {
    }

    public DelayDto(Long milestoneId, int duration) {
        this.milestoneId = milestoneId;
        this.duration = duration;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
