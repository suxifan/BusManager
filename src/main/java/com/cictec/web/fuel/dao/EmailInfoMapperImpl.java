package com.cictec.web.fuel.dao;

import com.cictec.web.fuel.model.EmailInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Repository("emailInfoMapperImpl")
public class EmailInfoMapperImpl implements EmailInfoMapper {

    @Resource(name = "sqlSessionFuel")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByPrimaryKey(String emailId) {
        return 0;
    }

    @Override
    public int insert(EmailInfo record) {

         return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.EmailInfoMapper.insert",record);

    }

    @Override
    public int insertSelective(EmailInfo record) {
        return 0;
    }

    @Override
    public EmailInfo selectByPrimaryKey(String emailId) {
        return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.EmailInfoMapper.selectByPrimaryKey",emailId);
    }

    @Override
    public int updateByPrimaryKeySelective(EmailInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(EmailInfo record) {
        return sqlSessionTemplate.update("com.cictec.web.fuel.dao.EmailInfoMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public List<EmailInfo> queryEmailInfo(Map param){
        return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.EmailInfoMapper.queryEmailInfoByParam",param);
    }
}