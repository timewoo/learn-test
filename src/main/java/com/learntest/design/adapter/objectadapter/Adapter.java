package com.learntest.design.adapter.objectadapter;

import com.learntest.design.adapter.Adaptee;
import com.learntest.design.adapter.Target;

/**
 * @author yanglin
 * @date 2021/1/25 16:23
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.request();
    }
}
