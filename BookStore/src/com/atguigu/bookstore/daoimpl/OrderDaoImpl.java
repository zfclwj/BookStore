package com.atguigu.bookstore.daoimpl;

import java.util.List;

import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.model.Order;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		String sql = "insert into `order` (order_id,create_time,total_price,status,id) values(?,?,?,?,?)";
		update(sql, order.getOrderId(), order.getCreateTime(), order.getTotalPrice(), order.getStatus(),
				order.getUserId());
	}

	@Override
	public List<Order> getUserOrders(int userId) {
		String sql = "select order_id orderId,create_time createTime,total_price totalPrice,status,id userId from `order` where id = ?";
		return getBeanList(sql, userId);
	}

	@Override
	public List<Order> getAllOrders() {
		String sql = "select order_id orderId,create_time createTime,total_price totalPrice,status,id userId from `order` ";
		return getBeanList(sql);
	}

	@Override
	public Order getOrderById(String orderId) {
		String sql = "select order_id orderId,create_time createTime,total_price totalPrice,status,id userId from `order` where order_id = ? ";
		return getBean(sql, orderId);
	}

	@Override
	public void updateOrderStatus(Order order) {
		String sql = "update  `order` set status = ? where order_id = ? ";
		update(sql, order.getStatus(), order.getOrderId());
	}
}
