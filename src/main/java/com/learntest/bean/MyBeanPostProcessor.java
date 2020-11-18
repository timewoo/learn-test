package com.learntest.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author yanglin
 * @date 2020/9/22 20:11
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor(){
        super();
        System.out.println("构造MyBeanPostProcessor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return bean;
    }
}
