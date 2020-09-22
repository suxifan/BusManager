package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusStop;

public interface BusStopMapper {
    int deleteByPrimaryKey(String busStopId);

    int insert(BusStop record);

    int insertSelective(BusStop record);

    BusStop selectByPrimaryKey(String busStopId);

    int updateByPrimaryKeySelective(BusStop record);

    int updateByPrimaryKey(BusStop record);
}