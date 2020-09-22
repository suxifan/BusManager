package com.cictec.web.auth.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.Head;
import com.cictec.web.fuel.model.ResponseInfo;
import com.cictec.web.fuel.service.IFuelSystemManagerService;

public class FuelBusinessShiroFilter implements Filter {
	private static final Logger logger = Logger.getLogger(FuelBusinessFilter.class);

	
	@Resource(name="fuelSystemManagerService")
	private IFuelSystemManagerService fuelSystemManagerService;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
				

		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		response.setContentType("text/html;charset=UTF-8");
		//System.out.println("拦截开始");
		String uri = request.getRequestURI();
		String fuelKey = request.getHeader("fuelKey");
		String deviceId = request.getHeader("deviceId");
		
		
		if (("fuelSys".equals(fuelKey)) && (deviceId!=null && deviceId.length() > 0)){
			if (uri.contains("/record/upload")) {
				logger.info("请求通过！");
				arg2.doFilter(arg0, arg1);
				
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
					} else {
						logger.info("该设备已登记，请求通过！");
						arg2.doFilter(arg0, arg1);
					}
					
					
				} else {
					head.setMsg("该设备还未登记，请联系管理员！");
					head.setSuccess("false");
					responseInfo.data = "";
					responseInfo.head = head;
					String jsonStr = JSON.toJSONString(responseInfo);
					com.cictec.web.fuel.util.ToolsUtil.writerJson(response, jsonStr);
					logger.info("该设备还未登记，请求未通过！");
				}
			}
			
		} else {
			logger.info("非法请求未通过！");

		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		

	}

}
