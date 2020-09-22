package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class GasDetail {
    private String gasDetailId;

    private String gasId;

    private String orgId;

    private String orgName;

    private String lineOrgName;

    private String lineOrgId;

    private String busNum;

    private String selfNum;

    private Short operateType;

    private BigDecimal gasVolume;

    private String gasUserId;

    private String gasUserName;

    private String driverId;

    private String driver;

    private String gasStationId;

    private String gasStation;

    private Date gasTime;
    
    private Date gasTimeOneMonthLater;
    
    private BigDecimal allVolume;
    
	private String gasTimeStr;
	
    private String createdStr;
    
	private String gasGunNum;

    private String lineName;

    private String lineId;

    private Date created;

    private String remark;
    
    private String gasDateStr;
    
    private String gasUUid;
    
    private String gasEquipmentUrl;


    private int gasStatus;
    private String busNumUrl;

    private String gasStationOrgId;

	public BigDecimal getAllVolume() {
		return allVolume;
	}

	public void setAllVolume(BigDecimal allVolume) {
		this.allVolume = allVolume;
	}

	public Date getGasTimeOneMonthLater() {
		return gasTimeOneMonthLater;
	}

	public void setGasTimeOneMonthLater(Date gasTimeOneMonthLater) {
		this.gasTimeOneMonthLater = gasTimeOneMonthLater;
	}

    public String getGasTimeStr() {
		return gasTimeStr;
	}

	public void setGasTimeStr(String gasTimeStr) {
		this.gasTimeStr = gasTimeStr;
	}

	public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}

    public String getGasDetailId() {
        return gasDetailId;
    }

    public void setGasDetailId(String gasDetailId) {
        this.gasDetailId = gasDetailId == null ? null : gasDetailId.trim();
    }

    public String getGasId() {
        return gasId;
    }

    public void setGasId(String gasId) {
        this.gasId = gasId == null ? null : gasId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getLineOrgName() {
        return lineOrgName;
    }

    public void setLineOrgName(String lineOrgName) {
        this.lineOrgName = lineOrgName == null ? null : lineOrgName.trim();
    }

    public String getLineOrgId() {
        return lineOrgId;
    }

    public void setLineOrgId(String lineOrgId) {
        this.lineOrgId = lineOrgId == null ? null : lineOrgId.trim();
    }

    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum == null ? null : busNum.trim();
    }

    public String getSelfNum() {
        return selfNum;
    }

    public void setSelfNum(String selfNum) {
        this.selfNum = selfNum == null ? null : selfNum.trim();
    }

    public Short getOperateType() {
        return operateType;
    }

    public void setOperateType(Short operateType) {
        this.operateType = operateType;
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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
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

	public String getGasDateStr() {
		return gasDateStr;
	}

	public void setGasDateStr(String gasDateStr) {
		this.gasDateStr = gasDateStr;
	}

	public String getGasEquipmentUrl() {
		return gasEquipmentUrl;
	}

	public void setGasEquipmentUrl(String gasEquipmentUrl) {
		this.gasEquipmentUrl = gasEquipmentUrl;
	}

	public String getGasUUid() {
		return gasUUid;
	}

	public void setGasUUid(String gasUUid) {
		this.gasUUid = gasUUid;
	}

	public int getGasStatus() {
		return gasStatus;
	}

	public void setGasStatus(int gasStatus) {
		this.gasStatus = gasStatus;
	}

    public String getBusNumUrl() {
        return busNumUrl;
    }

    public void setBusNumUrl(String busNumUrl) {
        this.busNumUrl = busNumUrl;
    }

    public String getGasStationOrgId() {
        return gasStationOrgId;
    }

    public void setGasStationOrgId(String gasStationOrgId) {
        this.gasStationOrgId = gasStationOrgId;
    }
}