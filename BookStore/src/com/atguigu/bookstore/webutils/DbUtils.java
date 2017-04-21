package com.atguigu.bookstore.webutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtils {
	private static ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("bookStore");
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = connectionLocal.get();
		if (connection == null) {
			connection = dataSource.getConnection();
			connectionLocal.set(connection);
			connection.setAutoCommit(false);
		}
		return connection;
	}

	public static void commitAndRealse() {
		Connection connection = connectionLocal.get();
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				connectionLocal.remove();
			}
		}
	}

	public static void rollBackAndRealse() {
		Connection connection = connectionLocal.get();
		if (connection != null) {
			try {
				connection.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				connectionLocal.remove();
			}
		}
	}
}
