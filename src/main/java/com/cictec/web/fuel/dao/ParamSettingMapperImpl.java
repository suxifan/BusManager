package com.cictec.web.fuel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.ParamSetting;

@Repository("paramSettingMapperImpl")
public class ParamSettingMapperImpl implements ParamSettingMapper {

	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(Integer settingId) {
		return 0;
	}

	@Override
	public int insert(ParamSetting record) {
		return 0;
	}

	@Override
	public int insertSelective(ParamSetting record) {
		return 0;
	}

	@Override
	public ParamSetting selectByPrimaryKey(Integer settingId) {
		return null;
	}

	@Override
	public int updateByPrimaryKey(ParamSetting record) {
		return 	this.sqlSessionTemplate.update("com.cictec.web.fuel.dao.ParamSettingMapper.updateByPrimaryKey", record);

	}

	@Override
	public int updateByPrimaryKeySelective(ParamSetting record) {
		return 0;
	}

	@Override
	public List<ParamSetting> getAllParamSettings() {
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.ParamSettingMapper.selectAllFuelParamSetting");
	}

	@Override
	public ParamSetting getParamBykey(String key) {
		
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.ParamSettingMapper.getParamBykey",key);
		
	}
    
}