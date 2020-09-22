package com.cictec.web.auth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.dao.IUserAuthManagementDao;
import com.cictec.web.auth.pojo.UserAuthData;


/**
 * 登录服务类（主表）
 * 
 * @Project BusManager
 * @author kxw(kongxiangwen@cictec.cn)
 * @since 2015-6-19
 * @version 1.0
 * @change_log
 */
@Service("loginService")
public class LoginService {

	Logger log = Logger.getLogger(LoginService.class);

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
	public UserAuthData getUserByAccount(String account) throws Exception
	{
	
		return  dao.getUserByAccount(account);
	}

}
