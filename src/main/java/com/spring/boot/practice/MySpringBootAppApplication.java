package com.spring.boot.practice;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MySpringBootAppApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	private static final Logger logger = Logger.getLogger(MySpringBootAppApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(MySpringBootAppApplication.class);
		//app.setBannerMode(Banner.Mode.OFF);
		app.addListeners((ApplicationEvent event) -> {
			logger.info("event :::::::::::::"+event.toString());
		});
		app.run(args);
		logger.info("app type"+app.getMainApplicationClass());
	}


}
