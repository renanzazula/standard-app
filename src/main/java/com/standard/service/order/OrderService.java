package com.standard.service.order;

import com.standard.domain.Order;

import java.util.List;

public interface OrderService {
	Order create(Order order);

	Order update(Order order);

	Order updateStatusOrder(Order order);
	Order updateStatusOrderToPending(Order order);

	void cancel(Order order);

	Order findById(Order order);

	List<Order> findAll();

	List<Order> filterOrder(Order order);

	 
}
