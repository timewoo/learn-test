package com.learntest.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglin
 * @date 2022/6/11 11:37
 */
public class Pecs {

    public static void main(String[] args) {
        // product extends
        pe();
        // consumer super
        cs();
    }

    private static Number pe(){
        List<? extends Number> list = List.of(1,2,3);
        return list.get(0);
    }

    private static void cs(){
        List<? super Number> list = List.of("2");
    }
}
