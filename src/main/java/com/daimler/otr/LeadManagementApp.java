package com.daimler.otr;

import com.daimler.otr.controller.RequestInputStreamFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class LeadManagementApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LeadManagementApp.class);
        application.run(args);
    }


    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter filter = new RequestInputStreamFilter();
        beanFactory.autowireBean(filter);
        registration.setFilter(filter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
