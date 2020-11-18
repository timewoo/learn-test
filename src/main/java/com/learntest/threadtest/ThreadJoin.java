package com.learntest.threadtest;

/**
 * @author yanglin
 * @date 2020/10/8 11:36
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int a =i;
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+a+"运行");
            });
            thread.start();
            thread.join();
//            Thread.sleep(3000);
            System.out.println("主线程执行");
        }
    }
}
