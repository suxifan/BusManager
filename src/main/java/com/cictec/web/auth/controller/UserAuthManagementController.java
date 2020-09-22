package com.cictec.web.auth.controller;
 
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cictec.web.auth.pojo.ClientQuery;
import com.cictec.web.auth.pojo.ClientQueryByUserName;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.pojo.UserTypes;
import com.cictec.web.auth.service.LoginService;
import com.cictec.web.auth.service.UserAuthManagementService;
import com.cictec.web.auth.util.CommonUtil;
import com.cictec.web.fuel.util.DBLogger;
import com.cictec.web.fuel.util.ToolsUtil;



@Controller
@RequestMapping(value="/AuthManagement") 
public class UserAuthManagementController{

	//private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(UserAuthManagementController.class);
	
	@Resource(name="loginService")
	public LoginService loginService;
	
  	@Resource(name="userAuthManagementService")
    public UserAuthManagementService userAuthManagementService;

    public UserAuthManagementService getUserAuthManagementService() {
        return userAuthManagementService;
    }
	
	private UserAuthData UserAuthData;


	public UserAuthData getUserAuthData() {
        return UserAuthData;
    }


    public void setUserAuthData(UserAuthData userAuthData) {
    	UserAuthData = userAuthData;
    }


    /**
	 * 跳转到列表页面	
	 **/
    @RequestMapping(value="/showError")
	public String showError(){ 
		return "/helloext/error";
	}
	
    
    @RequestMapping(value="/resetPwd")
    public @ResponseBody boolean resetPwd(HttpServletRequest request){
    	boolean isSuccess  = false;
    	try{
	    	String userId = request.getParameter("userId");
	    	String resetPwd = request.getParameter("resetPwd");
	    	
	    	UserAuthData resetUser = userAuthManagementService.getUserByUserId(userId);
	    	
	    	//判断当前登录用户是否系统管理
	    	String loginUserId = request.getSession().getAttribute("userId").toString();
	    	UserAuthData user = userAuthManagementService.getUserByUserId(loginUserId);
	    	if(user.getDobAccount().equals("admin")){
	    		
	    		//修改用户密码为新密码
	    		String newPwdMd5 = CommonUtil.getEncryptPwd(resetPwd);
	    		
	    		this.userAuthManagementService.updateUserPwd(resetUser.getDobUserId(),newPwdMd5);
	    		
	    		isSuccess = true;
	    	}
    	}catch(Exception e){
    		isSuccess = false;
    	}
    	
    	return isSuccess;
    }
    
