package com.learntest.design.abstractfactory;

/**
 * @author yanglin
 * @date 2021/1/22 11:33
 */
public abstract class AbstractFactory {

    /**
     *
     * @return
     */
    abstract Letter createLetter();

    /**
     *
     * @return
     */
    abstract Resume createResume();
}
