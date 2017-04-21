package com.atguigu.bookstore.daoimpl;

import java.util.List;

import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.model.OrderItem;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public List<OrderItem> getUserOrderItems(String orderId) {
		String sql = "select name,count,price,total_price totalPrice,order_id orderId from order_item where order_id = ?";
		return getBeanList(sql, orderId);
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		String sql = "select name,count,price,total_price totalPrice,order_id orderId from order_item";
		return getBeanList(sql);
	}

	@Override
	public void batchSaveOrderItem(List<OrderItem> list) {
		String sql = "insert into order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
		Object[][] args = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			args[i][0] = list.get(i).getName();
			args[i][1] = list.get(i).getCount();
			args[i][2] = list.get(i).getPrice();
			args[i][3] = list.get(i).getTotalPrice();
			args[i][4] = list.get(i).getOrderId();
		}
		batchUpdate(sql, args);
	}
}
