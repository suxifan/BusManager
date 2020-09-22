package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.GasModified;

public interface GasModifiedMapper {
    int deleteByPrimaryKey(String gasModifiedId);

    int insert(GasModified record);

    int insertSelective(GasModified record);

    GasModified selectByPrimaryKey(String gasModifiedId);

    GasModified selectByGasDetailId(String gasDetailId);
    
    int updateByPrimaryKeySelective(GasModified record);

    int updateByPrimaryKey(GasModified record);
	List<GasModified> selectByCondition(ClientQueryByParam daoQuery);
	void updateModifiedFuelCheckReport(Map<String, String> paraMap);

}