package com.atguigu.bookstore.webutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberUtils {
	public static double getDouble(String str, double price) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return price;
		}
	}

	public static int getInt(String str, int pageNo) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return pageNo;
		}
	}

	public static String getString(long Num) {
		return Num + "";
	}

	public static String getDateFormate(long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
