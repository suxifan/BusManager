package com.cictec.web.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.pojo.UserTypes;
import com.cictec.web.auth.service.UserAuthManagementService;


@Repository("userAuthManagementDao")
public class UserAuthManagementDao implements IUserAuthManagementDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSessionTemplate;
	Logger log = Logger.getLogger(UserAuthManagementDao.class);
	public List<UserAuthData> getUserInfo(UserAuthData userInfo, int start,
			int limit) throws Exception {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<UserAuthData> userAuthDataList = null;
		//int nowPageNum = (start - 1) * pagecount;
		//int pageSizeNow = pagecount;
		parameterMap.put("start", start);
		parameterMap.put("limit", limit);
		if(userInfo.getDobRealName()==null || userInfo.getDobRealName().equals("")){
			userAuthDataList = sqlSessionTemplate.selectList("UserAuthManagement.selectPageUserInfoList", parameterMap);
		}else{
			parameterMap.put("userName", userInfo.getDobRealName());
			userAuthDataList = sqlSessionTemplate.selectList("UserAuthManagement.selectPageUserInfoListByUserName", parameterMap);
		}
		return userAuthDataList;
	}

	public int getUserCount() throws Exception {
		int num = 0;
			num = sqlSessionTemplate.selectOne("UserAuthManagement.getUserCount");
			//return sqlSessionTemplate.selectOne("com.cictec.web.fuel.dao.GasRemindMapper.getRemindMsgCount",paramMap);
			return num;
	}
	
	
	public List<String> getRoleIdByUserId(String userId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dobUserId", userId);
		List<String> userAuthDataList = sqlSessionTemplate.selectList(
				"UserAuthManagement.getRoleIdByUserId", map);
		return userAuthDataList;
	}

	public UserAuthData getUserByAccount(String account) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dobAccount", account);
		UserAuthData user = sqlSessionTemplate.selectOne(
				"UserAuthManagement.getUserByAccount", map);
		return user;
	}

	
	public String getUserIdByUuid(String uuid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dobUuid", uuid);
		String userId = sqlSessionTemplate.selectOne(
				"UserAuthManagement.getUserIdByUuid", map);
		return userId;
	}
	
	
	public List<Roles> getRoleByUserId(String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dobUserId", userId);
		List<Roles> roleList = sqlSessionTemplate.selectList(
				"UserAuthManagement.getRoleByUserId", map);
		return roleList;
	}

	/**
	 * 保存用户管理信息
	 * 
	 * @param dailyOperaDriver
	 * @return
	 * @throws Exception
	 */
	public boolean insertUserInfo(UserAuthData userInfo) throws Exception {

		boolean flag = false;
		try {
			sqlSessionTemplate.insert("UserAuthManagement.insertUserInfo",
					userInfo);
			flag = true;
		} catch (Exception e) {
			flag = false;
			log.error(e.toString());
		}
		return flag;
	}

	public boolean insertUserRoleInfo(UserAuthData userInfo) throws Exception {
		boolean flag = false;
		
		
		try {
			if(userInfo.getContainRoleId()!=null && userInfo.getContainRoleId().size() >0){
				for (String roleid : userInfo.getContainRoleId()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("dobUserId", userInfo.getDobUserId());
						map.put("dobRoleId", roleid);
						sqlSessionTemplate.insert(
								"UserAuthManagement.insertUserRoleInfo", map);
				}
			}
			flag = true;
		} catch (Exception e) {
			log.error(e.toString());
			flag = false;
		}
		return flag;
	}

	/**
	 * 修改用户管理信息
	 * 
	 * @param dailyOperaDriver
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(UserAuthData userInfo) throws Exception {
		boolean flag = false;
		try {
			sqlSessionTemplate.update("UserAuthManagement.updateUserInfo",
					userInfo);
			flag = true;
		} catch (Exception e) {
			log.error(e.toString());
			flag = false;
		}
		return flag;
	}

	public boolean deleteUserInfo(String dobUserId) throws Exception {
		boolean flag = false;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dobUserId", dobUserId);
			sqlSessionTemplate.delete("UserAuthManagement.deleteUserInfo", map);
			flag = true;
		} catch (Exception e) {
			log.error(e.toString());
			flag = false;
		}
		return flag;
	}

	public boolean deleteUserRoleInfoByUserId(String userId) throws Exception {
		boolean flag = false;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dobUserId", userId);
			sqlSessionTemplate.delete(
					"UserAuthManagement.deleteUserRoleInfoByUserId", map);
			flag = true;
		} catch (Exception e) {
			log.error(e.toString());
			flag = false;
		}
		return flag;
	}
	
	
	
	public String getUserTypeNameByUserTypeId(String id)throws Exception
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userTypeId", id);
		String userTypeName = sqlSessionTemplate.selectOne(
				"UserAuthManagement.getUserTypeNameByUserTypeId", map);
		return userTypeName;
		
	}
	public List<UserTypes> getAllUserTypes()throws Exception
	{
		List<UserTypes> userTypeList = null;
		userTypeList = sqlSessionTemplate.selectList("UserAuthManagement.getAllUserTypes");
		return userTypeList;
	}
	
	/**
	 * 根据用户类型获取用户信息
	 */
	public List<UserAuthData> getUserByType(Map record) throws Exception{
		List<UserAuthData> userList = sqlSessionTemplate.selectList("UserAuthManagement.getAllUserByType",record);

		return userList;
	}

	
	public UserAuthData getUserByUserId(String dobUuid){
		return sqlSessionTemplate.selectOne("UserAuthManagement.getUserByUserId", dobUuid);
		
		
	}
	
	
	public boolean updateUserPwd(String userId,String newPwd){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dobPassword", newPwd);
		map.put("dobUserId", userId);
		
		return sqlSessionTemplate.update("UserAuthManagement.updateUserPwd", map) ==1?true:false;
		
	}
}
