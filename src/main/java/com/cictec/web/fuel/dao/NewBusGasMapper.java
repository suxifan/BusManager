package com.cictec.web.fuel.dao;

import java.util.List;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.NewBusGas;

public interface NewBusGasMapper {
    int deleteByPrimaryKey(String gasId);

    int insert(NewBusGas record);

    int insertSelective(NewBusGas record);

    NewBusGas selectByPrimaryKey(String gasId);

    int updateByPrimaryKeySelective(NewBusGas record);

    int updateByPrimaryKey(NewBusGas record);
	List<NewBusGas> selectByCondition(ClientQueryByParam daoQuery);
}