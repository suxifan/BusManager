package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.fuel.model.GasGun;
import com.cictec.web.fuel.model.GasStationPrincipal;

public interface IGasStationService {
	
	List<GasStationPrincipal> getAllGasStationInfo();
	
	List<GasStationPrincipal> getGasStationInfoByName(GasStationPrincipal station);

	boolean deleteGasStationInfoById(String id);

	boolean addGasStationInfo(GasStationPrincipal gasStationInfo);

	boolean updateGasStationInfo(GasStationPrincipal gasStationInfo);

	List<GasGun> selectAllGasGun(Map record);

	boolean addGasStationInfo(GasGun gasGunInfo);

	boolean deleteGasGunInfoById(String id);

	boolean modifyGasGunInfo(GasGun gasGunInfo);

	int selectTotalRecord();

    List<GasStationPrincipal> getAllStationMsg();
	
}
