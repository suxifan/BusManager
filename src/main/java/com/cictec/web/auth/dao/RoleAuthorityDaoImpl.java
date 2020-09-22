package com.cictec.web.auth.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cictec.web.auth.pojo.Fields;
import com.cictec.web.auth.pojo.Modules;
import com.cictec.web.auth.pojo.Roles;


/**
 * 角色管理dao
 * @author hehui
 *
 */
@Repository("roleAuthorityDao")
public class RoleAuthorityDaoImpl implements IRoleAuthorityDao {

	private static Logger LOG = Logger.getLogger(RoleAuthorityDaoImpl.class);
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public boolean addRole(Roles role) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.insert("RoleAuthorityMapper.insertRole", role);
			result = true;
		} catch (Exception e) {
			LOG.error("新增角色失败：" + e.toString());
		}
		return result;
	}

	@Override
	public List<Roles> getAllRoles() {
		
		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getAllRoles");
	}

	@Override
	public List<Modules> getModulesByRoleId(String roleId) {
		
		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getModulesByRoleId", roleId);
	}
	public List<Fields> getFieldsByRoleId(String roleId){
		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getFieldsByRoleId", roleId);

	}
	@Override
	public List<Roles> getRolesByRoleName(String roleName) {

		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getRolesByRoleName", roleName);
	}

	@Override
	public List<Modules> getAllModules() {
		
		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getAllModules");
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public boolean deleteRoleByRoleId(String roleId) {

		boolean result = false;
		try {
			sqlSessionTemplate.delete("RoleAuthorityMapper.deleteRoleByRoleId", roleId);
			sqlSessionTemplate.delete("RoleAuthorityMapper.deleteRoleModuleByRoleId", roleId);
			sqlSessionTemplate.delete("RoleAuthorityMapper.deleteRoleFieldByRoleId", roleId);
			result = true;
		} catch (Exception e) {
			LOG.error("删除角色失败：" + e.toString());
		}
		return result;
	}

	@Override
	public List<Fields> getFieldsByModuleId(String moduleId) {
		
		return sqlSessionTemplate.selectList("RoleAuthorityMapper.getFieldsByModuleId", moduleId);
	}

	@Override
	public boolean updateRole(Roles role) {
		
		boolean result = false;
		try {
			sqlSessionTemplate.update("RoleAuthorityMapper.updateRole", role);
			result = true;
		} catch (Exception e) {
			LOG.error("更新角色表失败：" + e.toString());
		}
		return result;
	}

	@Override
	public boolean updateRoleModule(List<Map<String, Object>> roleModuleList) {
		
		boolean result = false;
		try {
			if(roleModuleList.size() > 0) {
				
				sqlSessionTemplate.delete("RoleAuthorityMapper.deleteRoleModuleByRoleId", 
						roleModuleList.get(0).get("roleId"));
				for (Map<String, Object> map : roleModuleList) {
					sqlSessionTemplate.insert("RoleAuthorityMapper.insertRoleModule", map);
				}
			}
			result = true;
		} catch (Exception e) {
			LOG.error("更新角色模块表失败：" + e.toString());
		}
		return result;
	}

	@Override
	public boolean updateRoleField(List<Map<String, Object>> roleFieldList) {
		
		boolean result = false;
		try {
			if(roleFieldList.size() > 0) {
				
				sqlSessionTemplate.delete("RoleAuthorityMapper.deleteRoleFieldByRoleId", 
						roleFieldList.get(0).get("roleId"));
				for (Map<String, Object> map : roleFieldList) {
					sqlSessionTemplate.insert("RoleAuthorityMapper.insertRoleField", map);
				}
			}
			result = true;
		} catch (Exception e) {
			LOG.error("更新角色功能表失败：" + e.toString());
		}
		return result;
	}

}
