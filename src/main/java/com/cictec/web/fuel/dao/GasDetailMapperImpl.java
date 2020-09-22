package com.cictec.web.fuel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.cictec.web.fuel.model.GasMsg;
import com.cictec.web.fuel.model.GasStationDataForDay;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;

@Repository("gasDetailMapperImpl")
public class GasDetailMapperImpl implements GasDetailMapper {

	@Resource(name = "sqlSessionFuel")
	private SqlSessionTemplate sqlSessionTemplate;

	public int deleteByPrimaryKey(String gasDetailId) {
		return 0;
	}

	public int insert(GasDetail record) {
		return sqlSessionTemplate.insert("com.cictec.web.fuel.dao.GasDetailMapper.insert", record);
	}

	public int insertSelective(GasDetail record) {
		return 0;
	}

	public GasDetail selectByPrimaryKey(String gasDetailId) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		GasDetail userAuthDataList = null;
		{
			parameterMap.put("gasDetailId", gasDetailId);
			userAuthDataList = sqlSessionTemplate
					.selectOne(
							"com.cictec.web.fuel.dao.GasDetailMapper.selectByPrimaryKey",
							parameterMap);
		}
		return userAuthDataList;
	}

	public int updateByPrimaryKeySelective(GasDetail record) {
		return sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasDetailMapper.updateByPrimaryKeySelective",record);
	}

	public int updateByPrimaryKey(GasDetail record) {
		return 0;
	}

	@Override
	public List<FuelMonthReport> getFuelMonthReport(Map<String, String> paraMap) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getFuelMonthReport", paraMap);
	}

	@Override
	public List<FuelMonthReport> getFuelMonthReportBySubGroup(
			Map<String, String> paraMap) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getFuelMonthReportBySubGroup", paraMap);
	}
	
	@Override
	 public List<GasDetail> selectByCondition(ClientQueryByParam daoQuery)
	 {
		 List<GasDetail>gasLi = sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.selectByCondition", daoQuery);
		 return gasLi;
	 }

	@Override
	public List<GasDetail> getMultiFuelAddRecord(Map<String, String> paraMap) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getMultiFuelAddRecord", paraMap);
	}
	@Override
	public void updateModifiedFuelCheckReport(Map<String, String> paraMap)
	{
		sqlSessionTemplate.update("com.cictec.web.fuel.dao.GasDetailMapper.updateModifiedFuelCheckReport", paraMap);
	}

	@Override
	public List<GasDetail> queryByGasUUid(String gasUUid) {
		
		return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.queryByGasUUid",gasUUid);
	}
    @Override
    public List<GasDetail> getGasDetailByGasMsg(GasMsg gasMsg){

        return  sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getGasDetailByGasMsg",gasMsg);
    }

    @Override
    public List<GasStationDataForDay> queryGasStationDatForDay(Map<String,String> paramMap){
        return  sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getStationDataForDay",paramMap);
    }

    @Override
    public List<String> getMonthFuleByBusNum(Map<String, String> paramMap){

        return sqlSessionTemplate.selectList("com.cictec.web.fuel.dao.GasDetailMapper.getMonthFuleByBusNum", paramMap);
    } 
}