    /**
	 * 修改用户密码
	 **/
    @RequestMapping(value="/changePwd")
	public @ResponseBody boolean changePwd(HttpServletRequest request){ 
    	
    	boolean isSuccess = false ;
    	
    	String oldPwd = request.getParameter("oldPwd");
    	String newPwd = request.getParameter("newPwd");
    	String userId = request.getSession().getAttribute("userId").toString();
    	//String enPwd = CommonUtil.getEncryptPwd(pwd);
    	DBLogger.log("修改用户密码，ID:"+userId);
    	UserAuthData user;
		try {
			
			
			user = userAuthManagementService.getUserByUserId(userId);
		
			
    	//密码正确
    	if(user.getDobPassword().equals(CommonUtil.getEncryptPwd(oldPwd))){
    		isSuccess = true;
    		//修改用户密码为新密码
    		String newPwdMd5 = CommonUtil.getEncryptPwd(newPwd);
    		
    		this.userAuthManagementService.updateUserPwd(user.getDobUserId(),newPwdMd5);
    		
    		
    	}
    	//密码错误
    	else{
    		isSuccess = false;
    	}
    	
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
    	
		return isSuccess;
	}
	
    
    
    @RequestMapping(value="/getUserInfo")
    public @ResponseBody PagingResult<UserAuthData> getUserInfo(@RequestBody ClientQuery cq) throws Exception{
    	UserAuthData userInfot = new UserAuthData();
 		int page = cq.getPage();	//3
		int limit = cq.getLimit();	//25
		int start = cq.getStart();	//0
         log.warn("controleer getUserInfo:"+cq.getStart()+":"+cq.getLimit()+":"+cq.getPage());
        List<UserAuthData> userInfolist = null;// = new List<DailyOperaBus>();
         PagingResult<UserAuthData> pr = new PagingResult<UserAuthData>();
         
         try{
        	 userInfolist = (List<UserAuthData>)this.getUserAuthManagementService().getUserInfo(userInfot,start,limit);
        	 
         } catch (Exception e){
             this.log.error(e.toString());
         }
         
         List<Roles> roleList;
         List<String> roleIdList;  
         try{
        	 /*for(UserAuthData user:userInfolist){
	        	 roleList = (List<Roles>)this.getUserAuthManagementService().getRoleByUserId(user.getDobUserId());
	        	 for(Roles role:roleList)
	        	 {
	        		 log.warn("warn get role list:"+user.getDobUserId()+":"+role.getRoleName());
	        	 }
	        	 user.setLiHasRoles(roleList);
        		 roleIdList = (List<String>)this.getUserAuthManagementService().getRoleIdByUserId(user.getDobUserId());
	        	 user.setContainRoleId(roleIdList);
	        	 String ret = ToolsUtil.switchDecimalEndian(user.getCardNum());
	        	 user.setCardNum(ret);
        	 } */
        	 
        	 Iterator<UserAuthData> iter = userInfolist.iterator(); 
        	 while (iter.hasNext()) {    
            	 UserAuthData user = iter.next();  
	        	 roleList = (List<Roles>)this.getUserAuthManagementService().getRoleByUserId(user.getDobUserId());
	        	 for(Roles role:roleList)
	        	 {
	        		 log.warn("warn get role list:"+user.getDobUserId()+":"+role.getRoleName());
	        	 }
	        	 user.setLiHasRoles(roleList);
        		 roleIdList = (List<String>)this.getUserAuthManagementService().getRoleIdByUserId(user.getDobUserId());
	        	 user.setContainRoleId(roleIdList);
	        	 String ret = ToolsUtil.switchDecimalEndian(user.getCardNum());
	        	 user.setCardNum(ret);
	        	 if(user.getDobAccount().equals("admin")){
	        		 iter.remove();
	        	 }
        	 }
        	 
         } catch (Exception e){
             this.log.error(e.toString());
         }
 		//pr.setTotalCount(userInfolist.size());
 		pr.setTotalCount(this.getUserAuthManagementService().getUserCount());
 		
 		
 		if(userInfolist!=null && userInfolist.size() > 0){

			//int retStart = 0, retFin = 0;
			//retFin = page*limit > userInfolist.size() ? userInfolist.size():page*limit;
			//retStart = (page-1)*limit > userInfolist.size()-1 ? userInfolist.size()-1:(page-1)*limit;
			//List<UserAuthData> n_list = userInfolist.subList(retStart, retFin);
			
			
			
			pr.setData(userInfolist);

 		}
         return pr;
 	}

    
    
    @RequestMapping(value="/getUserInfoByUserName")
    public @ResponseBody PagingResult<UserAuthData> getUserInfoByUserName(@RequestBody ClientQueryByUserName cq){
    	UserAuthData userInfo = new UserAuthData();
         log.debug("controleer getUserInfoByUserName:"+cq.getUserName());
        List<UserAuthData> userInfolist = null;// = new List<DailyOperaBus>();
         PagingResult<UserAuthData> pr = new PagingResult<UserAuthData>();
         try{
        	 String userName = URLDecoder.decode(cq.getUserName(), "UTF-8");
        	 userInfo.setDobRealName(userName);   
        	 userInfolist = (List<UserAuthData>)this.getUserAuthManagementService().getUserInfo(userInfo,0,25);
        	 
         } catch (Exception e){
             this.log.error(e.toString());
         }
         
         List<Roles> roleList;
         List<String> roleIdList;
         
         try{
        	 for(UserAuthData user:userInfolist){
	        	 roleList = (List<Roles>)this.getUserAuthManagementService().getRoleByUserId(user.getDobUserId());
	        	 for(Roles role:roleList)
	        	 {
	        		 log.warn("warn get role list:"+user.getDobUserId()+":"+role.getRoleName());
	        	 }
	        	 user.setLiHasRoles(roleList);
        		 roleIdList = (List<String>)this.getUserAuthManagementService().getRoleIdByUserId(user.getDobUserId());
	        	 user.setContainRoleId(roleIdList);
	        	 String ret = ToolsUtil.switchDecimalEndian(user.getCardNum());
	        	 user.setCardNum(ret);
        	 }
         } catch (Exception e){
             this.log.error(e.toString());
         }
 		pr.setTotalCount(userInfolist.size());
 		pr.setData(userInfolist);
         return pr;
 	}
    
    
    

    @ResponseBody  
    @RequestMapping(value="/saveUserInfo")
    public Map<String, Object>   saveUserInfo(@ModelAttribute UserAuthData userAuthData, BindingResult result){ 
    	boolean flag =false;
    	
	 	/*ByteBuffer buffer= ByteBuffer.allocate(4);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asIntBuffer().put(Integer.parseInt(userAuthData.getCardNum()));
        buffer.order(ByteOrder.BIG_ENDIAN);    
        String ret = Integer.toString(buffer.getInt());
    	
        if(ret.length() < 10){
        	int zeroNums = 10 - ret.length();
        	for(int i = 0; i < zeroNums; i ++){
        		ret = "0"+ret;
        	}
        }*/
        String ret = ToolsUtil.switchDecimalEndian(userAuthData.getCardNum());
        
        log.warn("saveUserInfo card num :"+userAuthData.getCardNum() +" ret:"+ret) ;
        userAuthData.setCardNum(ret);
        
    	
        try{
        	if(userAuthData.getDobUserId()==null || "".equals(userAuthData.getDobUserId())){
        		flag = this.getUserAuthManagementService().saveUserInfo(userAuthData);
        		DBLogger.log("UserAdd:"+userAuthData.getDobUserId()+":"+userAuthData.getDobAccount());
        	}else{
        		DBLogger.log("UserMod:"+userAuthData.getDobUserId()+":"+userAuthData.getDobAccount());
        		flag = this.getUserAuthManagementService().updateUserInfo(userAuthData);
        	}
       	 
        } catch (Exception e){
            this.log.error(e.toString());
        }
    	
    	
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", flag);
        return map;//"success"; 
	}
    
    
    
	/**
	 * 
	 */
    @ResponseBody  
    @RequestMapping(value="/deleteUserInfo")
	public Map<String, Object>deleteUserInfo(String[] dobUserIds){
    	boolean flag =false;
    	for(String dobUserId:dobUserIds){
	    	log.warn("controller deleteUserInfdo"+dobUserId);
	    	DBLogger.log("删除用户ID:"+dobUserId);
		    try {
				flag = this.getUserAuthManagementService().deleteUserInfo(dobUserId);
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}
	    Map<String,Object> map = new HashMap<String, Object>();
        //map.put("success", "true");
	    map.put("success", flag);
    	//ModelAndView view = new ModelAndView("jsonView");
        return map;//"success"; 
	}
  
    
	@RequestMapping(value="/getAllUserTypes")
	public @ResponseBody PagingResult<UserTypes> getAllUserTypes(HttpServletRequest request, HttpServletResponse response) {
		
		List<UserTypes> ut = null;
		PagingResult<UserTypes> result = new PagingResult<UserTypes>();

		try{
			ut = this.getUserAuthManagementService().getAllUserTypes();
		}catch(Exception e){
			e.printStackTrace();
		}
		result.setTotalCount(ut.size());
		result.setData(ut);
		return result;
	}
	
	
    
}
