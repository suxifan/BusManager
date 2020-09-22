package com.cictec.web.fuel.dao;

import java.util.List;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.ClassFuelStat;

public interface ClassFuelStatMapper {
    int deleteByPrimaryKey(String statId);

    int insert(ClassFuelStat record);

    int insertSelective(ClassFuelStat record);

    ClassFuelStat selectByPrimaryKey(String statId);

    int updateByPrimaryKeySelective(ClassFuelStat record);

    int updateByPrimaryKey(ClassFuelStat record);
	 List<ClassFuelStat> selectByCondition(ClientQueryByParam daoQuery);

}