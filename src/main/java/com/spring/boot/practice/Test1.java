package com.spring.boot.practice;

import java.util.Calendar;

/**
 * Created by wangchong on 11/3/16.
 */
public class Test1 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime());
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        System.out.println(calendar.getTime().getTime());
        System.out.println(1);
        System.out.println(2);
        System.out.println(4);
        System.out.println(3);
        System.out.println(6);
    }
}
