package com.nusino.lab.microservices.model.order;

import com.nusino.lab.microservices.model.common.Item;
import com.nusino.lab.microservices.model.common.Order;

public class OrderResult {
	private Order order;
	private Item item;
	private String status;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
