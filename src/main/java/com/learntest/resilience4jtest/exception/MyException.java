package com.learntest.resilience4jtest.exception;

/**
 * @author yanglin
 * @date 2020/11/23 17:51
 */
public class MyException extends RuntimeException{
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }
}
