package com.spring.boot.practice;

import java.util.*;

/**
 * Created by wangchong on 9/20/16.
 */
public class Test {
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // compile-time error
        }
    }

    public static void main1(String... args){
        Service service = new Service();
        //Event<> e = new Event<>("","hello");
        //Object t = service::handleEvent;
        List<String> list = new ArrayList<>();

        System.out.println(Double.valueOf(Math.random()));
        System.out.println(Double.valueOf(Math.random()));
        System.out.println(Double.valueOf(Math.random()));
        System.out.println(3);

    }

    private static void say(String words, FunctionalInterface fn){

    }
}

class Service {
    public void handleEvent(Event<?> ev) {
        // handle the event data
        System.out.println(ev.e);
    }
}

class Event<T>{
    public String e;
    T ff ;
    public Event(String tt, String t){
        e = tt;
    }

    public void add(T f){
        ff = f;
    }
}