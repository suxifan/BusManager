package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.fuel.model.CollectDevice;

public interface CollectDeviceMapper {
    boolean deleteByPrimaryKey(String deviceId);
    
    boolean deleteByPrimaryKeyList(List deviceId);

    boolean insert(CollectDevice record);

    boolean insertSelective(CollectDevice record);

    CollectDevice selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(CollectDevice record);

    boolean updateByPrimaryKey(CollectDevice record);
    
    List<CollectDevice> selectByParams(Map record);
    
    CollectDevice selectByDeviceImei(String deviceImei);
}