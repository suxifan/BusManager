package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.GasGun;

@Repository("gasGunMapperImpl")
public class GasGunMapperImpl implements GasGunMapper{
	
	private static Logger LOG = Logger.getLogger(GasGunMapperImpl.class);
	
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public boolean deleteByPrimaryKey(String gasGunlId) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.delete("com.cictec.web.fuel.dao.GasGunMapper.deleteByPrimaryKey", gasGunlId);
			result = true;
		} catch (Exception e) {
			LOG.error("删除气枪信息：" + gasGunlId + " 失败！" + e.toString());
		}
		return result;
	}

	@Override
	public int insert(GasGun record) {
		return 0;
	}

	@Override
	public boolean insertSelective(GasGun record) {
		boolean result = false;
		try {
			sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasGunMapper.insertSelective", record);
			result = true;
		} catch (Exception e) {
			LOG.error("新增气枪信息失败：" + e.toString());
		}
		return result;
	}

	@Override
	public GasGun selectByPrimaryKey(Integer gasGunlId) {
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(GasGun record) {

		boolean result = false;
		try {
			sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasGunMapper.updateByPrimaryKeySelective", record);
			result = true;
		} catch (Exception e) {
			LOG.error("更新气枪信息失败：" + e.toString());
		}
		return result;
	}

	@Override
	public int updateByPrimaryKey(GasGun record) {
		return 0;
	}

	@Override
	public List<GasGun> selectAllGasGun(Map record) {
		List<GasGun> gasGunList = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasGunMapper.selectAllGasGun", record);
		return gasGunList;
	}

	@Override
	public int selectTotalRecord() {
		
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasGunMapper.selectTotalRecord");
	}

}
