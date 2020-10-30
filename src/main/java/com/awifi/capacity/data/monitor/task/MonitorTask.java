package com.awifi.capacity.data.monitor.task;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MonitorTask implements Runnable {

    private KafkaTemplate kafkaTemplate;
   private Logger logger = LoggerFactory.getLogger(getClass());

    public MonitorTask(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
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

            kafkaTemplate.send("test", "sss");
            logger.info("max：" + max + " used:" + used + " init:" + init + " SystemCpuLoad:" + systemCpuLoad + " processCpuLoad: " + processCpuLoad);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}