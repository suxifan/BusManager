package com.cictec.web.auth.pojo;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * Class comment here.
 *
 * @Project GJDD-DTD
 * @author lhl(lhl@cictec.cn)
 * @since 2014-5-12
 * @version 1.0
 * @change_log 
 *    	<pre>
 *			[2014-5-12 by xxx] Initialize class.
 *		</pre>
 */
public class UserAuthData extends BaseEntity{
	
	
/*
        	         { name: 'dobUuid', type: 'string' },
       	         { name: 'dobUserId', type: 'string' },
       	         { name: 'dobAccount', type: 'string' },
       	         { name: 'dobRealName', type: 'string' },
       	         { name: 'dobSex', type: 'int'},
       			 { name: 'dobEmail', type: 'string' },
       			 { name: 'dobMobile', type: 'string'},
       			 { name: 'dobOfficePhone', type: 'string' },
       			 { name: 'dobIdCard', type: 'string'},
       			 { name: 'dobErrorCount', type: 'int' },
       			 { name: 'dobLastLoginTime', type: 'date' },
       			 { name: 'dobLastLoginIp', type: 'string'},
       			 { name: 'dobRemark', type: 'string'}
 * 
 * 
 * */

	private static final long serialVersionUID = 2566492179293776848L;

	Logger log = Logger.getLogger(UserAuthData.class);

	//private static final long serialVersionUID = 6202749809626097742L;
	//private String dobUuid;//主键ID
	private String dobUserId;//用户ID
	private String dobAccount;//
	private String dobPassword;//
	private String dobRealName;//真实姓名
	private int dobSex;//性别
	private String dobEmail;//电子邮箱
	private String dobMobile;//手机号码
	private String dobOfficePhone;//办公室号码
	private String dobIdCard;//身份证号码
	private int dobErrorCount;//错误登录次数
	private Date dobLastLoginTime;//上次登录时间
	private String dobLastLoginIp;//上次登录IP
	private String dobRemark;//备注
	private String dobLastLoginTimeStr;
	private String employeeNum;//员工编号
	private String cardNum;//物理卡号
	private String userTypeId;//用户类型
	private String userTypeName;
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	private boolean isEnabled;
	
	//private String 
	
	public boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	private List<Roles> liHasRoles;
	private String roleString;
	
	private List<String> containRoleId;
	
	private String orgId;
	private String orgName;
	
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<String>getContainRoleId() {
		return containRoleId;
	}
	public void setContainRoleId(List<String> containRoleId ) {
		this.containRoleId = containRoleId;
	}
	public String getRoleString() {
		return roleString;
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	public List<Roles> getLiHasRoles() {
		return liHasRoles;
	}
	public void setLiHasRoles(List<Roles> liHasRoles) {
		String str = null;
		this.liHasRoles = liHasRoles;
		//log.warn("role size:"+liHasRoles.size());
		if(liHasRoles != null && liHasRoles.size() > 0){
			for(Roles role:liHasRoles){
				str += role.getRoleName();
				str += ",";	
			}
			str = str.substring(4,str.length()-1);
			setRoleString(str);
			
		}
		
	}
	public String getDobLastLoginTimeStr() {
        return dobLastLoginTimeStr;
    }
    public void setDobLastLoginTimeStr(String dobLastLoginTimeStr) {
        this.dobLastLoginTimeStr = dobLastLoginTimeStr;
    }
	
	public String getDobPassword() {
        return dobPassword;
    }
    public void setDobPassword(String dobPassword) {
        this.dobPassword = dobPassword;
    }
/*    public String getDobUuid() {
        return dobUuid;
    }
    public void setDobUuid(String dobUuid) {
        this.dobUuid = dobUuid;
    }*/
    public String getDobUserId() {
        return dobUserId;
    }
    public void setDobUserId(String dobUserId) {
        this.dobUserId = dobUserId;
    }
    public String getDobAccount() {
        return dobAccount;
    }
    public void setDobAccount(String dobAccount) {
        this.dobAccount = dobAccount;
    }
    public String getDobRealName() {
        return dobRealName;
    }
    public void setDobRealName(String dobRealName) {
        this.dobRealName = dobRealName;
    }
    public int getDobSex() {
        return dobSex;
    }
    public void setDobSex(int dobSex) {
        this.dobSex = dobSex;
    }
    
	public String getDobEmail() {
		return dobEmail;
	}
	public void setDobEmail(String dobEmail) {
		this.dobEmail = dobEmail;
	}
	public String getDobMobile() {
		return dobMobile;
	}
	public void setDobMobile(String dobMobile) {
		this.dobMobile = dobMobile;
	}
	public String getDobOfficePhone() {
		return dobOfficePhone;
	}
	public void setDobOfficePhone(String dobOfficePhone) {
		this.dobOfficePhone = dobOfficePhone;
	}
	public String getDobIdCard() {
		return dobIdCard;
	}
	public void setDobIdCard(String dobIdCard) {
		this.dobIdCard = dobIdCard;
	}
	public int getDobErrorCount() {
		return dobErrorCount;
	}
	public void setDobErrorCount(int dobErrorCount) {
		this.dobErrorCount = dobErrorCount;
	}
	public Date getDobLastLoginTime() {
		return dobLastLoginTime;
	}
	public void setDobLastLoginTime(Date dobLastLoginTime) {
		this.dobLastLoginTime = dobLastLoginTime;
	}
	public String getDobLastLoginIp() {
		return dobLastLoginIp;
	}
	public void setDobLastLoginIp(String dobLastLoginIp) {
		this.dobLastLoginIp = dobLastLoginIp;
	}
	public String getDobRemark() {
		return dobRemark;
	}
	public void setDobRemark(String dobRemark) {
		this.dobRemark = dobRemark;
	}

	
}
