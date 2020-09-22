package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.model.BusInfo;

public interface IBusInfoService {
	
	public List<BusInfo> getBusInfo(ClientQueryByParam daoQuery) throws Exception;
	
	public List<String> getBusNumsByStatus(int status);
	
	public List<BusInfo> getAllBusInfo(Map param) throws Exception;

    public boolean saveBusInfo(BusInfo busInfo);

    public boolean updateBusInfo(BusInfo businfo);

    public boolean deleteBusInfo(String busInfoId);

}
