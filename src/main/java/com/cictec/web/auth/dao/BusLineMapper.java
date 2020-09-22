package com.cictec.web.auth.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.BusLine;

public interface BusLineMapper {
    int deleteByPrimaryKey(String lineId);

    int insert(BusLine record);

    int insertSelective(BusLine record);

    List<BusLine> selectByPrimaryKey(String lineId);

    int updateByPrimaryKeySelective(BusLine record);

    int updateByPrimaryKey(BusLine record);
    List<BusLine> queryBusLineByKeywords(String key);
    BusLine queryBusLineByNameAndOrg(Map key);


}