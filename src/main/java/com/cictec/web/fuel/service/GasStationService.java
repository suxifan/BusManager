package com.cictec.web.fuel.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.fuel.dao.GasGunMapper;
import com.cictec.web.fuel.dao.GasStationPrincipalMapper;
import com.cictec.web.fuel.model.GasGun;
import com.cictec.web.fuel.model.GasStationPrincipal;

@Service("gasStationService")
public class GasStationService implements IGasStationService {

	@Autowired
	@Qualifier("gasStationPrincipalMapperImpl")
	private GasStationPrincipalMapper gasStationPrincipalDao;
	
	@Autowired
	@Qualifier("gasGunMapperImpl")
	private GasGunMapper gasGunDao;
	
	@Override
	public List<GasStationPrincipal> getAllGasStationInfo() {
		
		return gasStationPrincipalDao.getGasStationByParams(new GasStationPrincipal());
	}

	@Override
	public List<GasStationPrincipal> getGasStationInfoByName(
			GasStationPrincipal station) {
		
		return gasStationPrincipalDao.getGasStationByParams(station);
	}

	@Override
	public boolean deleteGasStationInfoById(String id) {
		
		return gasStationPrincipalDao.deleteGasStationInfoById(id);
	}

	@Override
	public boolean addGasStationInfo(GasStationPrincipal gasStationInfo) {
		
		return gasStationPrincipalDao.addGasStationInfo(gasStationInfo);
	}

	@Override
	public boolean updateGasStationInfo(GasStationPrincipal gasStationInfo) {
		
		return gasStationPrincipalDao.updateByPrimaryKeySelective(gasStationInfo);
	}

	@Override
	public List<GasGun> selectAllGasGun(Map record) {
		
		return gasGunDao.selectAllGasGun(record);
	}

	@Override
	public boolean addGasStationInfo(GasGun gasGunInfo) {
		
		return gasGunDao.insertSelective(gasGunInfo);
	}

	@Override
	public boolean deleteGasGunInfoById(String id) {
		
		return gasGunDao.deleteByPrimaryKey(id);
	}

	@Override
	public boolean modifyGasGunInfo(GasGun gasGunInfo) {
		
		return gasGunDao.updateByPrimaryKeySelective(gasGunInfo);
	}

	@Override
	public int selectTotalRecord() {
		
		return gasGunDao.selectTotalRecord();
	}

    @Override
    public List<GasStationPrincipal> getAllStationMsg() {
        return gasStationPrincipalDao.getAllGasStation();
    }

}
