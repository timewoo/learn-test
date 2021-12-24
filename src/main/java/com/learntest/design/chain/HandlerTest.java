package com.learntest.design.chain;

/**
 * @author yanglin
 * @date 2021/12/24 11:28
 */
public class HandlerTest {

    public static void main(String[] args) {
        AbstractHandler handler = new OneHandler(new TwoHandler(new ThreeHandler(null)));
        handler.getName("name",1);
        handler.getName("name",2);
        handler.getName("name",3);
    }
}
