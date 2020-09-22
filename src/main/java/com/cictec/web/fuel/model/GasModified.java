package com.cictec.web.fuel.model;

import java.math.BigDecimal;
import java.util.Date;

public class GasModified {
    private String gasModifiedId;

    private String gasStationId;

    private String gasStation;

    private String gasUserName;

    private String gasUserId;

    private BigDecimal preGasVolume;

    private BigDecimal postGasVolume;

    private int gasStatus;
    
    private String auditOptinion;
    
    private String gasDetailId;
    
    private String gasEquipmentUrl;
    
    private Date gasTime;

    private Date modified;

    private Date created;
    
    private String gasTimeStr;
    private String modifiedStr;
    private String createdStr;
    
    public String getGasTimeStr() {
		return gasTimeStr;
	}

	public void setGasTimeStr(String gasTimeStr) {
		this.gasTimeStr = gasTimeStr;
	}

	public String getModifiedStr() {
		return modifiedStr;
	}

	public void setModifiedStr(String modifiedStr) {
		this.modifiedStr = modifiedStr;
	}

	public String getCreatedStr() {
		return createdStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}

	public String getGasModifiedId() {
        return gasModifiedId;
    }

    public void setGasModifiedId(String gasModifiedId) {
        this.gasModifiedId = gasModifiedId == null ? null : gasModifiedId.trim();
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId == null ? null : gasStationId.trim();
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation == null ? null : gasStation.trim();
    }

    public String getGasUserName() {
        return gasUserName;
    }

    public void setGasUserName(String gasUserName) {
        this.gasUserName = gasUserName == null ? null : gasUserName.trim();
    }

    public String getGasUserId() {
        return gasUserId;
    }

    public void setGasUserId(String gasUserId) {
        this.gasUserId = gasUserId == null ? null : gasUserId.trim();
    }

    public BigDecimal getPreGasVolume() {
        return preGasVolume;
    }

    public void setPreGasVolume(BigDecimal preGasVolume) {
        this.preGasVolume = preGasVolume;
    }

    public BigDecimal getPostGasVolume() {
        return postGasVolume;
    }

    public void setPostGasVolume(BigDecimal postGasVolume) {
        this.postGasVolume = postGasVolume;
    }

    public Date getGasTime() {
        return gasTime;
    }

    public void setGasTime(Date gasTime) {
        this.gasTime = gasTime;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getGasEquipmentUrl() {
        return gasEquipmentUrl;
    }

    public void setGasEquipmentUrl(String gasEquipmentUrl) {
        this.gasEquipmentUrl = gasEquipmentUrl == null ? null : gasEquipmentUrl.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public int getGasStatus() {
		return gasStatus;
	}

	public void setGasStatus(int gasStatus) {
		this.gasStatus = gasStatus;
	}

	public String getAuditOptinion() {
		return auditOptinion;
	}

	public void setAuditOptinion(String auditOptinion) {
		this.auditOptinion = auditOptinion;
	}

	public String getGasDetailId() {
		return gasDetailId;
	}

	public void setGasDetailId(String gasDetailId) {
		this.gasDetailId = gasDetailId;
	}
    
    
}