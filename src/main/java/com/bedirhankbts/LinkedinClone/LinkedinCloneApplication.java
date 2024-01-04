package com.bedirhankbts.LinkedinClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class LinkedinCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedinCloneApplication.class, args);
	}

}
