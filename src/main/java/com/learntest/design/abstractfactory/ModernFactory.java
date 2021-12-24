package com.learntest.design.abstractfactory;

/**
 * @author yanglin
 * @date 2021/1/25 10:40
 */
public class ModernFactory extends AbstractFactory {


    @Override
    Letter createLetter() {
        return new ModernLetter();
    }

    @Override
    Resume createResume() {
        return new ModernResume();
    }
}
