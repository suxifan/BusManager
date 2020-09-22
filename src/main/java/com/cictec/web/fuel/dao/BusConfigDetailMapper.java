package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusConfigDetail;

public interface BusConfigDetailMapper {
    int deleteByPrimaryKey(String busConfigDetailId);

    int insert(BusConfigDetail record);

    int insertSelective(BusConfigDetail record);

    BusConfigDetail selectByPrimaryKey(String busConfigDetailId);

    int updateByPrimaryKeySelective(BusConfigDetail record);

    int updateByPrimaryKey(BusConfigDetail record);
}