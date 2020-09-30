package com.nusino.lab.microservices.repository.payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nusino.lab.microservices.model.payment.Payment;


@Repository
public interface PaymentBookingRepository extends JpaRepository<Payment, Long> {

	@Query(value = "SELECT * from MS_PAYMENT order by paid_date desc limit 10", nativeQuery = true)
	public List<Payment> findAllPayments();
}
