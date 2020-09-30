package com.nusino.lab.microservices.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.order.Order;
import com.nusino.lab.microservices.service.order.OrderService;

import io.seata.spring.annotation.GlobalTransactional;


@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> allOrders() {
		return orderService.allOrders();
	}
	
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 10000)
	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public Order order(@RequestBody Order order) {
		return orderService.playOrder(order);
	}
}
