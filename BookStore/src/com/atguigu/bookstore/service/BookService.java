package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.model.Book;
import com.atguigu.bookstore.model.Page;

public interface BookService {
	public int updateBook(String sql, Object... args);

	public Book getBook(String sql, Object... args);

	public List<Book> getBookList(Object... args);

	public long getCount(Object... args);

	public Page<Book> getPage(int pageNum);

	public Page<Book> getClientPage(int pageNo, double minPrice, double maxPrice);
	
	public List<Book> getClientBookList(Object... args);
	
	public long getClientCount(Object... args);
}
