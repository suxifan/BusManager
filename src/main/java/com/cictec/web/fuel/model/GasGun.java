package com.cictec.web.fuel.model;

import java.util.Date;

public class GasGun {
	
    private String gasGunId;

    private String gasStation;

    private String gasStationId;

    private String gasGunNum;

    private Date created;

    public String getGasGunId() {
		return gasGunId;
	}

	public void setGasGunId(String gasGunId) {
		this.gasGunId = gasGunId;
	}

	public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation == null ? null : gasStation.trim();
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId == null ? null : gasStationId.trim();
    }

    public String getGasGunNum() {
        return gasGunNum;
    }

    public void setGasGunNum(String gasGunNum) {
        this.gasGunNum = gasGunNum == null ? null : gasGunNum.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}