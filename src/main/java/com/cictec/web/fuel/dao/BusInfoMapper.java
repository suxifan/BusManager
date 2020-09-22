package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.BusInfo;

public interface BusInfoMapper {
	
    int deleteByPrimaryKey(String busInfoId);

    int insert(BusInfo record);

    int insertSelective(BusInfo record);

    BusInfo selectByPrimaryKey(String busInfoId);

    int updateByPrimaryKeySelective(BusInfo record);

    int updateByPrimaryKey(BusInfo record);
    
    List<BusInfo>selectByParams(ClientQueryByParam daoQuery);

    List<BusInfo>selectByBusNum(ClientQueryByParam daoQuery);

	List<String> getBusNumsByStatus(int status);
	
	List<BusInfo>selectAllBusByParams(Map record);
}