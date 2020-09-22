package com.cictec.web.fuel.model;

import java.util.Date;

public class BusConfigDetail {
    private String busConfigDetailId;

    private Long busConfigId;

    private String selfNum;

    private String busNum;

    private String busModel;

    private Date enableDate;

    private String remark;

    private String originSelfNum;

    public String getBusConfigDetailId() {
        return busConfigDetailId;
    }

    public void setBusConfigDetailId(String busConfigDetailId) {
        this.busConfigDetailId = busConfigDetailId == null ? null : busConfigDetailId.trim();
    }

    public Long getBusConfigId() {
        return busConfigId;
    }

    public void setBusConfigId(Long busConfigId) {
        this.busConfigId = busConfigId;
    }

    public String getSelfNum() {
        return selfNum;
    }

    public void setSelfNum(String selfNum) {
        this.selfNum = selfNum == null ? null : selfNum.trim();
    }

    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum == null ? null : busNum.trim();
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel == null ? null : busModel.trim();
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOriginSelfNum() {
        return originSelfNum;
    }

    public void setOriginSelfNum(String originSelfNum) {
        this.originSelfNum = originSelfNum == null ? null : originSelfNum.trim();
    }
}