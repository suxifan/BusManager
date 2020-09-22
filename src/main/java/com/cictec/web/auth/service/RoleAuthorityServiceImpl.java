package com.cictec.web.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cictec.web.auth.dao.IRoleAuthorityDao;
import com.cictec.web.auth.pojo.Fields;
import com.cictec.web.auth.pojo.Modules;
import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.util.UUIDGenerator;


/**
 * 角色管理service
 * @author hehui
 *
 */
@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements IRoleAuthorityService {

	@Autowired
	@Qualifier("roleAuthorityDao")
	private IRoleAuthorityDao dao;

	@Override
	public boolean addRole(Roles role) {
		role.setRoleId(UUIDGenerator.genUuidStr());
		return dao.addRole(role);
	}

	@Override
	public List<Roles> getAllRoles() {
		
		return dao.getAllRoles();
	}

	@Override
	public List<Modules> getModulesByRoleId(String roleId) {
		
		return dao.getModulesByRoleId(roleId);
	}

	@Override
	public List<Fields> getFieldsByRoleId(String roleId) {
		
		return dao.getFieldsByRoleId(roleId);
	}
	@Override
	public List<Roles> getRolesByRoleName(String roleName) {

		return dao.getRolesByRoleName(roleName);
	}

	@Override
	public List<Modules> getAllModules() {
		
		return dao.getAllModules();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class })
	@Override
	public boolean deleteRoleByRoleId(String roleId) {

		return dao.deleteRoleByRoleId(roleId);
	}

	@Override
	public List<Fields> getFieldsByModuleId(String moduleId) {
		
		return dao.getFieldsByModuleId(moduleId);
	}

	@Override
	public boolean updateRole(Roles role) {
		
		return dao.updateRole(role);
	}

	@Override
	public boolean updateRoleModule(List<Map<String, Object>> roleModuleList) {

		return dao.updateRoleModule(roleModuleList);
	}

	@Override
	public boolean updateRoleField(List<Map<String, Object>> roleFieldList) {

		return dao.updateRoleField(roleFieldList);
	}

	
}
