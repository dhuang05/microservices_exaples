package com.nusino.lab.microservices.model.common;

import java.util.Date;


public class Order {

	private Long orderId;

	private Long clientAccountId;

	private Long itemId;

	private Integer quantity;

	private Date orderDate = new Date();

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getClientAccountId() {
		return clientAccountId;
	}

	public void setClientAccountId(Long clientAccountId) {
		this.clientAccountId = clientAccountId;
	}


	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
	
}
