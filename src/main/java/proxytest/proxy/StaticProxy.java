package proxytest.proxy;

import proxytest.interfaces.Subject;

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
        System.out.println("proxy");
        subject.test();
    }
}
