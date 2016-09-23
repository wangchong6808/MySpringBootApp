package com.spring.boot.practice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wangchong on 9/23/16.
 */
@Service
public class MyRestTemplate {
    private final RestTemplate template;

    public MyRestTemplate(RestTemplateBuilder builder){
        builder = builder.setConnectTimeout(1000);
        builder = builder.setReadTimeout(10000);
        this.template = builder.build();
    }

    public RestTemplate getTemplate(){
        return template;
    }
}
