package com.learntest.design;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author yanglin
 * @date 2020/9/28 21:14
 */
@Component
public class DemoPublisher {

    @Resource
    ApplicationContext applicationContext;

    public void publisher(String message){
        applicationContext.publishEvent(new DemoEvent(this, message));
    }
}
