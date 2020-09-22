package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.AppVersion;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer appId);
    
    AppVersion selectByOsType(int osType);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);
}