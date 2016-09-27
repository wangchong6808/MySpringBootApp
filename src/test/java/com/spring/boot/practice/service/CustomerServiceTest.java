package com.spring.boot.practice.service;

import com.spring.boot.practice.MySpringBootAppApplication;
import com.spring.boot.practice.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes=MySpringBootAppApplication.class)
//@DirtiesContext
@SpringBootTest
@TestPropertySource("/test.properties")
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
    @Rollback
    public void save() throws Exception {
        Customer customer = new Customer();
        customer.setId(String.valueOf(Double.valueOf(Math.random()*1000).intValue()));
        customer.setIntroduction("inserted in test");
        customer.setAddress("ChengDu");
        customer.setName("Piyali");
        customerService.save(customer);
        List<Customer> customers = customerService.findByName("Piyali");
        assertThat(customers.get(0).getAddress()).isEqualTo("ChengDu");
        customerService.delete(customer);
    }

    @Test
    public void mapReduce() throws Exception {

    }

    @Test
    public void mapReduce1() throws Exception {

    }

}