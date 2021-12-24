package com.learntest.base;

/**
 * @author yanglin
 * @date 2020/10/5 13:56
 */
public class Test {

    public static void main(String[] args) {
//        String str = "a";
//        String str1 = "a";
//        System.out.println(str==str1);
//        System.out.println(str.equals(str1));
        Long a = 2L;
        Integer b = 1;
        Long c= 1L;
        Integer d = 1;

        System.out.println(a.equals(b+c));
        System.out.println(a.equals(b+d));
    }
}
