package com.learntest.design.chain;

/**
 * @author yanglin
 * @date 2021/12/24 13:51
 */
public abstract class AbstractHandler {

    private final AbstractHandler next;

    protected AbstractHandler(AbstractHandler next) {
        this.next = next;
    }

    public boolean doNext(String name, Integer number) {
        if (next == null) {
            return false;
        } else {
            return next.getName(name, number);
        }
    }

    abstract boolean getName(String name, Integer number);
}
