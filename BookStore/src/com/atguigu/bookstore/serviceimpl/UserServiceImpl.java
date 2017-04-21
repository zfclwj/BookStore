package com.atguigu.bookstore.serviceimpl;

import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.daoimpl.UserDaoImpl;
import com.atguigu.bookstore.model.User;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userdao = new UserDaoImpl();

	@Override
	public void regist(User user) {
		userdao.regist(user);
	}

	@Override
	public boolean login(String name, String password) {
		return userdao.checkedUser(name, password);
	}

	@Override
	public long getNameCount(Object... args) {
		String sql = "select count(*) from users where name=?";
		return userdao.getCount(sql, args);
	}

	@Override
	public int getUserId(String name) {
		return userdao.getUserIdByName(name);
	}

	@Override
	public User getUser(String name) {
		return userdao.getUser(name);
	}
}
