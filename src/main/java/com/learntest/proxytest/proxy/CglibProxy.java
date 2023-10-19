package com.learntest.proxytest.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yanglin
 * @date 2022/10/23 11:04
 */
public class CglibProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglibProxy");
        return methodProxy.invokeSuper(o,objects);
    }
}
