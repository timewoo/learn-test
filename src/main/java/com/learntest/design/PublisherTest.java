package com.learntest.design;

import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author yanglin
 * @date 2020/9/28 21:18
 */
public class PublisherTest {

    @Resource
    private DemoPublisher demoPublisher;

    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
//        applicationContext.publishEvent(new DemoEvent(this,"hello world"));
    }
}
