package com.nusino.lab.microservices.controllers.payment;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.payment.Payment;
import com.nusino.lab.microservices.service.payment.PaymentBookingService;

import io.seata.spring.annotation.GlobalTransactional;


@RestController
@RequestMapping("api")
public class PaymentBookingController {
	@Autowired
	private PaymentBookingService paymentService;
	
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 3000)
	@PostMapping(path = "payment", produces = MediaType.APPLICATION_JSON_VALUE)
	public Payment bookPayment(@RequestBody Payment payment) {
		return paymentService.bookPayment(payment);
	}
	
	@GetMapping(path = "payments", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Payment> allPayments() {
		return paymentService.findAllPayments();
	}
	
}
