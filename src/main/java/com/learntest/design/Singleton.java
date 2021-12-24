package com.learntest.design;

import org.jboss.marshalling.util.ShortFieldPutter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author yanglin
 * @date 2021/1/4 10:32
 */
public class Singleton {

    private static volatile Singleton uniqueInstance;

    private Singleton(){}

    public static Singleton getUniqueInstance(){
        if (uniqueInstance==null){
            synchronized (Singleton.class){
                if (uniqueInstance==null){
                    uniqueInstance = new Singleton();
                }

            }
        }
        return uniqueInstance;
    }

    public static void main(String[] args) {
//        LocalDate localDateTime = LocalDate.of(2021,1,22);
//        LocalDate localDateTime1 = LocalDate.of(2021,1,30);
//        System.out.println(localDateTime.toEpochDay()-localDateTime1.toEpochDay());
        Date date = new Date();
        Date date1 = new Date();
        date.setTime(date.getTime()+1000*60*60*24*19);
        System.out.println(ChronoUnit.DAYS.between(date.toInstant(),date1.toInstant()));
    }
}
