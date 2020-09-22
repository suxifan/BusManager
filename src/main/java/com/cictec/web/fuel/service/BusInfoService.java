package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.fuel.dao.BusInfoMapper;
import com.cictec.web.fuel.model.BusInfo;

@Service("busInfoService")
public class BusInfoService implements IBusInfoService {

	@Autowired
	@Qualifier("busInfoMapperImpl")
	private BusInfoMapper busInfoDao;
	
	@Override
	public List<String> getBusNumsByStatus(int status) {
		
		return busInfoDao.getBusNumsByStatus(status);
	}
	
	@Override
	public List<BusInfo> getBusInfo(ClientQueryByParam daoQuery) throws Exception {
		
		List<BusInfo> liBus = busInfoDao.selectByParams(daoQuery);
		return liBus;
	}
	
	@Override
	public List<BusInfo> getAllBusInfo(Map param) throws Exception {		
		List<BusInfo> liBus = busInfoDao.selectAllBusByParams(param);
		return liBus;
	}

    @Override
    public boolean saveBusInfo(BusInfo busInfo) {
        return busInfoDao.insertSelective(busInfo)==0?false:true;
    }

    @Override
    public boolean updateBusInfo(BusInfo businfo) {
        return busInfoDao.updateByPrimaryKeySelective(businfo)==0?false:true;
    }

    @Override
    public boolean deleteBusInfo(String busInfoId) {
        return busInfoDao.deleteByPrimaryKey(busInfoId)==0?false:true;
    }


}
