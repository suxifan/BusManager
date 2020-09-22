package com.cictec.web.auth.filter;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  
  
public class UserAuthManagementFilter  extends HandlerInterceptorAdapter{  
  
    public void afterCompletion(HttpServletRequest arg0,  
            HttpServletResponse arg1, Object arg2, Exception arg3)  
            throws Exception {  
        // TODO Auto-generated method stub  
        //System.out.println("回调执行");  
    }  
  
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,  
            Object arg2, ModelAndView arg3) throws Exception {  
        // TODO Auto-generated method stub  
        //System.out.println("调用controller后进入");  
    }  
      
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
        System.out.println("拦截开始"+request.getRequestURI()+":"+request.getContextPath()+":"+request.getServletPath()+":"+request.getQueryString());  
        if(request.getRequestURI().contains("getUserInfo")){
	        if ("GET".equalsIgnoreCase(request.getMethod())) { 
	        	System.out.println("非法的getUserInfo GET请求 =============================="); 
	        	request.getRequestDispatcher("../error.html").forward(request, response);        
	        	return false;
	        }else{
	        	return true;	
	        }
        }else{
        	return true; 
        }
    
    }  
      
}  
