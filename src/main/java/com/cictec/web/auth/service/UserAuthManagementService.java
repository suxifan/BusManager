package com.cictec.web.auth.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.dao.IUserAuthManagementDao;
import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.pojo.UserTypes;
import com.cictec.web.auth.util.CommonUtil;
import com.cictec.web.auth.util.UUIDGenerator;


/**
 * 发车牌业务处理类（主表）
 * 
 * @Project GJDD-SRV
 * @author luyk(luyukai@cictec.cn)
 * @since 2015-3-2
 * @version 1.0
 * @change_log
 */
@Service("userAuthManagementService")
public class UserAuthManagementService {

	Logger log = Logger.getLogger(UserAuthManagementService.class);

	@Autowired
	@Qualifier("userAuthManagementDao")
	private IUserAuthManagementDao dao;
	
	/**
	 * 获取列表信息
	 * 
	 * @param dailyOperaBusN
	 * @param start
	 * @param pagecount
	 * @return
	 * @throws Exception
	 */
	public List<UserAuthData> getUserInfo(UserAuthData userAuthData, int start,
			int pagecount) throws Exception {

		return dao.getUserInfo(userAuthData, start,
				pagecount);

		// return null;
	}

	public String getUserIdByUuid(String uuid) throws Exception {

		return dao.getUserIdByUuid(uuid);
		// return null;
	}

	public List<String> getRoleIdByUserId(String userId) throws Exception {

		return dao.getRoleIdByUserId(userId);
		// return null;
	}

	public List<Roles> getRoleByUserId(String userId) throws Exception {

		return dao.getRoleByUserId(userId);
		// return null;
	}
	
	
	public List<UserTypes> getAllUserTypes() throws Exception {

		return dao.getAllUserTypes();
		// return null;
	}

	public String  getUserTypeNameByUserTypeId(String id) throws Exception {

		return dao.getUserTypeNameByUserTypeId(id);
		// return null;
	}
	
	public boolean saveUserInfo(UserAuthData userAuthData) throws Exception {

		boolean flag = false;
		log.debug("service saveUserinfo.");
		
		try {
			
			//String hexS = Integer.toHexString( Integer.parseInt(userAuthData.getCardNum()) );		
			//log.warn("ref:"+userAuthData.getCardNum()+" ins:"+hexS +" ret:"+ret+".......");
			//userAuthData.setDobUuid(UUIDGenerator.genUuidStr());
			userAuthData.setDobUserId(UUIDGenerator.genUuidStr());
			userAuthData.setDobPassword(CommonUtil.getEncryptPwd(userAuthData.getDobPassword()));
			flag = dao.insertUserInfo(userAuthData);
			flag = dao.insertUserRoleInfo(
					userAuthData);
		} catch (Exception e) {
			this.log.error(e.toString());
		}
		return flag;
	}

	public boolean updateUserInfo(UserAuthData userAuthData) throws Exception {

		boolean flag = false;
		log.warn("service updateUserInfo.");
		try {
			UserAuthData oldUser = dao.getUserByAccount(userAuthData.getDobAccount());
			
			//提交密码和原加密密码相同，则用户未进行改密码操作，否则，密码修改，执行重新加密
			if(oldUser.getDobPassword().equals(userAuthData.getDobPassword())){
				userAuthData.setDobPassword(null);
			}else{
				userAuthData.setDobPassword(CommonUtil.getEncryptPwd(userAuthData.getDobPassword()));
			}
			flag = dao.updateUserInfo(userAuthData);
			flag = dao.deleteUserRoleInfoByUserId(
					userAuthData.getDobUserId());
			flag = dao.insertUserRoleInfo(
					userAuthData);
		} catch (Exception e) {

		}
		return flag;
		// return null;
	}

	/***
	 * 删除用户数据
	 * 
	 * @param dod
	 * @return
	 */
	public boolean deleteUserInfo(String userId) {
		boolean flag = false;
		log.warn("service deleteUserInfo.");
		//String userId = null;
		try {
			//userId = dao.getUserIdByUuid(dobUuid);
			flag = dao.deleteUserInfo(userId);
			// flag =
			// dao.deleteUserRoleInfo(dobUuid);
			dao.deleteUserRoleInfoByUserId(userId);

		} catch (Exception e) {
			log.error(e.toString());
		}
		return flag;
	}

	/**
	 * 根据用户类型获取用户信息
	 * @param record
	 * @return 用户列表
	 */
	public List<UserAuthData> getUserByType(Map map) throws Exception {

		return dao.getUserByType(map);

	}
	
	
	public UserAuthData getUserByUserId(String userId){
		return dao.getUserByUserId(userId);
	}
	
	/**
	 * 修改密码方法
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	public boolean updateUserPwd(String userId,String newPwd){
		return dao.updateUserPwd(userId, newPwd);
	}
	
	public int getUserCount() throws Exception{
		return dao.getUserCount();
	}
}
