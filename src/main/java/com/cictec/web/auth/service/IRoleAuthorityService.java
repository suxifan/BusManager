package com.cictec.web.auth.service;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.Fields;
import com.cictec.web.auth.pojo.Modules;
import com.cictec.web.auth.pojo.Roles;


public interface IRoleAuthorityService {

	boolean addRole(Roles role);
	
	List<Roles> getAllRoles();

	List<Modules> getModulesByRoleId(String roleId);
	List<Fields> getFieldsByRoleId(String roleId);
	List<Roles> getRolesByRoleName(String roleName);

	List<Modules> getAllModules();

	boolean deleteRoleByRoleId(String roleId);

	List<Fields> getFieldsByModuleId(String moduleId);

	boolean updateRole(Roles role);

	boolean updateRoleModule(List<Map<String, Object>> roleModuleList);

	boolean updateRoleField(List<Map<String, Object>> roleFieldList);
}
