package com.learntest.bean;

import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yanglin
 * @date 2020/9/21 22:21
 */
@ToString
public class Person implements DisposableBean, InitializingBean, BeanFactoryAware, BeanNameAware {

    private String name;

    private BeanFactory beanFactory;

    private String beanName;

    public Person() {
        System.out.println("构造函数");
    }

    public void setName(String name) {
        System.out.println("注入属性");
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean");
    }

    @PostConstruct
    public void anInit() {
        System.out.println("anInit");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }

    public void myInit() {
        System.out.println("myInit");
    }

    public void myDestory() {
        System.out.println("myDestory");
    }

    @PreDestroy
    public void anDestory() {
        System.out.println("anDestory");
    }
}
