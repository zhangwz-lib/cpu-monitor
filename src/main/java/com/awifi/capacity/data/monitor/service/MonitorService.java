package com.awifi.capacity.data.monitor.service;

import com.awifi.capacity.data.monitor.properties.MonitorProperties;
import com.awifi.capacity.data.monitor.task.MonitorTask;
import com.awifi.capacity.data.monitor.threadpool.NamedScheduledExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.TimeUnit;

public class MonitorService {

    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    MonitorProperties monitorProperties;

    public void init() {
        NamedScheduledExecutorService namedScheduledExecutorService = new NamedScheduledExecutorService("cpu-monitor", 10);
        namedScheduledExecutorService.scheduleAtFixedRate(new MonitorTask(kafkaTemplate), 1000, monitorProperties.getFrequency(), TimeUnit.MILLISECONDS);
        System.out.println("====启动监控信息=====");
    }
}