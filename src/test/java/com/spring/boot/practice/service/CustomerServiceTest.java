package com.spring.boot.practice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findByID(){
        assertThat(customerService.findOne("123").getName()).isEqualTo("Jack");
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findOne() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void mapReduce() throws Exception {

    }

    @Test
    public void mapReduce1() throws Exception {

    }

}