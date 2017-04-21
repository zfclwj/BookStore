package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.model.OrderItem;

public interface OrderItemDao {
	public void batchSaveOrderItem(List<OrderItem> list);

	public List<OrderItem> getUserOrderItems(String orderId);

	public List<OrderItem> getAllOrderItems();
}
