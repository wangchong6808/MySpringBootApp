package com.spring.boot.practice.applicationrunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangchong on 9/18/16.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${LC_CTYPE}")
    private String charset;

    @Value("${my.secret}")
    private String secret;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        boolean debug = applicationArguments.containsOption("debug");
        List<String> files = applicationArguments.getNonOptionArgs();
        System.out.println("--------------MyapplicationRunner is called---------------- charset is "+charset+" secret is "+secret);
    }
}
