package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.GasCylinderType;

public interface GasCylinderTypeMapper {
    int deleteByPrimaryKey(Integer cylinderTypeId);

    int insert(GasCylinderType record);

    int insertSelective(GasCylinderType record);

    GasCylinderType selectByPrimaryKey(Integer cylinderTypeId);

    int updateByPrimaryKeySelective(GasCylinderType record);

    int updateByPrimaryKey(GasCylinderType record);
}