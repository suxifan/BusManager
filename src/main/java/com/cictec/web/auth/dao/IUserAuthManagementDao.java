package com.cictec.web.auth.dao;

import java.util.List;
import java.util.Map;

import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.pojo.UserTypes;

public interface IUserAuthManagementDao {
	public List<UserAuthData> getUserInfo(UserAuthData userInfo, int start,
			int pagecount) throws Exception;

	public List<String> getRoleIdByUserId(String userId) throws Exception;

	public String getUserIdByUuid(String uuid) throws Exception;

	public List<Roles> getRoleByUserId(String userId) throws Exception;

	/**
	 * 保存用户管理信息
	 * 
	 * @param dailyOperaDriver
	 * @return
	 * @throws Exception
	 */
	public boolean insertUserInfo(UserAuthData userInfo) throws Exception;

	public boolean insertUserRoleInfo(UserAuthData userInfo) throws Exception;

	/**
	 * 修改用户管理信息
	 * 
	 * @param dailyOperaDriver
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(UserAuthData userInfo) throws Exception;

	public boolean deleteUserInfo(String dobUuid) throws Exception;

	public boolean deleteUserRoleInfoByUserId(String userId) throws Exception;

	public UserAuthData getUserByAccount(String account) throws Exception;

	public String getUserTypeNameByUserTypeId(String id) throws Exception;

	public List<UserTypes> getAllUserTypes() throws Exception;
	/**
	 * 根据用户类型获取用户信息
	 */
	public List<UserAuthData> getUserByType(Map record) throws Exception;
	
	public UserAuthData getUserByUserId(String dobUuid);
	/**
	 * 修改密码方法
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	public boolean updateUserPwd(String userId,String newPwd);
	
	public int getUserCount() throws Exception;
}
