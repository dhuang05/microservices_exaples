package com.nusino.lab.microservices.model.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MS_ORDER")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "client_account_id")
	private Long clientAccountId;

	@Column(name = "item_Id")
	private Long itemId;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "ordered_date")
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
