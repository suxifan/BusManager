package com.cictec.web.fuel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.service.OrgService;
import com.cictec.web.auth.util.ToolsUtil;
import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.model.GasGun;
import com.cictec.web.fuel.model.GasStationPrincipal;
import com.cictec.web.fuel.service.IGasStationService;

@Controller
@RequestMapping(value="/gasStationMgmt") 
public class GasStationController {

	private static final Logger LOG = Logger.getLogger(GasStationController.class);
	
	@Resource(name = "gasStationService")
	public IGasStationService gasStationService;
	
	@Resource(name="orgService")
    public OrgService orgService;
	
	@RequestMapping(value="/getAllGasStationInfo")
	public @ResponseBody PagingResult<GasStationPrincipal> getAllGasStationInfo(HttpServletRequest request, HttpServletResponse response) {
		
		List<GasStationPrincipal> gasStations = gasStationService.getAllGasStationInfo();
		List<Orgs> stations = orgService.getOrgsByOrgType(Constants.GAS_STATION_TYPE_ID);
		for (Orgs org : stations) {
			
			for (GasStationPrincipal gasStation : gasStations) {
				if(gasStation.getGasStationId().equals("1")){
                    gasStation.setEnable(true);
                }
                if(org.getOrgId().equals(gasStation.getGasStationId())) {
					gasStation.setEnable(org.getIsEnabled());
				}
			}
		}
		PagingResult<GasStationPrincipal> result = new PagingResult<GasStationPrincipal>();
		result.setTotalCount(gasStations.size());
		result.setData(gasStations);
		return result;
	}
	
	@RequestMapping(value="/getGasStationInfoByName")
	public @ResponseBody PagingResult<GasStationPrincipal> getGasStationInfoByName(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		
		String gasStationName = request.getParameter("gasStationName");
		gasStationName = URLDecoder.decode(gasStationName, "UTF-8");
		GasStationPrincipal station = new GasStationPrincipal();
		station.setGasStation(gasStationName);
		List<GasStationPrincipal> gasStations = gasStationService.getGasStationInfoByName(station);
		List<Orgs> stations = orgService.getOrgsByOrgType(Constants.GAS_STATION_TYPE_ID);
		for (Orgs org : stations) {
			
			for (GasStationPrincipal gasStation : gasStations) {
				if(org.getOrgId().equals(gasStation.getGasStationId())) {
					gasStation.setEnable(org.getIsEnabled());
				}
			}
		}
		PagingResult<GasStationPrincipal> result = new PagingResult<GasStationPrincipal>();
		result.setTotalCount(gasStations.size());
		result.setData(gasStations);
		return result;
	}
	
	@RequestMapping(value="/deleteGasStationInfoById")
	public void deleteGasStationInfoById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		boolean result = true;
		String[] principalIds = request.getParameterValues("principalIds");
    	for (String id : principalIds) {
    		result = gasStationService.deleteGasStationInfoById(id);
		}
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
	@RequestMapping(value="/addGasStationInfo")
	public void addGasStationInfo(@ModelAttribute GasStationPrincipal gasStationInfo, HttpServletResponse response) throws IOException {
		
		boolean result = true;
		gasStationInfo.setPrincipalId(UUIDGenerator.genUuidStr());
		gasStationInfo.setExpired(com.cictec.web.fuel.util.ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"),
				gasStationInfo.getExpiredStr()));
		Calendar calendar = Calendar.getInstance();
		gasStationInfo.setCreated(calendar.getTime());
		result = gasStationService.addGasStationInfo(gasStationInfo);
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
	@RequestMapping(value="/modifyGasStationInfo")
	public void modifyGasStationInfo(@ModelAttribute GasStationPrincipal gasStationInfo, HttpServletResponse response) throws IOException {
		
		gasStationInfo.setExpired(com.cictec.web.fuel.util.ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"),
				gasStationInfo.getExpiredStr()));
		boolean result = gasStationService.updateGasStationInfo(gasStationInfo);
		Orgs info = new Orgs();
		info.setOrgId(gasStationInfo.getGasStationId());
		info.setIsEnabled(gasStationInfo.isEnable());
		boolean result1 = orgService.updateOrgInfo(info);
		ToolsUtil.writeJSON(response, "{success:" + (result && result1) + "}");
	}
	
	@RequestMapping(value="/getAllGasGunInfo")
	public @ResponseBody PagingResult<GasGun> getAllGasGunInfo(HttpServletRequest request, HttpServletResponse response) {
		
		int pageSize = Integer.parseInt(request.getParameter("limit"));
		int offset = Integer.parseInt(request.getParameter("start"));
		Map<String, Integer> paraMap = new HashMap<String, Integer>();
		paraMap.put("pageSize", pageSize);
		paraMap.put("offset", offset);
		List<GasGun> pagingGasGunInfos = gasStationService.selectAllGasGun(paraMap);
		int totalCount = gasStationService.selectTotalRecord();
		PagingResult<GasGun> result = new PagingResult<GasGun>();
		result.setTotalCount(totalCount);
		result.setData(pagingGasGunInfos);
		return result;
	}
	
	@RequestMapping(value="/addGasGunInfo")
	public void addGasGunInfo(@ModelAttribute GasGun gasGunInfo, HttpServletResponse response) throws IOException {
		
		boolean result = true;
		gasGunInfo.setGasGunId(UUIDGenerator.genUuidStr());
		Calendar calendar = Calendar.getInstance();
		gasGunInfo.setCreated(calendar.getTime());
		result = gasStationService.addGasStationInfo(gasGunInfo);
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
	@RequestMapping(value="/deleteGasGunInfoById")
	public void deleteGasGunInfoById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		boolean result = true;
		String[] gasGunIds = request.getParameterValues("gasGunIds");
    	for (String id : gasGunIds) {
    		result = gasStationService.deleteGasGunInfoById(id);
		}
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
	@RequestMapping(value="/modifyGasGunInfo")
	public void modifyGasGunInfo(@ModelAttribute GasGun GasGunInfo, HttpServletResponse response) throws IOException {
		
		boolean result = gasStationService.modifyGasGunInfo(GasGunInfo);
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
}
