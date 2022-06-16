package com.learntest.bean.resource;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yanglin
 * @date 2020/12/22 15:21
 */
@Component
//@Lazy
@Getter
@Scope(value = "SimpleThreadScope")
public class Bean1 {
//
//    @Autowired
//    private Bean5 bean5;
//
////    @Lookup
//    public Bean5 getBean5(){
//        return bean5;
//    }

//    public Bean1(Bean5 bean5) {
//        this.bean5 = bean5;
//    }
}
