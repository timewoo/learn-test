package com.learntest.bean;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * @author yanglin
 * @date 2021/1/21 17:37
 */
@Component
public class MyLifecycle implements Lifecycle {

    protected volatile boolean running = false;

    /**
     * 判断是否运行，运行则不调用
     */
    @Override
    public void start() {
        System.out.println("lifecycle start");
        running = true;
    }

    /**
     * 判断是否运行，没有运行则不调用
     */
    @Override
    public void stop() {
        System.out.println("lifecycle stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
