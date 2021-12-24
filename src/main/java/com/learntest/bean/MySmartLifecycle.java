package com.learntest.bean;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author yanglin
 * @date 2021/1/21 19:37
 */
@Component
public class MySmartLifecycle implements SmartLifecycle {

    protected volatile boolean running = false;

    /**
     * 判断isAutoStartup，true则自动调用start方法
     * refresh方法判断，true调用start方法
     * @return
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        // 需要调用callback.run()，不调用DefaultLifecycleProcessor认为stop未完成
        // 默认超时30s后自动结束
//        callback.run();
        System.out.println("MySmartLifecycle callback");
        running = false;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public void start() {
        System.out.println("MySmartLifecycle start");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("MySmartLifecycle stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
