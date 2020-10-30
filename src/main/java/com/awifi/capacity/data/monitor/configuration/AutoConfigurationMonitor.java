package com.awifi.capacity.data.monitor.configuration;

import com.awifi.capacity.data.monitor.properties.MonitorProperties;
import com.awifi.capacity.data.monitor.service.MonitorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MonitorProperties.class)
@ConditionalOnClass(MonitorService.class)
@ConditionalOnProperty(prefix = "awifi.capacity.monitor", value = {"enable"}, havingValue = "true")
public class AutoConfigurationMonitor {

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public MonitorService monitorService() {
        return new MonitorService();
    }
}