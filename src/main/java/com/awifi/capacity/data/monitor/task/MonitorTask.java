package com.awifi.capacity.data.monitor.task;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.InetAddress;

public class MonitorTask implements Runnable {

    private KafkaTemplate<String, Object> kafkaTemplate;

    private String kafkaTopic;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MonitorTask(KafkaTemplate kafkaTemplate, String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    public void run() {
        try {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            long max = memoryMXBean.getHeapMemoryUsage().getMax();
            long used = memoryMXBean.getHeapMemoryUsage().getUsed();
            long init = memoryMXBean.getHeapMemoryUsage().getInit();

            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                    .getOperatingSystemMXBean();
            double systemCpuLoad = osmxb.getSystemLoadAverage();
            double processCpuLoad = osmxb.getProcessCpuLoad();

            kafkaTemplate.send(kafkaTopic, "sss");
            String hostName = InetAddress.getLocalHost().getHostName();
            String currentIpAddress = InetAddress.getByName(hostName).getHostAddress();
            logger.info("max：{} used: {} init: {} SystemCpuLoad: {} processCpuLoad: {}  currentIpAddress： {}", max, used, init, systemCpuLoad, processCpuLoad, currentIpAddress);
        } catch (Exception e) {
            logger.error("cpu monitor handle error：{}", e);
        }

    }
}