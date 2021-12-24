package com.learntest.redis;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yanglin
 * @date 2020/11/17 14:09
 */
@Configuration
@ComponentScan(basePackages = "com.learntest.redis")
public class RedisTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisTest.class);
        RedisValue redisValue = applicationContext.getBean(RedisValue.class);
        redisValue.setStringValue("1","1");
    }
}
