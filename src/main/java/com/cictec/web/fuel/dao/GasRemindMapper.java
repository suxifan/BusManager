package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.fuel.model.GasRemind;

public interface GasRemindMapper {
	
    int deleteByPrimaryKey(String remindId);

    int insert(GasRemind record);

    int insertSelective(GasRemind record);

    GasRemind selectByPrimaryKey(String remindId);

    int updateByPrimaryKeySelective(GasRemind record);

    int updateByPrimaryKey(GasRemind record);
    
    List<GasRemind> getRemindMsg(Map paramMap);

	int getNotReadRemindMsgCount();

	boolean updateRemindMsgToRead();

	int getRemindMsgCount(Map paramMap);
}