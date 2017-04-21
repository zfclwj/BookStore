package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.model.Cart;
import com.atguigu.bookstore.model.Order;
import com.atguigu.bookstore.model.OrderItem;
import com.atguigu.bookstore.service.OrderItemService;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.serviceimpl.OrderItemServiceImpl;
import com.atguigu.bookstore.serviceimpl.OrderServiceImpl;
import com.atguigu.bookstore.serviceimpl.UserServiceImpl;
import com.atguigu.bookstore.webutils.NumberUtils;

@WebServlet("*.orderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 23333L;
	private OrderService orderService = new OrderServiceImpl();
	private OrderItemService orderItemService = new OrderItemServiceImpl();
	private UserService userService = new UserServiceImpl();

	protected void saveOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");
		String name = (String) session.getAttribute("name");

		String orderId = NumberUtils.getString(System.currentTimeMillis());
		String userId = NumberUtils.getString(userService.getUserId(name));
		String creatTime = NumberUtils.getDateFormate(System.currentTimeMillis());

		Order order = new Order(orderId, creatTime, cart.getTotalPrice(), 0, userId);
		orderService.saveOrder(order);
		orderItemService.saveOrderItem(cart, orderId);

		session.setAttribute("orderId", orderId);
		session.removeAttribute("cart");

		response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
	}

	protected void getUserOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		int userId = userService.getUserId(name);
		List<Order> orders = orderService.getUserOrders(userId);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}

	protected void getUserOrdersInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		List<OrderItem> orderItems = orderItemService.getUserOrderItems(orderId);
		request.setAttribute("orderItems", orderItems);
		request.getRequestDispatcher("/pages/order/order_items.jsp").forward(request, response);
	}

	protected void recevieGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		Order order = orderService.getOrderById(orderId);
		order.setStatus(2);
		orderService.updateOrderStatus(order);
		getUserOrders(request, response);
	}
}
