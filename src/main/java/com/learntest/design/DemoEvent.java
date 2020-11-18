package com.learntest.design;

import org.springframework.context.ApplicationEvent;

/**
 * @author yanglin
 * @date 2020/9/28 21:10
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    public String getMessage(){
        return message;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source,String message) {
        super(source);
        this.message = message;
    }
}
