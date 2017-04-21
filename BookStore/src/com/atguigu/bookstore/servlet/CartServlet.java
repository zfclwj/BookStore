package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.model.Book;
import com.atguigu.bookstore.model.Cart;
import com.atguigu.bookstore.model.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.serviceimpl.BookServiceImpl;
import com.atguigu.bookstore.webutils.NumberUtils;

@WebServlet("*.CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 2333L;
	private BookService bookService = new BookServiceImpl();

	protected void addCartItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = NumberUtils.getInt(request.getParameter("id"), 1);
		String sql = "select * from books where id=?";
		Book book = bookService.getBook(sql, id);
		Cart cart = getCart(request, response);
		cart.addCartItems(new CartItem(id, book.getName(), book.getPrice()));
		session.setAttribute("cart", cart);
		session.setAttribute("lastName", book.getName());

		Map<String, Object> resultJson = new HashMap<>();
		resultJson.put("lastName", book.getName());
		resultJson.put("totalCount", cart.getTotalCount());
		response.getWriter().write(gson.toJson(resultJson));
	}

	protected Cart getCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
		return cart;
	}

	protected void updateItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = NumberUtils.getInt(request.getParameter("id"), -1);
		int count = NumberUtils.getInt(request.getParameter("count"), -1);
		Cart cart = getCart(request, response);
		cart.updateItems(id, count);
		session.setAttribute("cart", cart);

		Map<String, Object> resultJson = new HashMap<>();
		resultJson.put("totalPrice", cart.getTotalPrice());
		resultJson.put("totalCount", cart.getTotalCount());
		resultJson.put("price", cart.getCart().get(id).getTotalPrice());
		response.getWriter().write(gson.toJson(resultJson));
	}

	protected void deleteItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = getCart(request, response);
		Boolean flag = cart.deleteItems(NumberUtils.getInt(request.getParameter("id"), -1));
		session.setAttribute("cart", cart);

		Map<Object, Object> resultJson = new HashMap<>();
		resultJson.put("totalPrice", cart.getTotalPrice());
		resultJson.put("totalCount", cart.getTotalCount());
		resultJson.put("flag", flag);
		response.getWriter().write(gson.toJson(resultJson));
		
		if (cart.getCart().size() == 0) {
			session.removeAttribute("cart");
			session.removeAttribute("lastName");
		}
	}

	protected void clearItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		session.removeAttribute("lastName");
		response.getWriter().write("true");
	}
}
