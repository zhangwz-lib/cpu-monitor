package com.awifi.capacity.data.monitor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "awifi.capacity.monitor")
public class MonitorProperties {
    private Long frequency = 1000L;

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }
}