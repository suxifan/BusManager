package com.cictec.web.fuel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.service.OrgService;
import com.cictec.web.auth.util.UUIDGenerator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.model.BusInfo;
import com.cictec.web.fuel.model.ClassFuelStat;
import com.cictec.web.fuel.model.FuelMonthReport;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasModified;
import com.cictec.web.fuel.model.NewBusGas;
import com.cictec.web.fuel.model.ParamSetting;
import com.cictec.web.fuel.service.IBusInfoService;
import com.cictec.web.fuel.service.IFuelReportService;
import com.cictec.web.fuel.service.IFuelSystemManagerService;
import com.cictec.web.fuel.util.ExcelUtil;
import com.cictec.web.fuel.util.PropertiesUtil;
import com.cictec.web.fuel.util.ToolsUtil;
import com.vividsolutions.jts.io.ParseException;

@Controller
@RequestMapping(value = "/fuelReport")
public class FuelReportController {

	private static final Logger LOG = Logger.getLogger(FuelReportController.class);

	@Resource(name = "fuelReportService")
	public IFuelReportService fuelreportservice;

	@Resource(name = "busInfoService")
	public IBusInfoService busInfoService;
	@Resource(name="fuelSystemManagerService")
	public IFuelSystemManagerService fuelSystemManagerService;
    @Resource(name="orgService")
    public OrgService orgService;

	
	/**
	 * 天然气材料月终汇总
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFuelMonthReport")
	public @ResponseBody
	PagingResult<FuelMonthReport> getFuelMonthReport(
			HttpServletRequest request, HttpServletResponse response) {

		PagingResult<FuelMonthReport> result = new PagingResult<FuelMonthReport>();
		try {
			String orgId = request.getParameter("orgId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");


			Map paraMap = new HashMap<String, String>();
			paraMap.put("orgId", orgId);
            paraMap.put("startDate", startDate);
            paraMap.put("endDate", endDate);

            ParamSetting param = fuelSystemManagerService.getParamSettingByType(Constants.GAS_AGREEMENT_PRICE);
			paraMap.put("price", param!=null?(new BigDecimal(param.getParaValue())):0);
			
			List<FuelMonthReport> fuelMonthReports = fuelreportservice.getFuelMonthReport(paraMap);
			result.setTotalCount(fuelMonthReports.size());
			result.setData(fuelMonthReports);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("FuelReportController->getFuelMonthReport方法执行失败！"+ e.toString());
		}
		return result;
	}


    @RequestMapping(value = "/saveBusInfo")
    public void  saveBusInfo(@ModelAttribute BusInfo busInfo, HttpServletResponse response) throws IOException {

        boolean result = false;

        Orgs parentOrg = orgService.getParentOrgByOrgId(busInfo.getOrgId());
        busInfo.setOrgParentId(parentOrg.getOrgId());
        busInfo.setOrgParentName(parentOrg.getOrgName());
        if(busInfo.getBusInfoId()==null||busInfo.getBusInfoId().equals("")){
            busInfo.setBusStatus((short)1);
            busInfo.setCreated(new Date());
            busInfo.setBusInfoId(UUIDGenerator.genUuidStr());
            result = busInfoService.saveBusInfo(busInfo);
        }else{
            result = busInfoService.updateBusInfo(busInfo);
        }
       com.cictec.web.auth.util.ToolsUtil.writeJSON(response, "{success:" + result + "}");
    }



    @RequestMapping(value = "/deleteBusInfo")
    public void  deleteBusInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
           String busInfoId = request.getParameter("busInfoId");

           boolean result = busInfoService.deleteBusInfo(busInfoId);
         com.cictec.web.auth.util.ToolsUtil.writeJSON(response, "{success:" + result + "}");

    }

	/**
	 * 集团油气月终汇总
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFuelMonthReportBySubGroup")
	public @ResponseBody
	PagingResult<FuelMonthReport> getFuelMonthReportBySubGroup(
			HttpServletRequest request, HttpServletResponse response) {

		PagingResult<FuelMonthReport> result = new PagingResult<FuelMonthReport>();
		try {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String fuelGasStationOrgId = request.getParameter("fuelGasStationOrgId");



            Map<String, String> paraMap = new HashMap<String, String>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

            if(startDate==null||startDate==""){

                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, 1);
                startDate=sf.format(ca.getTime());

            }
            if(endDate==null||endDate==""){
                endDate = sf.format(new Date());
            }
            paraMap.put("startDate", startDate);
            paraMap.put("endDate", endDate);
            paraMap.put("fuelGasStationOrgId", fuelGasStationOrgId);


            List<FuelMonthReport> fuelMonthReports = fuelreportservice.getFuelMonthReportBySubGroup(paraMap);
			result.setTotalCount(fuelMonthReports.size());
			result.setData(fuelMonthReports);
		} catch (Exception e) {
			LOG.error("FuelReportController->getFuelMonthReport方法执行失败！"+ e.toString());
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @return PagingResult<GasDetail> 分页数据。
	 */
	public <T>  List<T> FilterResultByUserOrgs(List<T> ref)
	{
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
       	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
       	
       	String parentType = (String)request.getSession().getAttribute("parentType");
       	String branchParentName = (String)request.getSession().getAttribute("branchParentName");
       	String branchParentId = (String)(String)request.getSession().getAttribute("branchParentId");
       	String lineTeamParentId = (String)request.getSession().getAttribute("lineTeamParentId");
       	String lineTeamParentName = (String)request.getSession().getAttribute("lineTeamParentName");

       	
       	List<T> ret = new ArrayList<T>();
       	if(ref.size() > 0){
           	LOG.error("FilterResultByUserOrgs:"+ref.size()+":"+ref.get(0).getClass().getName()+":"+GasDetail.class.getName()+":"+parentType);

       		if(ref.get(0).getClass().getName().equals(GasDetail.class.getName())){
	       	
	       	
		     	 if(parentType.equals("group")){
		      		 return ref;
		      	 }else if(parentType.equals("branch")){
		      		 for(T t:ref){
		      			 if( ((GasDetail)t).getOrgId().equals(branchParentId)){
		      				 ret.add(t);
		      			 }
		      		 }
		      		 //return record.get('orgId') == branchId;
		      	 }else if(parentType.equals("lineTeam")){
		      		 
		      		for(T t:ref){
		      			 if( ((GasDetail)t).getLineOrgId().equals(lineTeamParentId)){
		      				 ret.add(t);
		      			 }
		      		 }
		      		 //return record.get('lineOrgId') == lineTeamId; 
		      	 }  
       		}else if(ref.get(0).getClass().getName().equals(GasModified.class.getName())){
    	       	
           }else{
       			LOG.error("invalid calss type.");
           }
       		return ret;
       	
       	}
       	
       	
       	
       	
       	
		return ref;
	}
	
	
	@RequestMapping(value = "/getBranchFuelReport")
	public @ResponseBody
	PagingResult<GasDetail> getBranchFuelReport(@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String selfNum = cqo.getSelfNum();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		LOG.debug("controler getFuelReportByParams.id:" + orgId +  "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate + "line:" + cqo.getLineId() + "op:"
				+ cqo.getOperType());

		List<GasDetail> gasInfolist = null;
		PagingResult<GasDetail> pr = new PagingResult<GasDetail>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgId(cqo.getLineOrgId());
		daoQuery.setLineId(cqo.getLineId());
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}
//		if (!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month)) {
//			// userInfot.setYear(year);
//			Date date = ToolsUtil.StrToDate(year + "-" + month + "-" + "01");
//			String qStr = year + "-"+ (Integer.parseInt(month) > 9 ? month : "0" + month);
//			daoQuery.setQueryTime(date);
//			daoQuery.setQueryTimeStr(qStr);
//		}
		if (!StringUtils.isEmpty(startDate)) {
			daoQuery.setQueryTimeRangeStart(startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			daoQuery.setQueryTimeRangeEnd(endDate);
		}

		/*
		 * QueryBranchFuelReport:分公司车队油气月终汇总表请求
		 * QueryBranchBusFuelReport:分公司单车油气月终汇总表请求
		 * QueryBranchDetailFuelReport:分公司油气明细日表请求
		 */
		if (queryType != null) {
			try {
				gasInfolist = (List<GasDetail>) fuelreportservice.getBranchFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				
				List<GasDetail> li = FilterResultByUserOrgs(gasInfolist);
				pr.setTotalCount(li.size());
				pr.setData(li);
			}

		}
		return pr;
	}

	
	
	
	
