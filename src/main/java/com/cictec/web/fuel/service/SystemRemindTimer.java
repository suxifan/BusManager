package com.cictec.web.fuel.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.model.CollectDevice;
import com.cictec.web.fuel.model.GasDetail;
import com.cictec.web.fuel.model.GasRecord;
import com.cictec.web.fuel.model.GasRemind;
import com.cictec.web.fuel.model.GasStationPrincipal;
import com.cictec.web.fuel.model.ParamSetting;
import com.cictec.web.fuel.service.IBusInfoService;
import com.cictec.web.fuel.service.IFuelReportService;
import com.cictec.web.fuel.service.IFuelSystemManagerService;
import com.cictec.web.fuel.service.IGasStationService;
import com.cictec.web.fuel.service.ISystemRemindService;
import com.cictec.web.fuel.util.ToolsUtil;

@Service
public class SystemRemindTimer {
	
	private static final Logger LOG = Logger.getLogger(SystemRemindTimer.class);

	public final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
  	@Resource(name="systemRemindService")
    public ISystemRemindService systemRemindService;
  	
  	@Resource(name="busInfoService")
  	public IBusInfoService busInfoService;
  	
  	@Resource(name="fuelSystemManagerService")
    public IFuelSystemManagerService fuelSystemManagerService;
  	
	@Resource(name = "fuelReportService")
	public IFuelReportService fuelreportservice;
	
	@Resource(name = "gasStationService")
	public IGasStationService gasStationService;


    @Resource(name="fuelEmailService")
    public IFuelEmailService fuelEmailService;

	
	@PostConstruct
	private void scheduleTask() {
		
		//每次任务开始日期
		final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date checkTaskTime = calendar.getTime();
        
        //每次任务开始日期的前一天
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	final Date lastDayTime = calendar.getTime();
    	final String lastDayFormCheckTaskDay = format.format(lastDayTime);
        

        //中午12点收取邮件
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);   //邮件当天中午12点检查
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date dayCheckTaskTime = calendar.getTime();

        //晚上11点解析数据
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);  //附件解析当天晚上11点进行
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date nightCheckTaskTime = calendar.getTime();

        Timer timer = new Timer();


        timer.scheduleAtFixedRate(new TimerTask() {


            //中午12点收一次邮件
            @Override
            public void run(){

                //收邮件
                fuelEmailService.receiveMail();
            }

        }, dayCheckTaskTime, 1000 * 60 * 60 * 24);//这里设定将延时每天固定执行


          //晚上11点收一次,然后解析
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run(){

                //收邮件
                fuelEmailService.receiveMail();
                //解析邮件
                fuelEmailService.processingUnProcessingEmail();

            }

        }, nightCheckTaskTime, 1000 * 60 * 60 * 24);//这里设定将延时每天固定执行


