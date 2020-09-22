package com.cictec.web.fuel.dao;

import java.util.List;

import com.cictec.web.fuel.model.GasStationPrincipal;

public interface GasStationPrincipalMapper {
	
    int deleteByPrimaryKey(Integer principalId);

    int insert(GasStationPrincipal record);

    int insertSelective(GasStationPrincipal record);

    GasStationPrincipal selectByPrimaryKey(Integer principalId);

    GasStationPrincipal selectByGasStationId(String gasStationId);

    
    boolean updateByPrimaryKeySelective(GasStationPrincipal record);

    int updateByPrimaryKey(GasStationPrincipal record);
    
    List<GasStationPrincipal> getGasStationByParams(GasStationPrincipal GasStation);

	boolean deleteGasStationInfoById(String id);

	boolean addGasStationInfo(GasStationPrincipal gasStationInfo);
	
	List<GasStationPrincipal> getAllGasStation();

    GasStationPrincipal getGasStationByEmail(String email);

}