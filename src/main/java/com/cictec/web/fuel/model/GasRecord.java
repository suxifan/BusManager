package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class GasRecord {
    private String gasUuid;

    private String busNum;

    private String selfNum;

    private BigDecimal gasVolume;

    private String gasUserId;

    private String gasUserName;

    private String driverId;

    private String driver;

    private Date gasTime;
    
    private String gasDayStr;
    
    private String startDayStr;//格式：YYYY-MM-DD
    
    private String endDayStr;//格式：YYYY-MM-DD
    
    private String startTimeStr;//格式：YYYY-MM-DD HH:MM:ss
    
    private String endTimeStr;//格式：YYYY-MM-DD HH:MM:ss
    
    private String gasStationId;
    
    private String gasStationName;
    
    private String deviceImei;

    private Date created;

    public String getGasUuid() {
        return gasUuid;
    }

    public void setGasUuid(String gasUuid) {
        this.gasUuid = gasUuid == null ? null : gasUuid.trim();
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

    public Date getGasTime() {
        return gasTime;
    }

    public void setGasTime(Date gasTime) {
        this.gasTime = gasTime;
    }

	public String getGasDayStr() {
		return gasDayStr;
	}

	public void setGasDayStr(String gasDayStr) {
		this.gasDayStr = gasDayStr;
	}

	public String getStartDayStr() {
		return startDayStr;
	}

	public void setStartDayStr(String startDayStr) {
		this.startDayStr = startDayStr;
	}

	public String getEndDayStr() {
		return endDayStr;
	}

	public void setEndDayStr(String endDayStr) {
		this.endDayStr = endDayStr;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getGasStationId() {
		return gasStationId;
	}

	public void setGasStationId(String gasStationId) {
		this.gasStationId = gasStationId;
	}

	public String getGasStationName() {
		return gasStationName;
	}

	public void setGasStationName(String gasStationName) {
		this.gasStationName = gasStationName;
	}

	public String getDeviceImei() {
		return deviceImei;
	}

	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}

	public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}