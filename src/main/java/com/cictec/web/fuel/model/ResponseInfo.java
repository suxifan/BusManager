package com.cictec.web.fuel.model;

public class ResponseInfo {
	public int code;
	//public int totalRow;
	public Object data;
	public Object head;
	//public String toPage;
	public ResponseInfo() {
		code = 200;
		data = null;
		head = null;
	}
}