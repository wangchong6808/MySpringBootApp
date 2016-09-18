package com.spring.boot.practice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MySpringBootAppApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(MySpringBootAppApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.addListeners((ApplicationEvent event) -> {
			System.out.println("event :::::::::::::"+event.toString());
		});
		app.run(args);
		System.out.println("app type"+app.getMainApplicationClass());
	}


}
