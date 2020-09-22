package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.EmailProcessingLog;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository("emailProcessingLogMapperImpl")
public class EmailProcessingLogMapperImpl implements EmailProcessingLogMapper {

    @Resource(name = "sqlSessionFuel")
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public int deleteByPrimaryKey(String uuid) {
        return 0;
    }

    @Override
    public int insert(EmailProcessingLog record) {
        return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.EmailProcessingLogMapper.insert",record);
    }

    @Override
    public int insertSelective(EmailProcessingLog record) {
        return 0;
    }

    @Override
    public EmailProcessingLog selectByPrimaryKey(String uuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EmailProcessingLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(EmailProcessingLog record) {
        return 0;
    }

    @Override
    public List<EmailProcessingLog> queryByEmailId(String emailId) {
        return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.EmailProcessingLogMapper.queryByEmailId",emailId);
    }
}