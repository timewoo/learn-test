package com.learntest.base.polymorphic;

/**
 * @author yanglin
 * @date 2022/9/22 17:03
 */
public class Y implements X {
    @Override
    public void show(X x) {
        System.out.println("x");
    }

    public void show(Y y) {
        System.out.println("y");
    }
}
