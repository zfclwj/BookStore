package com.atguigu.bookstore.serviceimpl;

import java.util.List;

import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.daoimpl.BookDaoImpl;
import com.atguigu.bookstore.model.Book;
import com.atguigu.bookstore.model.Page;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public int updateBook(String sql, Object... args) {
		return bookDao.updateBook(sql, args);
	}

	@Override
	public Book getBook(String sql, Object... args) {
		return bookDao.getBook(sql, args);
	}

	@Override
	public List<Book> getBookList(Object... args) {
		String sql = "select id,name,author,price,sales,stock,img_path imgPath from books limit ?,"
				+ Page.defaultPageSize;
		return bookDao.getBookList(sql, args);
	}

	@Override
	public long getCount(Object... args) {
		String sql = "select count(*) from books ";
		return bookDao.getCount(sql, args);
	}

	@Override
	public Page<Book> getPage(int pageNum) {
		List<Book> books = getBookList(Page.defaultPageSize * (pageNum - Page.initializePage));
		long count = getCount();
		Page<Book> page = new Page<>(count, pageNum, books, "page.Book");
		return page;
	}

	@Override
	public Page<Book> getClientPage(int pageNo, double minPrice, double maxPrice) {
		List<Book> books = getClientBookList(minPrice, maxPrice, Page.defaultPageSize * (pageNo - Page.initializePage));
		long count = getClientCount(minPrice, maxPrice);
		Page<Book> page = new Page<>(count, pageNo, books, "pageByPrice.clientBookServlet");
		return page;
	}

	@Override
	public List<Book> getClientBookList(Object... args) {
		String sql = "select id,name,author,price,sales,stock,img_path imgPath from books where price between ? and ? limit ?,"
				+ Page.defaultPageSize;
		return bookDao.getBookList(sql, args);
	}

	@Override
	public long getClientCount(Object... args) {
		String sql = "select count(*) from books where price between ? and ? ";
		return bookDao.getCount(sql, args);
	}
}
