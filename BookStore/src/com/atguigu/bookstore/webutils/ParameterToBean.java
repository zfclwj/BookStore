package com.atguigu.bookstore.webutils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ParameterToBean {
	public static <T> T getBean(Class<T> clazz, Map<String, String[]> map) {
		T t = null;
		try {
			t = clazz.newInstance();
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
