package com.awifi.capacity.data.monitor.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class NamedScheduledExecutorService extends ScheduledThreadPoolExecutor {

    public NamedScheduledExecutorService(String name, Integer corePoolSize) {
        super(corePoolSize, new NamedThreadFactory(name));
    }
}