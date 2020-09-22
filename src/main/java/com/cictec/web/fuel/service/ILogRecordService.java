package com.cictec.web.fuel.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.dao.LogRecordMapper;
import com.cictec.web.fuel.model.LogRecord;


public interface ILogRecordService {
	public boolean saveLog(LogRecord lr);	
}
