package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.dao.ClassFuelStatMapper;
import com.cictec.web.fuel.dao.GasDetailMapper;
import com.cictec.web.fuel.dao.GasModifiedMapper;
import com.cictec.web.fuel.dao.NewBusGasMapper;
import com.cictec.web.fuel.model.ClassFuelStat;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasModified;
import com.cictec.web.fuel.model.NewBusGas;

@Service("fuelReportService")
public class FuelReportService implements IFuelReportService {

	@Autowired
	@Qualifier("gasDetailMapperImpl")
	private GasDetailMapper gasDetailDao;
	
	@Autowired
	@Qualifier("gasModifiedMapperImpl")
	private GasModifiedMapper daoModified;
	
	
	@Autowired
	@Qualifier("classFuelStatMapperImpl")
	private ClassFuelStatMapper classFuelStatMapperImpl;
	
	
	@Autowired
	@Qualifier("newBusGasMapperImpl")
	private NewBusGasMapper newBusGasMapperImpl;
	
	@Override
	public List<FuelMonthReport> getFuelMonthReport(Map<String, String> paraMap) {
		
		return gasDetailDao.getFuelMonthReport(paraMap);
	}

	@Override
	public List<FuelMonthReport> getFuelMonthReportBySubGroup(
			Map<String, String> paraMap) {
		
		return gasDetailDao.getFuelMonthReportBySubGroup(paraMap);
	}
	@Override
	public List<GasDetail> getBranchFuelReport(ClientQueryByParam daoQuery) throws Exception {
		
		List<GasDetail> liGas = gasDetailDao.selectByCondition(daoQuery);
		return liGas;
	}
	@Override
	public List<GasModified> getBranchModifiedFuelReport(ClientQueryByParam daoQuery) throws Exception {
		
		List<GasModified> liGas = daoModified.selectByCondition(daoQuery);
		return liGas;
	}
	@Override
	public void updateModifiedFuelCheckReport(Map<String, String> paraMap)
	{
		daoModified.updateModifiedFuelCheckReport(paraMap);
		if(!StringUtils.isEmpty(paraMap.get("gasDetailId"))){
			gasDetailDao.updateModifiedFuelCheckReport(paraMap);
		}
	}
	@Override
	public List<GasDetail> getMultiFuelAddRecord(Map<String, String> paraMap) {
		
		return gasDetailDao.getMultiFuelAddRecord(paraMap);
	}
	
	@Override
	public List<ClassFuelStat> getClassFuelStatReport(ClientQueryByParam daoQuery) throws Exception
	{
		List<ClassFuelStat> liGas = classFuelStatMapperImpl.selectByCondition(daoQuery);
		return liGas;
	}
	
	
	@Override
	public List<NewBusGas> getNewBusGasReport(ClientQueryByParam daoQuery) throws Exception
	{
		List<NewBusGas> liGas = newBusGasMapperImpl.selectByCondition(daoQuery);
		return liGas;
	}


}
