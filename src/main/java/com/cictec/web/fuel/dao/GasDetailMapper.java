package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasMsg;
import com.cictec.web.fuel.model.GasStationDataForDay;

public interface GasDetailMapper {
	
    public int deleteByPrimaryKey(String gasDetailId);

    public int insert(GasDetail record);

    public int insertSelective(GasDetail record);

    public GasDetail selectByPrimaryKey(String gasDetailId);

    public int updateByPrimaryKeySelective(GasDetail record);

    public int updateByPrimaryKey(GasDetail record);
    
    public List<FuelMonthReport> getFuelMonthReport(Map<String, String> paraMap);

	public List<FuelMonthReport> getFuelMonthReportBySubGroup(
			Map<String, String> paraMap);
    
	public List<GasDetail> selectByCondition(ClientQueryByParam daoQuery);

	public List<GasDetail> getMultiFuelAddRecord(Map<String, String> paraMap);

	public void updateModifiedFuelCheckReport(Map<String, String> paraMap);

	public List<GasDetail> queryByGasUUid(String gasUUid);

    public List<GasDetail> getGasDetailByGasMsg(GasMsg gasMsg);

    List<GasStationDataForDay> queryGasStationDatForDay(Map<String, String> paramMap);

    List<String> getMonthFuleByBusNum(Map<String, String> paramMap);
}