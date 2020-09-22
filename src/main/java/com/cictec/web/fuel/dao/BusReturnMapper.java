package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusReturn;

public interface BusReturnMapper {
    int deleteByPrimaryKey(Long busReturnD);

    int insert(BusReturn record);

    int insertSelective(BusReturn record);

    BusReturn selectByPrimaryKey(Long busReturnD);

    int updateByPrimaryKeySelective(BusReturn record);

    int updateByPrimaryKey(BusReturn record);
}