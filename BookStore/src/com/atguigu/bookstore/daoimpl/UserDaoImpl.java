package com.atguigu.bookstore.daoimpl;

import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.model.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public boolean checkedUser(String name, String password) {
		boolean flag = false;
		String sql = "select count(*) from users where name=? and password=?";
		long count = getValue(sql, name, password);
		if (count == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void regist(User user) {
		String sql = "insert into users(name,password,email) values(?,?,?)";
		update(sql, user.getUsername(), user.getPassword(), user.getEmail());
	}

	@Override
	public long getCount(String sql, Object... args) {
		return getValue(sql, args);
	}

	@Override
	public int getUserIdByName(String name) {
		String sql = "select * from users where name = ?";
		User user = getBean(sql, name);
		return user.getId();
	}

	@Override
	public User getUser(String name) {
		String sql = "select id,name username,password,email,status from users where name=?";
		return getBean(sql, name);
	}
}
