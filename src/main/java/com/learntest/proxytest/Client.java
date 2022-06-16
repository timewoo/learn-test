package com.learntest.proxytest;

import com.learntest.proxytest.implement.Target;
import com.learntest.proxytest.interfaces.Subject;
import com.learntest.proxytest.proxy.DynamicProxy;
import com.learntest.proxytest.proxy.StaticProxy;

import java.lang.reflect.Proxy;

/**
 * @author yanglin
 * @date 2020/9/20 17:34
 */
public class Client {

    public static void main(String[] args) {
        staticProxy();
        dynamicProxy();
    }

    public static void staticProxy(){
        Subject subject = new Target();
        StaticProxy staticProxy = new StaticProxy(subject);
        staticProxy.test();
    }

    public static void dynamicProxy(){
        Target target = new Target();
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        ClassLoader classLoader = target.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader,new Class[]{Subject.class},dynamicProxy);
        subject.test();
    }
}
