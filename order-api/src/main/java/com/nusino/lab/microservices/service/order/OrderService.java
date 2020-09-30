package com.nusino.lab.microservices.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.model.order.Order;
import com.nusino.lab.microservices.repository.order.OrderRepository;
import com.nusino.lab.microservices.streams.common.AuditLogProducer;


@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AuditLogProducer auditLogProducer;
	
	//private Random roolBackRandom = new Random(); 


	public List<Order> allOrders() {
		return orderRepository.findAllOrders();
	}

	@Transactional
	public Order playOrder(Order order) {
		if(order.getItemId() == null || order.getClientAccountId() == null || order.getQuantity() == null || order.getQuantity() <=0 ) {
			auditLogProducer.auditLog(OperationType.ROLLBACK, order.getClass(),order.getOrderId(),  order, "Invalid order");
			throw new RuntimeException("Invalid order");
		}

		//record order
		order = orderRepository.save(order);
		auditLogProducer.auditLog(OperationType.INSERT, order.getClass(), order.getOrderId(), order, "New order");
		return order;
	}
}
