package com.nusino.lab.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients
@EnableFeignClients
@SpringBootApplication
public class SaleSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleSystemApplication.class, args);

	}
}

