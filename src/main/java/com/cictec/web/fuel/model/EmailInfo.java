package com.cictec.web.fuel.model;

import java.util.Date;

public class EmailInfo {

    public static final int EMAIL_PROCESSING_TYPE_UNPROCESSING=0;

    public static final int EMAIL_PROCESSING_TYPE_PROCESSING=1;

    public static final int EMAIL_PROCESSING_TYPE_ERRORDATA = 2;

    public static final int DOWNLOAD_TYPE_NO=0;

    public static final int DOWNLOAD_TYPE_YES=1;


    private String emailId;

    private String sendAddress;

    private Date sendTime;

    private String sendTimeStr;

    private Integer downloadType;

    private Date downloadTime;

    private String downloadTimeStr;

    private Integer processingState;

    private String excelPath;

    private String customItem1;

    private String customItem2;

    private String customItem3;

    private String customItem4;

    private String customItem5;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public Integer getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(Integer downloadType) {
        this.downloadType = downloadType;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getDownloadTimeStr() {
        return downloadTimeStr;
    }

    public void setDownloadTimeStr(String downloadTimeStr) {
        this.downloadTimeStr = downloadTimeStr;
    }

    public Integer getProcessingState() {
        return processingState;
    }

    public void setProcessingState(Integer processingState) {
        this.processingState = processingState;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getCustomItem1() {
        return customItem1;
    }

    public void setCustomItem1(String customItem1) {
        this.customItem1 = customItem1;
    }

    public String getCustomItem2() {
        return customItem2;
    }

    public void setCustomItem2(String customItem2) {
        this.customItem2 = customItem2;
    }

    public String getCustomItem3() {
        return customItem3;
    }

    public void setCustomItem3(String customItem3) {
        this.customItem3 = customItem3;
    }

    public String getCustomItem4() {
        return customItem4;
    }

    public void setCustomItem4(String customItem4) {
        this.customItem4 = customItem4;
    }

    public String getCustomItem5() {
        return customItem5;
    }

    public void setCustomItem5(String customItem5) {
        this.customItem5 = customItem5;
    }
}