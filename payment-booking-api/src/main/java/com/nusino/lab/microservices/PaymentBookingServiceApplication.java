package com.nusino.lab.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@SpringBootApplication
public class PaymentBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentBookingServiceApplication.class, args);

	}

}
