package com.cictec.web.fuel.dao;

import java.util.List;
import java.util.Map;
import com.cictec.web.fuel.model.GasGun;

public interface GasGunMapper {
	boolean deleteByPrimaryKey(String gasGunlId);

    int insert(GasGun record);

    boolean insertSelective(GasGun record);

    GasGun selectByPrimaryKey(Integer gasGunlId);

    boolean updateByPrimaryKeySelective(GasGun record);

    int updateByPrimaryKey(GasGun record);
    
    List<GasGun>selectAllGasGun(Map record);
    
    int selectTotalRecord();

}