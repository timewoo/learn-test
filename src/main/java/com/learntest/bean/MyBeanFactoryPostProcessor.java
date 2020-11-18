package com.learntest.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author yanglin
 * @date 2020/9/23 16:48
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor(){
        super();
        System.out.println("构造MyBeanFactoryPostProcessor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory");
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        person.getPropertyValues().add("name","110");
    }
}
