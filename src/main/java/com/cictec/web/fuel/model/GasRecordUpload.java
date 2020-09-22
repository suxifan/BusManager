package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class GasRecordUpload {
	
	private String busNum;
	private String selfNum;
	private String gasVolume;
	private String gasUserId;
	private String gasUserName;
	private String driverId;
	private String driver;
	private String gasStationId;
	private String gasStationName;
	private String gasTime;
	private String gasGunNum;
	private String preGasVolume;
	private String modified;
	private String isEquipment;
	private String deviceImei;
	private String uploadPicPath;
    private String uploadBusNumPath;
	
	private String preUserName; //加气修改人姓名
	private String preUserId;  //加气修改人ID
	private String uuid;
	
	public String getBusNum() {
		return busNum;
	}
	public void setBusNum(String busNum) {
		this.busNum = busNum;
	}
	public String getSelfNum() {
		return selfNum;
	}
	public void setSelfNum(String selfNum) {
		this.selfNum = selfNum;
	}
	public String getGasVolume() {
		return gasVolume;
	}
	public void setGasVolume(String gasVolume) {
		this.gasVolume = gasVolume;
	}
	public String getGasUserId() {
		return gasUserId;
	}
	public void setGasUserId(String gasUserId) {
		this.gasUserId = gasUserId;
	}
	public String getGasUserName() {
		return gasUserName;
	}
	public void setGasUserName(String gasUserName) {
		this.gasUserName = gasUserName;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
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
	public String getGasTime() {
		return gasTime;
	}
	public void setGasTime(String gasTime) {
		this.gasTime = gasTime;
	}
	public String getGasGunNum() {
		return gasGunNum;
	}
	public void setGasGunNum(String gasGunNum) {
		this.gasGunNum = gasGunNum;
	}
	public String getPreGasVolume() {
		return preGasVolume;
	}
	public void setPreGasVolume(String preGasVolume) {
		this.preGasVolume = preGasVolume;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getIsEquipment() {
		return isEquipment;
	}
	public void setIsEquipment(String isEquipment) {
		this.isEquipment = isEquipment;
	}
	public String getDeviceImei() {
		return deviceImei;
	}
	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}
	public String getUploadPicPath() {
		return uploadPicPath;
	}
	public void setUploadPicPath(String uploadPicPath) {
		this.uploadPicPath = uploadPicPath;
	}
	public String getPreUserName() {
		return preUserName;
	}
	public void setPreUserName(String preUserName) {
		this.preUserName = preUserName;
	}
	public String getPreUserId() {
		return preUserId;
	}
	public void setPreUserId(String preUserId) {
		this.preUserId = preUserId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


    public String getUploadBusNumPath() {
        return uploadBusNumPath;
    }

    public void setUploadBusNumPath(String uploadBusNumPath) {
        this.uploadBusNumPath = uploadBusNumPath;
    }
}