package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class NewBusGas {
    private String gasId;

    private String busName;

    private String orgName;

    private BigDecimal gasVolume;

    private String gasUserId;

    private String gasUserName;

    private String driverId;

    private String driver;

    private String gasStationId;

    private String gasStation;

    private Date gasTime;
    private String gasTimeStr;
    public String getGasTimeStr() {
		return gasTimeStr;
	}

	public void setGasTimeStr(String gasTimeStr) {
		this.gasTimeStr = gasTimeStr;
	}
	private String gasGunNum;

    private Date created;
    private String createdStr;
    
    public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}
    private String remark;

    public String getGasId() {
        return gasId;
    }

    public void setGasId(String gasId) {
        this.gasId = gasId == null ? null : gasId.trim();
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName == null ? null : busName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public BigDecimal getGasVolume() {
        return gasVolume;
    }

    public void setGasVolume(BigDecimal gasVolume) {
        this.gasVolume = gasVolume;
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId == null ? null : driverId.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
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

    public Date getGasTime() {
        return gasTime;
    }

    public void setGasTime(Date gasTime) {
        this.gasTime = gasTime;
    }

    public String getGasGunNum() {
        return gasGunNum;
    }

    public void setGasGunNum(String gasGunNum) {
        this.gasGunNum = gasGunNum == null ? null : gasGunNum.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}