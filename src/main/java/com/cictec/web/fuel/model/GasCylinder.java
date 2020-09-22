package com.cictec.web.fuel.model;

import java.util.Date;

public class GasCylinder {
    private String gasCylinderId;

    private String gasCylinderNo;

    private String selfNum;

    private String busNum;

    private String busModel;

    private Integer manufacturerId;

    private String manufacturer;

    private String gasCylinderNum;

    private Date productDate;

    private Integer cylinderTypeId;

    private String cylinderTitle;

    private String remark;

    private Date created;

    public String getGasCylinderId() {
        return gasCylinderId;
    }

    public void setGasCylinderId(String gasCylinderId) {
        this.gasCylinderId = gasCylinderId == null ? null : gasCylinderId.trim();
    }

    public String getGasCylinderNo() {
        return gasCylinderNo;
    }

    public void setGasCylinderNo(String gasCylinderNo) {
        this.gasCylinderNo = gasCylinderNo == null ? null : gasCylinderNo.trim();
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

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getGasCylinderNum() {
        return gasCylinderNum;
    }

    public void setGasCylinderNum(String gasCylinderNum) {
        this.gasCylinderNum = gasCylinderNum == null ? null : gasCylinderNum.trim();
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Integer getCylinderTypeId() {
        return cylinderTypeId;
    }

    public void setCylinderTypeId(Integer cylinderTypeId) {
        this.cylinderTypeId = cylinderTypeId;
    }

    public String getCylinderTitle() {
        return cylinderTitle;
    }

    public void setCylinderTitle(String cylinderTitle) {
        this.cylinderTitle = cylinderTitle == null ? null : cylinderTitle.trim();
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