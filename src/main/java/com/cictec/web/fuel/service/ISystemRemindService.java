package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.fuel.model.GasRecord;
import com.cictec.web.fuel.model.GasRemind;

public interface ISystemRemindService {

	List<GasRemind> getRemindMsg(Map paramMap);

	int getNotReadRemindMsgCount();

	boolean updateRemindMsgToRead();

	void insert(GasRemind remind);
	
	List<GasRecord> getGasRecordByParam(GasRecord gasRecord);

	int getRemindMsgCount(Map paramMap);
}
