package com.atguigu.bookstore.serviceimpl;

import java.util.List;

import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.daoimpl.OrderDaoImpl;
import com.atguigu.bookstore.model.Order;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao = new OrderDaoImpl();

	@Override
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	@Override
	public List<Order> getUserOrders(int userId) {
		return orderDao.getUserOrders(userId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public Order getOrderById(String orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Override
	public void updateOrderStatus(Order order) {
		orderDao.updateOrderStatus(order);
	}
}
