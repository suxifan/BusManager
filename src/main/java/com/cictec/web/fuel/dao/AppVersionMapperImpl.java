package com.cictec.web.fuel.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.AppVersion;

@Repository("appVersionMapperImpl")
public class AppVersionMapperImpl implements AppVersionMapper {

	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(Integer appId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AppVersion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AppVersion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppVersion selectByPrimaryKey(Integer appId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(AppVersion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(AppVersion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppVersion selectByOsType(int osType) {
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.AppVersionMapper.selectByOsType", osType);
	}
    
}