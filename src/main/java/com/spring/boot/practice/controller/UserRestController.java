package com.spring.boot.practice.controller;

import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.model.User;
import com.spring.boot.practice.service.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by wangchong on 9/18/16.
 */
@RestController
@RequestMapping(value="/users")
public class UserRestController {

    @Autowired
    MyRestTemplate myRestTemplate;

    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long user) {
        // ...
        User myuser = new User();
        myuser.setId(String.valueOf(user));
        myuser.setName("Jack");
        return myuser;
    }

    @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
    List<Customer> getUserCustomers(@PathVariable Long user) {
        // ...
        return null;
    }

    @RequestMapping(value="/{user}", method= RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long user) {
        // ...
        return null;
    }

    @RequestMapping(value="/greeting/{name}", method=RequestMethod.GET)
    public User greetUser(@PathVariable String name, HttpEntity<byte[]> requestEntity, @ModelAttribute User user){
        System.out.println("greetUser method called......");
        //user.setIntroduction("this is newly created user.");
        return user;
        //return getUser(111l);
    }


    @ModelAttribute
    public User createUser(@RequestParam(required = false) String name){
        System.out.println("createUser method called......");
        //return "{message:\"Hello "+user.getName()+"!\"}";
        //return getUser(111l);
        User user = new User();
        user.setName(name);
        user.setId(String.valueOf(new Random(10).nextInt()));
        user.setIntroduction("this is newly created user.");
        return user;
    }


}
