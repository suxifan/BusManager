package com.cictec.web.fuel.util;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.model.LogRecord;
import com.cictec.web.fuel.service.ILogRecordService;

/**
 * 
 * @author xiangwen.kong
 *
 */
public class DBLogger {

	//@Resource(name="logRecordService")
    private static ILogRecordService logRecService;	
	private static boolean isInit = false;
	private static LogRecord record = null;
	private static RequestAttributes ra = null;
	private static HttpServletRequest request = null;
	public static void log(String info)
	{
		if(!isInit){
			record = new LogRecord();
	    	isInit = true;
		}

    	ra = RequestContextHolder.getRequestAttributes();
    	request = ((ServletRequestAttributes)ra).getRequest();
    	record.setUserName((String)request.getSession().getAttribute("userName"));
    	record.setUserId((String)request.getSession().getAttribute("userId"));  	
    	record.setIp(request.getRemoteAddr());	
			record.setContent(info);
	    	record.setLogId(UUIDGenerator.genUuidStr());
	    	record.setCreated(new Date());
	    	
	    	logRecService.saveLog(record);
	}
	public static ILogRecordService getLogRecService() {
		return logRecService;
	}
	public static void setLogRecService(ILogRecordService logRecService) {
		DBLogger.logRecService = logRecService;
	}
	

}
