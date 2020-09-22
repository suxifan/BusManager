package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.ParamSetting;

@Repository("collectDeviceMapperImpl")
public class CollectDeviceMapperImpl implements CollectDeviceMapper {

	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public boolean deleteByPrimaryKey(String deviceId) {
		boolean flag = false;
		try {
			sqlSessionTemplate.delete("com.cictec.web.fuel.dao.CollectDeviceMapper.deleteByPrimaryKey",
					deviceId);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean insert(CollectDevice record) {
		boolean flag = false;
		try {
			sqlSessionTemplate.insert("com.cictec.web.fuel.dao.CollectDeviceMapper.insertSelective",
					record);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean insertSelective(CollectDevice record) {
		return false;
	}

	@Override
	public List<CollectDevice> selectByParams(Map record) {
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.CollectDeviceMapper.selectByParams",record);
	}

	@Override
	public CollectDevice selectByPrimaryKey(String deviceId) {
		return null;
	}

	@Override
	public boolean updateByPrimaryKey(CollectDevice record) {
		boolean flag = false;
		try {
			sqlSessionTemplate.update("com.cictec.web.fuel.dao.CollectDeviceMapper.updateByPrimaryKeySelective",
					record);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public int updateByPrimaryKeySelective(CollectDevice record) {
		return 0;
	}

	@Override
	public CollectDevice selectByDeviceImei(String deviceImei) {
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.CollectDeviceMapper.selectByDeviceImei",deviceImei);
	}

	@Override
	public boolean deleteByPrimaryKeyList(List deviceIds) {
		boolean flag = false;
		try {
			sqlSessionTemplate.delete("com.cictec.web.fuel.dao.CollectDeviceMapper.deleteByPrimaryKeyList",
					deviceIds);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	
    
}