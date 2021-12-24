package com.learntest.copy;

import org.springframework.beans.BeanUtils;

/**
 * @author yanglin
 * @date 2021/1/6 20:13
 */
public class TestCopy {

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.setId(1L);
        Test3 test3 = new Test3();
        test3.setId(2L);
//        BeanUtils.copyProperties(test2,test3);
        BeanUtils.copyProperties(test3,test2);
        System.out.println(test2.getId());
    }
}
