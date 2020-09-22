package com.cictec.web.auth.pojo;

import java.util.List;

public class PagingResult<T> {
	
	private int totalCount;
	private List<T> data;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