	//@RequestMapping(value = "/getFuelReportByParams")
	@RequestMapping(value = "/getBranchBusFuelReport")
	public @ResponseBody
	PagingResult<GasDetail> getBranchBusFuelReport(@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String selfNum = cqo.getSelfNum();
        String lineName  = cqo.getLineName();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		LOG.debug("controler getFuelReportByParams.id:" + orgId + "y:" + year
				+ "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate + "lineName:" + lineName 
				+ "ed:" + endDate + "line:" + cqo.getLineId() + "op:"
				+ cqo.getOperType());

		List<GasDetail> gasInfolist = null;
		PagingResult<GasDetail> pr = new PagingResult<GasDetail>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgId(cqo.getLineOrgId());
		daoQuery.setLineName(cqo.getLineName());
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}

		if (!StringUtils.isEmpty(startDate)) {
			daoQuery.setQueryTimeRangeStart(startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			daoQuery.setQueryTimeRangeEnd(endDate);
		}
		if (queryType != null) {
			try {
				gasInfolist = (List<GasDetail>) fuelreportservice.getBranchFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				
				List<GasDetail> li = FilterResultByUserOrgs(gasInfolist);
				pr.setTotalCount(li.size());
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<GasDetail> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		}
		return pr;
	}
	
	@RequestMapping(value = "/getBranchDetailFuelReport")
	public @ResponseBody
	PagingResult<GasDetail> getBranchDetailFuelReport(@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String selfNum = cqo.getSelfNum();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String lineName = cqo.getLineName();
		LOG.debug("controler getFuelReportByParams.id:" + orgId + "y:" + year
				+ "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate + "line:" + cqo.getLineId() + "op:"
				+ cqo.getOperType());

		List<GasDetail> gasInfolist = null;
		PagingResult<GasDetail> pr = new PagingResult<GasDetail>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgId(cqo.getLineOrgId());
		daoQuery.setLineId(cqo.getLineId());
		if (!StringUtils.isEmpty(lineName)) {
			daoQuery.setLineName(lineName);
		}
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}
		if (!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month)) {
			// userInfot.setYear(year);
			Date date = ToolsUtil.StrToDate(year + "-" + month + "-" + "01");
			String qStr = year + "-"+ (Integer.parseInt(month) > 9 ? month : "0" + month);
			daoQuery.setQueryTime(date);
			daoQuery.setQueryTimeStr(qStr);
		}
		if (!StringUtils.isEmpty(startDate)) {
			Date date = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}

