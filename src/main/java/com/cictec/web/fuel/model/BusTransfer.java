package com.cictec.web.fuel.model;

import java.util.Date;

public class BusTransfer {
    private String busTransferId;

    private String selfNum;

    private String busNum;

    private String busModel;

    private String underpanNum;

    private Short busTransferType;

    private String busTransferTitle;

    private String remark;

    private Date created;

    public String getBusTransferId() {
        return busTransferId;
    }

    public void setBusTransferId(String busTransferId) {
        this.busTransferId = busTransferId == null ? null : busTransferId.trim();
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

    public String getUnderpanNum() {
        return underpanNum;
    }

    public void setUnderpanNum(String underpanNum) {
        this.underpanNum = underpanNum == null ? null : underpanNum.trim();
    }

    public Short getBusTransferType() {
        return busTransferType;
    }

    public void setBusTransferType(Short busTransferType) {
        this.busTransferType = busTransferType;
    }

    public String getBusTransferTitle() {
        return busTransferTitle;
    }

    public void setBusTransferTitle(String busTransferTitle) {
        this.busTransferTitle = busTransferTitle == null ? null : busTransferTitle.trim();
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
}