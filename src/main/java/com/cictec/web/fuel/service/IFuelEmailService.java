package com.cictec.web.fuel.service;

import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.fuel.model.*;

import java.util.List;
import java.util.Map;

public interface IFuelEmailService {

    /**
     * 读取excel
     * @param emailInfo
     * @param gasStationPrincipal
     */
    public void readExcel(EmailInfo emailInfo,GasStationPrincipal gasStationPrincipal);

    /**
     * 收邮件
     */
    public boolean receiveMail();

    /**
     * 处理所有待处理邮件
     * @return
     */
    public void processingUnProcessingEmail();


    /**
     * 查询email的日志
     * @param emailId
     * @return
     */
    public List<EmailProcessingLog> queryByEmailId(String emailId);

    List<GasStationPrincipal> getAllStation();

    List<GasStationDataForDay> getGasStationDataForDay(Map<String, String> param);

//    public int queryByEmailIdCount(String emailId);

}
