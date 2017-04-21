package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.model.Cart;
import com.atguigu.bookstore.model.OrderItem;

public interface OrderItemService {
	public void saveOrderItem(Cart cart,String orderId);

	public List<OrderItem> getUserOrderItems(String orderId);

	public List<OrderItem> getAllOrderItems();
}
