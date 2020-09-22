package com.cictec.web.auth.pojo;

public class PageElementData/* extends BaseEntity*/{

	private String dobPageElementId;//页面元素ID
	private String dobPageElementName;//页面元素名称
	private String dobPageElementDesc;//页面描述
	private boolean dobIsHidden;
	public boolean isDobIsHidden() {
		return dobIsHidden;
	}
	public void setDobIsHidden(boolean dobIsHidden) {
		this.dobIsHidden = dobIsHidden;
	}
	public String getDobPageElementId() {
		return dobPageElementId;
	}
	public void setDobPageElementId(String dobPageElementId) {
		this.dobPageElementId = dobPageElementId;
	}
	public String getDobPageElementName() {
		return dobPageElementName;
	}
	public void setDobPageElementName(String dobPageElementName) {
		this.dobPageElementName = dobPageElementName;
	}
	public String getDobPageElementDesc() {
		return dobPageElementDesc;
	}
	public void setDobPageElementDesc(String dobPageElementDesc) {
		this.dobPageElementDesc = dobPageElementDesc;
	}
	
}
