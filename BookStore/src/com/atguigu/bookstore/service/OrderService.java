package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.model.Order;

public interface OrderService {
	public void saveOrder(Order order);

	public List<Order> getUserOrders(int userId);

	public List<Order> getAllOrders();

	public Order getOrderById(String orderId);
	
	public void updateOrderStatus(Order order);
}
