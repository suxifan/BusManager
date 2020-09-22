package com.cictec.web.fuel.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final List<String>fuelMonthReportTitle = Arrays.asList("序号","路别","数量","协议价格","金额");
	
	public static final List<String>groupFuelMonthReportTitle = Arrays.asList("序号","燃气公司","单位","数量","协议价格","金额");
	
	public static final List<String>multiFuelAddCountReport = Arrays.asList("序号","加气日期","加气时间","车牌号","自编号","加气量","备注");
	
	public static final List<String>QueryBranchFuelReportTitle = Arrays.asList("序号","分公司","路队","线路","数量","协议价格","金额");
	
	public static final List<String>QueryBranchBusFuelReportTitle = Arrays.asList("序号","车牌号","自编号","数量");
	
//	public static final List<String>QueryBranchDetailFuelReportTitle = Arrays.asList("序号","加气日期","分公司","路队","线路","车牌号","自编号","运营类型","数量","司机","加气员","加气站","枪号","加气时间","是否刷卡","备注");

    public static final List<String>QueryBranchDetailFuelReportTitle = Arrays.asList("序号","加气日期","分公司","路队","线路","车牌号","自编号","运营类型","数量","加气站","加气时间");


    public static final List<String> QueryBranchModifiedFuelReportTitle = Arrays.asList("序号","日期","加气站","加气员","原始加气量","修改后加气量","加气量照片","备注");

	//停驶车辆状态
	public static final int STOP_RUNNING_BUS_STATUS = 3;
	
	//消息提醒状态 (0:未读     1:已读)
	public static final int NOT_READ_REMIND_MSG_STATUS = 0;
	
	public static final int READ_REMIND_MSG_STATUS = 1;
	
	//系统提醒消息类型-记录未上传
	public static final int GAS_RECORD_NOT_UPLOAD_REMIND_TYPE = 1;
	//系统提醒消息类型-停驶车辆加气提醒	
	public static final int STOP_RUNNING_BUS_ADDGAS_REMIND_TYPE = 2;
	//系统提醒消息类型-报修提醒
	public static final int DEVICE_REPAIR_REMIND_TYPE = 3;
	//系统提醒消息类型-在用设备长时间不上线提醒
	public static final int DEVICE_LONGTIME_NOT_TO_USE_REMIND_TYPE = 5;
	//系统提醒消息类型-多次加气提醒
	public static final int MULTI_ADD_GAS_REMIND_TYPE = 6;
	//系统提醒消息类型-中断提醒
	public static final int EXCEPTION_ERROR_REMIND_TYPE = 7;
	
	//设备使用状态(0:未使用     1:在使用)
	public static final int DEVICE_STATUS_USE = 1;
	
	public static final int DEVICE_STATUS_UNUSED = 0;
	
	//加气站类型ID
	public static final String GAS_STATION_TYPE_ID = "3";

	//参数设置对应的key---start
	//同一车连续加气记录时间段设置
	public static final String GAS_TIME_INTERVAL_SET = "GasTimeIntervalSet";
	//公交车牌前缀设置
	public static final String LICENSE_PLATE_PREFIX = "LicensePlatePrefix";
	//设备不上线时间设置
	public static final String NOT_ONLINE_TIME_SETTING = "NotOnlineTimeSetting";
	//加气协议价设置
	public static final String GAS_AGREEMENT_PRICE = "GasAgreementPrice";
	//同一车辆多次加气次数设置
	public static final String MUTI_GAS_TIMES_SETTING = "MutiGasTimesSetting";	
	//参数设置对应的key---end	
	
	//用户类型-加气员
	public static final String USER_TYPE_GAS = "2";
	
	//加气记录状态 
	public static final int GAS_STATUS_NORMAL = 1;
	public static final int GAS_STATUS_NOT_AUDIT = 2;
	public static final int GAS_STATUS_AUDIT_SUCCESS = 3;
	public static final int GAS_STATUS_AUDIT_FAILURE = 4;

	
	
}
