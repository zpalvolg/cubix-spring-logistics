package hu.cubix.spring.zpalvolgyi.logistics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "delay")
@Component
public class DelayConfigurationProperties {
    private List<Integer> limits;
    private List<Integer> percentages;

    public List<Integer> getLimits() {
        return limits;
    }

    public void setLimits(List<Integer> limits) {
        this.limits = limits;
    }

    public List<Integer> getPercentages() {
        return percentages;
    }

    public void setPercentages(List<Integer> percentages) {
        this.percentages = percentages;
    }
}
