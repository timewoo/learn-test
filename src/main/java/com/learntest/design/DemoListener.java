package com.learntest.design;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author yanglin
 * @date 2020/9/28 21:12
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String message = event.getMessage();
        System.out.println(message);
    }
}
