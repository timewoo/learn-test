package com.learntest.automatic;

import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yanglin
 * @date 2020/11/11 20:07
 */
//@ComponentScan
//@SpringBootConfiguration
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ConfigurationTest {

    private volatile static ConfigurationTest configurationTest;

    public ConfigurationTest(){};

    public static ConfigurationTest getTest(){
        if (configurationTest==null){
            synchronized (configurationTest){
                if (configurationTest==null){
                    configurationTest = new ConfigurationTest();
                }
            }
        }
        return configurationTest;
    }
}
