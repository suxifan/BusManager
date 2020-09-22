package com.cictec.web.fuel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.fuel.model.GasStationPrincipal;

@Repository("gasStationPrincipalMapperImpl")
public class GasStationPrincipalMapperImpl implements GasStationPrincipalMapper {

	private static Logger LOG = Logger.getLogger(GasStationPrincipalMapperImpl.class);
	
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int deleteByPrimaryKey(Integer principalId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GasStationPrincipal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GasStationPrincipal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GasStationPrincipal selectByPrimaryKey(Integer principalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateByPrimaryKeySelective(GasStationPrincipal record) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasStationPrincipalMapper.updateByPrimaryKeySelective", record);
			result = true;
		} catch (Exception e) {
			LOG.error("更新加气站信息失败：" + e.toString());
		}
		return result;
	}

	@Override
	public int updateByPrimaryKey(GasStationPrincipal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GasStationPrincipal> getGasStationByParams(GasStationPrincipal GasStation) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasStationPrincipalMapper.getGasStationByParams", GasStation);
	}

	@Override
	public boolean deleteGasStationInfoById(String principalId) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.delete("com.cictec.web.fuel.dao.GasStationPrincipalMapper.deleteByPrimaryKey", principalId);
			result = true;
		} catch (Exception e) {
			LOG.error("删除加气站：" + principalId + " 失败！" + e.toString());
		}
		return result;
	}

	@Override
	public boolean addGasStationInfo(GasStationPrincipal gasStationInfo) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasStationPrincipalMapper.insertSelective", gasStationInfo);
			result = true;
		} catch (Exception e) {
			LOG.error("新增加气站信息失败：" + e.toString());
		}
		return result;
	}

    @Override
    public List<GasStationPrincipal> getAllGasStation() {
        return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasStationPrincipalMapper.selectAll");

    }

    @Override
    public GasStationPrincipal getGasStationByEmail(String email) {
        return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasStationPrincipalMapper.getGasStationByEmail",email);
    }

    @Override
	public GasStationPrincipal selectByGasStationId(String gasStationId) {
		
		return this.sqlSessionTemplate.selectOne("selectByGasStationId", gasStationId);
	}

}
