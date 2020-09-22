package com.cictec.web.auth.controller;
 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cictec.web.auth.pojo.Fields;
import com.cictec.web.auth.pojo.LoginForm;
import com.cictec.web.auth.pojo.Modules;
import com.cictec.web.auth.pojo.PageElementData;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.service.IRoleAuthorityService;
import com.cictec.web.auth.service.LoginService;
import com.cictec.web.auth.service.UserAuthManagementService;
import com.cictec.web.auth.util.CommonUtil;
import com.cictec.web.auth.util.GenerateIcon;
import com.cictec.web.fuel.util.DBLogger;



@Controller
@RequestMapping(value="/admin") 
public class LoginController{
	
	private static final Logger LOG = Logger.getLogger(LoginController.class);
    /**
	 * 跳转到列表页面	
	 **/
	@Resource(name="loginService")
    public LoginService loginService;
	@Autowired
	@Qualifier("roleAuthorityService")
	private IRoleAuthorityService roleAuthorityService;
	
	@Resource(name="userAuthManagementService")
    public UserAuthManagementService userAuthManagementService;
	
    @RequestMapping(value="/errorPage")
	public String showError(){ 
		return "redirect:/error.html";
		
	}
    
    @RequestMapping(value="/login")
	public String login(){ 
		return "redirect:/login.jsp";
	}
    @ResponseBody 
    @RequestMapping(value="/logout")
   	public String logout(){ 
    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
    	LOG.warn("User: "+request.getSession().getAttribute("userName")+" logged out");
    	DBLogger.log("用户 "+ request.getSession().getAttribute("userName") + " 退出");
    	

    	Subject currentUser = SecurityUtils.getSubject();
    	currentUser.logout();
    	
    	request.getSession().setAttribute("userName", "");
    	request.getSession().setAttribute("userId", "");

    	DBLogger.log("登出成功");

    	return "success";
   	}
    public boolean checkLogin(String account, String pwd) {
        String enPwd = CommonUtil.getEncryptPwd(pwd);
        
        LOG.debug("checkLogin...... "+pwd +":"+enPwd);
        UserAuthData user = null;
        try{
        	user = loginService.getUserByAccount(account);
        } catch (Exception e){
            LOG.error(e.toString());
        }
        if(user != null){
        	LOG.debug("getting user"+user.getDobAccount());
        }else{
        	LOG.debug("getting user is null");
        }
        // 启用的用户
        if (null != user) {
            if (user.getDobPassword().equals(enPwd) && user.getIsEnabled()) {
            	
            	AuthenticationToken token = new UsernamePasswordToken(user.getDobAccount(),user.getDobPassword());
            	Subject currentUser = SecurityUtils.getSubject();
            	currentUser.login(token); // 安全框架登录
            	
            	
            	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
            	request.getSession().setAttribute("userName", user.getDobAccount());
            	request.getSession().setAttribute("userId", user.getDobUserId());
            	DBLogger.log("用户 "+ user.getDobAccount() + " 登录");
            	
                
                
            	return true;
            }
        }
        return false;
    }
    
    @ResponseBody 
    @RequestMapping(value="/doLogin")
  	 public String doLogin(@ModelAttribute LoginForm loginForm, BindingResult result){ 

    	//request.getSession();	
    	// String sRandcode = request.getSession().getAttribute("sessionCode").toString();
       String username = loginForm.getUsername();
       String password = loginForm.getPassword();
       if(checkLogin(username, password) == true){   
    	   return "succLogin";
       }else{
    	   return "errLogin";//"\"errLogin\""; 
       }
   	}
  
    @RequestMapping(value="/home")
	public String home(){ 
		 return "redirect:/main.jsp";
	}
    
    @RequestMapping(value = "/randPic")
    public void randPic(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
        	BufferedImage image = GenerateIcon.genRandIconBufImage();
            ServletOutputStream sos = resp.getOutputStream(); 
            ImageIO.write(image, "jpeg", sos); 
            sos.close(); 
        } catch (Exception e) {
            LOG.error(e);//反对
        }
    }
    
    @ResponseBody 
    @RequestMapping(value="/getPageElement")
   	 public List<PageElementData> getPageElement(){ 
    	List<PageElementData> liPageElement = new ArrayList<PageElementData>();  	
    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
    	LOG.debug("getElementProperty.......:"+request.getSession().getAttribute("userName"));
    	
    	List<String> liRole = null;
    	List<Modules> liModule = null;
    	List<Fields> liField = null;
    	try{
    		liRole = userAuthManagementService.getRoleIdByUserId((String)request.getSession().getAttribute("userId"));

	    	for(String role:liRole){
	        	liModule = roleAuthorityService.getModulesByRoleId(role);	
	        	for(Modules mod:liModule){
	        		PageElementData p1 = new PageElementData();
	        		p1.setDobPageElementId(mod.getModuleId());
	        		p1.setDobIsHidden(false);
	        		liPageElement.add(p1);
	        	}
	    	
	    	}
	
	    	for(String role:liRole){
	        	liField = roleAuthorityService.getFieldsByRoleId(role);	
	        	for(Fields field:liField){
	        		PageElementData p1 = new PageElementData();
	        		p1.setDobPageElementId(field.getFieldId());
	        		p1.setDobIsHidden(false);
	        		liPageElement.add(p1);
	        	}
	    	
	    	}
	
    	}catch (Exception e){
            LOG.error(e.toString());
        }
    	return liPageElement;//"\"list\"";
   	}

    
}
