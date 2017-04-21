package com.atguigu.bookstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.daoimpl.BookDaoImpl;
import com.atguigu.bookstore.daoimpl.OrderItemDaoImpl;
import com.atguigu.bookstore.model.Book;
import com.atguigu.bookstore.model.Cart;
import com.atguigu.bookstore.model.CartItem;
import com.atguigu.bookstore.model.OrderItem;
import com.atguigu.bookstore.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
	private OrderItemDao orderItemdao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public void saveOrderItem(Cart cart, String orderId) {
		Map<Integer, CartItem> map = cart.getCart();
		List<OrderItem> list = new ArrayList<>();
		for (CartItem cartItem : map.values()) {
			OrderItem orderItem = new OrderItem(cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
					cartItem.getTotalPrice(), orderId);
			list.add(orderItem);

			String sql = "select id,name,author,price,sales,stock,img_path imgPath from books where name=?";
			Book book = bookDao.getBook(sql, orderItem.getName());
			String sl = "update books set sales=? ,stock=? where name=?";
			bookDao.updateBook(sl, book.getSales() + orderItem.getCount(), book.getStock() - orderItem.getCount(),
					orderItem.getName());
		}
		orderItemdao.batchSaveOrderItem(list);
	}

	@Override
	public List<OrderItem> getUserOrderItems(String orderId) {
		return orderItemdao.getUserOrderItems(orderId);
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		return orderItemdao.getAllOrderItems();
	}
}
