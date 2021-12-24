package com.learntest.bean.resource;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author yanglin
 * @date 2020/12/22 15:22
 */
@Configuration
@ComponentScan(basePackages = "com.learntest.bean.resource")
public class ResourceConfig {

    @Bean
    public static CustomScopeConfigurer customScopeConfigurer(){
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("SimpleThreadScope",new SimpleThreadScope());
        customScopeConfigurer.setScopes(stringObjectHashMap);
        return customScopeConfigurer;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ResourceConfig.class);
        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                Bean1 bean = applicationContext.getBean(Bean1.class);
//                System.out.println(bean);
//            }).start();
            Bean1 bean = applicationContext.getBean(Bean1.class);
//            System.out.println(bean.getBean5());
            System.out.println(bean);
        }
    }
}
