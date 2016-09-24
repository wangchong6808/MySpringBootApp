package com.spring.boot.practice.controller;

import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.model.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by wangchong on 9/18/16.
 * Test with below link
 * http://localhost:8080/model/greeting/Tom1?introduction=my name is tom
 */
@RestController
@RequestMapping(value="/model")
public class ModelAttributeRestController {

    private static final Logger logger = Logger.getLogger(ModelAttributeRestController.class);

    @RequestMapping(value="/greeting/{name}", method=RequestMethod.GET)
    public User greetUser(@PathVariable String name, HttpEntity<byte[]> requestEntity, @ModelAttribute User user, @ModelAttribute("myUser") User myuser){
        logger.info("greetUser method called......");
        //user's introduction is "my name is tom", means it is overridden by value in request,
        // but id which is set in createUser method is still here.
        //the test proved that User is initialized in @ModelAttribute method then apply values from request in @RequestMapping method
        return user;
    }


    @ModelAttribute
    public User createUser(@RequestParam(required = false) String name){
        logger.info("createUser method called......");
        User user = new User();
        user.setName(name);
        user.setId(String.valueOf(new Random(10).nextInt()));
        user.setIntroduction("this is newly created user.");
        return user;
    }

    @ModelAttribute
    public void createUser(@RequestParam(required = false) String name, Model model){
        logger.info("createUser11 method called......");
        User user = new User();
        user.setName(name);
        user.setId(String.valueOf(new Random(5).nextInt()));
        user.setIntroduction("this is newly created user111.");
        model.addAttribute("myUser",user);
    }


}
