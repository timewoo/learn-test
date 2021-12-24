package com.learntest.ioccontainer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yanglin
 * @date 2020/12/11 11:03
 */
public class Student {

    public static boolean isBeanInstantiated = false;

    public void postConstruct(){
        setBeanInstantiated(true);
    }

    public boolean getIsBeanInstantiated(){
        return isBeanInstantiated;
    }

    public void setBeanInstantiated(boolean isBeanInstantiated){
        isBeanInstantiated = isBeanInstantiated;
    }
}
