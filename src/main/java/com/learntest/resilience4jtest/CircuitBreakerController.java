package com.learntest.resilience4jtest;

import com.learntest.resilience4jtest.exception.MyException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;

/**
 * @author yanglin
 * @date 2020/11/23 11:20
 */
@RestController
public class CircuitBreakerController {

    private CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().minimumNumberOfCalls(10).failureRateThreshold(20f).waitDurationInOpenState(Duration.ofSeconds(50)).recordExceptions(RuntimeException.class).ignoreExceptions(IOException.class).enableAutomaticTransitionFromOpenToHalfOpen().build();

    private CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

    private CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("circuitBreaker");

    @GetMapping("/cb-one")
    public Integer one(@RequestParam("number") Integer number) {
        CircuitBreaker circuitBreaker1 = circuitBreakerRegistry.circuitBreaker("circuitBreaker1");
        if (number == 0) {
            circuitBreaker1.reset();
        }
        CheckedFunction0<Integer> decorateCheckedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker1, () -> {
            if (number == 1) {
                throw new MyException("熔断报错");
            }
            return number;
        });
        Try<Integer> aTry = Try.of(decorateCheckedSupplier).recover(throwable -> {
            if (!(throwable instanceof MyException)) {
                System.out.println("错误信息：" + throwable.getMessage());
                return 3;
            }
            return 4;
        });
        return aTry.get();
    }

    @GetMapping("/cb-more")
    public Integer more(@RequestParam("number") Integer number) {
        CircuitBreaker circuitBreaker2 = circuitBreakerRegistry.circuitBreaker("circuitBreaker2");
        if (number == 0) {
            circuitBreaker.reset();
            circuitBreaker2.reset();
        }
        CheckedFunction0<Integer> decorateCheckedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
            if (number == 1) {
                throw new RuntimeException("第一个熔断报错");
            }
            return number;
        });
        CheckedFunction1<Object, Integer> decorateCheckedFunction = CircuitBreaker.decorateCheckedFunction(circuitBreaker2, (input) -> {
            if (number == 2) {
                throw new RuntimeException("第二个熔断报错");
            }
            return number;
        });
        Try<Integer> aTry = Try.of(decorateCheckedSupplier).mapTry(decorateCheckedFunction);
        return aTry.get();
    }
}
