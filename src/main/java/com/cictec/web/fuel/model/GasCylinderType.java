package com.cictec.web.fuel.model;

public class GasCylinderType {
    private Integer cylinderTypeId;

    private String cylinderTitle;

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
}