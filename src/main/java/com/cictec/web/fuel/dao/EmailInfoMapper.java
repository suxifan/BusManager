package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.EmailInfo;

import java.util.List;
import java.util.Map;

public interface EmailInfoMapper {
    int deleteByPrimaryKey(String emailId);

    int insert(EmailInfo record);

    int insertSelective(EmailInfo record);

    EmailInfo selectByPrimaryKey(String emailId);

    int updateByPrimaryKeySelective(EmailInfo record);

    int updateByPrimaryKey(EmailInfo record);

    List<EmailInfo> queryEmailInfo(Map param);
}