package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.BusInfo;

@Repository("busInfoMapperImpl")
public class BusInfoMapperImpl implements BusInfoMapper {
    
	@Resource(name = "sqlSessionBus")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int deleteByPrimaryKey(String busInfoId)
    {
    	return sqlSessionTemplate.delete("com.cictec.web.fuel.dao.BusInfoMapper.deleteByPrimaryKey",busInfoId);
    }

    public int insert(BusInfo record)
    {
    	return 0;
    }

    public int insertSelective(BusInfo record)
    {
        return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.BusInfoMapper.insertSelective",record);
    }

    public BusInfo selectByPrimaryKey(String busInfoId)
    {
    	return null;
    }

    public int updateByPrimaryKeySelective(BusInfo record)
    {
    	return   sqlSessionTemplate.update("com.cictec.web.fuel.dao.BusInfoMapper.updateByPrimaryKeySelective",record);
    }

    public int updateByPrimaryKey(BusInfo record)
    {
    	return 0;
    }
    @Override
    public List<BusInfo>selectByParams(ClientQueryByParam daoQuery)
    {
		 List<BusInfo>busLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.BusInfoMapper.selectByParams", daoQuery);
		 return busLi;
    }
    
    @Override
    public List<BusInfo>selectByBusNum(ClientQueryByParam daoQuery)
    {
    	List<BusInfo>busLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.BusInfoMapper.selectByBusNum", daoQuery);
    	return busLi;
    }

	@Override
	public List<String> getBusNumsByStatus(int status) {
		
		 List<String> busNums = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.BusInfoMapper.getBusNumsByStatus", status);
		 return busNums;
	}

	@Override
	public List<BusInfo> selectAllBusByParams(Map record) {
		List<BusInfo>busList = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.BusInfoMapper.selectAllbusByParams", record);
		return busList;
	}
}