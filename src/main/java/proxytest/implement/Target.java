package proxytest.implement;

import proxytest.interfaces.Subject;

/**
 * @author yanglin
 * @date 2020/9/20 17:37
 */
public class Target implements Subject {
    @Override
    public void test() {
        System.out.println("target");
    }
}
