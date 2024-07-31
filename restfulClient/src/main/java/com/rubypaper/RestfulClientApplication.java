package com.rubypaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.rubypaper","com.ruby"})
public class RestfulClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulClientApplication.class, args);
	}

}
