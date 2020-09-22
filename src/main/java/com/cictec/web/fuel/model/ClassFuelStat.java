package com.cictec.web.fuel.model;

import java.util.Date;

public class ClassFuelStat {
    private String statId;

    private String gasUserId;

    private String gasUserName;

    private String gasStationId;

    private String gasStation;

    private Date created;
    private String deviceImei;
    public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	private String createdStr;

    public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}

	private String gasStatUrl;

    private String remark;

    public String getStatId() {
        return statId;
    }

    public void setStatId(String statId) {
        this.statId = statId == null ? null : statId.trim();
    }

    public String getGasUserId() {
        return gasUserId;
    }

    public void setGasUserId(String gasUserId) {
        this.gasUserId = gasUserId == null ? null : gasUserId.trim();
    }

    public String getGasUserName() {
        return gasUserName;
    }

    public void setGasUserName(String gasUserName) {
        this.gasUserName = gasUserName == null ? null : gasUserName.trim();
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId == null ? null : gasStationId.trim();
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation == null ? null : gasStation.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getGasStatUrl() {
        return gasStatUrl;
    }

    public void setGasStatUrl(String gasStatUrl) {
        this.gasStatUrl = gasStatUrl == null ? null : gasStatUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}