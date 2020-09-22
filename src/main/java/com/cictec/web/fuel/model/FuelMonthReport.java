package com.cictec.web.fuel.model;

import java.math.BigDecimal;

public class FuelMonthReport {
	
	private String lineId;//线路编号
	
	private String lineName;//线路名称
	public String getLineOrgId() {
		return lineOrgId;
	}

	public void setLineOrgId(String lineOrgId) {
		this.lineOrgId = lineOrgId;
	}

	public String getLineOrgName() {
		return lineOrgName;
	}

	public void setLineOrgName(String lineOrgName) {
		this.lineOrgName = lineOrgName;
	}

	private String lineOrgId;//
	private String lineOrgName;
	
	private String orgId;//分公司标识
	
	private String orgName;//分公司名称
	
	private float price;//油气单价
	
	private BigDecimal sumvolume;//加气总量
	
	private BigDecimal amount;//加气总金额
	
	private String remark;//备注
	
	private String fuelName = "天然气";//材料名称
	
	private String unit = "m3";//材料单位

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getSumvolume() {
		return sumvolume;
	}

	public void setSumvolume(BigDecimal sumvolume) {
		this.sumvolume = sumvolume;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
