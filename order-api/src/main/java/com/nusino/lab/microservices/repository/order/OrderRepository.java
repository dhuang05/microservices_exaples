package com.nusino.lab.microservices.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nusino.lab.microservices.model.order.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * from MS_ORDER order by ordered_date limit 100", nativeQuery = true)
	public List<Order> findAllOrders();

}
