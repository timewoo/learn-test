package com.learntest.locktest;

import java.util.concurrent.*;

/**
 * @author yanglin
 * @date 2020/9/13 17:41
 */
public class SemaphoreTest {

    private static final int threadCount = 550;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(300, 300, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPoolExecutor.execute(() -> {
                try {
                    semaphore.acquire(10);
                    test(threadNum);
                    semaphore.release(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }
}