package com.spring.boot.practice;

import org.springframework.test.annotation.ProfileValueSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchong on 9/27/16.
 */
public class TestProfileValueSource implements ProfileValueSource {


    private static Map<String, String> valueMap = new HashMap<>();
    static {
        valueMap.put("env","local");
    }
    @Override
    public String get(String s) {

        return valueMap.get(s);
    }


}
