package com.learntest.base.polymorphic;

/**
 * @author yanglin
 * @date 2022/9/22 16:59
 */
public class checkPolymorphic {


    public static void main(String[] args) {
        X x = new Y();
        Y y = new Y();
        x.show(x);
        x.show(y);
        y.show(x);
        y.show(y);
    }
}
