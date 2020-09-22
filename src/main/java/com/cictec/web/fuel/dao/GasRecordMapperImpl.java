package com.cictec.web.fuel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.GasRecord;

@Repository("gasRecordMapperImpl")
public class GasRecordMapperImpl implements GasRecordMapper {

	private static Logger LOG = Logger.getLogger(GasRecordMapperImpl.class);
	
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(String gasUuid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GasRecord record) {
		return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasRecordMapper.insert", record);
	}

	@Override
	public int insertSelective(GasRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GasRecord selectByPrimaryKey(String gasUuid) {
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasRecordMapper.selectByPrimaryKey", gasUuid);
	}

	@Override
	public int updateByPrimaryKeySelective(GasRecord record) {
		return sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasRecordMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(GasRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GasRecord> getGasRecordByParam(GasRecord gasRecord) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasRecordMapper.getGasRecordByParam", gasRecord);
	}
	
}
