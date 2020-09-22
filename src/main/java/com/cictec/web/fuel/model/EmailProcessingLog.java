package com.cictec.web.fuel.model;

import java.util.Date;

public class EmailProcessingLog {
    private String uuid;

    private String emailId;

    private String content;

    private Date creatTime;

    private String customItem1;

    private String customItem2;

    private String customItem3;

    private String customItem4;

    private String customItem5;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId == null ? null : emailId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCustomItem1() {
        return customItem1;
    }

    public void setCustomItem1(String customItem1) {
        this.customItem1 = customItem1 == null ? null : customItem1.trim();
    }

    public String getCustomItem2() {
        return customItem2;
    }

    public void setCustomItem2(String customItem2) {
        this.customItem2 = customItem2 == null ? null : customItem2.trim();
    }

    public String getCustomItem3() {
        return customItem3;
    }

    public void setCustomItem3(String customItem3) {
        this.customItem3 = customItem3 == null ? null : customItem3.trim();
    }

    public String getCustomItem4() {
        return customItem4;
    }

    public void setCustomItem4(String customItem4) {
        this.customItem4 = customItem4 == null ? null : customItem4.trim();
    }

    public String getCustomItem5() {
        return customItem5;
    }

    public void setCustomItem5(String customItem5) {
        this.customItem5 = customItem5 == null ? null : customItem5.trim();
    }
}