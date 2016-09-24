package com.spring.boot.practice.controller;

import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.model.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by wangchong on 9/18/16.
 * http://localhost:8080/customer/11
 */
@RestController
@RequestMapping(value="/customer")
public class CustomerRestController {

    private static final Logger logger = Logger.getLogger(CustomerRestController.class);

    @RequestMapping(value="/{customer_id}", method=RequestMethod.GET)
    public Customer getUser(@PathVariable Long customer_id, @ModelAttribute Customer customer) {
        if(customer.getName()==null){
            customer.setName("sample");
        }
        if(customer.getIntroduction()==null){
            customer.setIntroduction("I am a sample customer.");
        }
        if(customer.getId()==null){
            customer.setId(String.valueOf(customer_id));
        }
        if(customer.getAddress()==null){
            customer.setAddress("my address is Beijing.");
        }
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return customer;
    }

}
