package com.atguigu.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	public static final int initializePage = 1;
	public static final int defaultPageSize = 4;
	private long countSize;// 总记录数
	private long pageSize = defaultPageSize;// 每页几条记录
	private long pageNoCount;// 总页码数
	private long pageNo;// 当前页码
	private String url;
	private List<T> list = new ArrayList<>();

	public Page(long count, int pageNo, List<T> list, String url) {
		super();
		this.countSize = count;
		this.pageNo = pageNo;
		this.list = list;
		this.url = url;
	}

	public Page() {
		super();
	}

	public long getCountSize() {
		return countSize;
	}

	public long getPageSize() {
		return pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getPageNoCount() {
		if (countSize % pageSize == 0) {
			pageNoCount = countSize / pageSize;
		} else {
			pageNoCount = countSize / pageSize + 1;
		}
		return pageNoCount;
	}

	public long getPageNo() {
		return pageNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
