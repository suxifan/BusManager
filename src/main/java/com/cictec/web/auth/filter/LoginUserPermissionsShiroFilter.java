package com.cictec.web.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class LoginUserPermissionsShiroFilter extends AuthorizingRealm  {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {

		
		System.out.print("拦截开始");
		
		UsernamePasswordToken currUser = (UsernamePasswordToken)arg0;
		String userAccount = currUser.getUsername();
		String pwd = String.valueOf(currUser.getPassword());
		
		//角色权限代码，如有需要可以添加
		
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userAccount,pwd,"");

		return authcInfo;
	}
	
	
}
