package com.learntest.resilience4jtest;

import com.learntest.resilience4jtest.exception.MyException;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanglin
 * @date 2020/11/23 17:59
 */
@RestController
public class RetryController {

    private RetryConfig retryConfig = RetryConfig.<String>custom().maxAttempts(4).retryExceptions(RuntimeException.class).ignoreExceptions(IOException.class).retryOnResult(s -> s.contains("Kerry")).waitDuration(Duration.ofSeconds(5)).build();

    private RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);

    @GetMapping("/retry")
    public String retry(@RequestParam("number") Integer number){
        Retry retry = retryRegistry.retry("retry");
        AtomicInteger i=new AtomicInteger();
        CheckedFunction0<String> decorateCheckedSupplier = Retry.decorateCheckedSupplier(retry, () -> {
            System.out.println("重试");
            if (i.incrementAndGet()<2){
                throw new MyException("重试错误");
            }
            return "Kerry";
        });
        Try<String> aTry = Try.of(decorateCheckedSupplier);
        return aTry.get();
    }
}
