package com.learntest.threadtest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglin
 * @date 2020/10/28 15:47
 */
public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        SynchronizeTest synchronizeTest = new SynchronizeTest();
        for (int i = 0; i < 3; i++) {
            final Integer index = i;
//            threadPoolExecutor.execute(() -> {
//                try {
//                    synchronizeTest.test1(index);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
            new Thread(()->{
                try {
                    synchronizeTest.test1(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        threadPoolExecutor.shutdown();
    }
}
