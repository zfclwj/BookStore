package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.model.Order;
import com.atguigu.bookstore.model.OrderItem;
import com.atguigu.bookstore.service.OrderItemService;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.serviceimpl.OrderItemServiceImpl;
import com.atguigu.bookstore.serviceimpl.OrderServiceImpl;

@WebServlet("*.serverOrderServlet")
public class ServerOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 2333L;
	private OrderService orderService = new OrderServiceImpl();
	private OrderItemService orderItemService = new OrderItemServiceImpl();

	protected void getAllOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> orders = orderService.getAllOrders();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

	protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		Order order = orderService.getOrderById(orderId);
		order.setStatus(1);
		orderService.updateOrderStatus(order);
		getAllOrders(request, response);
	}

	protected void getOrdersInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		List<OrderItem> orderItems = orderItemService.getUserOrderItems(orderId);
		request.setAttribute("orderItems", orderItems);
		request.getRequestDispatcher("/pages/order/order_items.jsp").forward(request, response);
	}
}
