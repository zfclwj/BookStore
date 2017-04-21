package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.model.Book;

public interface BookDao {
	public int updateBook(String sql, Object... args);
	
	public Book getBook(String sql, Object... args);

	public List<Book> getBookList(String sql, Object... args);
	
	public long getCount(String sql, Object... args);
}
