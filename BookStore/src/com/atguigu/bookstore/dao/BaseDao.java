package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.webutils.DbUtils;

public class BaseDao<T> {
	private Class<T> clazz;
	private QueryRunner queryRunner = new QueryRunner();

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type superClass = this.getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type[] args = parameterizedType.getActualTypeArguments();
			clazz = (Class<T>) args[0];
		}
	}

	public T getBean(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = DbUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<T> getBeanList(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = DbUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int update(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = DbUtils.getConnection();
			return queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int[] batchUpdate(String sql, Object[][] args) {
		Connection connection = null;
		try {
			connection = DbUtils.getConnection();
			return queryRunner.batch(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public long getValue(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = DbUtils.getConnection();
			return (long) queryRunner.query(connection, sql, new ScalarHandler<>(), args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
