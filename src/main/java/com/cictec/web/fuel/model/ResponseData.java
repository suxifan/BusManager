package com.cictec.web.fuel.model;

import java.util.List;

public class ResponseData {
	private List <BusInfoVo> busInfos;
	private List <UserVo> users;
	private List <GasGun> gasGuns;
	public List<BusInfoVo> getBusInfos() {
		return busInfos;
	}
	public void setBusInfos(List<BusInfoVo> busInfos) {
		this.busInfos = busInfos;
	}
	public List<UserVo> getUsers() {
		return users;
	}
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}
	public List<GasGun> getGasGuns() {
		return gasGuns;
	}
	public void setGasGuns(List<GasGun> gasGuns) {
		this.gasGuns = gasGuns;
	}
	
}
