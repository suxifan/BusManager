package com.cictec.web.fuel.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.fuel.model.*;

public interface IFuelBusinessService {
	/**
	 * 校验用户名密码，校验成功返回User对象，校验失败返回null
	 * @param account
	 * @param pwd
	 * @return
	 */
	UserAuthData checkLogin(String account , String pwd);
	/**
	 * 封装Data到ResponseInfo
	 * @param user
	 * @return
	 */
	ResponseInfo packageDataToResponseInfo(UserAuthData user);
	
	/**
	 * 设备报修，通过IMEI查询设备，并插入消息提醒
	 * @param deviceImei
	 * @return
	 */
	boolean deviceRepair(String deviceImei);
	
	/**
	 * 数据同步
	 * @return
	 */
	public ResponseInfo getAllData();
	
	/**
	 * 版本更新，根据系统类型，查询版本信息，封装到ResponseInfo
	 * @param osType
	 * @return
	 */
	ResponseInfo getAppVersionToResponseInfo(String osType);
	
	/**
	 * 保存上传的加气记录信息
	 * @return
	 */
	ResponseInfo saveUploadGasRecordInfo(GasRecordUpload gasRecordUpload) throws Exception ;
	
	
	public void insertErrorRemind();
	
	GasDetail getGasDetailByGasUUid(String gasUUid);

    /**
     * 保存加气记录统计单
     * @param map
     * @return
     */
    public boolean saveClassFuleStat(Map<String,String> map);

    /**
     * 保持加气记录
     * @param gasRecord
     * @return
     */
    public boolean saveGasRecord(GasRecord gasRecord);

    /**
     * 保存加气明细信息
     * @param gasDetail
     * @return
     */
    public boolean saveGasDetail(GasDetail gasDetail);


    /**
     * 加气数据重复性校验
     * @param gasMsg
     * @return
     */
    public boolean checkGasMsg(GasMsg gasMsg);


    /**
     * 保持读取的邮件信息
     * @param emailInfo
     */
    public void saveEmailInfo(EmailInfo emailInfo);



    public EmailInfo getEmailInfoById(String emailId);

    /**
     * 通过Email地址获取所属加气站
     * @param email
     * @return
     */
    public GasStationPrincipal getGasStationByEmail(String email);


    public List<EmailInfo> queryEmailInfo(Map param);

    /**
     * 获取 该车在该年每个月的加气量集合
     * @param year
     * @param busNum
     * @return
     */
    List getFuelMonthDataByBusNumForYear(String year, String busNum);
}
