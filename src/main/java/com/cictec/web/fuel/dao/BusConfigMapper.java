package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusConfig;

public interface BusConfigMapper {
    int deleteByPrimaryKey(Long busConfigId);

    int insert(BusConfig record);

    int insertSelective(BusConfig record);

    BusConfig selectByPrimaryKey(Long busConfigId);

    int updateByPrimaryKeySelective(BusConfig record);

    int updateByPrimaryKey(BusConfig record);
}