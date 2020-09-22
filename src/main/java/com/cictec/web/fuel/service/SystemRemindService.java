package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.fuel.dao.GasRecordMapper;
import com.cictec.web.fuel.dao.GasRemindMapper;
import com.cictec.web.fuel.model.GasRecord;
import com.cictec.web.fuel.model.GasRemind;

@Service("systemRemindService")
public class SystemRemindService implements ISystemRemindService {

	@Autowired
	@Qualifier("gasRemindMapperImpl")
	private GasRemindMapper gasRemindDao;
	
	@Autowired
	@Qualifier("gasRecordMapperImpl")
	private GasRecordMapper gasRecordDao;
	
	@Override
	public List<GasRemind> getRemindMsg(Map paramMap) {
		
		return gasRemindDao.getRemindMsg(paramMap);
	}
	
	@Override
	public int getRemindMsgCount(Map paramMap) {
		
		return gasRemindDao.getRemindMsgCount(paramMap);
	}

	@Override
	public int getNotReadRemindMsgCount() {
		
		return gasRemindDao.getNotReadRemindMsgCount();
	}

	@Override
	public boolean updateRemindMsgToRead() {
		
		return gasRemindDao.updateRemindMsgToRead();
	}

	@Override
	public void insert(GasRemind remind) {
		
		gasRemindDao.insert(remind);
	}

	@Override
	public List<GasRecord> getGasRecordByParam(GasRecord gasRecord) {
		
		return gasRecordDao.getGasRecordByParam(gasRecord);
	}


	
}
