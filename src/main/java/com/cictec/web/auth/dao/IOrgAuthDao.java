package com.cictec.web.auth.dao;

import java.util.List;

import com.cictec.web.auth.pojo.OrgTypes;
import com.cictec.web.auth.pojo.Orgs;


public interface IOrgAuthDao {
	//public List<TreeNode> getAllOrgs() throws Exception;
	public List<Orgs> getAllOrgInfo() throws Exception;
	public List<Orgs> getAllOrgLine() throws Exception;
	public boolean insertOrgInfo(Orgs info) throws Exception;
	public boolean updateOrgInfo(Orgs info);
	public boolean deleteOrgInfo(String orgId) throws Exception;
	public List<OrgTypes> getAllOrgTypes() throws Exception;
	/**
	 * 根据机构标识得到父机构
	 */
	public Orgs getParentOrgByOrgId(String orgId) throws Exception;
	
	List<Orgs> getOrgsByOrgType(String orgType);


    Orgs selectByPrimaryKey(String orgId);
}
