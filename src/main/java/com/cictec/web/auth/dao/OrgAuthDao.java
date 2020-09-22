package com.cictec.web.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.OrgTypes;
import com.cictec.web.auth.pojo.Orgs;


@Repository("orgAuthDao")
public class OrgAuthDao implements IOrgAuthDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Orgs> getAllOrgInfo() throws Exception{
		
		List<Orgs> orgList = sqlSessionTemplate.selectList(
				"OrgAuthMgmt.selectOrgInfoList");
		return orgList;
		
	}
	@Override
	public List<Orgs> getAllOrgLine() throws Exception{
		
		List<Orgs> orgList = sqlSessionTemplate.selectList(
				"OrgAuthMgmt.selectOrgLineList");
		return orgList;
		
	}
	public boolean insertOrgInfo(Orgs info) throws Exception{
		boolean flag = false;
		try {
			sqlSessionTemplate.insert("OrgAuthMgmt.insertOrgInfo",
					info);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public boolean updateOrgInfo(Orgs info) {
		boolean flag = false;
		try {
			sqlSessionTemplate.update("OrgAuthMgmt.updateOrgInfo", info);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	
	public boolean deleteOrgInfo(String orgId) throws Exception {
		boolean flag = false;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);
			sqlSessionTemplate.delete("OrgAuthMgmt.deleteOrgInfo", map);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	@Override
	public List<OrgTypes>  getAllOrgTypes() throws Exception
	{
		List<OrgTypes> orgTypeList = null;
		try{
			orgTypeList = sqlSessionTemplate.selectList("OrgAuthMgmt.getAllOrgTypes");
		}catch (Exception e) {
		}
		return orgTypeList;
	}
	
	/**
	 * 根据机构标识得到父机构
	 */
	@Override
	public Orgs getParentOrgByOrgId(String orgId) throws Exception {
		Orgs org = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);
			org = sqlSessionTemplate.selectOne("OrgAuthMgmt.getParentOrgByOrgId",orgId);
		}catch (Exception e) {
			
		}
		
		return org;
	}
	@Override
	public List<Orgs> getOrgsByOrgType(String orgType) {
		
		return sqlSessionTemplate.selectList("OrgAuthMgmt.getOrgsByOrgType", orgType);
	}

    @Override
    public Orgs selectByPrimaryKey(String orgId) {
        Map map = new HashMap();
        map.put("orgId",orgId);
        return sqlSessionTemplate.selectOne("OrgAuthMgmt.selectByPrimaryKey", map);

    }

}
