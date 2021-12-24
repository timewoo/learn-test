package com.learntest.design.chain;

/**
 * @author yanglin
 * @date 2021/12/24 11:22
 */
public class OneHandler extends AbstractHandler {

    public OneHandler(AbstractHandler next) {
        super(next);
    }

    @Override
    public boolean getName(String name, Integer number) {
        if (number==1){
            System.out.println("name:"+name+",number:"+number);
            return true;
        }else {
            return doNext(name,number);
        }
    }
}
