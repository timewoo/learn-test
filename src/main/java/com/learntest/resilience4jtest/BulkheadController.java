package com.learntest.resilience4jtest;

import io.github.resilience4j.bulkhead.*;
import io.vavr.control.Try;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

/**
 * @author yanglin
 * @date 2020/11/24 15:22
 */
@RestController
public class BulkheadController {

    private BulkheadConfig bulkheadConfig = BulkheadConfig.custom().maxConcurrentCalls(1).maxWaitDuration(Duration.ofSeconds(0)).build();

    private BulkheadRegistry bulkheadRegistry = BulkheadRegistry.of(bulkheadConfig);

//    private Bulkhead bulkhead = bulkheadRegistry.bulkhead("bulkhead");

    private ThreadPoolBulkheadConfig threadPoolBulkheadConfig = ThreadPoolBulkheadConfig.custom().coreThreadPoolSize(5).keepAliveDuration(Duration.ofSeconds(2)).maxThreadPoolSize(10).queueCapacity(10).build();

    private ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry = ThreadPoolBulkheadRegistry.of(threadPoolBulkheadConfig);

    @GetMapping("bulkhead")
    public CompletionStage<String> bulkhead(@RequestParam("number") Integer number) {
//        Bulkhead bulkhead = bulkheadRegistry.bulkhead("bulkhead");
//        CheckedFunction0<String> decorateCheckedSupplier = Bulkhead.decorateCheckedSupplier(bulkhead, () -> number.toString());
//        Try<String> aTry = Try.of(decorateCheckedSupplier).recover(throwable -> "并发限制");
//        return aTry.get();
        ThreadPoolBulkhead bulkhead = threadPoolBulkheadRegistry.bulkhead("bulkhead");
        Supplier<CompletionStage<String>> supplier = ThreadPoolBulkhead.decorateSupplier(bulkhead, () -> number.toString());
        Try<CompletionStage<String>> recover = Try.ofSupplier(supplier).recover(throwable ->CompletableFuture.supplyAsync(()->"并发限流"));
        return recover.get();
    }
}
