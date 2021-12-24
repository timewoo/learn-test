package com.learntest.design.chain;

/**
 * @author yanglin
 * @date 2021/12/24 11:27
 */
public class ThreeHandler extends AbstractHandler{
    public ThreeHandler(AbstractHandler next) {
        super(next);
    }

    @Override
    boolean getName(String name, Integer number) {
        if (number==3){
            System.out.println("name:"+name+",number:"+number);
            return true;
        }else {
            return doNext(name, number);
        }
    }
}
