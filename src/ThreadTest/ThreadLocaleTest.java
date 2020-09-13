package ThreadTest;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanglin
 * @date 2020/9/5 10:35
 */
public class ThreadLocaleTest implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmmss"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocaleTest localeTest = new ThreadLocaleTest();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(localeTest, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread name:" + Thread.currentThread().getName() + " default Formatter:" + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread name:" + Thread.currentThread().getName() + " Formatter:" + formatter.get().toPattern());
    }
}
