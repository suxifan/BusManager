package com.cictec.web.auth.pojo;

import java.util.Date;



public class ClientQueryByParam extends ClientQuery{


private String orgId;
private String year;
private String month;
private String queryType;
private String lineOrgId;
private String lineId;
private String gasStationId;
private String busNum;
private String selfNum;
private int operType;
private String lineName;
private String lineOrgName;
public String getLineOrgName() {
	return lineOrgName;
}

public void setLineOrgName(String lineOrgName) {
	this.lineOrgName = lineOrgName;
}

public String getLineName() {
	return lineName;
}

public void setLineName(String lineName) {
	this.lineName = lineName;
}

private String queryTimeRangeStart;
private String queryTimeRangeEnd;
private Date queryTime;
private String queryTimeStr;
public String getQueryTimeRangeStart() {
	return queryTimeRangeStart;
}

public void setQueryTimeRangeStart(String queryTimeRangeStart) {
	this.queryTimeRangeStart = queryTimeRangeStart;
}

public String getQueryTimeRangeEnd() {
	return queryTimeRangeEnd;
}

public void setQueryTimeRangeEnd(String queryTimeRangeEnd) {
	this.queryTimeRangeEnd = queryTimeRangeEnd;
}
public int getOperType() {
	return operType;
}

public void setOperType(int operType) {
	this.operType = operType;
}

public String getLineOrgId() {
	return lineOrgId;
}

public void setLineOrgId(String lineOrgId) {
	this.lineOrgId = lineOrgId;
}

public String getLineId() {
	return lineId;
}

public void setLineId(String lineId) {
	this.lineId = lineId;
}

public String getGasStationId() {
	return gasStationId;
}

public void setGasStationId(String gasStationId) {
	this.gasStationId = gasStationId;
}

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


public String getQueryTimeStr() {
	return queryTimeStr;
}

public void setQueryTimeStr(String queryTimeStr) {
	this.queryTimeStr = queryTimeStr;
}

private Date queryTimeEnd;
public Date getQueryTime() {
	return queryTime;
}

public void setQueryTime(Date queryTime) {
	this.queryTime = queryTime;
}

public Date getQueryTimeEnd() {
	return queryTimeEnd;
}

public void setQueryTimeEnd(Date queryTimeOneMonthLater) {
	this.queryTimeEnd = queryTimeOneMonthLater;
}

public String getQueryType() {
	return queryType;
}

public void setQueryType(String queryType) {
	this.queryType = queryType;
}

public String getYear() {
	return year;
}

public void setYear(String year) {
	this.year = year;
}

public String getMonth() {
	return month;
}

public void setMonth(String month) {
	this.month = month;
}

public String getOrgId() {
	return orgId;
}

public void setOrgId(String userName) {
	this.orgId = userName;
}
	
}