//        timer.scheduleAtFixedRate(new TimerTask() {
//
//			@Override
//			public void run() {
//            	//加气站晚班换班加气数据未上传提醒
//            	List<GasStationPrincipal> gasStations = gasStationService.getAllGasStationInfo();
//        		for (GasStationPrincipal gasStation : gasStations) {
//
//        			String nightTime = gasStation.getNightTime();
//        			Calendar calendar = Calendar.getInstance();
//        			calendar.setTime(ToolsUtil.StrToDate(timeFormat, nightCheckTaskDay + " " + nightTime.split("-")[0]));
//        			calendar.add(Calendar.DAY_OF_MONTH, -1);
//        			calendar.add(Calendar.HOUR_OF_DAY, 2);
//        			String startTimeStr = timeFormat.format(calendar.getTime());
//
//        			calendar.setTime(ToolsUtil.StrToDate(timeFormat, nightCheckTaskDay + " " + nightTime.split("-")[1]));
//        			calendar.add(Calendar.HOUR_OF_DAY, 2);
//        			String endTimeStr = timeFormat.format(calendar.getTime());
//
//        			GasRecord notUploasGasRecord = new GasRecord();
//        			notUploasGasRecord.setStartTimeStr(startTimeStr);
//        			notUploasGasRecord.setEndTimeStr(endTimeStr);
//        			notUploasGasRecord.setGasStationId(gasStation.getGasStationId());
//        			List<GasRecord> gasRecords = systemRemindService.getGasRecordByParam(notUploasGasRecord);
//        			if(gasRecords != null && !gasRecords.isEmpty()) {
//            			GasRemind remind = new GasRemind();
//            			remind.setRemindId(UUIDGenerator.genUuidStr());
//            			remind.setRemindType(Constants.GAS_RECORD_NOT_UPLOAD_REMIND_TYPE);
//            			remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
//            			String msg = nightCheckTaskDay + ", 加气站:" + gasStation.getGasStationId() + ", 晚班已经换班2小时, 尚未有加气数据上传！"
//            					+ "加气站负责人:" + gasStation.getPrincipal() + ", 联系电话:" + gasStation.getMobilePhone();
//            			remind.setRemindContent(msg);
//            			remind.setCreated(ToolsUtil.getCurrTime());//nightCheckTaskTime
//            			systemRemindService.insert(remind);
//        			}
//				}
//			}
//
//        }, nightCheckTaskTime, 1000 * 60 * 60 * 24);//这里设定将延时每天固定执行
        
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//			@Override
//			public void run() {
//            	//加气站白班换班加气数据未上传提醒
//            	List<GasStationPrincipal> gasStations = gasStationService.getAllGasStationInfo();
//        		for (GasStationPrincipal gasStation : gasStations) {
//
//        			String daytime = gasStation.getDayTime();
//        			Calendar calendar = Calendar.getInstance();
//        			calendar.setTime(ToolsUtil.StrToDate(timeFormat, dayCheckTaskDay + " " + daytime.split("-")[0]));
//        			calendar.add(Calendar.HOUR_OF_DAY, 2);
//        			String startTimeStr = timeFormat.format(calendar.getTime());
//
//        			calendar.setTime(ToolsUtil.StrToDate(timeFormat, dayCheckTaskDay + " " + daytime.split("-")[1]));
//        			calendar.add(Calendar.HOUR_OF_DAY, 2);
//        			String endTimeStr = timeFormat.format(calendar.getTime());
//
//        			GasRecord notUploasGasRecord = new GasRecord();
//        			notUploasGasRecord.setStartTimeStr(startTimeStr);
//        			notUploasGasRecord.setEndTimeStr(endTimeStr);
//        			notUploasGasRecord.setGasStationId(gasStation.getGasStationId());
//        			List<GasRecord> gasRecords = systemRemindService.getGasRecordByParam(notUploasGasRecord);
//        			if(gasRecords != null && !gasRecords.isEmpty()) {
//            			GasRemind remind = new GasRemind();
//            			remind.setRemindId(UUIDGenerator.genUuidStr());
//            			remind.setRemindType(Constants.GAS_RECORD_NOT_UPLOAD_REMIND_TYPE);
//            			remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
//            			String msg = dayCheckTaskDay + ", 加气站:" + gasStation.getGasStationId() + ", 白班已经换班2小时, 尚未有加气数据上传！"
//            					+ "加气站负责人:" + gasStation.getPrincipal() + ", 联系电话:" + gasStation.getMobilePhone();
//            			remind.setRemindContent(msg);
//            			remind.setCreated(nightCheckTaskTime);//dayCheckTaskTime
//            			systemRemindService.insert(remind);
//        			}
//				}
//			}
//
//        }, dayCheckTaskTime, 1000 * 60 * 60 * 24);//这里设定将延时每天固定执行
        
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//            	//停驶车辆加气提醒
//            	if (busInfoService == null) {
//            		return;
//            	}
//            	List<String> stopRunningBusNums = busInfoService.getBusNumsByStatus(Constants.STOP_RUNNING_BUS_STATUS);
//            	for (String stopRunningBusNum : stopRunningBusNums) {
//
//            		GasRecord gasRecord = new GasRecord();
//            		gasRecord.setBusNum(stopRunningBusNum);
//            		gasRecord.setGasDayStr(lastDayFormCheckTaskDay);
//            		List<GasRecord> records = systemRemindService.getGasRecordByParam(gasRecord);
//            		if(records != null && !records.isEmpty()) {
//            			GasRemind remind = new GasRemind();
//            			remind.setRemindId(UUIDGenerator.genUuidStr());
//            			remind.setRemindType(Constants.STOP_RUNNING_BUS_ADDGAS_REMIND_TYPE);
//            			remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
//            			remind.setRemindContent(lastDayFormCheckTaskDay + ", 停驶车辆：" + stopRunningBusNum + ", 有加气记录！");
//            			remind.setCreated(dayCheckTaskTime);//checkTaskTime
//            			systemRemindService.insert(remind);
//            		}
//				}

