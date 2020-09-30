package com.nusino.lab.microservices.clientproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nusino.lab.microservices.config.common.AuditInterceptor;
import com.nusino.lab.microservices.model.common.Order;

import io.seata.spring.annotation.GlobalTransactional;

@FeignClient(name="order-service", configuration = AuditInterceptor.class)
@RibbonClient(name="order-service", configuration = AuditInterceptor.class)
@RequestMapping("/api")
public interface OrderApiClient {
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 3000)
	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public Order order(@RequestBody Order order);
}