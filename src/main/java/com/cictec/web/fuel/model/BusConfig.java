package com.cictec.web.fuel.model;

import java.util.Date;

public class BusConfig {
    private Long busConfigId;

    private String busConfigTitle;

    private Date publishTime;

    public Long getBusConfigId() {
        return busConfigId;
    }

    public void setBusConfigId(Long busConfigId) {
        this.busConfigId = busConfigId;
    }

    public String getBusConfigTitle() {
        return busConfigTitle;
    }

    public void setBusConfigTitle(String busConfigTitle) {
        this.busConfigTitle = busConfigTitle == null ? null : busConfigTitle.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}