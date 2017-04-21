package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.model.Book;
import com.atguigu.bookstore.model.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.serviceimpl.BookServiceImpl;
import com.atguigu.bookstore.webutils.NumberUtils;

@WebServlet("*.clientBookServlet")
public class ClientBookServlet extends BaseServlet {
	private static final long serialVersionUID = 2333333L;
	private BookService bookService = new BookServiceImpl();

	protected void pageByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String min = request.getParameter("minPrice");
		String max = request.getParameter("maxPrice");
		String pageNum = request.getParameter("pageNo");

		double minPrice = NumberUtils.getDouble(min, 0);
		double maxPrice = NumberUtils.getDouble(max, Double.MAX_VALUE);
		int pageNo = NumberUtils.getInt(pageNum, 1);

		Page<Book> page = bookService.getClientPage(pageNo, minPrice, maxPrice);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
	}
}
