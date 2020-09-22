package com.cictec.web.auth.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.Head;
import com.cictec.web.fuel.model.ResponseInfo;
import com.cictec.web.fuel.service.IFuelSystemManagerService;

public class FuelBusinessFilter extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(FuelBusinessFilter.class);
	
	@Resource(name="fuelSystemManagerService")
	private IFuelSystemManagerService fuelSystemManagerService;
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
		//System.out.println("调用controller后进入");
	}

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("拦截开始");
		String uri = request.getRequestURI();
		String fuelKey = request.getHeader("fuelKey");
		String deviceId = request.getHeader("deviceId");
		if (("fuelSys".equals(fuelKey)) && (deviceId!=null && deviceId.length() > 0)){
			if (uri.contains("/record/upload")) {
				logger.info("请求通过！");
				return true;
			} else {
				CollectDevice collectDevice = fuelSystemManagerService.selectByDeviceImei(deviceId);
				ResponseInfo responseInfo = new ResponseInfo();
				Head head = new Head();
				if (collectDevice !=null) {
					if (collectDevice.getStatus()== 0) {
						head.setMsg("该设备已经被停用，请联系管理员！");
						head.setSuccess("false");
						responseInfo.data = "";
						responseInfo.head = head;
						String jsonStr = JSON.toJSONString(responseInfo);
						com.cictec.web.fuel.util.ToolsUtil.writerJson(response, jsonStr);
						logger.info("该设备已经被停用，请联系管理员！");
						return false;
					} else {
						logger.info("该设备已登记，请求通过！");
						return true;
					}
					
				} else {
					head.setMsg("该设备还未登记，请联系管理员！");
					head.setSuccess("false");
					responseInfo.data = "";
					responseInfo.head = head;
					String jsonStr = JSON.toJSONString(responseInfo);
					com.cictec.web.fuel.util.ToolsUtil.writerJson(response, jsonStr);
					logger.info("该设备还未登记，请求未通过！");
					return false;
				}
			}
			
		} else {
			logger.info("非法请求未通过！");
			return false;
		}
		
	}

	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)throws Exception {
		//System.out.println("回调执行");
	}

}
