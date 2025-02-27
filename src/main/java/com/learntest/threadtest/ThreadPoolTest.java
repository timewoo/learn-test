package com.learntest.threadtest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yanglin
 * @date 2020/10/11 11:50
 */
@Slf4j
public class ThreadPoolTest {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>(1), new MyThreadFactory().setNameFormat("子线程").build());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        while (true) {
//            try {
//                exeOrSub(System.currentTimeMillis());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Thread.sleep(2000);
//        }
//        List<Future<?>> futures = getFutures();
//        futures.forEach(a -> {
//            try {
//                System.out.println(a.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
        for (int i = 0; i < 10; i++) {
            catchException(i);
        }
//        Thread.sleep(5000);
        threadPoolExecutor.shutdown();
    }

    public static void catchException(Integer index) throws ExecutionException, InterruptedException {
        threadPoolExecutor.submit(() -> {
//            try {
//                System.out.println(2 / index);
//            }catch (Exception e){
//                System.out.println("error:"+e.getMessage());
//            }
            System.out.println(2 / index);
        });
    }

    public static List<Future<?>> getFutures() {
        List<Future<?>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                Future<?> submit = threadPoolExecutor.submit(() -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0;
                });
                list.add(submit);
            } else {
                Future<?> submit = threadPoolExecutor.submit(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                });
                list.add(submit);
            }
        }
        return list;
    }

    /**
     * 线程池execute和submit
     */
    public static void exeOrSub(Long id) {
        threadPoolExecutor.execute(() -> {
            log.info("子线程执行" + id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorService);
//        executorCompletionService.submit(() -> {
//            log.info("子线程执行"+id);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Future<?> future = threadPoolExecutor.submit(() -> {
//            log.info("子线程执行开始");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("子线程执行结束");
//        });
//        Object object = null;
//        try {
//            object = future.get(1000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        log.info("" + object);
//        log.info("主线程执行");
//        threadPoolExecutor.shutdown();
    }

//    public static void checkHandler(Integer handler){
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>());
//        switch (handler){
//            case 1:
//                threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//            case 2:
//                threadPoolExecutor.setRejectedExecutionHandler();
//        }



//    }
}
