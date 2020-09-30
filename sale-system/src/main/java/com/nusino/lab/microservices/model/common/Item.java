package com.nusino.lab.microservices.model.common;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
		private Long itemId;

		private String name;

		private BigDecimal price;

		private Integer quantity;

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
