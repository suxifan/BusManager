package com.cictec.web.fuel.service;

import com.cictec.web.auth.dao.IOrgAuthDao;
import com.cictec.web.auth.dao.IUserAuthManagementDao;
import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.dao.*;
import com.cictec.web.fuel.model.*;
import com.cictec.web.fuel.util.PropertiesUtil;
import com.cictec.web.fuel.util.ToolsUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("fuelEmailService")
public class FuelEmailService implements IFuelEmailService{
	private static final Logger LOG = Logger.getLogger(FuelEmailService.class);
	
	@Autowired
	@Qualifier("userAuthManagementDao")
	private IUserAuthManagementDao userAuthManagementDao;
 
	@Autowired
	@Qualifier("busInfoMapperImpl")
	private BusInfoMapper busInfoDao;
	
	
	@Autowired
	@Qualifier("orgAuthDao")
	private IOrgAuthDao orgAuthDao;
	
	@Resource(name="collectDeviceMapperImpl")
	private CollectDeviceMapper collectDeviceDao;

	
	@Autowired
	@Qualifier("gasGunMapperImpl")
	private GasGunMapper gasGunDao;

    @Autowired
    @Qualifier("classFuelStatMapperImpl")
    private ClassFuelStatMapper classFuelStatDao;


	@Resource(name="appVersionMapperImpl")
	private AppVersionMapper appVersionDao;
	
	@Autowired
	@Qualifier("gasRecordMapperImpl")
	private GasRecordMapper gasRecordDao;
	
	@Autowired
	@Qualifier("gasDetailMapperImpl")
	private GasDetailMapper gasDetailDao;
	
	@Autowired
	@Qualifier("gasModifiedMapperImpl")
	private GasModifiedMapper gasModifiedDao;
	
	@Autowired
	@Qualifier("paramSettingMapperImpl")
	private ParamSettingMapper paramSettingDao;
	
	@Autowired
	@Qualifier("gasStationPrincipalMapperImpl")
	private GasStationPrincipalMapper gasStationPrincipalDao;

    @Autowired
    @Qualifier("emailInfoMapperImpl")
    private EmailInfoMapper emailInfoMapperDao;

    @Autowired
    @Qualifier("emailProcessingLogMapperImpl")
    private EmailProcessingLogMapper emailProcessingLogMapperDao;

    @Autowired
    @Qualifier("gasRemindMapperImpl")
    private GasRemindMapper gasRemindDao;


