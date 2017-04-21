package com.atguigu.bookstore.model;

import java.math.BigDecimal;

public class CartItem{
	private Integer id;
	private String name;
	private BigDecimal price;
	private int count = 1;

	public BigDecimal getTotalPrice() {
		return price.multiply(new BigDecimal(count));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CartItem(Integer id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public CartItem() {
		super();
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", price=" + price + ", count=" + count + "]";
	}
}
