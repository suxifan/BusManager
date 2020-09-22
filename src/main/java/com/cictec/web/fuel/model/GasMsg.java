package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class GasMsg {

    public static final int EMAIL_UPLOAD = 1;
    public static final int SYSTEM_IMPORT = 2;


    private String busNum;
    private BigDecimal gasVolume;
    private String excelPath;
    private String gasStationId;
    private String gasStationName;
    private Date gasTime;
    private int status;//1加气站上传 2系统导入

    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    public BigDecimal getGasVolume() {
        return gasVolume;
    }

    public void setGasVolume(BigDecimal gasVolume) {
        this.gasVolume = gasVolume;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public Date getGasTime() {
        return gasTime;
    }

    public void setGasTime(Date gasTime) {
        this.gasTime = gasTime;
    }

    public String getGasStationName() {
        return gasStationName;
    }

    public void setGasStationName(String gasStationName) {
        this.gasStationName = gasStationName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
