package com.cictec.web.fuel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.ClassFuelStat;
import com.cictec.web.fuel.model.NewBusGas;
@Repository("newBusGasMapperImpl")
public class NewBusGasMapperImpl implements NewBusGasMapper{
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	public int deleteByPrimaryKey(String gasId)
    {
    	return 0;
    }

	public int insert(NewBusGas record){
    	return 0;
    }

	public int insertSelective(NewBusGas record){
    	return 0;
    }

	public NewBusGas selectByPrimaryKey(String gasId){
    	return null;
    }

	public int updateByPrimaryKeySelective(NewBusGas record){
    	return 0;
    }

    public int updateByPrimaryKey(NewBusGas record){
    	return 0;
    }
    @Override
	 public List<NewBusGas> selectByCondition(ClientQueryByParam daoQuery)
	 {
		 List<NewBusGas>gasLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.NewBusGasMapper.selectByCondition", daoQuery);
		 return gasLi;
	 }
}