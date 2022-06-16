package com.learntest.proxytest.proxy;

import com.learntest.proxytest.interfaces.Subject;

/**
 * @author yanglin
 * @date 2020/9/20 17:37
 */
public class StaticProxy implements Subject {

    private Subject subject;

    public StaticProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public void test() {
        System.out.println("staticProxy");
        subject.test();
    }
}
