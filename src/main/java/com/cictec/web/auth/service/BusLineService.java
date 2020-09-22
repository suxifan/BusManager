package com.cictec.web.auth.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.dao.BusLineMapper;
import com.cictec.web.auth.pojo.BusLine;
import com.cictec.web.auth.util.UUIDGenerator;


/**
 * 组织机构服务类
 * 
 * @Project BusManager
 * @author kxw(kongxiangwen@cictec.cn)
 * @since 2015-6-19
 * @version 1.0
 * @change_log
 */
@Service("servBusLineService")
public class BusLineService {

	Logger log = Logger.getLogger(BusLineService.class);

	@Resource(name="repoBusLineDao")
	private BusLineMapper dao;
	
	/**
	 * 获取列表信息
	 * 
	 * @param dailyOperaBusN
	 * @param start
	 * @param pagecount
	 * @return
	 * @throws Exception
	 */
	/*public List<TreeNode> getAllOrgs() throws Exception
	{
	
		return  dao.getAllOrgs();
	}*/
	
	public List<BusLine> getAllLines()
	{
	
		return  dao.selectByPrimaryKey(null);
	}
	
	public boolean saveBusLine(BusLine busLine) throws Exception {

		boolean flag = false;
		log.debug("service saveBusLine.");
		try {
			//userAuthData.setDobUuid(UUIDGenerator.genUuidStr());
			busLine.setCreated(new Date());
			busLine.setLineId(UUIDGenerator.genUuidStr());
			//userAuthData.setDobPassword(CommonUtil.getEncryptPwd(userAuthData.getDobPassword()));
			dao.insertSelective(busLine);
			flag = true;
		} catch (Exception e) {

		}
		return flag;
	}

	public boolean updateBusLine(BusLine busLine) throws Exception {

		boolean flag = false;
		log.debug("service saveBusLine.");
		try {
			busLine.setCreated(new Date());
			dao.updateByPrimaryKey(busLine);
			flag = true;
		} catch (Exception e) {

		}
		return flag;
		// return null;
	}
	
	public boolean deleteBusLine(String lineId) {
		boolean flag = false;
		log.debug("service deleteBusLine.");
		try {
			dao.deleteByPrimaryKey(lineId);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}
	
	public List<BusLine> queryBusLineByKeywords(String key){
		log.debug("service queryBusLineByKeywords:" + key);
		return  dao.queryBusLineByKeywords(key);
	}
	
}
