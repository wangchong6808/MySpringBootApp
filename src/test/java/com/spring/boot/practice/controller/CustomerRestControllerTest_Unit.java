package com.spring.boot.practice.controller;

import com.spring.boot.practice.model.Customer;
import com.spring.boot.practice.repository.CustomerRepository;
import com.spring.boot.practice.service.CustomerService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest_Unit {

    private static final Logger logger = Logger.getLogger(CustomerRestControllerTest_Unit.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        logger.info("setup.............................");
        Customer customer = new Customer();
        customer.setId("123");
        customer.setName("Jack");
        customer.setAddress("ShangHai");
        given(customerService.findOne("123")).willReturn(customer);
        //given(customerRepository.findOne("123")).willReturn(customer);

    }

    @Test
    public void should_call_getuser_and_return_name_jack_address_shanghai() throws Exception {
        this.mockMvc.perform(get("/app/customer/123").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.Address").value("ShangHai"));
    }

}