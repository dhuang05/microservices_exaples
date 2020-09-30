package com.nusino.lab.microservices.service.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.model.payment.Payment;
import com.nusino.lab.microservices.repository.payment.PaymentBookingRepository;
import com.nusino.lab.microservices.streams.common.AuditLogProducer;

@Service
public class PaymentBookingService {
		
	@Autowired
	private PaymentBookingRepository paymentRepository;
	

	@Autowired
	private AuditLogProducer auditLogProducer;
	
	@Transactional
	public Payment bookPayment(Payment payment) {
		payment = paymentRepository.save(payment);
		auditLogProducer.auditLog(OperationType.INSERT, Payment.class, payment.getPaymentId(), payment, "book payment");
		return payment;
	}
	
	public List<Payment> findAllPayments() {
		return paymentRepository.findAllPayments();
	}
}
