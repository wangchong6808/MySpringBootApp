package com.spring.boot.practice.controller;

import com.spring.boot.practice.service.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchong on 9/18/16.
 */
@RestController
@RequestMapping(value="/app")
public class MyRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    @RequestMapping(value="/{user_id}", method=RequestMethod.GET)
    public String getUser(@PathVariable Long user_id) {
        String template = "http://localhost:8080/customer/%s";
        String link = String.format(template,String.valueOf(user_id));
        System.out.println(link);
        String result = myRestTemplate.getTemplate().getForObject(link, String.class);
        return result;
    }

}
