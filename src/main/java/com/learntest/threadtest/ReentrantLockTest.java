package com.learntest.threadtest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanglin
 * @date 2020/10/30 11:26
 */
public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final Integer index = i;
            new Thread(() -> {
                try {
                    lock.lock();
                    test(index);
                    if (index == 2) {
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }

    }

    public static void test(Integer index) throws InterruptedException {
        System.out.println("Thread " + index + " start");
        if (index != 2) {
            condition.await(3000, TimeUnit.MILLISECONDS);
//            condition.await();
        }
        System.out.println("Thread " + index + " end");
    }
}
