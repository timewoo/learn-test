package com.learntest.classtest;

/**
 * @author yanglin
 * @date 2020/9/12 14:05
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println("Parent " + ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println("Parent " + ClassLoaderTest.class.getClassLoader().getParent().getParent());
    }
}
