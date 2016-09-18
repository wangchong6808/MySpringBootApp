package com.spring.boot.practice.applicationrunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by wangchong on 9/18/16.
 */
@Component
public class MyCommanLineRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("--------------MyCommanLineRunner is called----------------");
    }
}
