package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.model.Order;

public interface OrderDao {
	public void saveOrder(Order order);

	public List<Order> getUserOrders(int userId);

	public List<Order> getAllOrders();
	
	public Order getOrderById(String orderId);
	
	public void updateOrderStatus(Order order);
}
