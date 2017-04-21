package com.atguigu.bookstore.model;

import java.math.BigDecimal;

public class Order {
	private String orderId;
	private String createTime;
	private BigDecimal totalPrice;
	private int status;//0表示未发货，1表示已发货，2表示已签收
	private String userId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Order(String orderId, String createTime, BigDecimal totalPrice, int status, String userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.userId = userId;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createTime=" + createTime + ", totalPrice=" + totalPrice + ", status="
				+ status + ", userId=" + userId + "]";
	}
}
