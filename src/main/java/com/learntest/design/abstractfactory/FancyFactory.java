package com.learntest.design.abstractfactory;

/**
 * @author yanglin
 * @date 2021/1/25 10:40
 */
public class FancyFactory extends AbstractFactory {


    @Override
    Letter createLetter() {
        FancyLetter fancyLetter = new FancyLetter();
        fancyLetter.setName("FancyLetter");
        return fancyLetter;
    }

    @Override
    Resume createResume() {
        FancyResume fancyResume = new FancyResume();
        fancyResume.setName("FancyResume");
        return fancyResume;
    }
}
