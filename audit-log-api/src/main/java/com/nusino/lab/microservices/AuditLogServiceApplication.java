package com.nusino.lab.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuditLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditLogServiceApplication.class, args);

	}

}
