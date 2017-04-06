package com.daimler.otr;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {

    @Scheduled(fixedDelay = 5000)
    public void printMessage(){
        System.out.println("Hi there！！！");
    }
}
