package LockTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yanglin
 * @date 2020/9/13 19:42
 */
public class CountDownLatchTest {

    private static final int threadCount = 550;

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(300, 300, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
//        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
//        for (int i = 0; i < threadCount; i++) {
//            final int threadNum = i;
//            threadPoolExecutor.execute(() -> {
//                try {
//                    test(threadNum);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        countDownLatch.await();
//        threadPoolExecutor.shutdown();
//        System.out.println("finish");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            threadPoolExecutor.execute(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"----------"+threadNum);
            });
        }
        countDownLatch.countDown();
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum:" + threadNum);
        Thread.sleep(1000);
    }
}
