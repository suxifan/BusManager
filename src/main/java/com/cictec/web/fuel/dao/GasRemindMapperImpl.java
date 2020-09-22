package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.GasRemind;

@Repository("gasRemindMapperImpl")
public class GasRemindMapperImpl implements GasRemindMapper {

	private static Logger LOG = Logger.getLogger(GasRemindMapperImpl.class);
	
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(String remindId) {
		return 0;
	}

	@Override
	public int insert(GasRemind record) {
		
		int result = 0;
		try {
			result = sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasRemindMapper.insert", record);
		} catch (Exception e) {
			LOG.error("插入系统提醒消息:" + record.getRemindContent() + ", 失败！" + e.toString());
		}
		return result;
	}

	@Override
	public int insertSelective(GasRemind record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GasRemind selectByPrimaryKey(String remindId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GasRemind record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GasRemind record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GasRemind> getRemindMsg(Map paramMap) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasRemindMapper.getRemindMsg",paramMap);
	}
	
	@Override
	public int getRemindMsgCount(Map paramMap) {
		
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasRemindMapper.getRemindMsgCount",paramMap);
	}
	
	@Override
	public int getNotReadRemindMsgCount() {
		
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasRemindMapper.getNotReadRemindMsgCount");
	}

	@Override
	public boolean updateRemindMsgToRead() {
		
		boolean result = false;
		try {
			sqlSessionTemplate.update("updateRemindMsgToRead");
			result = true;
		} catch (Exception e) {
			LOG.error("更新角色表失败：" + e.toString());
		}
		return result;
	}

}
