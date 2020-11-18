package com.learntest.threadtest;

/**
 * @author yanglin
 * @date 2020/10/28 17:27
 */
public class SynchronizeTest {

    public static synchronized void test(Integer index) throws InterruptedException {
        System.out.println("Static Thead" + index + " start");
        Thread.sleep(3000);
        System.out.println("Static Thread" + index + " finish");
    }

    public synchronized void test2(Integer index) throws InterruptedException {
        System.out.println("Thead" + index + " start");
        Thread.sleep(3000);
        System.out.println("Thread" + index + " finish");
    }

    public void test1(Integer index) throws InterruptedException {
        synchronized (this) {
            System.out.println("Thead" + index + " start");
//            Thread.sleep(3000);
            if (index!=2){
                this.wait(7000);
            }else{
                this.notify();
            }
            System.out.println("Thread" + index + " finish");
        }
    }
}
