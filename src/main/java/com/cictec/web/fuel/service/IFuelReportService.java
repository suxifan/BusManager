package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.ClassFuelStat;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasModified;
import com.cictec.web.fuel.model.NewBusGas;

public interface IFuelReportService {

	List<FuelMonthReport> getFuelMonthReport(Map<String, String> paraMap);

	List<FuelMonthReport> getFuelMonthReportBySubGroup(
			Map<String, String> paraMap);

	public List<GasDetail> getBranchFuelReport(ClientQueryByParam daoQuery) throws Exception;
	
	public List<GasModified> getBranchModifiedFuelReport(ClientQueryByParam daoQuery) throws Exception;
	
	public List<ClassFuelStat> getClassFuelStatReport(ClientQueryByParam daoQuery) throws Exception;

	public List<NewBusGas> getNewBusGasReport(ClientQueryByParam daoQuery) throws Exception;

	
	void updateModifiedFuelCheckReport(Map<String, String> paraMap);
	List<GasDetail> getMultiFuelAddRecord(Map<String, String> paraMap);
}
