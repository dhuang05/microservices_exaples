package com.nusino.lab.microservices.model.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MS_ACCOUNT")
public class Account {
	@Id 
	@GeneratedValue
	 @Column(name="account_id")
	 private Long accountId;
	
	 @Column(name="name")
	 private String name;
	 
	 @Column(name="balance")
	 private BigDecimal balance;
	 
	 @Column(name="updated_date")
	 private Date updatedDate;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	 
	
	
}