//            	//设备不在线时间(天)
//            	ParamSetting param1 = fuelSystemManagerService.getParamSettingByType(Constants.NOT_ONLINE_TIME_SETTING);
//                int deviceNotOnlineTime = Integer.valueOf(param1.getParaValue());
//
//            	calendar.add(Calendar.DAY_OF_MONTH, 0-deviceNotOnlineTime);
//            	String startDay = format.format(calendar.getTime());
//            	for (CollectDevice device : collectDevices) {
//
//            		GasRecord gasRecord = new GasRecord();
//            		gasRecord.setDeviceImei(device.getDeviceImei());
//            		gasRecord.setStartDayStr(startDay);
//            		gasRecord.setEndDayStr(lastDayFormCheckTaskDay);
//            		List<GasRecord> records = systemRemindService.getGasRecordByParam(gasRecord);
//            		if(records == null || records.isEmpty()) {
//            			GasRemind remind = new GasRemind();
//            			remind.setRemindId(UUIDGenerator.genUuidStr());
//            			remind.setRemindType(Constants.DEVICE_LONGTIME_NOT_TO_USE_REMIND_TYPE);
//            			remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
//            			String msg = startDay + " 到 " + lastDayFormCheckTaskDay + "这段时间内, " + device.getGasStation() + "【设备号:" + device.getDeviceNum()
//            					    + ", 设备名称:" + device.getDeviceName() + "】长时间没上线！";
//            			remind.setRemindContent(msg);
//            			remind.setCreated(ToolsUtil.getCurrTime());
//            			systemRemindService.insert(remind);
//            		}
//				}
            	//多次加气提醒
//    			Map<String, String> mutilGasParaMap = new HashMap<String, String>();
//    			paraMap.put("gasDate", lastDayFormCheckTaskDay);
//    			//每天规定加气的最大次数
//    			ParamSetting param2 = fuelSystemManagerService.getParamSettingByType(Constants.MUTI_GAS_TIMES_SETTING);
//    			int multiAddGasCount = Integer.valueOf(param2.getParaValue());
//
//    			paraMap.put("mutiGasTimes", multiAddGasCount);
//
//    			List<GasDetail> multiAddGasRecords = fuelreportservice.getMultiFuelAddRecord(mutilGasParaMap);
//    			for (GasDetail record : multiAddGasRecords) {
//        			GasRemind remind = new GasRemind();
//        			remind.setRemindId(UUIDGenerator.genUuidStr());
//        			remind.setRemindType(Constants.MULTI_ADD_GAS_REMIND_TYPE);
//        			remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
//        			String msg = lastDayFormCheckTaskDay + ", 车牌号:" + record.getBusNum() + "有" + multiAddGasCount + "次以上加气记录！";
//        			remind.setRemindContent(msg);
//        			remind.setCreated(ToolsUtil.getCurrTime());//checkTaskTime
//        			systemRemindService.insert(remind);
//				}
//
//            }
//        }, checkTaskTime, 1000 * 60 * 60 * 24);//这里设定将延时每天固定执行
	}
	
}
