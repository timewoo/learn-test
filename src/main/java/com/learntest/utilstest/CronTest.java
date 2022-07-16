package com.learntest.utilstest;

import org.redisson.executor.CronExpression;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author yanglin
 * @date 2022/7/15 15:33
 */
public class CronTest {

    public static void main(String[] args) {
        CronExpression expression = new CronExpression("0 0/10 3-4 1/1 * ? ");
        boolean flag = true;
        Date date = java.util.Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        while (flag) {
            date = expression.getNextValidTimeAfter(date);
            System.out.println(date);
        }
    }
}
