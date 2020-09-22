package com.cictec.web.fuel.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.util.ToolsUtil;
import com.cictec.web.fuel.model.*;
import com.cictec.web.fuel.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value="/fuelEmail")
public class FuelEmailController {

	private static final Logger LOG = Logger.getLogger(FuelEmailController.class);


    @Resource(name="fuelBusinessService")
    public IFuelBusinessService fuelBusinessService;

    @Resource(name="fuelEmailService")
    public IFuelEmailService fuelEmailService;



    @RequestMapping(value="/getStationNoDataDay")
    public @ResponseBody PagingResult<GasStationDataForDay> getStationNoDataDay(HttpServletRequest request,
                                                              HttpServletResponse response){
        PagingResult<GasStationDataForDay> result = new PagingResult<GasStationDataForDay>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try{


        Date startDate =  simpleDateFormat.parse(startDateStr);
        Date endDate =  simpleDateFormat.parse(endDateStr);

        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(startDate);//设置日期起始时间

        List<GasStationPrincipal> gasStationPrincipals =  fuelEmailService.getAllStation();

        Map param = new HashMap();
        param.put("startDate",startDateStr+" 00:01");
        param.put("endDate",endDateStr+" 23:59");

        List<GasStationDataForDay> detailExitsDay = fuelEmailService.getGasStationDataForDay(param);

        List<GasStationDataForDay> noDataDays = new ArrayList<GasStationDataForDay>();

            //循环日期
        while(!dd.getTime().after(endDate)){
            String str = simpleDateFormat.format(dd.getTime());
            dd.add(Calendar.DATE, 1);//进行当前日期月份加1
            //循环站点
            for (GasStationPrincipal gasStationPrincipal : gasStationPrincipals){
                if(!gasStationDetailContainsRecord(detailExitsDay, str, gasStationPrincipal)){

                    GasStationDataForDay gasStationDataForDay = new GasStationDataForDay();
                    gasStationDataForDay.setGasStation(gasStationPrincipal.getGasStation());
                    gasStationDataForDay.setGasTimeStr(str);

                    noDataDays.add(gasStationDataForDay);
                }
            }

        }

            result.setTotalCount(noDataDays.size());
            result.setData(noDataDays);

        }catch (Exception e){
                 e.printStackTrace();
        }






        return result;
    }



