package com.awifi.capacity.data.monitor.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    private String threadName;
    private boolean daemon;
    private ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadFactory(String threadName) {
        init(threadName, false);
    }

    public NamedThreadFactory(String threadName, boolean daemon) {
        init(threadName, daemon);
    }

    private void init(String threadName, boolean daemon) {
        this.threadName = threadName + ":";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, threadName + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        return thread;
    }
}