package com.nusino.lab.microservices.service.salesystem;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.clientproxy.AccountApiClient;
import com.nusino.lab.microservices.clientproxy.InventoryApiClient;
import com.nusino.lab.microservices.clientproxy.OrderApiClient;
import com.nusino.lab.microservices.clientproxy.PaymentBookingApiClient;
import com.nusino.lab.microservices.model.common.Account;
import com.nusino.lab.microservices.model.common.Debit;
import com.nusino.lab.microservices.model.common.Item;
import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.model.common.Order;
import com.nusino.lab.microservices.model.common.Payment;
import com.nusino.lab.microservices.model.order.OrderResult;
import com.nusino.lab.microservices.streams.common.AuditLogProducer;


@Service
public class SaleService {

	@Autowired
	private OrderApiClient orderApiClient;
	
	@Autowired
	private AccountApiClient accountApiClient;
	
	@Autowired
	private PaymentBookingApiClient paymentApiClient;
	
	@Autowired
	private InventoryApiClient inventoryApiClient;
	

	@Autowired
	private AuditLogProducer auditLogProducer;
	
	//private Random roolBackRandom = new Random(); 


	@Transactional
	public OrderResult playOrder(Order order) {
		OrderResult orderResult = new OrderResult();
		try {
			//record order
			order = orderApiClient.order(order);		
			
			//inventory check
			Item item = inventoryApiClient.increaseItemNumber(order.getItemId(), -1 * order.getQuantity());

			//Book payment
			Payment payment = new Payment();
			payment.setAccountId(order.getClientAccountId());
			BigDecimal amount = new BigDecimal(item.getPrice().doubleValue() * order.getQuantity());
			payment.setPaymentAmount(amount);
			payment.setPaidDate(new Date());
			payment.setDetail("for item " + item.getName());
			payment = paymentApiClient.bookPayment(payment);

				 
			//debit to account
			Debit debit = new Debit();
			debit.setAccountId(payment.getAccountId());
			debit.setAmount(payment.getPaymentAmount());
			Account account  = accountApiClient.debit(debit);
			 
			//prepare return;
			Item itemInfo = new Item();
			itemInfo.setItemId(item.getItemId());
			itemInfo.setName(item.getName());
			itemInfo.setPrice(item.getPrice());
			itemInfo.setQuantity(order.getQuantity());
			orderResult.setStatus("OK");
			orderResult.setItem(itemInfo);
			orderResult.setOrder(order);
			
		} catch(RuntimeException rEx) {
			orderResult.setStatus(rEx.getMessage());
			orderResult.setOrder(order);
			//log
			auditLogProducer.auditError(Order.class, null, order, rEx.getMessage());
			throw rEx;
		}
		
		return orderResult;
	}
}
