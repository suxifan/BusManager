package com.cictec.web.auth.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.dao.IOrgAuthDao;
import com.cictec.web.auth.pojo.OrgTypes;
import com.cictec.web.auth.pojo.Orgs;
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
@Service("orgService")
public class OrgService {

	Logger log = Logger.getLogger(OrgService.class);

	@Autowired
	@Qualifier("orgAuthDao")
	private IOrgAuthDao dao;
	
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
	
	public List<Orgs> getAllOrgInfo() throws Exception
	{
	
		return  dao.getAllOrgInfo();
	}
	public List<Orgs> getAllOrgLine() throws Exception
	{
		
		return  dao.getAllOrgLine();
	}
	public List<OrgTypes> getAllOrgTypes() throws Exception
	{
	
		return  dao.getAllOrgTypes();
	}
	
	
	public boolean saveOrgInfo(Orgs info) throws Exception
	{
		boolean flag = false;
		try {
			info.setOrgId(UUIDGenerator.genUuidStr());
			flag = dao.insertOrgInfo(info);
		} catch (Exception e) {

		}
		return flag;
	}
	
	public boolean updateOrgInfo(Orgs info)
	{
		return dao.updateOrgInfo(info);
	}
	public boolean deleteOrgInfo(String orgId) {
		boolean flag = false;
		log.warn("service deleteOrgInfo.");
		try {
			flag = dao.deleteOrgInfo(orgId);
		} catch (Exception e) {

		}
		return flag;
	}
	
	/**
	 * 根据机构标识得到父机构
	 */
	public Orgs getParentOrgByOrgId(String orgId) {
		Orgs org = null;
		try {
			org = dao.getParentOrgByOrgId(orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return org;
	}
	
	public List<Orgs> getOrgsByOrgType(String orgType) {
		
		return dao.getOrgsByOrgType(orgType);
	}
	
}
