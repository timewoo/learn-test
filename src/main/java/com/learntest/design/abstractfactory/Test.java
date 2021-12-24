package com.learntest.design.abstractfactory;

/**
 * @author yanglin
 * @date 2021/1/25 11:01
 */
public class Test {

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new FancyFactory();
        Letter letter = abstractFactory.createLetter();
        System.out.println(letter.getName());
    }
}
