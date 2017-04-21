package com.atguigu.bookstore.daoimpl;

import java.util.List;

import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.model.Book;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public int updateBook(String sql, Object... args) {
		return super.update(sql, args);
	}

	@Override
	public Book getBook(String sql, Object... args) {
		return super.getBean(sql, args);
	}

	@Override
	public List<Book> getBookList(String sql, Object... args) {
		return super.getBeanList(sql, args);
	}

	@Override
	public long getCount(String sql, Object... args) {
		return super.getValue(sql, args);
	}
}
