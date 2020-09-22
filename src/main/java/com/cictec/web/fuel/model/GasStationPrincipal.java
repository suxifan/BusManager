package com.cictec.web.fuel.model;

import java.util.Date;

public class GasStationPrincipal {
	
    private String principalId;

    private String gasStation;

    private String gasStationId;

    private String principal;

    private String fixedTel;

    private String mobilePhone;

    private String email;

    private String dayTime;

    private String nightTime;

    private String gasStationAddr;

    private String latiLong;

    private String remark;

    private Date created;
    
    private Date expired;
    
    private String expiredStr;
    
    private boolean enable;

    private String excelParam;

    private Date emailSendDate; //虚拟字段

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation == null ? null : gasStation.trim();
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId == null ? null : gasStationId.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getFixedTel() {
        return fixedTel;
    }

    public void setFixedTel(String fixedTel) {
        this.fixedTel = fixedTel == null ? null : fixedTel.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime == null ? null : dayTime.trim();
    }

    public String getNightTime() {
        return nightTime;
    }

    public void setNightTime(String nightTime) {
        this.nightTime = nightTime == null ? null : nightTime.trim();
    }

    public String getGasStationAddr() {
        return gasStationAddr;
    }

    public void setGasStationAddr(String gasStationAddr) {
        this.gasStationAddr = gasStationAddr == null ? null : gasStationAddr.trim();
    }

    public String getLatiLong() {
        return latiLong;
    }

    public void setLatiLong(String latiLong) {
        this.latiLong = latiLong == null ? null : latiLong.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public String getExpiredStr() {
		return expiredStr;
	}

	public void setExpiredStr(String expiredStr) {
		this.expiredStr = expiredStr;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

    public String getExcelParam() {
        return excelParam;
    }

    public void setExcelParam(String excelParam) {
        this.excelParam = excelParam;
    }

    public Date getEmailSendDate() {
        return emailSendDate;
    }

    public void setEmailSendDate(Date emailSendDate) {
        this.emailSendDate = emailSendDate;
    }
}