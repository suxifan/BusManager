package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.LogRecord;

public interface LogRecordMapper {
    int deleteByPrimaryKey(String logId);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);
}