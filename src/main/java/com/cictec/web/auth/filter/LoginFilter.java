package com.cictec.web.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
  
public class LoginFilter  extends HandlerInterceptorAdapter{  
	Logger log = Logger.getLogger(LoginFilter.class);
    public void afterCompletion(HttpServletRequest arg0,  
            HttpServletResponse arg1, Object arg2, Exception arg3)  
            throws Exception {  
        // TODO Auto-generated method stub  
        //System.out.println("回调执行");  
    }  
  
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object arg2, ModelAndView arg3) throws Exception {  
        // TODO Auto-generated method stub  
        //System.out.println("调用controller后进入"); 
    	 if(request.getRequestURI().contains("doLogin")){
             log.warn("post doLogin 拦截开始"+request.getRequestURI()+":"+request.getContextPath()+":"+request.getServletPath()+":"+request.getQueryString());  
             return;
         }else{
         	return; 
         }
    }  
      
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
        if(request.getRequestURI().contains("doLogin")){
            log.warn("pre doLogin 拦截开始"+request.getRequestURI()+":"+request.getContextPath()+":"+request.getServletPath()+":"+request.getQueryString());  
            log.warn("pre login name:"+ request.getParameter("username"));
            
            return true;
        	//request.getSession().setAttribute("sessionRandCode", sRand);
        }else{
        	return true; 
        }
    
    }  
      
}  
