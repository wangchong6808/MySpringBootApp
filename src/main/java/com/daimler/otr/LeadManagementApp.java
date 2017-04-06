package com.daimler.otr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeadManagementApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LeadManagementApp.class);
        application.run(args);
    }

}
