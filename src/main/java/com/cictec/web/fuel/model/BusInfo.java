package com.cictec.web.fuel.model;

import java.util.Date;

public class BusInfo {
	
    private String busInfoId;

    private Short sortNo;

    private String selfNum;

    private String busNum;

    private String nuclear;

    private Integer brandId;

    private String brandName;

    private String busModel;

    private Date enableDate;

    private String remark;

    private String engineNum;

    private String engineModel;

    private String underpanNum;

    private String underpanModel;

    private String assets;

    private String assetsNum;

    private String assetsValue;

    private String busClass;

    private String fuelClass;

    private String checkLoad;

    private String seat;

    private String color;

    private String outlineSize;

    private String place;

    private String busPrice;

    private String purchaseTax;

    private String holdFee;

    private String warmWind;

    private String seatForm;

    private String floor;

    private String dispPower;

    private String discStandard;

    private String tireSize;

    private String tireDistance;

    private String axisDistance;

    private String steelSpring;

    private String kerbMass;

    private String totalMass;

    private Date factoryDate;

    private String frontOverhang;

    private String rearOverhang;

    private String approachAngle;

    private String departureAngle;

    private String busHeight;

    private String doorHeight;

    private String floorHeight;

    private String frontAxleLoad;

    private String rearAxleLoad;

    private String minClearance;

    private String minDiameter;

    private String maxSpeed;

    private String maxClimbGradient;

    private String tank;

    private String cylinder;

    private Short busStatus;

    private Date created;

    private String lineName;

    private String lineId;

    private String orgId;

    private String orgName;
    
    private String orgParentId;

    private String orgParentName;

    public String getBusInfoId() {
        return busInfoId;
    }

    public void setBusInfoId(String busInfoId) {
        this.busInfoId = busInfoId == null ? null : busInfoId.trim();
    }

    public Short getSortNo() {
        return sortNo;
    }

    public void setSortNo(Short sortNo) {
        this.sortNo = sortNo;
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

    

    public String getNuclear() {
		return nuclear;
	}

	public void setNuclear(String nuclear) {
		this.nuclear = nuclear;
	}

	public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
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

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum == null ? null : engineNum.trim();
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel == null ? null : engineModel.trim();
    }

    public String getUnderpanNum() {
        return underpanNum;
    }

    public void setUnderpanNum(String underpanNum) {
        this.underpanNum = underpanNum == null ? null : underpanNum.trim();
    }

    public String getUnderpanModel() {
        return underpanModel;
    }

    public void setUnderpanModel(String underpanModel) {
        this.underpanModel = underpanModel == null ? null : underpanModel.trim();
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets == null ? null : assets.trim();
    }

    public String getAssetsNum() {
        return assetsNum;
    }

    public void setAssetsNum(String assetsNum) {
        this.assetsNum = assetsNum == null ? null : assetsNum.trim();
    }

    public String getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(String assetsValue) {
        this.assetsValue = assetsValue == null ? null : assetsValue.trim();
    }

    public String getBusClass() {
        return busClass;
    }

    public void setBusClass(String busClass) {
        this.busClass = busClass == null ? null : busClass.trim();
    }

    public String getFuelClass() {
        return fuelClass;
    }

    public void setFuelClass(String fuelClass) {
        this.fuelClass = fuelClass == null ? null : fuelClass.trim();
    }

    public String getCheckLoad() {
        return checkLoad;
    }

