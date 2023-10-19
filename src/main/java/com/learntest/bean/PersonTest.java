package com.learntest.bean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yanglin
 * @date 2020/9/23 16:58
 */
public class PersonTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) classPathXmlApplicationContext.getBeanFactory();

        Person person = classPathXmlApplicationContext.getBean("person", Person.class);
        System.out.println(person);
//        classPathXmlApplicationContext.start();
//        classPathXmlApplicationContext.stop();
//        classPathXmlApplicationContext.refresh();
//        classPathXmlApplicationContext.close();
//        classPathXmlApplicationContext.stop();
//        classPathXmlApplicationContext.registerShutdownHook();
    }
}
