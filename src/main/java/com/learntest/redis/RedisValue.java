package com.learntest.redis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanglin
 * @date 2021/1/20 15:00
 */
@Configuration
@EnableAutoConfiguration
public class RedisValue {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public void setStringValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
}
