package com.cictec.web.auth.pojo;

import java.io.Serializable;

public class Fields implements Serializable {

	private static final long serialVersionUID = -3265341571374270800L;
	
	private String fieldId;
	private String fieldName;
	private String fieldDisplay;
	private boolean isEnabled;
	
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDisplay() {
		return fieldDisplay;
	}
	public void setFieldDisplay(String fieldDisplay) {
		this.fieldDisplay = fieldDisplay;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
