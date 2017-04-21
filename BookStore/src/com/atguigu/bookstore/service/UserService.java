package com.atguigu.bookstore.service;

import com.atguigu.bookstore.model.User;

public interface UserService {
	public void regist(User user);

	public boolean login(String name, String password);

	public long getNameCount(Object...args);
	
	public int getUserId(String name);
	
	public User getUser(String name);
}
