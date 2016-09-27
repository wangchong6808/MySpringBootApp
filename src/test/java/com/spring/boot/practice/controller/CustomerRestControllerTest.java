package com.spring.boot.practice.controller;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class CustomerRestControllerTest {

    private static final Logger logger = Logger.getLogger(CustomerRestControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        logger.info("setup.............................");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("setup is done.....................");
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new CustomerRestController()).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getUser() throws Exception {
        this.mockMvc.perform(get("/app/customer/123").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.Address").value("ShangHai"))
                .andExpect(content().string("{\"id\":\"123\",\"introduction\":\"it is me\",\"name\":\"Jack\",\"contacts\"" +
                        ":[{\"name\":\"Jack\",\"tel\":\"138119113\"},{\"name\":\"Linda\",\"tel\":\"08212333\"}],\"Address\":" +
                        "\"ShangHai\",\"JavaClassName\":\"com.spring.boot.practice.model.Customer\"}"));

    }

    @Test
    public void calculate() throws Exception {

    }

    @Test
    public void createCustomer() throws Exception {

    }

}