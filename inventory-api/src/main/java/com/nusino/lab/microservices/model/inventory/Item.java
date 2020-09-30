package com.nusino.lab.microservices.model.inventory;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MS_ITEM")
public class Item {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "updated_date")
	private Date updatedDate = new Date();

	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