		if (queryType != null) {
			try {
				gasInfolist = (List<GasDetail>) fuelreportservice.getBranchFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				
				List<GasDetail> li = FilterResultByUserOrgs(gasInfolist);
				pr.setTotalCount(li.size());
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<GasDetail> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		}
		return pr;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 获取车辆基本信息。
	 * 
	 * @return PagingResult<GasDetail> 分页数据。
	 */
	@RequestMapping(value = "/getBusInfo")
	public @ResponseBody
	PagingResult<BusInfo> getBusInfo(@RequestBody ClientQueryByParam cqo)
			throws ParseException, java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String selfNum = cqo.getSelfNum();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		LOG.debug("controler getBusInfo.id:" + orgId + "y:" + year + "m:"
				+ month + "type:" + queryType + "start:" + cqo.getStart()
				+ "li:" + cqo.getLimit() + "sd:" + startDate + "ed:" + endDate
				+ "line:" + cqo.getLineId());

		List<BusInfo> busInfolist = null;
		PagingResult<BusInfo> pr = new PagingResult<BusInfo>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgId(cqo.getLineOrgId());
		daoQuery.setLineId(cqo.getLineId());
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());
        daoQuery.setPage(cqo.getPage());
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}
		if (!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month)) {
			// userInfot.setYear(year);
			Date date = ToolsUtil.StrToDate(year + "-" + month + "-" + "01");
			String qStr = year + "-"+ (Integer.parseInt(month) > 9 ? month : "0" + month);
			
			
			daoQuery.setQueryTime(date);
			daoQuery.setQueryTimeStr(qStr);
		}
		if (!StringUtils.isEmpty(startDate)) {
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDate(endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}



		try {
			busInfolist = busInfoService.getBusInfo(daoQuery);
		} catch (Exception e) {
			LOG.error(e.toString());
		}
		if (busInfolist != null&&busInfolist.size()!=0) {
			pr.setTotalCount(busInfolist.size());

            int page = daoQuery.getPage();
            int limit = daoQuery.getLimit();
            int start = daoQuery.getStart();
            int retStart = 0, retFin = 0;
            retFin = page*limit > busInfolist.size() ? busInfolist.size():page*limit;
            retStart = (page-1)*limit > busInfolist.size()-1 ? busInfolist.size()-1:(page-1)*limit;
            List<BusInfo> n_list = busInfolist.subList(retStart, retFin);
            pr.setData(n_list);

        }
		return pr;
	}

	/**
	 * 加气数据修改明细表
	 * 
	 * @return PagingResult<GasModified> 分页数据。
	 */
	@RequestMapping(value = "/getModifiedFuelReportByParams")
	public @ResponseBody
	PagingResult<GasModified> getBranchModifiedFuelReportByParams(
			@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String queryType = cqo.getQueryType();
		LOG.debug("controler getModifiedFuelReportByParams.id:" + orgId + "y:"
				+ year + "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate);
		List<GasModified> gasInfolist = null;
		PagingResult<GasModified> pr = new PagingResult<GasModified>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}
		//StringUtils.isEmpty(str);
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());
		if (!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month)) {
			
			Date date = ToolsUtil.StrToDate(year + "-" + month + "-" + "01");
			String qStr = year + "-"
					+ (Integer.parseInt(month) > 9 ? month : "0" + month);

			daoQuery.setQueryTime(date);
			daoQuery.setQueryTimeStr(qStr);
		}
		if (!StringUtils.isEmpty(startDate)) {
			//Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"), endDate);
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDateEnd( endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}
		if (!StringUtils.isEmpty(queryType) && queryType.equals("QueryBranchModifiedFuelReport")) {

			try {
				gasInfolist = (List<GasModified>) fuelreportservice
						.getBranchModifiedFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				List<GasModified> li = gasInfolist;
				pr.setTotalCount(li.size());	
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<GasModified> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		}
		return pr;
	}

	
	
	
	/**
	 * 加气数据修改明细表
	 * 
	 * @return PagingResult<GasModified> 分页数据。
	 */
	@RequestMapping(value = "/getModifiedFuelCheckReportByParams")
	public @ResponseBody
	PagingResult<GasModified> getModifiedFuelCheckReportByParams(
			@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String queryType = cqo.getQueryType();
		LOG.debug("controler getModifiedFuelCheckReportByParams.id:" + orgId + "y:"
				+ year + "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate);
		List<GasModified> gasInfolist = null;
		PagingResult<GasModified> pr = new PagingResult<GasModified>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}
		//StringUtils.isEmpty(str);
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());
		if (!StringUtils.isEmpty(year) && !StringUtils.isEmpty(month)) {
			
			Date date = ToolsUtil.StrToDate(year + "-" + month + "-" + "01");
			String qStr = year + "-"
					+ (Integer.parseInt(month) > 9 ? month : "0" + month);

			daoQuery.setQueryTime(date);
			daoQuery.setQueryTimeStr(qStr);
		}
		if (!StringUtils.isEmpty(startDate)) {
			//Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"), endDate);
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDateEnd( endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}
		if (!StringUtils.isEmpty(queryType) && queryType.equals("QueryBranchModifiedFuelCheckReport")) {

			try {
				gasInfolist = (List<GasModified>) fuelreportservice
						.getBranchModifiedFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				List<GasModified> li = gasInfolist;
				pr.setTotalCount(li.size());	
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<GasModified> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		}
		return pr;
	}

	
	
	@RequestMapping(value = "/updateModifiedFuelCheckReport")
	public @ResponseBody
	boolean updateModifiedFuelCheckReport(HttpServletRequest request, HttpServletResponse response) throws ParseException,
			java.text.ParseException {
		String gasModifiedId = request.getParameter("gasModifiedId");
		String gasDetailId = request.getParameter("gasDetailId");
		String gasStatus = request.getParameter("gasStatus");
		String auditOptinion = request.getParameter("auditOptinion");
		Map paraMap = new HashMap<String, String>();
		paraMap.put("gasModifiedId", gasModifiedId);	
		paraMap.put("gasDetailId", gasDetailId);
		paraMap.put("gasStatus", gasStatus);
		paraMap.put("auditOptinion", auditOptinion);
		boolean isOk = false;
		LOG.debug("controler updateModifiedFuelCheckReport.id:" + gasModifiedId+":"+gasStatus+":"+auditOptinion+":"+gasDetailId);
			try {
				fuelreportservice.updateModifiedFuelCheckReport(paraMap);
				isOk = true;
			} catch (Exception e) {
				LOG.error(e.toString());
			}
		return isOk;
	}
	
	
	
	
	
	
	
	
	/**
	 * 多次加气统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getMultiFuelAddRecord")
	public @ResponseBody
	PagingResult<GasDetail> getMultiFuelAddRecord(HttpServletRequest request,
			HttpServletResponse response) {

		PagingResult<GasDetail> result = new PagingResult<GasDetail>();
		try {
			String gasDate = request.getParameter("gasDate");
			String busNo = request.getParameter("busNo");
			String selfNo = request.getParameter("selfNo");
			Map paraMap = new HashMap<String, String>();
			paraMap.put("gasDate", gasDate);
			paraMap.put("busNo", busNo);
			paraMap.put("selfNo", selfNo);
			
			ParamSetting param = fuelSystemManagerService.getParamSettingByType(Constants.MUTI_GAS_TIMES_SETTING);
			paraMap.put("mutiGasTimes", param!=null?Integer.parseInt(param.getParaValue()):"");

			List<GasDetail> fuelMonthReports = fuelreportservice
					.getMultiFuelAddRecord(paraMap);
			result.setTotalCount(fuelMonthReports.size());
			result.setData(fuelMonthReports);
		} catch (Exception e) {
			LOG.error("FuelReportController->getMultiFuelAddRecord方法执行失败！"
					+ e.toString());
		}
		return result;
	}

	/**
	 * 分公司车队油气月终汇总表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getFuelMonthReportExcel")
	public @ResponseBody
	String getFuelMonthReportExcel(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String excelType = request.getParameter("excelType");
		String data = request.getParameter("data");

		JSONArray jsonArray = JSONArray.fromObject(data);
		JSONObject jsonObject;
		GasDetail jd = new GasDetail();
		List<List<String>> allExcelData = new ArrayList<List<String>>();
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		String excelName = ToolsUtil.getDate(new Date());
		if (excelType != null && excelType.equals("QueryBranchFuelReport")) {
			for (int i = 0; i < jsonArray.size(); i++) {

				jsonObject = jsonArray.getJSONObject(i);
				jd = (GasDetail) JSONObject.toBean(jsonObject, GasDetail.class);
				List<String> excelData = new ArrayList<String>();
				excelData.add(Integer.toString(i + 1));
				excelData.add(jd.getOrgName());
				excelData.add(jd.getLineOrgName());
				excelData.add(jd.getLineName());
				excelData.add(jd.getAllVolume().toString());
                excelData.add("");
                excelData.add("");


                allExcelData.add(excelData);

			}
			excelName = "BranchReport" + excelName + ".xls";
			ExcelUtil.exportToExcel(webRoot + PropertiesUtil.getProperty("exportToExcelDir") + excelName,
					Constants.QueryBranchFuelReportTitle, allExcelData);

		} 
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}

	
	
	/**
	 * 分公司单车油气月终汇总表
	 *
	 * @return
	 */
	
	@RequestMapping(value = "/getBranchBusFuelReportExcel")
	public @ResponseBody
	String getBranchBusFuelReportExcel(@RequestBody ClientQueryByParam cqo) throws UnsupportedEncodingException {

    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		List<List<String>> allExcelData = new ArrayList<List<String>>();
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		
		String excelName = ToolsUtil.getDate(new Date());
		
		String orgId = cqo.getOrgId();
		String lineorgName = cqo.getLineOrgName();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String selfNum = cqo.getSelfNum();
        String lineName  = cqo.getLineName();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		LOG.debug("controler getFuelReportByParams.id:" + orgId + "y:" + year
				+ "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate + "lineorgName:" + 
				lineorgName+ "lineName:" + lineName
				+ "ed:" + endDate + "line:" + cqo.getLineId() + "op:"
				+ cqo.getOperType());

		List<GasDetail> gasInfolist = null;
		PagingResult<GasDetail> pr = new PagingResult<GasDetail>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgName(cqo.getLineOrgName());
		daoQuery.setLineName(cqo.getLineName());
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}

		if (!StringUtils.isEmpty(startDate)) {
			daoQuery.setQueryTimeRangeStart(startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			daoQuery.setQueryTimeRangeEnd(endDate);
		}
		
			try {
				gasInfolist = (List<GasDetail>) fuelreportservice.getBranchFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				List<GasDetail> li = FilterResultByUserOrgs(gasInfolist);
				int i = 0;
				for (GasDetail jd:li) {
					List<String> excelData = new ArrayList<String>();
					excelData.add(Integer.toString(++i));
					excelData.add(jd.getBusNum());
					excelData.add(jd.getSelfNum());
					excelData.add(jd.getAllVolume().toString());
					allExcelData.add(excelData);
				}
			}
			excelName = "BranchBusReport" + excelName + ".xls";
			ExcelUtil.exportToExcel(webRoot + PropertiesUtil.getProperty("exportToExcelDir") + excelName,
					Constants.QueryBranchBusFuelReportTitle, allExcelData);
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}
	
	
	
	
	
	
	/**
	 * 加气数据明细表
	 *
	 * @return
	 * @throws java.text.ParseException 
	 */
	
	@RequestMapping(value = "/getBranchDetailFuelReportExcel")
	public @ResponseBody
	String getBranchDetailFuelReportExcel(@RequestBody ClientQueryByParam cqo) throws UnsupportedEncodingException, java.text.ParseException {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();

		List<List<String>> allExcelData = new ArrayList<List<String>>();
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		String excelName = ToolsUtil.getDate(new Date());

		String orgId = cqo.getOrgId();
		String selfNum = cqo.getSelfNum();
		String queryType = cqo.getQueryType();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		LOG.debug("controler getFuelReportByParams.id:" + orgId + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate + "line:" + cqo.getLineId() + "op:"
				+ cqo.getOperType());

		List<GasDetail> gasInfolist = null;
		PagingResult<GasDetail> pr = new PagingResult<GasDetail>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}

		daoQuery.setLineOrgId(cqo.getLineOrgId());
		daoQuery.setLineId(cqo.getLineId());
		daoQuery.setGasStationId(cqo.getGasStationId());
		daoQuery.setBusNum(cqo.getBusNum());
		daoQuery.setOperType(cqo.getOperType());

		if (!StringUtils.isEmpty(selfNum)) {
			daoQuery.setSelfNum(selfNum);
		}

		if (!StringUtils.isEmpty(startDate)) {
			Date date = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}
			try {
				gasInfolist = (List<GasDetail>) fuelreportservice.getBranchFuelReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				
				List<GasDetail> li = FilterResultByUserOrgs(gasInfolist);
				int i = 0;
				BigDecimal stat = new BigDecimal("0");
				for (GasDetail jd:li) {
					List<String> excelData = new ArrayList<String>();
					excelData.add(Integer.toString(++i));
					excelData.add(jd.getGasTimeStr().substring(0,10));
					excelData.add(jd.getOrgName());
					excelData.add(jd.getLineOrgName());
					excelData.add(jd.getLineName());
					excelData.add(jd.getBusNum());
					excelData.add(jd.getSelfNum());
					
					String operStatus = "其他";
					if(!StringUtils.isEmpty(jd.getOperateType().toString())){
			    	  if(jd.getOperateType().toString().equals("1")){
			    		  operStatus = "运营";
			    	  }else if(jd.getOperateType().toString().equals("2")){
			    		  operStatus = "非运营";
			    	  }else if(jd.getOperateType().toString().equals("3")){
			    		  operStatus = "停驶";
			    	  }else if(jd.getOperateType().toString().equals("4")){
			    		  operStatus = "报废";
			    	  }else if(jd.getOperateType().toString().equals("5")){
			    		  operStatus = "在用(非运营组织)";
			    	  }
					}
					excelData.add(operStatus);
					excelData.add(jd.getGasVolume().toString());
				//	excelData.add(jd.getDriver());
				//	excelData.add(jd.getGasUserName());
					excelData.add(jd.getGasStation());
				//	excelData.add(jd.getGasGunNum());
					excelData.add(jd.getGasTimeStr().substring(11));
//					if(!StringUtils.isEmpty(jd.getDriverId())&& jd.getDriverId().equals("sign")){
//						excelData.add("签字");
//					}else{
//						excelData.add("刷卡");
//					}
//					excelData.add(jd.getRemark());

					allExcelData.add(excelData);
					stat = stat.add(jd.getGasVolume());
				}
				
				
				List<String> excelData = new ArrayList<String>();
				excelData.add("合计");
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(Double.toString(stat.doubleValue()));
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				excelData.add(null);
				allExcelData.add(excelData);
				
				
				
			
			}
			excelName = "BranchDetailReport" + excelName + ".xls";
			ExcelUtil.exportToExcel(webRoot + PropertiesUtil.getProperty("exportToExcelDir") + excelName,
					Constants.QueryBranchDetailFuelReportTitle, allExcelData);

		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}

/*	@RequestMapping(value = "/getBranchModifiedFuelReportExcel")
	public @ResponseBody
	String getBranchModifiedFuelReportExcel(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String excelType = request.getParameter("excelType");
		String data = request.getParameter("data");

		JSONArray jsonArray = JSONArray.fromObject(data);
		JSONObject jsonObject;
		GasModified jm = new GasModified();
		List<List<String>> allExcelData = new ArrayList<List<String>>();
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		String excelName = ToolsUtil.getDate(new Date());
		if (!StringUtils.isEmpty(excelType) && excelType.equals("QueryBranchModifiedFuelReport")) {
			for (int i = 0; i < jsonArray.size(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				jm = (GasModified) JSONObject.toBean(jsonObject,
						GasModified.class);
				List<String> excelData = new ArrayList<String>();

				excelData.add(Integer.toString(i + 1));
				excelData.add(jm.getGasTimeStr());
				excelData.add(jm.getGasStation());
				excelData.add(jm.getGasUserName());
				excelData.add(jm.getPreGasVolume().toString());
				excelData.add(jm.getPostGasVolume().toString());
				excelData.add(jm.getGasEquipmentUrl());
				excelData.add(null);
				allExcelData.add(excelData);
			}
			excelName = "BranchModifiedReport" + excelName + ".xls";
			ExcelUtil.exportToExcel(webRoot + PropertiesUtil.getProperty("exportToExcelDir") + excelName,
					Constants.QueryBranchModifiedFuelReportTitle, allExcelData);

		}
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}*/	
	
	@RequestMapping(value = "/getBranchModifiedFuelReportExcel")
	public @ResponseBody
	String getBranchModifiedFuelReportExcel(@RequestBody ClientQueryByParam cqo) throws ParseException, java.text.ParseException, UnsupportedEncodingException{

    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		List<List<String>> allExcelData = new ArrayList<List<String>>();
		String webRoot = request.getSession().getServletContext().getRealPath("/");
		String excelName = ToolsUtil.getDate(new Date());

		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String queryType = cqo.getQueryType();
		String orgId = cqo.getOrgId();
		
		LOG.debug("controler getBranchModifiedFuelReportExcel."+ "type:" + queryType + "sd:" + startDate
				+ "ed:" + endDate+"org:"+orgId);
		List<GasModified> gasInfolist = null;
		PagingResult<GasModified> pr = new PagingResult<GasModified>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}
		if (!StringUtils.isEmpty(startDate)) {
			//Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"), endDate);
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDateEnd( endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}
		
		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}

		try {
				gasInfolist = (List<GasModified>) fuelreportservice
						.getBranchModifiedFuelReport(daoQuery);
		} catch (Exception e) {
				LOG.error(e.toString());
		}
			if(gasInfolist != null){
				int i = 0;
				for (GasModified gm:gasInfolist) {
					List<String> excelData = new ArrayList<String>();
	
					excelData.add(Integer.toString(++i));
					excelData.add(gm.getGasTimeStr());
					excelData.add(gm.getGasStation());
					excelData.add(gm.getGasUserName());
					excelData.add(gm.getPreGasVolume().toString());
					excelData.add(gm.getPostGasVolume().toString());
					excelData.add(gm.getGasEquipmentUrl());
					excelData.add(null);
					allExcelData.add(excelData);
				}
			}	
			excelName = "BranchModifiedReport" + excelName + ".xls";
			ExcelUtil.exportToExcel(webRoot + PropertiesUtil.getProperty("exportToExcelDir") + excelName,
					Constants.QueryBranchModifiedFuelReportTitle, allExcelData);

		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 天然气材料月终汇总excel导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/exportFuelMonthDataToExcel")
	public @ResponseBody
	String exportFuelMonthDataToExcel(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String data = request.getParameter("data");
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<List<String>> exportData = new ArrayList<List<String>>();
		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			FuelMonthReport report = (FuelMonthReport) JSONObject.toBean(
					jsonObject, FuelMonthReport.class);
			List<String> record = new ArrayList<String>();
			record.add(Integer.toString(i + 1));
			record.add(report.getLineName());
			record.add(String.valueOf(report.getSumvolume()));
			record.add("");
			record.add("");
			exportData.add(record);
		}
		String exportToExcelPath = request.getSession().getServletContext().getRealPath("/") + PropertiesUtil.getProperty("exportToExcelDir");
		String excelName = ToolsUtil.getDate(new Date());
		// excelName = "天然气材料月终汇总("+excelName+").xls";
		excelName = "fuelMonthReport(" + excelName + ").xls";
		ExcelUtil.exportToExcel(exportToExcelPath + excelName, Constants.fuelMonthReportTitle, exportData);
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}

	/**
	 * 集团油气月终汇总excel导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/exportGroupFuelMonthDataToExcel")
	public @ResponseBody
	String exportGroupFuelMonthDataToExcel(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String data = request.getParameter("data");
        String fuelGasStationName = request.getParameter("fuelGasStationName");
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<List<String>> exportData = new ArrayList<List<String>>();
		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			FuelMonthReport report = (FuelMonthReport) JSONObject.toBean(
					jsonObject, FuelMonthReport.class);
			List<String> record = new ArrayList<String>();
			record.add(Integer.toString(i + 1));
            record.add(fuelGasStationName);
			record.add(report.getOrgName());
			record.add(String.valueOf(report.getSumvolume()));
			record.add("");
            record.add("");
			exportData.add(record);
		}
		String exportToExcelPath = request.getSession().getServletContext().getRealPath("/") + PropertiesUtil.getProperty("exportToExcelDir");
		String excelName = ToolsUtil.getDate(new Date());
		excelName = "groupFuelMonthReport(" + excelName + ").xls";
		ExcelUtil.exportToExcel(exportToExcelPath + excelName, Constants.groupFuelMonthReportTitle, exportData);
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}

	/**
	 * 多次加气统计excel导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/exportMultiFuelAddCountDataToExcel")
	public @ResponseBody
	String exportMultiFuelAddCountDataToExcel(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		String data = request.getParameter("data");
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<List<String>> exportData = new ArrayList<List<String>>();
		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			GasDetail report = (GasDetail) JSONObject.toBean(jsonObject, GasDetail.class);
			List<String> record = new ArrayList<String>();
			record.add(Integer.toString(i + 1));
			record.add(report.getGasDateStr());
			record.add(report.getGasTimeStr());
			record.add(report.getBusNum());
			record.add(report.getSelfNum());
			record.add(String.valueOf(report.getGasVolume()));
			record.add(report.getRemark());
			exportData.add(record);
		}
		String exportToExcelPath = request.getSession().getServletContext().getRealPath("/") + PropertiesUtil.getProperty("exportToExcelDir");
		String excelName = ToolsUtil.getDate(new Date());		
		excelName = "multiFuelAddCountReport(" + excelName + ").xls";
		ExcelUtil.exportToExcel(exportToExcelPath + excelName, Constants.multiFuelAddCountReport, exportData);
		return PropertiesUtil.getProperty("exportToExcelDir") + URLEncoder.encode(excelName, "utf-8");
	}
	
	
	
	
	
	
	
	/**
	 * 加气站班次日汇总
	 * 
	 * @return PagingResult<GasModified> 分页数据。
	 */
	@RequestMapping(value = "/getClassFuelStatReportByParams")
	public @ResponseBody
	PagingResult<ClassFuelStat> getClassFuelStatReportByParams(
			@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String queryType = cqo.getQueryType();
		LOG.debug("controler getClassFuelStatReportByParams.id:" + orgId + "y:"
				+ year + "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate);
		List<ClassFuelStat> gasInfolist = null;
		PagingResult<ClassFuelStat> pr = new PagingResult<ClassFuelStat>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}
		//StringUtils.isEmpty(str);
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(startDate)) {
			//Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"), endDate);
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDateEnd( endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}

			try {
				gasInfolist = (List<ClassFuelStat>) fuelreportservice
						.getClassFuelStatReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				List<ClassFuelStat> li = gasInfolist;
				pr.setTotalCount(li.size());	
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<ClassFuelStat> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		return pr;
	}
	
	
	/**
	 * 新车加气汇总
	 * 
	 * @return PagingResult<GasModified> 分页数据。
	 */
	@RequestMapping(value = "/getNewBusGasReportByParams")
	public @ResponseBody
	PagingResult<NewBusGas> getNewBusGasReportByParaams(
			@RequestBody ClientQueryByParam cqo) throws ParseException,
			java.text.ParseException {
		String orgId = cqo.getOrgId();
		String year = cqo.getYear();
		String month = cqo.getMonth();
		String startDate = cqo.getQueryTimeRangeStart();
		String endDate = cqo.getQueryTimeRangeEnd();
		String queryType = cqo.getQueryType();
		LOG.debug("controler getNewBusGasReportByParams.id:" + orgId + "y:"
				+ year + "m:" + month + "type:" + queryType + "start:"
				+ cqo.getStart() + "li:" + cqo.getLimit() +"pg:"+cqo.getPage() + "sd:" + startDate
				+ "ed:" + endDate);
		List<NewBusGas> gasInfolist = null;
		PagingResult<NewBusGas> pr = new PagingResult<NewBusGas>();
		ClientQueryByParam daoQuery = new ClientQueryByParam();

		if (!StringUtils.isEmpty(orgId)) {
			daoQuery.setOrgId(orgId);
		}
		if (!StringUtils.isEmpty(queryType)) {
			daoQuery.setQueryType(queryType);
		}
		//StringUtils.isEmpty(str);
		daoQuery.setLimit(cqo.getLimit());
		daoQuery.setStart(cqo.getStart());

		if (!StringUtils.isEmpty(startDate)) {
			//Date dateEnd = ToolsUtil.StrToDate(new SimpleDateFormat("yyyy-MM-dd"), endDate);
			Date date = ToolsUtil.StrTimeToDate(startDate);
			daoQuery.setQueryTime(date);
		}
		if (!StringUtils.isEmpty(endDate)) {
			Date dateEnd = ToolsUtil.StrTimeToDateEnd( endDate);
			daoQuery.setQueryTimeEnd(dateEnd);
		}

			try {
				gasInfolist = (List<NewBusGas>) fuelreportservice
						.getNewBusGasReport(daoQuery);
			} catch (Exception e) {
				LOG.error(e.toString());
			}
			if (gasInfolist != null && gasInfolist.size() > 0) {
				List<NewBusGas> li = gasInfolist;
				pr.setTotalCount(li.size());	
					int page = cqo.getPage();
					int limit = cqo.getLimit();
					int start = cqo.getStart();
					int retStart = 0, retFin = 0;
					retFin = page*limit > li.size() ? li.size():page*limit;
					retStart = (page-1)*limit > li.size()-1 ? li.size()-1:(page-1)*limit;
					List<NewBusGas> n_list = li.subList(retStart, retFin);
					pr.setData(n_list);
			}

		return pr;
	}
	
	
	
	
	
	
	
	
	
	

}













