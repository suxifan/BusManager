package com.cictec.web.fuel.dao;

import java.util.List;

import com.cictec.web.fuel.model.ParamSetting;

public interface ParamSettingMapper {
    int deleteByPrimaryKey(Integer settingId);

    int insert(ParamSetting record);

    int insertSelective(ParamSetting record);

    ParamSetting selectByPrimaryKey(Integer settingId);
    
    ParamSetting getParamBykey(String key);

    int updateByPrimaryKeySelective(ParamSetting record);

    int updateByPrimaryKey(ParamSetting record);
    
    List<ParamSetting>  getAllParamSettings();
}