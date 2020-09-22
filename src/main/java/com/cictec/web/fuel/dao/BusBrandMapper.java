package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.BusBrand;

public interface BusBrandMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(BusBrand record);

    int insertSelective(BusBrand record);

    BusBrand selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(BusBrand record);

    int updateByPrimaryKey(BusBrand record);
}