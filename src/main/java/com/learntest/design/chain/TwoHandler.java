package com.learntest.design.chain;

/**
 * @author yanglin
 * @date 2021/12/24 11:25
 */
public class TwoHandler extends AbstractHandler{

    public TwoHandler(AbstractHandler next) {
        super(next);
    }
    @Override
    boolean getName(String name, Integer number) {
        if (number==2){
            System.out.println("name:"+name+",number:"+number);
            return true;
        }else {
            return doNext(name, number);
        }
    }
}
