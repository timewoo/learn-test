package com.learntest.circuitbreaker.resilience4jtest;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author yanglin
 * @date 2020/11/24 13:57
 */
@RestController
public class RateLimiterController {

    private RateLimiterConfig rateLimiterConfig = RateLimiterConfig.custom().timeoutDuration(Duration.ofMillis(1)).limitForPeriod(1).limitRefreshPeriod(Duration.ofSeconds(2)).build();

    private RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig);


    @GetMapping("rateLimiter")
    public String rateLimiter(@RequestParam("number") Integer number){
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("rateLimiter");
        CheckedFunction0<String> decorateCheckedSupplier = RateLimiter.decorateCheckedSupplier(rateLimiter, ()->number.toString());
        Try<String> aTry = Try.of(decorateCheckedSupplier).recover(throwable->"限流");
        return aTry.get();
    }

}
