package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.ParamSetting;

public interface IFuelSystemManagerService {

	List<ParamSetting> getAllFuelParamSetting();
	
	boolean updataParamSetting(ParamSetting paramSetting);
	
	boolean saveCollectDevice(CollectDevice collectDevice);
	
	List<CollectDevice> getCollectDevice(Map<String, Object> paraMap);
	boolean deleteCollectDeviceForList(List deviceIds);

	boolean deleteCollectDevice(String deviceId);
	public CollectDevice selectByDeviceImei(String deviceImei);
	public ParamSetting getParamSettingByType(String type);
}
