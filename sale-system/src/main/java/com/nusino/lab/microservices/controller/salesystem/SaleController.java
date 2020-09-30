package com.nusino.lab.microservices.controller.salesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.common.Order;
import com.nusino.lab.microservices.model.order.OrderResult;
import com.nusino.lab.microservices.service.salesystem.SaleService;

import io.seata.spring.annotation.GlobalTransactional;


@RestController
@RequestMapping("/api")
public class SaleController {

	@Autowired
	private SaleService saleService;


	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 10000)
	@PostMapping(path = "/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderResult playOrder(@RequestBody Order order) {
		return saleService.playOrder(order);
	}
}
