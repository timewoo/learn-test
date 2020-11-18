package com.learntest.threadtest;

import org.springframework.transaction.TransactionStatus;

/**
 * @author yanglin
 * @date 2020/8/31 20:46
 */
public class DeadLock {

    private static Object object1 = new Object();

    private static Object object2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread() + "get object1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get object2");
                synchronized (object2) {
                    System.out.println(Thread.currentThread() + "get object2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread() + "get object2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get object1");
                synchronized (object1) {
                    System.out.println(Thread.currentThread() + "get object1");
                }
            }
        }, "线程 2").start();
    }
}
