package com.psp.creditcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardServiceApplication.class, args);
	}

}
