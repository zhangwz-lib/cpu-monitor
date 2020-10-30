package com.awifi.capacity.data.monitor.service;

import com.awifi.capacity.data.monitor.properties.MonitorProperties;
import com.awifi.capacity.data.monitor.task.MonitorTask;
import com.awifi.capacity.data.monitor.threadpool.NamedScheduledExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

public class MonitorService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private MonitorProperties monitorProperties;

    public void init() {
        NamedScheduledExecutorService namedScheduledExecutorService = new NamedScheduledExecutorService("cpu-monitor", 10);
        namedScheduledExecutorService.scheduleAtFixedRate(new MonitorTask(kafkaTemplate, monitorProperties.getKafkaTopic()), 1000, monitorProperties.getFrequency(), TimeUnit.MILLISECONDS);
        logger.debug("启动cpu监控信息");
    }
}