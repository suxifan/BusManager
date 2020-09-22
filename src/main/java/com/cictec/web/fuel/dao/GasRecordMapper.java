package com.cictec.web.fuel.dao;

import java.util.List;

import com.cictec.web.fuel.model.GasRecord;

public interface GasRecordMapper {
    int deleteByPrimaryKey(String gasUuid);

    int insert(GasRecord record);

    int insertSelective(GasRecord record);

    GasRecord selectByPrimaryKey(String gasUuid);

    int updateByPrimaryKeySelective(GasRecord record);

    int updateByPrimaryKey(GasRecord record);
    
	public List<GasRecord> getGasRecordByParam(GasRecord gasRecord);
}