    public void setCheckLoad(String checkLoad) {
        this.checkLoad = checkLoad == null ? null : checkLoad.trim();
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getOutlineSize() {
        return outlineSize;
    }

    public void setOutlineSize(String outlineSize) {
        this.outlineSize = outlineSize == null ? null : outlineSize.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(String busPrice) {
        this.busPrice = busPrice == null ? null : busPrice.trim();
    }

    public String getPurchaseTax() {
        return purchaseTax;
    }

    public void setPurchaseTax(String purchaseTax) {
        this.purchaseTax = purchaseTax == null ? null : purchaseTax.trim();
    }

    public String getHoldFee() {
        return holdFee;
    }

    public void setHoldFee(String holdFee) {
        this.holdFee = holdFee == null ? null : holdFee.trim();
    }

    public String getWarmWind() {
        return warmWind;
    }

    public void setWarmWind(String warmWind) {
        this.warmWind = warmWind == null ? null : warmWind.trim();
    }

    public String getSeatForm() {
        return seatForm;
    }

    public void setSeatForm(String seatForm) {
        this.seatForm = seatForm == null ? null : seatForm.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getDispPower() {
        return dispPower;
    }

    public void setDispPower(String dispPower) {
        this.dispPower = dispPower == null ? null : dispPower.trim();
    }

    public String getDiscStandard() {
        return discStandard;
    }

    public void setDiscStandard(String discStandard) {
        this.discStandard = discStandard == null ? null : discStandard.trim();
    }

    public String getTireSize() {
        return tireSize;
    }

    public void setTireSize(String tireSize) {
        this.tireSize = tireSize == null ? null : tireSize.trim();
    }

    public String getTireDistance() {
        return tireDistance;
    }

    public void setTireDistance(String tireDistance) {
        this.tireDistance = tireDistance == null ? null : tireDistance.trim();
    }

    public String getAxisDistance() {
        return axisDistance;
    }

    public void setAxisDistance(String axisDistance) {
        this.axisDistance = axisDistance == null ? null : axisDistance.trim();
    }

    public String getSteelSpring() {
        return steelSpring;
    }

    public void setSteelSpring(String steelSpring) {
        this.steelSpring = steelSpring == null ? null : steelSpring.trim();
    }

    public String getKerbMass() {
        return kerbMass;
    }

    public void setKerbMass(String kerbMass) {
        this.kerbMass = kerbMass == null ? null : kerbMass.trim();
    }

    public String getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(String totalMass) {
        this.totalMass = totalMass == null ? null : totalMass.trim();
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public String getFrontOverhang() {
        return frontOverhang;
    }

    public void setFrontOverhang(String frontOverhang) {
        this.frontOverhang = frontOverhang == null ? null : frontOverhang.trim();
    }

    public String getRearOverhang() {
        return rearOverhang;
    }

    public void setRearOverhang(String rearOverhang) {
        this.rearOverhang = rearOverhang == null ? null : rearOverhang.trim();
    }

    public String getApproachAngle() {
        return approachAngle;
    }

    public void setApproachAngle(String approachAngle) {
        this.approachAngle = approachAngle == null ? null : approachAngle.trim();
    }

    public String getDepartureAngle() {
        return departureAngle;
    }

    public void setDepartureAngle(String departureAngle) {
        this.departureAngle = departureAngle == null ? null : departureAngle.trim();
    }

    public String getBusHeight() {
        return busHeight;
    }

    public void setBusHeight(String busHeight) {
        this.busHeight = busHeight == null ? null : busHeight.trim();
    }

    public String getDoorHeight() {
        return doorHeight;
    }

    public void setDoorHeight(String doorHeight) {
        this.doorHeight = doorHeight == null ? null : doorHeight.trim();
    }

    public String getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(String floorHeight) {
        this.floorHeight = floorHeight == null ? null : floorHeight.trim();
    }

    public String getFrontAxleLoad() {
        return frontAxleLoad;
    }

    public void setFrontAxleLoad(String frontAxleLoad) {
        this.frontAxleLoad = frontAxleLoad == null ? null : frontAxleLoad.trim();
    }

    public String getRearAxleLoad() {
        return rearAxleLoad;
    }

    public void setRearAxleLoad(String rearAxleLoad) {
        this.rearAxleLoad = rearAxleLoad == null ? null : rearAxleLoad.trim();
    }

    public String getMinClearance() {
        return minClearance;
    }

    public void setMinClearance(String minClearance) {
        this.minClearance = minClearance == null ? null : minClearance.trim();
    }

    public String getMinDiameter() {
        return minDiameter;
    }

    public void setMinDiameter(String minDiameter) {
        this.minDiameter = minDiameter == null ? null : minDiameter.trim();
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed == null ? null : maxSpeed.trim();
    }

    public String getMaxClimbGradient() {
        return maxClimbGradient;
    }

    public void setMaxClimbGradient(String maxClimbGradient) {
        this.maxClimbGradient = maxClimbGradient == null ? null : maxClimbGradient.trim();
    }

    public String getTank() {
        return tank;
    }

    public void setTank(String tank) {
        this.tank = tank == null ? null : tank.trim();
    }

    public String getCylinder() {
        return cylinder;
    }

    public void setCylinder(String cylinder) {
        this.cylinder = cylinder == null ? null : cylinder.trim();
    }

    public Short getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(Short busStatus) {
        this.busStatus = busStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
    
    public String getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}
}