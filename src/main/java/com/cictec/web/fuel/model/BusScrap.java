package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class BusScrap {
    private Long busScrapId;

    private String busNum;

    private String busModel;

    private Date enableDate;

    private Date scrapDate;

    private String recycleProve;

    private BigDecimal subsidyAmount;

    private Date accountDate;

    private String engineNum;

    private String underpanNum;

    private Date cancellDate;

    private String remark;

    private Date created;

    public Long getBusScrapId() {
        return busScrapId;
    }

    public void setBusScrapId(Long busScrapId) {
        this.busScrapId = busScrapId;
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

    public Date getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }

    public String getRecycleProve() {
        return recycleProve;
    }

    public void setRecycleProve(String recycleProve) {
        this.recycleProve = recycleProve == null ? null : recycleProve.trim();
    }

    public BigDecimal getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(BigDecimal subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum == null ? null : engineNum.trim();
    }

    public String getUnderpanNum() {
        return underpanNum;
    }

    public void setUnderpanNum(String underpanNum) {
        this.underpanNum = underpanNum == null ? null : underpanNum.trim();
    }

    public Date getCancellDate() {
        return cancellDate;
    }

    public void setCancellDate(Date cancellDate) {
        this.cancellDate = cancellDate;
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