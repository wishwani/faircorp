package com.emse.spring.faircorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class FaircorpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaircorpApplication.class, args);
	}



}

