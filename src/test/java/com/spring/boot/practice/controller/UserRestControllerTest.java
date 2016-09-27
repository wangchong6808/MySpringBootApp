package com.spring.boot.practice.controller;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserRestControllerTest {

    private static final Logger logger = Logger.getLogger(UserRestControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        logger.info("setup .............................");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new CustomerRestController()).build();
        logger.info("setup is done.....................");
    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getUserCustomers() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void greetUser() throws Exception {

    }

    @Test
    public void createUser() throws Exception {

    }

}