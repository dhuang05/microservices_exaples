package com.nusino.lab.microservices.clientproxy;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nusino.lab.microservices.config.common.AuditInterceptor;
import com.nusino.lab.microservices.model.common.Payment;



@FeignClient(name="payment-booking-service", configuration = AuditInterceptor.class)
@RibbonClient(name="payment-booking-service", configuration = AuditInterceptor.class)
@RequestMapping("api")
public interface PaymentBookingApiClient {
	
	@PostMapping(path = "payment", produces = MediaType.APPLICATION_JSON_VALUE)
	public Payment bookPayment(@RequestBody Payment payment);
	
	@GetMapping(path = "payments", produces = MediaType.APPLICATION_JSON_VALUE)
	public Payment allPayments(@RequestBody Payment payment);
}
