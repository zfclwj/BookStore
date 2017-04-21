package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.model.User;

public interface UserDao {
	public boolean checkedUser(String name, String password);

	public void regist(User user);

	public long getCount(String sql, Object... args);

	public int getUserIdByName(String name);
	
	public User getUser(String name);
}
