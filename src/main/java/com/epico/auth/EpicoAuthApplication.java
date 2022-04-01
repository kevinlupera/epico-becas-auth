package com.epico.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "com.epico.auth.api", "com.epico.auth.entity", "com.epico.auth.repository",
		"com.epico.auth.config",
		"com.epico.auth.security" })
public class EpicoAuthApplication {


	public static void main(String[] args) {
		SpringApplication.run(EpicoAuthApplication.class, args);
	}

}
