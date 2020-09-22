package com.cictec.web.fuel.model;

import java.util.Date;

public class GasRemind {
    private String remindId;

    private int remindType;

    private int remindStatus;

    private String remindContent;

    private Date created;
    
    private String createdStr;

    public String getRemindId() {
        return remindId;
    }

    public void setRemindId(String remindId) {
        this.remindId = remindId == null ? null : remindId.trim();
    }

    public int getRemindType() {
        return remindType;
    }

    public void setRemindType(int remindType) {
        this.remindType = remindType;
    }

    public int getRemindStatus() {
        return remindStatus;
    }

    public void setRemindStatus(int remindStatus) {
        this.remindStatus = remindStatus;
    }

    public String getRemindContent() {
        return remindContent;
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent == null ? null : remindContent.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}
	
}