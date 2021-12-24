package com.learntest.bean.resource;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author yanglin
 * @date 2020/12/24 11:04
 */
@Component
@Getter
//@Lazy
@Scope(SCOPE_PROTOTYPE)
public class Bean5 {

//    public Bean1 bean1;

//    public Bean5(Bean1 bean1){
//        this.bean1 = bean1;
//    }
}
