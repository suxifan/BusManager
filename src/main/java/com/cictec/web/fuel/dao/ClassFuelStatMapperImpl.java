package com.cictec.web.fuel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.ClassFuelStat;
@Repository("classFuelStatMapperImpl")
public class ClassFuelStatMapperImpl implements ClassFuelStatMapper{
	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;
	public int deleteByPrimaryKey(String statId)
    {
    	return 0;
    }

	public int insert(ClassFuelStat record){
    	return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.ClassFuelStatMapper.insert",record);
    }

	public int insertSelective(ClassFuelStat record){
    	return 0;
    }

	public ClassFuelStat selectByPrimaryKey(String statId){
    	return null;
    }

	public int updateByPrimaryKeySelective(ClassFuelStat record){
    	return 0;
    }

    public int updateByPrimaryKey(ClassFuelStat record){
    	return 0;
    }
    
    @Override
	 public List<ClassFuelStat> selectByCondition(ClientQueryByParam daoQuery)
	 {
		 List<ClassFuelStat>gasLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.ClassFuelStatMapper.selectByCondition", daoQuery);
		 return gasLi;
	 }
}