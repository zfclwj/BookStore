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
import com.atguigu.bookstore.webutils.ParameterToBean;

@WebServlet("*.Book")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 2333L;
	private BookService bookService = new BookServiceImpl();

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "insert into books(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
		Book book = ParameterToBean.getBean(Book.class, request.getParameterMap());
		bookService.updateBook(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(),
				book.getImgPath());
		response.sendRedirect(request.getContextPath() + "/page.Book?pageNo=" + request.getParameter("pageNo"));
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sql = "delete from books where id=?";
		bookService.updateBook(sql, id);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "update books set name=? ,author=? ,price=?, sales=?,stock=? where id=?";
		Book book = ParameterToBean.getBean(Book.class, request.getParameterMap());
		bookService.updateBook(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(),
				book.getId());
		response.sendRedirect(request.getContextPath() + "/page.Book?pageNo=" + request.getParameter("pageNo"));
	}

	protected void getBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sql = "select id,name,author,price,sales,stock,img_path imgPath from books where id=?";
		Book book = bookService.getBook(sql, id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo = request.getParameter("pageNo");
		int pageNum = Integer.parseInt(pageNo);
		Page<Book> page = bookService.getPage(pageNum);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
}
