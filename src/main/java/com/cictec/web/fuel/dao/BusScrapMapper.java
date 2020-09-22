package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusScrap;

public interface BusScrapMapper {
    int deleteByPrimaryKey(Long busScrapId);

    int insert(BusScrap record);

    int insertSelective(BusScrap record);

    BusScrap selectByPrimaryKey(Long busScrapId);

    int updateByPrimaryKeySelective(BusScrap record);

    int updateByPrimaryKey(BusScrap record);
}