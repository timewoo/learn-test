package proxytest;

import proxytest.implement.Target;
import proxytest.interfaces.Subject;
import proxytest.proxy.DynamicProxy;
import proxytest.proxy.StaticProxy;

import java.lang.reflect.Proxy;

/**
 * @author yanglin
 * @date 2020/9/20 17:34
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Target();
        StaticProxy proxy = new StaticProxy(subject);
        proxy.test();

        Target target = new Target();
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        ClassLoader classLoader = subject.getClass().getClassLoader();
        Subject subject1 = (Subject) Proxy.newProxyInstance(classLoader,new Class[]{Subject.class},dynamicProxy);
        subject1.test();

    }
}
