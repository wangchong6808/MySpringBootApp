package com.daimler.otr.controller;

import com.spring.boot.practice.TestProfileValueSource;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ProfileValueSourceConfiguration(TestProfileValueSource.class)
@IfProfileValue(name = "env", value = "local")
public class LeadControllerTest {

    private static final Logger logger = Logger.getLogger(LeadControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new LeadController()).build();

    }

    @Test
    public void should_create_lead_and_return_success() throws Exception {
        this.mockMvc.perform(post("/lead").param("user_name", "jack").param("lead_info", "{\"first_name\":\"姓\", \"last_name\":\"名\", \"gender\":\"male/female\", \"mobile\":\"13911911999\", \"fixed_line_number\":\"66668888\",\n" +
                "    \"wechat\":\"zhang123\",\"company\":\"Daimler\", \"address\":\"东直门\", \"channel\":\"散客线索\", \"role\":\"购买人\", \"grade\":\"A\", \"comments\": \"备注信息\"}").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("OK"));
                //.andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.Address").value("ShangHai"));
    }

}