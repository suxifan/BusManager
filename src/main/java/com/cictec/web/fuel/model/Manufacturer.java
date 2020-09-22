package com.cictec.web.fuel.model;

public class Manufacturer {
    private Integer manufacturerId;

    private String manufacturerTitle;

    private String manufacturerType;

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerTitle() {
        return manufacturerTitle;
    }

    public void setManufacturerTitle(String manufacturerTitle) {
        this.manufacturerTitle = manufacturerTitle == null ? null : manufacturerTitle.trim();
    }

    public String getManufacturerType() {
        return manufacturerType;
    }

    public void setManufacturerType(String manufacturerType) {
        this.manufacturerType = manufacturerType == null ? null : manufacturerType.trim();
    }
}