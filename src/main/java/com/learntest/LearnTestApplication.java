package com.learntest;

import com.learntest.automatic.ConfigurationTest;
import com.learntest.zk.ZkClientTest;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;

/**
 * @author yanglin
 * @date 2020/10/14 13:50
 */
@SpringBootApplication
public class LearnTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnTestApplication.class,args);
    }
}
