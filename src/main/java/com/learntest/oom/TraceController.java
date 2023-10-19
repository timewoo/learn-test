package com.learntest.oom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanglin
 * @date 2022/11/20 11:24
 */
@RestController
@RequestMapping("/trace")
public class TraceController {

    @GetMapping("/test")
    public String test(){
        new Thread(()-> asyncTrace()).start();
        return "test";
    }

    public void asyncTrace(){
        System.out.println("asyncTrace");
    }
}
