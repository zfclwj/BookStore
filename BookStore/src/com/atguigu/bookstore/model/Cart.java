package com.atguigu.bookstore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> cart = new HashMap<>();

	public Map<Integer, CartItem> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, CartItem> cart) {
		this.cart = cart;
	}

	public void addCartItems(CartItem cartItem) {
		if (cart.containsKey(cartItem.getId())) {
			CartItem item = cart.get(cartItem.getId());
			item.setCount(item.getCount() + 1);
			cart.put(item.getId(), item);
		} else {
			cart.put(cartItem.getId(), cartItem);
		}
	}

	public void updateItems(Integer id, Integer count) {
		cart.get(id).setCount(count);
	}

	public boolean deleteItems(Integer id) {
		int before = cart.size();
		cart.remove(id);
		int after = cart.size();
		return before > after;
	}

	public void clearItems() {
		cart.clear();
	}

	public Integer getTotalCount() {
		Integer count = 0;
		for (CartItem item : cart.values()) {
			count += item.getCount();
		}
		return count;
	}

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(0);
		for (CartItem item : cart.values()) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}
		return totalPrice;
	}
}
