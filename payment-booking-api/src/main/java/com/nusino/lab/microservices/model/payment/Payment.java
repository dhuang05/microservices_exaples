package com.nusino.lab.microservices.model.payment;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MS_PAYMENT")
public class Payment {
	@Id
	@GeneratedValue
	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "payment_amount")
	private BigDecimal paymentAmount;

	@Column(name = "detail")
	private String detail = "";

	@Column(name = "paid_date")
	private Date paidDate = new Date();

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	
}
