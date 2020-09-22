package com.cictec.web.fuel.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.dao.UserAuthManagementDao;
import com.cictec.web.fuel.model.LogRecord;
@Repository("logRecordMapperImpl")
public class LogRecordMapperImpl implements LogRecordMapper{
	@Resource(name="sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	Logger log = Logger.getLogger(LogRecordMapperImpl.class);
	public int deleteByPrimaryKey(String logId)
	{
		return 0;
	}

	public int insert(LogRecord record)
	{
		return 0;
	}

	public int insertSelective(LogRecord record)
	{
		try{
		sqlSessionTemplate.insert("com.cictec.web.fuel.dao.LogRecordMapper.insertSelective",
				record);
		}catch(Exception e){
			log.error(e.toString());
		}
		return 0;
	}

	public LogRecord selectByPrimaryKey(String logId)
	{
		return null;
	}

	public int updateByPrimaryKeySelective(LogRecord record)
	{
		return 0;
	}

	public int updateByPrimaryKey(LogRecord record)
	{
		return 0;
	}
}