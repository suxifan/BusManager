package com.cictec.web.fuel.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.dao.LogRecordMapper;
import com.cictec.web.fuel.model.LogRecord;


/**
 * 组织机构服务类
 * 
 * @Project BusManager
 * @author kxw(kongxiangwen@cictec.cn)
 * @since 2015-6-19
 * @version 1.0
 * @change_log
 */
@Service("logRecordService")
public class LogRecordService implements ILogRecordService{

	Logger log = Logger.getLogger(LogRecordService.class);

	@Resource(name="logRecordMapperImpl")
	private LogRecordMapper dao;
	public boolean saveLog(LogRecord lr){

			boolean flag = false;
			dao.insertSelective(lr);
			flag = true;
			return flag;
	}

	
}
