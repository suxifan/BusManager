package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.EmailProcessingLog;

import java.util.List;

public interface EmailProcessingLogMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(EmailProcessingLog record);

    int insertSelective(EmailProcessingLog record);

    EmailProcessingLog selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(EmailProcessingLog record);

    int updateByPrimaryKey(EmailProcessingLog record);

    List<EmailProcessingLog> queryByEmailId(String emailId);
}