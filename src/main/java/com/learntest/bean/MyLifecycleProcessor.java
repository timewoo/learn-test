package com.learntest.bean;

import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yanglin
 * @date 2021/1/22 14:18
 */
@Component
public class MyLifecycleProcessor implements LifecycleProcessor {

    protected volatile boolean running = false;

    @Override
    public void onRefresh() {
        System.out.println("MyLifecycleProcessor refresh");
        running = true;
    }

    @Override
    public void onClose() {
        System.out.println("MyLifecycleProcessor close");
        running = false;
    }

    @Override
    public void start() {
        System.out.println("MyLifecycleProcessor start");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("MyLifecycleProcessor stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