    @Override
    public void readExcel(EmailInfo emailInfo,GasStationPrincipal gasStationPrincipal) {


        if(emailInfo!=null){

            int  emailProcessingType  = EmailInfo.EMAIL_PROCESSING_TYPE_PROCESSING; // 状态修改为处理中

            //配置根目录
            //获取上传文件存放目录
            String rootPath =   PropertiesUtil.getProperty("upload.RootDir");

            String filePath = rootPath+emailInfo.getExcelPath();





            //获取模板参数
            String excelParam = gasStationPrincipal.getExcelParam();

            String[] params = excelParam.split(",");




            InputStream stream = null;
            try {

                stream = new FileInputStream(filePath);

                Workbook wb = null;

                //获取文件后缀名
                String prefix = filePath.substring(filePath.lastIndexOf(".")+1);
                //2003的excel
                if(prefix.equals("xls")){

                    wb = new HSSFWorkbook(stream);

                }
                //2007及以上excel
                else if(prefix.equals("xlsx")){

                    wb = new XSSFWorkbook(stream);

                }
                else{
                    LOG.error("文件格式不正确!");
                }

                if(wb!=null){
                    Sheet sheet1 = wb.getSheetAt(0);


                    int i = Integer.parseInt(params[0]);
                    int busCellNum =   Integer.parseInt(params[1]);
                    int gasVollNum =   Integer.parseInt(params[2]);
                    int gastimeNum = Integer.parseInt(params[3]);
                    for(;i<=sheet1.getLastRowNum();i++){

                        Row row = sheet1.getRow(i);

                        Cell cell1 = row.getCell(busCellNum);
                        Cell cell2 = row.getCell(gasVollNum);
                        Cell cell3 = row.getCell(gastimeNum);


                        if(checkCellIsEmpty(cell1)&&checkCellIsEmpty(cell2)&&checkCellIsEmpty(cell3)){
                            GasMsg gasMsg = new GasMsg();

                            try{

                                //时间是字符串需要转换


                                //封装加气信息
                                gasMsg.setBusNum(cell1.getStringCellValue().toUpperCase());
                                gasMsg.setGasVolume(new BigDecimal(cell2.getNumericCellValue()));

                                Date gasDate = null;
                                if(cell3.getCellType()==Cell.CELL_TYPE_STRING){
                                    String dateStr = cell3.getStringCellValue();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss",Locale.US);
                                    try{
                                        gasDate = sdf.parse(dateStr);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }else{
                                    gasDate = cell3.getDateCellValue();
                                }
                                gasMsg.setGasTime(gasDate);

                                //如果是导入信息,从cell里面取加气站
                                if(gasStationPrincipal.getGasStationId().equals("1")){

                                    Cell cell4 = row.getCell(Integer.parseInt(params[4]));
                                    String gasStationName = cell4.getStringCellValue();

                                    //获取所有加气站
                                    List<Orgs> stations = orgAuthDao.getOrgsByOrgType(Constants.GAS_STATION_TYPE_ID);
                                    Orgs org = getSameOrgsByOrgName(stations,gasStationName);


                                    gasMsg.setGasStationId(org.getOrgId());
                                    gasMsg.setGasStationName(org.getOrgName());
                                    gasMsg.setStatus(GasMsg.SYSTEM_IMPORT);

                                }
                                else{
                                    gasMsg.setGasStationId(gasStationPrincipal.getGasStationId());
                                    gasMsg.setGasStationName(gasStationPrincipal.getGasStation());
                                    gasMsg.setStatus(GasMsg.EMAIL_UPLOAD);
                                }

                            } catch (IllegalStateException e){
                                  e.printStackTrace();
                            }

                            if(chechGasMsg(gasMsg)){
                                //处理加气信息失败
                                if(!gasMsgHandle(gasMsg)){
                                    EmailProcessingLog emailProcessingLog = new EmailProcessingLog();
                                    emailProcessingLog.setUuid(UUIDGenerator.genUuidStr());
                                    emailProcessingLog.setEmailId(emailInfo.getEmailId());
                                    emailProcessingLog.setCreatTime(new Date());
                                    emailProcessingLog.setContent("第"+(i+1)+"行  数据有误!");

                                    emailProcessingLogMapperDao.insert(emailProcessingLog);

                                    emailProcessingType = EmailInfo.EMAIL_PROCESSING_TYPE_ERRORDATA;

                                    CellStyle cellStyle=wb.createCellStyle();
                                    cellStyle.setFillForegroundColor(HSSFColor.RED.index);
                                    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

                                    Iterator<Cell> it = row.cellIterator();
                                    while (it.hasNext()){
                                        Cell cell =  it.next();
                                        cell.setCellStyle(cellStyle);

                                    }

                                }else{//加气记录导入成功
                                    CellStyle cellStyle=wb.createCellStyle();
                                    cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
                                    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

                                    Iterator<Cell> it = row.cellIterator();
                                    while (it.hasNext()){
                                        Cell cell =  it.next();
                                        cell.setCellStyle(cellStyle);
                                    }
                                }
                            }else{

                                EmailProcessingLog emailProcessingLog = new EmailProcessingLog();
                                emailProcessingLog.setUuid(UUIDGenerator.genUuidStr());
                                emailProcessingLog.setEmailId(emailInfo.getEmailId());
                                emailProcessingLog.setCreatTime(new Date());
                                emailProcessingLog.setContent("第"+ (i+1) +"行  数据已存在!");

                                emailProcessingLogMapperDao.insert(emailProcessingLog);

                                emailProcessingType = EmailInfo.EMAIL_PROCESSING_TYPE_ERRORDATA;

                                CellStyle cellStyle=wb.createCellStyle();
                                cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
                                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

                                Iterator<Cell> it = row.cellIterator();
                                while (it.hasNext()){
                                    Cell cell =  it.next();
                                    cell.setCellStyle(cellStyle);

                                }

                            }

                        }else{
                            //最后一行信息为空不提示数据不完整
                            if(i<sheet1.getLastRowNum()){


                                EmailProcessingLog emailProcessingLog = new EmailProcessingLog();
                                emailProcessingLog.setUuid(UUIDGenerator.genUuidStr());
                                emailProcessingLog.setEmailId(emailInfo.getEmailId());
                                emailProcessingLog.setCreatTime(new Date());
                                emailProcessingLog.setContent("第"+ (i+1) +"行  数据有误!");

                                emailProcessingLogMapperDao.insert(emailProcessingLog);

                                emailProcessingType = EmailInfo.EMAIL_PROCESSING_TYPE_ERRORDATA;


                                //设置样式
                                CellStyle cellStyle=wb.createCellStyle();
                                cellStyle.setFillForegroundColor(HSSFColor.RED.index);
                                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

                                Iterator<Cell> it = row.cellIterator();
                                while (it.hasNext()){
                                    Cell cell =  it.next();
                                    cell.setCellStyle(cellStyle);

                                }


                            }

                        }


                           try{
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss",Locale.US);
                            Date date = cell3.getDateCellValue();
                            cell3.setCellType(Cell.CELL_TYPE_STRING);
                            cell3.setCellValue(sdf.format(date));
                           }catch (Exception e){
                               LOG.error(filePath+"第"+(i+1)+"时间格式有误!");
                           }

                    }
                }

                FileOutputStream fileOut=new FileOutputStream(filePath);
                wb.write(fileOut);
                fileOut.close();
            }

            catch (FileNotFoundException e) {


                EmailProcessingLog emailProcessingLog = new EmailProcessingLog();
                emailProcessingLog.setUuid(UUIDGenerator.genUuidStr());
                emailProcessingLog.setEmailId(emailInfo.getEmailId());
                emailProcessingLog.setCreatTime(new Date());
                emailProcessingLog.setContent("异常中断");

                emailProcessingLogMapperDao.insert(emailProcessingLog);

                emailProcessingType = EmailInfo.EMAIL_PROCESSING_TYPE_ERRORDATA;


                e.printStackTrace();
            }
            catch (IOException e) {

                EmailProcessingLog emailProcessingLog = new EmailProcessingLog();
                emailProcessingLog.setUuid(UUIDGenerator.genUuidStr());
                emailProcessingLog.setEmailId(emailInfo.getEmailId());
                emailProcessingLog.setCreatTime(new Date());
                emailProcessingLog.setContent("异常中断");

                emailProcessingLogMapperDao.insert(emailProcessingLog);

                emailProcessingType = EmailInfo.EMAIL_PROCESSING_TYPE_ERRORDATA;

                e.printStackTrace();
            } finally {
                if(emailInfo.getProcessingState()!=emailProcessingType){
                    emailInfo.setProcessingState(emailProcessingType);
                    emailInfoMapperDao.updateByPrimaryKey(emailInfo);
                }
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }





    @Override
    public boolean receiveMail() {
        boolean isSuccess = true;
        try {


        //需要从配置文件读取
        String EmailServiceType = PropertiesUtil.getProperty("email.EmailServiceType");
        String EmailServiceUrl = PropertiesUtil.getProperty("email.EmailServiceUrl");
        int EmailServicePort = Integer.parseInt(PropertiesUtil.getProperty("email.EmailServicePort"));
        String EmailUserName = PropertiesUtil.getProperty("email.EmailUserName");
        String EmailPsw = PropertiesUtil.getProperty("email.EmailPsw");


        //准备加气站基础信息
        List<GasStationPrincipal> gasStationPrincipals =   gasStationPrincipalDao.getGasStationByParams(new GasStationPrincipal());

        List<String> gasStationEmails = new ArrayList<String>();

        Map<String,GasStationPrincipal> emailGasStationMap = new HashMap<String,GasStationPrincipal>();

        for(GasStationPrincipal gasStationPrincipal:gasStationPrincipals){
            gasStationEmails.add(gasStationPrincipal.getEmail());
            emailGasStationMap.put(gasStationPrincipal.getEmail(),gasStationPrincipal);
        }




            //连接信息
            URLName urlname = new URLName(EmailServiceType,EmailServiceUrl,EmailServicePort,null,EmailUserName,EmailPsw);

            Session session = Session.getInstance( new Properties());

            Store store = session.getStore(urlname);

            store.connect();

            Folder folder = store.getDefaultFolder();// 默认父目录

            if (folder == null) {
                LOG.error("服务器不可用");
                isSuccess = false;
            }


            Folder popFolder = folder.getFolder("INBOX");// 获取收件箱

            Folder fuelFolder =  folder.getFolder("fuelInfo");  //燃料文件夹

            popFolder.open(Folder.READ_WRITE);// 可读邮件,可以删邮件的模式打开目录
            //列出来收件箱 下所有邮件
            Message[] messages = popFolder.getMessages();

            for(Message message : messages){

                //发件人邮箱
                InternetAddress a = (InternetAddress)message.getFrom()[0];


                //过滤加气站的邮箱,只对加气站发过来的邮件进行处理
                if(gasStationEmails.contains(a.getAddress())){

                    //如果有附件,则进行解析
                    if( message.isMimeType("multipart/*") ){


                        GasStationPrincipal gasStationPrincipal =  emailGasStationMap.get(a.getAddress());
                        gasStationPrincipal.setEmailSendDate(message.getSentDate());
                        //解析邮件
                        mailReceiver(message, gasStationPrincipal);

                        //有附件的邮件归档到燃料文件夹
                        Message[] fuelMsg = new Message[]{message};
                        popFolder.copyMessages(fuelMsg,fuelFolder);

                        //删除收件箱原文件
                        message.setFlag(Flags.Flag.DELETED, true);
                    }

                }
            }

            popFolder.close(true);

            store.close();

        } catch (Exception e) {
            isSuccess = false;
            LOG.error("邮件服务器连接失败!");
        }
         finally {
            return isSuccess;
        }
    }


    private boolean gasStationEmailSendType(List<EmailInfo> emailInfos,GasStationPrincipal gasStationPrincipal){

        String emailAdd = gasStationPrincipal.getEmail();


        //如果是系统导入,直接返回TRUE
        if(gasStationPrincipal.getGasStationId().equals("1")){
            return true;
        }

        for(EmailInfo emailInfo:emailInfos){
            if(emailInfo.getSendAddress().equals(emailAdd)){
                return true;
            }
        }

        return false;
    }


    @Override
    public void processingUnProcessingEmail() {

        try {
            //获取所有待处理邮件
            Map param = new HashMap();
            param.put("processingState", EmailInfo.EMAIL_PROCESSING_TYPE_UNPROCESSING);
            List<EmailInfo> list = emailInfoMapperDao.queryEmailInfo(param);


            //查询发送email的加气站,获取模板参数
            List<GasStationPrincipal> gasStationPrincipals = gasStationPrincipalDao.getGasStationByParams(new GasStationPrincipal());

            List<String> gasStationEmails = new ArrayList<String>();

            List<GasStationPrincipal> unSendStationList = new ArrayList<GasStationPrincipal>();

            Map<String, GasStationPrincipal> emailGasStationMap = new HashMap<String, GasStationPrincipal>();

            //准备基础数据
            for (GasStationPrincipal gasStationPrincipal : gasStationPrincipals) {
                gasStationEmails.add(gasStationPrincipal.getEmail());
                emailGasStationMap.put(gasStationPrincipal.getEmail(), gasStationPrincipal);

                if (!gasStationEmailSendType(list, gasStationPrincipal)) {
                    //数据未上传提醒

                    GasRemind remind = new GasRemind();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String day = sdf.format(new Date());
                    remind.setRemindId(UUIDGenerator.genUuidStr());
                    remind.setRemindType(Constants.GAS_RECORD_NOT_UPLOAD_REMIND_TYPE);
                    remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
                    String msg = "加气站:" + gasStationPrincipal.getGasStation() + "," + day + "  加气数据未上传！";
                    remind.setRemindContent(msg);
                    remind.setCreated(ToolsUtil.getCurrTime());//nightCheckTaskTime
                    gasRemindDao.insert(remind);

                }


            }


            //解析邮件
            for (EmailInfo emailInfo : list) {

                GasStationPrincipal gasStationPrincipal = emailGasStationMap.get(emailInfo.getSendAddress());

                readExcel(emailInfo, gasStationPrincipal);

            }
        }catch (Exception e){
            LOG.error("解析错误:"+e.toString());
        }


    }

    @Override
    public List<EmailProcessingLog> queryByEmailId(String emailId) {
        List<EmailProcessingLog> list = new ArrayList<EmailProcessingLog>();

        if(emailId!=null&&emailId!=""){
            list = emailProcessingLogMapperDao.queryByEmailId(emailId);
        }
        return list;
    }



    @Override
    public List<GasStationPrincipal> getAllStation(){
        List<GasStationPrincipal> gasStationPrincipals =    gasStationPrincipalDao.getGasStationByParams(new GasStationPrincipal());
        return      gasStationPrincipals;
    }



    /**
     * 解析邮件
     *
     * @return
     * @throws java.io.IOException
     * @throws javax.mail.MessagingException
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    private void mailReceiver(Message msg,GasStationPrincipal gasStationPrincipal)throws Exception{
        // 发件人信息
        Address[] froms = msg.getFrom();
        // getContent() 是获取包裹内容, Part相当于外包装
        Object o = msg.getContent();

        if(o instanceof Multipart) {
            Multipart multipart = (Multipart) o ;
            reMultipart(multipart,gasStationPrincipal);
        } else if (o instanceof Part){
            Part part = (Part) o;
            rePart(part,gasStationPrincipal);
        } else {
            //文本类型
        }
    }



    /**
     * @param part 解析内容
     * @throws Exception
     */
    private void rePart(Part part,GasStationPrincipal gasStationPrincipal) throws MessagingException,
            UnsupportedEncodingException, IOException, FileNotFoundException {
        if (part.getDisposition() != null) {

            String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText解决附件名乱码问题

            InputStream in = part.getInputStream();// 打开附件的输入流

            //配置根目录
            //获取上传文件存放目录
            String rootPath =   PropertiesUtil.getProperty("upload.RootDir");


            //UUid防止文件名重复
            String uuid = UUIDGenerator.genUuidStr();

            // 读取附件字节并存储到文件中
            String path = getPathByGasStationPrincipal(rootPath,gasStationPrincipal)+"/"+uuid+strFileNmae;

            String prefix = path.substring(path.lastIndexOf(".")+1);

            //excel类型的邮件进行下载和解析
            if(prefix.equals("xls")||prefix.equals("xlsx")){

                FileOutputStream out = new FileOutputStream(rootPath+path);
                int data;
                while((data = in.read()) != -1) {
                    out.write(data);
                }
                in.close();
                out.close();

                //保存邮件信息
                insertEmailInfo(path,gasStationPrincipal);
            }
        }
    }


    /**
     * 保存邮件及附件相关信息
     */
    private void insertEmailInfo(String path,GasStationPrincipal gasStationPrincipal){

        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setExcelPath(path);
        emailInfo.setSendAddress(gasStationPrincipal.getEmail());
        emailInfo.setDownloadTime(new Date());
        emailInfo.setProcessingState(EmailInfo.EMAIL_PROCESSING_TYPE_UNPROCESSING); //状态未处理
        emailInfo.setDownloadType(EmailInfo.DOWNLOAD_TYPE_YES);
        emailInfo.setEmailId(UUIDGenerator.genUuidStr());
        emailInfo.setSendTime(gasStationPrincipal.getEmailSendDate());
        emailInfo.setCustomItem1(gasStationPrincipal.getGasStation());

        saveEmailInfo(emailInfo);

    }

    /**
     * 获取时间,和加气站,获取附件归档目录
     * @param gasStationPrincipal
     * @return
     */
    private String getPathByGasStationPrincipal(String rootPath,GasStationPrincipal gasStationPrincipal){
        //配置根目录
        //获取上传文件存放目录
        //String rootPath =   PropertiesUtil.getProperty("upload.RootDir");

        File root = new File(rootPath);

        if(root.exists()){
            root.mkdir();
        }

        //二级文件夹，名称用天标识，如：20150714
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String secondPath ="/"+df.format(new Date());

        File secondFile = new File(rootPath+secondPath);
        if (!secondFile.exists()){
            secondFile.mkdir();
        }


        File  thirdFile = new File(rootPath+secondPath+"/"+gasStationPrincipal.getGasStation());
        if(!thirdFile.exists()){
            thirdFile.mkdir();
        }




        return secondPath+"/"+gasStationPrincipal.getGasStation();
    }

    /**
     * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)）
     * @throws Exception
     */
    private void reMultipart(Multipart multipart,GasStationPrincipal gasStationPrincipal) throws Exception {
        //System.out.println("邮件共有" + multipart.getCount() + "部分组成");
        // 依次处理各个部分
        for (int j = 0, n = multipart.getCount(); j < n; j++) {
            //System.out.println("处理第" + j + "部分");
            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
            // 也可能是另一个小包裹(MultipPart)
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
            if (part.getContent() instanceof Multipart) {
                Multipart p = (Multipart) part.getContent();// 转成小包裹
                //递归迭代
                reMultipart(p,gasStationPrincipal);
            } else {
                rePart(part,gasStationPrincipal);
            }
        }
    }





    /**
     * cell为空返回false,cell不为空返回true
     * @param cell
     * @return
     */
    private boolean checkCellIsEmpty(Cell cell){
        if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            return false;
        }
        else
            return true;
    }

    /**
     * 加气信息校验
     * @return
     */
    private boolean chechGasMsg(GasMsg gasMsg){
        List<GasDetail> list = gasDetailDao.getGasDetailByGasMsg(gasMsg);
        return list.size()==0?true:false;
    }


    /**
     * 处理加气记录
     * @param gasMsg
     * @return
     */
    private boolean  gasMsgHandle(GasMsg gasMsg){

        String busNum = gasMsg.getBusNum();
        ClientQueryByParam clientQueryByParam = new ClientQueryByParam();
        clientQueryByParam.setBusNum(busNum);
        try {
//            List<BusInfo> list =getBusInfo(clientQueryByParam);
            List<BusInfo> list =getBusInfoByBusNum(clientQueryByParam);
            if(list!=null&&list.size()>0){
                BusInfo busInfo = list.get(0);

                GasRecord gasRecord = new GasRecord();

                //封装加气记录
                gasRecord.setGasTime(gasMsg.getGasTime());
                gasRecord.setGasVolume(gasMsg.getGasVolume());
                gasRecord.setBusNum(busInfo.getBusNum());
                gasRecord.setSelfNum(busInfo.getSelfNum());
                gasRecord.setCreated(new Date());
                gasRecord.setGasStationId(gasMsg.getGasStationId());
                gasRecord.setGasStationName(gasMsg.getGasStationName());
                gasRecord.setGasUuid(UUIDGenerator.genUuidStr());


                GasDetail gasDetail = new GasDetail();
                gasDetail.setGasDetailId(UUIDGenerator.genUuidStr());

                gasDetail.setBusNum(busInfo.getBusNum());

                gasDetail.setCreated(new Date());
                gasDetail.setGasId(gasRecord.getGasUuid());
                gasDetail.setGasStation(gasMsg.getGasStationName());
                gasDetail.setGasStationId(gasMsg.getGasStationId());
                gasDetail.setGasTime(gasRecord.getGasTime());
                gasDetail.setGasVolume(gasRecord.getGasVolume());
                gasDetail.setSelfNum(busInfo.getSelfNum());

                //查询车牌对应的车辆信息
                gasDetail.setLineId(busInfo.getLineId());
                gasDetail.setLineName(busInfo.getLineName());
                gasDetail.setLineOrgId(busInfo.getOrgId());
                gasDetail.setLineOrgName(busInfo.getOrgName());
                gasDetail.setOperateType(busInfo.getBusStatus());

                //通过车辆所在路队，查询路队的父节点，即分公司信息。
                gasDetail.setOrgId(busInfo.getOrgParentId());
                gasDetail.setOrgName(busInfo.getOrgParentName());

                String a =orgAuthDao.getParentOrgByOrgId(gasMsg.getGasStationId()).getOrgId();

                //设置加气公司
                gasDetail.setGasStationOrgId(orgAuthDao.getParentOrgByOrgId(gasMsg.getGasStationId()).getOrgId());
                gasDetail.setGasStatus(gasMsg.getStatus());


                return saveGasRecord(gasRecord)&&saveGasDetail(gasDetail);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return false;
    }


    private List<BusInfo> getBusInfo(ClientQueryByParam daoQuery) throws Exception {

        List<BusInfo> liBus = busInfoDao.selectByParams(daoQuery);
        return liBus;
    }
    
    private List<BusInfo> getBusInfoByBusNum(ClientQueryByParam daoQuery) throws Exception {
    	
    	List<BusInfo> liBus = busInfoDao.selectByBusNum(daoQuery);
    	return liBus;
    }


    private boolean saveGasRecord(GasRecord gasRecord){

        int num = gasRecordDao.insert(gasRecord);

        return num==1?true:false;
    }


    private boolean saveGasDetail(GasDetail gasDetail) {
        int num = gasDetailDao.insert(gasDetail);

        return num==1?true:false;
    }



    public void saveEmailInfo(EmailInfo emailInfo) {

        emailInfoMapperDao.insert(emailInfo);

    }

    private Orgs getSameOrgsByOrgName(List<Orgs> orgsList, String orgName){


        for(Orgs org:orgsList){
            if(org.getOrgName().equals(orgName)){
                return org;
            }
        }


        return null;
    }


    @Override
    public List<GasStationDataForDay> getGasStationDataForDay(Map<String, String> param){
       return gasDetailDao.queryGasStationDatForDay(param);
    }


}
