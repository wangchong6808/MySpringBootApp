package com.spring.boot.practice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchong on 9/17/16.
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Example.class);
        //app.setBannerMode(Banner.Mode.OFF);
        app.addListeners((ApplicationEvent event) -> {
            System.out.println("event :::::::::::::"+event.toString());
        });
        app.run(args);
    }

}
