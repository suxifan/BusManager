package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasModified;

@Repository("gasModifiedMapperImpl")
public class GasModifiedMapperImpl implements GasModifiedMapper{
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	public int deleteByPrimaryKey(String gasModifiedId)
    {
    	return 0;
    }

    public int insert(GasModified record){
    	
		return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasModifiedMapper.insert", record);
    }

    public int insertSelective(GasModified record){
    	return 0;
    }

    public GasModified selectByPrimaryKey(String gasModifiedId){
    	return null;
    }

    public int updateByPrimaryKeySelective(GasModified record){
    	return sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasModifiedMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GasModified record){
    	return 0;
    }
    
	@Override
	 public List<GasModified> selectByCondition(ClientQueryByParam daoQuery)
	 {
		 List<GasModified>gasLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasModifiedMapper.selectByCondition", daoQuery);
		 return gasLi;
	 }

	@Override
	public void updateModifiedFuelCheckReport(Map<String, String> paraMap)
	{
		sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasModifiedMapper.updateModifiedFuelCheckReport", paraMap);

	}


	@Override
	public GasModified selectByGasDetailId(String gasDetailId) {
		return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasModifiedMapper.selectByGasDetailId", gasDetailId);
	}

}