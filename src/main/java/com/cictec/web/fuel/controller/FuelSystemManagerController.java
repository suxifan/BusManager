package com.cictec.web.fuel.controller;
 
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasModified;
import com.cictec.web.fuel.model.ParamSetting;
import com.cictec.web.fuel.service.IFuelReportService;
import com.cictec.web.fuel.service.IFuelSystemManagerService;
import com.cictec.web.fuel.util.ExcelUtil;
import com.vividsolutions.jts.io.ParseException;

@Controller
@RequestMapping(value="/fuelSystemManager") 
public class FuelSystemManagerController {
	
	private static final Logger LOG = Logger.getLogger(FuelSystemManagerController.class);
	
  	@Resource(name="fuelSystemManagerService")
    public IFuelSystemManagerService fuelSystemManagerService;
  	
  	
  	/**
  	 * 参数设置
  	 * @param request
  	 * @param response
  	 * @return
  	 */
	@RequestMapping(value="/getFuelParamSetting")
	public @ResponseBody PagingResult<ParamSetting> getFuelParamSetting(HttpServletRequest request, 
			HttpServletResponse response) {
		
    	PagingResult<ParamSetting> result = new PagingResult<ParamSetting>();
    	try {
			List<ParamSetting> fuelMonthReports = fuelSystemManagerService.getAllFuelParamSetting();
			result.setTotalCount(fuelMonthReports.size());
			result.setData(fuelMonthReports);
		} catch (Exception e) {
			LOG.error("FuelSystemManagerController->getFuelParamSetting方法执行失败！" + e.toString());
		}
    	return result;
	}
	
	
	@ResponseBody  
    @RequestMapping(value="/saveParamSetinInfo")
    public Map<String, Object>   saveParamSetinInfo(@ModelAttribute ParamSetting paramSetting, BindingResult result){ 
    	boolean flag =false;
    	
        try{
        	flag = this.fuelSystemManagerService.updataParamSetting(paramSetting);
       	 
        } catch (Exception e){
        	 LOG.error(e.toString());
        }
    	
    	
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", flag);
        return map;//"success"; 
	}
    
	
	/**
  	 * 设备管理
  	 * @param request
  	 * @param response
  	 * @return
	 * 
  	 */
	@RequestMapping(value="/getCollectDevice")
	public @ResponseBody PagingResult<CollectDevice> getCollectDevice(HttpServletRequest request, 
			HttpServletResponse response) throws UnsupportedEncodingException {
		
    	PagingResult<CollectDevice> result = new PagingResult<CollectDevice>();
    	
    	String deviceName = request.getParameter("deviceName");
    	int start = request.getParameter("start")!=null?Integer.parseInt(request.getParameter("start")):null;
    	int limit = request.getParameter("limit")!=null?Integer.parseInt(request.getParameter("limit")):null;  
    	int page = request.getParameter("page")!=null?Integer.parseInt(request.getParameter("page")):null;  

    	if(deviceName!=null&&(!"".equals("deviceName"))){
    		deviceName = URLDecoder.decode(deviceName,"utf-8");
    	}
    	Map param = new HashMap();
    	param.put("deviceName",deviceName);
//		其他查询条件
//    	param.put("deviceNum", "");
//    	param.put("gasStation", "");
    	
    	try {
			List<CollectDevice> collectDevices = fuelSystemManagerService.getCollectDevice(param);
			result.setTotalCount(collectDevices.size());
			
			int retStart = 0, retFin = 0;
			retFin = page*limit > collectDevices.size() ? collectDevices.size():page*limit;
			retStart = (page-1)*limit > collectDevices.size()-1 ? collectDevices.size()-1:(page-1)*limit;
			
			List<CollectDevice> collectDevicesLimit = collectDevices.subList(retStart, retFin);
			result.setData(collectDevicesLimit);
		} catch (Exception e) {
			LOG.error("FuelSystemManagerController->getCollectDevice方法执行失败！" + e.toString());
		}
    	return result;
	}
	
	@ResponseBody  
    @RequestMapping(value="/saveCollectDeviceInfo")
    public Map<String, Object>   saveCollectDeviceInfo(@ModelAttribute CollectDevice collectDevice, BindingResult result){ 
    	boolean flag =false;
    	try{
    		flag =	fuelSystemManagerService.saveCollectDevice(collectDevice);
    	}catch(Exception e){
			LOG.error("FuelSystemManagerController->saveCollectDeviceInfo方法执行失败！" + e.toString());
    	}
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", flag);
        return map;//"success"; 
	}
    
	  @ResponseBody  
	    @RequestMapping(value="/deleteCollectDevice")
		public Map<String, Object>deleteCollectDevice(String deviceId){
		    String[] ids = deviceId.split(",");
		    boolean flag =false;
		    try {
				flag = this.fuelSystemManagerService.deleteCollectDeviceForList( Arrays.asList(ids));
			}catch (Exception e) {
				e.printStackTrace();
			}
		    Map<String,Object> map = new HashMap<String, Object>();
	        //map.put("success", "true");
		    map.put("success", flag);
	    	//ModelAndView view = new ModelAndView("jsonView");
	        return map;//"success"; 
		}
	  	
}
