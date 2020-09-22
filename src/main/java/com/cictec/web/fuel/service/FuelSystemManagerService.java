package com.cictec.web.fuel.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.dao.CollectDeviceMapper;
import com.cictec.web.fuel.dao.ParamSettingMapper;
import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.ParamSetting;

@Service("fuelSystemManagerService")
public class FuelSystemManagerService implements IFuelSystemManagerService {
	
	@Autowired
	@Qualifier("paramSettingMapperImpl")
	private ParamSettingMapper paramSettingImpl;
	
	@Autowired
	@Qualifier("collectDeviceMapperImpl")
	private CollectDeviceMapper collectDeviceMapperImpl;
	
	@Override
	public List<ParamSetting> getAllFuelParamSetting() {
		
		return 	paramSettingImpl.getAllParamSettings();
	}
	
	@Override
	public ParamSetting getParamSettingByType(String type){
		return paramSettingImpl.getParamBykey(type);
	}
	
	@Override
	public boolean updataParamSetting(ParamSetting paramSetting) {
		
		return paramSettingImpl.updateByPrimaryKey(paramSetting)==1?true:false;
	}

	@Override
	public List<CollectDevice> getCollectDevice(Map<String, Object> paraMap) {
		return collectDeviceMapperImpl.selectByParams(paraMap);
	}
	
	@Override
	public CollectDevice selectByDeviceImei(String deviceImei){
		return collectDeviceMapperImpl.selectByDeviceImei(deviceImei);
	}
	
	@Override
	public boolean saveCollectDevice(CollectDevice collectDevice) {
		
		boolean result = false; 
		if(collectDevice.getDeviceId().equals("")||collectDevice.getDeviceId()==null){
			collectDevice.setDeviceId(UUIDGenerator.genUuidStr());
			collectDevice.setCreated((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			result = this.collectDeviceMapperImpl.insert(collectDevice);
    	}else{
    		result = this.collectDeviceMapperImpl.updateByPrimaryKey(collectDevice);
    	}
    	
		 return result;
	}
	
	/***
	 * 删除用户数据
	 * 
	 * @param
	 * @return
	 */
	public boolean deleteCollectDevice(String deviceId) {
		    boolean flag = false;
			flag = collectDeviceMapperImpl.deleteByPrimaryKey(deviceId);
		return flag;
	}
	
	/***
	 * 删除用户数据
	 * 
	 * @param
	 * @return
	 */
	public boolean deleteCollectDeviceForList(List deviceIds) {
		    boolean flag = false;
			flag = collectDeviceMapperImpl.deleteByPrimaryKeyList(deviceIds);
		return flag;
	}
	
	
}
