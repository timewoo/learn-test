package com.learntest.locktest;

import java.util.concurrent.*;

/**
 * @author yanglin
 * @date 2020/9/13 20:30
 */
public class CyclicBarrierTest {

    private static final int threadCount = 550;

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
//            Thread.sleep(1000);
            threadPoolExecutor.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException, BrokenBarrierException {
        System.out.println("threadNum:" + threadNum + " is ready");
        try {
            cyclicBarrier.await(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("----------CyclicBarrierException------------");
        }
        System.out.println("threadNum:" + threadNum + "is finish");
    }
}
