package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.GasCylinder;

public interface GasCylinderMapper {
    int deleteByPrimaryKey(String gasCylinderId);

    int insert(GasCylinder record);

    int insertSelective(GasCylinder record);

    GasCylinder selectByPrimaryKey(String gasCylinderId);

    int updateByPrimaryKeySelective(GasCylinder record);

    int updateByPrimaryKey(GasCylinder record);
}