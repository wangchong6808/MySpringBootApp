package com.spring.boot.practice;

import com.spring.boot.practice.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootAppApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findByID(){
		Assertions.assertThat(customerService.findOne("123").getName()).isEqualTo("Jack");
	}

}
