package com.cictec.web.auth.pojo;

import java.util.Date;

public class BusLine {
    private String lineId;

    private String lineName;

    private String lineAlias;

    private String lineOrgId;
    private String lineOrgName;

    public String getLineOrgName() {
		return lineOrgName;
	}

	public void setLineOrgName(String lineOrgName) {
		this.lineOrgName = lineOrgName;
	}

	private Integer lineStatus;

    private Date created;
    private String createdStr;

    public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}

	public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getLineAlias() {
        return lineAlias;
    }

    public void setLineAlias(String lineAlias) {
        this.lineAlias = lineAlias == null ? null : lineAlias.trim();
    }

    public String getLineOrgId() {
        return lineOrgId;
    }

    public void setLineOrgId(String lineOrgId) {
        this.lineOrgId = lineOrgId == null ? null : lineOrgId.trim();
    }

    public Integer getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Integer lineStatus) {
        this.lineStatus = lineStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}