package com.nusino.lab.microservices.model.common;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
	private Long paymentId;
	private Long accountId;
	private BigDecimal paymentAmount;
	private String detail = "";
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