    @RequestMapping(value="/getEmailInfo")
    public @ResponseBody PagingResult<EmailInfo> getEmailInfo(HttpServletRequest request,
                                                                      HttpServletResponse response)  {


            PagingResult<EmailInfo> result = new PagingResult<EmailInfo>();

            String startDateStr = request.getParameter("startDate");
            String endDateStr =    request.getParameter("endDate");
            String queryType =   request.getParameter("queryType");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");





        if(startDateStr==null||startDateStr==""||endDateStr==null||endDateStr==""){


            startDateStr =  simpleDateFormat.format(new Date());
            endDateStr =   simpleDateFormat.format(new Date());

            }

            String startstr = request.getParameter("start");
            String limitstr = request.getParameter("limit");
            String pagestr  = request.getParameter("page");

            int start = startstr!=null&&startstr!=""?Integer.parseInt(request.getParameter("start")):null;
            int limit = limitstr!=null&&limitstr!=""?Integer.parseInt(request.getParameter("limit")):null;
            int page = pagestr!=null&&pagestr!=""?Integer.parseInt(request.getParameter("page")):null;


        Map param = new HashMap();
            param.put("startDate",startDateStr+" 00:00");
            param.put("endDate",endDateStr+" 23:59");
            param.put("queryType",queryType);





            try {

                //查询记录
                if(queryType==null||queryType==""||queryType.equals("0")||queryType.equals("null")){

                    List<EmailInfo> emailInfos = fuelBusinessService.queryEmailInfo(param);


                    if(emailInfos.size()!=0){
                        int retStart = 0, retFin = 0;
                        retFin = page*limit > emailInfos.size() ? emailInfos.size():page*limit;
                        retStart = (page-1)*limit > emailInfos.size()-1 ? emailInfos.size()-1:(page-1)*limit;
                        result.setTotalCount(emailInfos.size());
                        result.setData(emailInfos.subList(retStart,retFin));
                    }

                //查询未发送邮件数据
                }else{

                    List<EmailInfo> emailInfos = fuelBusinessService.queryEmailInfo(param);


                    List<EmailInfo> unSendList = new ArrayList<EmailInfo>();

                    List<GasStationPrincipal> gasStationPrincipals =  fuelEmailService.getAllStation();

                    Date startDate =  simpleDateFormat.parse(startDateStr);
                    Date endDate =  simpleDateFormat.parse(endDateStr);

                    Calendar dd = Calendar.getInstance();//定义日期实例
                    dd.setTime(startDate);//设置日期起始时间

                    //循环日期
                    while(!dd.getTime().after(endDate)){
                        String str = simpleDateFormat.format(dd.getTime());
                        dd.add(Calendar.DATE, 1);//进行当前日期月份加1
                        //循环站点
                        for (GasStationPrincipal gasStationPrincipal : gasStationPrincipals){
                                  if(!emailListContainsRecord(emailInfos,str,gasStationPrincipal)){
                                      EmailInfo emailInfo = new EmailInfo();
                                      emailInfo.setCustomItem1(gasStationPrincipal.getGasStation());
                                      emailInfo.setProcessingState(-1);
                                      emailInfo.setSendTimeStr(str);

                                      unSendList.add(emailInfo);
                                  }
                        }

                    }


                    int retStart = 0, retFin = 0;
                    retFin = page*limit > unSendList.size() ? unSendList.size():page*limit;
                    retStart = (page-1)*limit > unSendList.size()-1 ? unSendList.size()-1:(page-1)*limit;
                    result.setTotalCount(unSendList.size());
                    result.setData(unSendList.subList(retStart,retFin));

                }


            } catch (Exception e) {
                LOG.error("FuelEmailController->getEmailInfo方法执行失败！" + e.toString());
            }


        return result;
    }

    private boolean gasStationDetailContainsRecord(List<GasStationDataForDay> list,String date,GasStationPrincipal gasStationPrincipal){

        for(GasStationDataForDay gasStationDataForDay :list){
            if(gasStationDataForDay.getGasStation().equals(gasStationPrincipal.getGasStation())&&gasStationDataForDay.getGasTimeStr().equals(date)){
                 return true;
            }

        }
        return false;

    }



    private boolean emailListContainsRecord(List<EmailInfo> emailInfoList,String date,GasStationPrincipal gasStationPrincipal){

        for(EmailInfo emailInfo :emailInfoList){
            if(emailInfo.getSendAddress().equals(gasStationPrincipal.getEmail())&&emailInfo.getSendTimeStr().equals(date)){
                return true;
            }
        }
        return false;

    }

    @RequestMapping(value="/receiveEmail")
    public @ResponseBody boolean receiveEmail(HttpServletRequest request, HttpServletResponse response){
        //收邮件
        return  fuelEmailService.receiveMail();
//        return true;
    }
    
    @RequestMapping(value="/downloadEmail")
    public @ResponseBody boolean downloadEmail(HttpServletRequest request, HttpServletResponse response){
    	//收邮件
    	Boolean boo = fuelEmailService.receiveMail();
    	//解析邮件
        fuelEmailService.processingUnProcessingEmail();
    	return  boo;
    }

    @RequestMapping(value="/processingEmail")
    public void processingEmail(HttpServletRequest request, HttpServletResponse response){
        //解析邮件
        fuelEmailService.processingUnProcessingEmail();
    }


    @RequestMapping(value="/getEmailProcessingLog")
    public @ResponseBody PagingResult<EmailProcessingLog> getEmailProcessingLog(HttpServletRequest request, HttpServletResponse response) {
        String emailId = request.getParameter("emailId");

        List<EmailProcessingLog> list = fuelEmailService.queryByEmailId(emailId);

        PagingResult<EmailProcessingLog> result = new PagingResult<EmailProcessingLog>();

        result.setData(list);

        result.setTotalCount(list.size());

        return result;
    }



    }
