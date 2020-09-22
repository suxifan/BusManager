/*
 * FuelBusinessController.java
 * Copyright 2013-2020 by 北京中航讯科技股份有限公司
 * 2015/07/06 cuigang of 北京中航讯科技股份有限公司
 */
package com.cictec.web.fuel.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cictec.web.auth.dao.BusLineMapper;
import com.cictec.web.auth.dao.IOrgAuthDao;
import com.cictec.web.auth.pojo.BusLine;
import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.dao.BusInfoMapper;
import com.cictec.web.fuel.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.service.LoginService;
import com.cictec.web.auth.service.UserAuthManagementService;
import com.cictec.web.auth.util.CommonUtil;
import com.cictec.web.fuel.service.IFuelBusinessService;
import com.cictec.web.fuel.util.PropertiesUtil;

/**
 * 燃料数据采集接口Controller
 * @author cuigang
 */
@Controller
public class FuelBusinessController {
	private static final Logger LOG = Logger.getLogger(FuelBusinessController.class);	
	
	@Resource(name="fuelBusinessService")
	private IFuelBusinessService fuelBusinessService;	
	
	@Resource(name="loginService")
    public LoginService loginService;

	@Resource(name="userAuthManagementService")
    public UserAuthManagementService userAuthManagementService;

    @Autowired
    @Qualifier("orgAuthDao")
    private IOrgAuthDao orgAuthDao;

    @Autowired
    @Qualifier("repoBusLineDao")
    private BusLineMapper busLineMapperDao;

    @Autowired
    @Qualifier("busInfoMapperImpl")
    private BusInfoMapper busInfoMapperDao;
    /**
     * 接口3.8
     * 交班加气量统计图片上传
     * @param request
     * @param response
     */
    @RequestMapping(value="/gasTotalInfo/upload", method = RequestMethod.POST)
    public void gasTotoleInfoUpload(HttpServletRequest request,HttpServletResponse response){

           boolean isSuccess = false;

           try{

               Map<String,String> map = new HashMap();

               response.setContentType("text/html;charset=UTF-8");

                //获得磁盘文件条目工厂。
                DiskFileItemFactory factory = new DiskFileItemFactory();


                //获取上传文件存放目录
                String uploadDirPath = PropertiesUtil.getProperty("upload.RootDir");
                //获取文件上传需要保存的路径。
                //String path = request.getSession().getServletContext().getRealPath(uploadDirPath);
                //如果文件夹不存在，则新建目标文件夹
                File fileRootDir = new File(uploadDirPath);
                if(!fileRootDir.exists()){
                    fileRootDir.mkdir();
                }

                //二级文件夹，名称用天标识，如：20150714
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                String secondpath ="/"+df.format(new Date());

                File secondDir = new File(uploadDirPath+secondpath);
                if(!secondDir.exists()){
                    secondDir.mkdir();
                }

                //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
                factory.setRepository(new File(uploadDirPath));
                //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
                factory.setSizeThreshold(1024*1024);
                //上传处理工具类
                ServletFileUpload upload = new ServletFileUpload(factory);

                //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。
                List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
                for(FileItem item:list){
                    //获取表单属性名字。
                    String name = item.getFieldName();
                    //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
                    if(item.isFormField()){
                        map.put(name, item.getString("utf-8"));
                    }
                    else{


                        String filename = item.getName();
                        //获取时间戳
                        String beforeFileName = System.currentTimeMillis()+"";
                        //文件名称前面加上时间戳
                        filename = beforeFileName+filename;



                        //返回文件相对路径
                        map.put("gasStatUrl",secondpath+"/"+filename);



                                /*第三方提供的方法直接写到文件中。
                                 * item.write(new File(path,filename));*/
                        //收到写到接收的文件中。
                        OutputStream out = new FileOutputStream(new File(secondDir.getPath(),filename));
                        InputStream in = item.getInputStream();

                        int length = 0;
                        byte[] buf = new byte[1024];
                        //System.out.println("获取文件总量的容量:"+ item.getSize());

                        while((length = in.read(buf))!=-1){
                            out.write(buf,0,length);
                        }
                        in.close();
                        out.close();
                    }
                 }
                 //IMEI
                 map.put("deviceImei",request.getHeader("deviceId"));
               isSuccess = this.fuelBusinessService.saveClassFuleStat(map);




           }catch(Exception e){

               isSuccess = false;

           }finally {

               ResponseInfo responseInfo = new ResponseInfo();
               responseInfo.head =  this.pachageHead(isSuccess,"上传成功!","上传失败!");
               responseInfo.data = "";

               writerJson(response,JSON.toJSONString(responseInfo));
           }




    }



	/**
	 * 用户登录（接口3.1）
	 * @author qianbaohua
	 */
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	public void userLogin(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/html;charset=UTF-8");
			
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			UserAuthData user = fuelBusinessService.checkLogin(account, password);
			ResponseInfo responseInfo = fuelBusinessService.packageDataToResponseInfo(user);		
			writerJson(response,JSON.toJSONString(responseInfo));	
		}catch(Exception e){
			LOG.error("FuelBusinessController->userLogin方法执行失败！" + e.toString());
		}
	}	
	
	/**
	 * 加气记录上传(接口3.2)
	 * @author qianbaohua
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/record/upload", method = RequestMethod.POST)
	public void recordUpload(HttpServletRequest request,HttpServletResponse response){
		boolean isSuccess = true;
		ResponseInfo responseInfo = null;
		try{
			response.setContentType("text/html;charset=UTF-8");
			
			GasRecordUpload gasRecordUpload = getGasRecordUploadByRequest(request);
			
			responseInfo =  fuelBusinessService.saveUploadGasRecordInfo(gasRecordUpload);
			
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
			//插入异常中断提醒
			fuelBusinessService.insertErrorRemind();
			LOG.error("FuelBusinessController->recordUpload方法执行失败！" + e.toString());
		}finally{
				if(responseInfo==null){
					responseInfo = new ResponseInfo();
					responseInfo.head = pachageHead(isSuccess,"上传成功","上传失败");
					responseInfo.data = "";
				}
				
				writerJson(response,JSON.toJSONString(responseInfo));
		}
		
		
	}	
	
	
	
	
	/**
	 * 封装数据到GasRecordUpload对象
	 * @author qianbaohua
	 * @param request
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private GasRecordUpload getGasRecordUploadByRequest(HttpServletRequest request) throws Exception {

			GasRecordUpload gasRecordUpload = new GasRecordUpload();
					
			Map<String,String> gasRecordUploadMap =  uploadGasEquipment(request);
			
			//map封装到bean
			BeanUtils.populate(gasRecordUpload, gasRecordUploadMap);
			
			gasRecordUpload.setDeviceImei(request.getHeader("deviceId"));
		
		return gasRecordUpload;
	}	
	
	/**
	 * 读取流，上传流文件，将上传的流文件相对路径和表单的其他属性封装到map
	 * @author qianbaohua
	 * @param request
	 * @return
	 */
	private Map<String,String> uploadGasEquipment(HttpServletRequest request) {
		
		GasRecordUpload gasRecordUpload = new GasRecordUpload();

		Map result = new HashMap();
				
		
		try{
			request.setCharacterEncoding("utf-8");
			
			
			  //如果加气记录存在，则不保存图片
            if(request.getParameter("uuid")!=null&&fuelBusinessService.getGasDetailByGasUUid(request.getParameter("uuid"))!=null){  
            	
                Enumeration paramNames = request.getParameterNames();  
                while (paramNames.hasMoreElements()) {  
                    String paramName = (String) paramNames.nextElement();  
          
                    String[] paramValues = request.getParameterValues(paramName);  
                    if (paramValues.length == 1) {  
                        String paramValue = paramValues[0];  
                        if (paramValue.length() != 0) {  
                        	result.put(paramName, paramValue);  
                        }  
                    }  
                }
                result.put("uploadPicPath", "");
                result.put("uploadBusNumPath", "");


            }else{
            	
            
			
			
	        //获得磁盘文件条目工厂。  
	        DiskFileItemFactory factory = new DiskFileItemFactory();  
	          
	        
	        //获取上传文件存放目录
	        String uploadDirPath = PropertiesUtil.getProperty("upload.RootDir");
	       //获取文件上传需要保存的路径。
	        //String path = request.getSession().getServletContext().getRealPath(uploadDirPath);  
	        //如果文件夹不存在，则新建目标文件夹
	        File fileRootDir = new File(uploadDirPath);
	        if(!fileRootDir.exists()){
	        	fileRootDir.mkdir();
	        }

	        //二级文件夹，名称用天标识，如：20150714
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
			String secondpath ="/"+df.format(new Date());
	       
			File secondDir = new File(uploadDirPath+secondpath);
			if(!secondDir.exists()){
				secondDir.mkdir();
			}
	       
	        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
	        factory.setRepository(new File(uploadDirPath));  
	        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
	        factory.setSizeThreshold(1024*1024);  
	        //上传处理工具类  
	        ServletFileUpload upload = new ServletFileUpload(factory);  
	       
	            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
	            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
	            for(FileItem item:list){  
	                //获取表单属性名字。  
	                String name = item.getFieldName();  
	                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
	                if(item.isFormField()){
	                	result.put(name, item.getString("utf-8"));
	                }
	                else{
	                	  //获取路径名
//		                String value = item.getName();
//		                //取到最后一个反斜杠。
//		                int start = value.lastIndexOf(".");
//		                //截取上传文件的 字符串名字。+1是去掉反斜杠。
//		                String filename = value.substring(start);
//
                        String filename = item.getName();
		            	//获取时间戳
		        		String beforeFileName = System.currentTimeMillis()+"";
		        		//文件名称前面加上时间戳
		        		filename = beforeFileName+filename;


                        //车牌
                        if(name.equals("busImage")){
                            result.put("uploadBusNumPath",secondpath+"/"+filename);

                        }
                        else{
                            //返回文件相对路径
                            result.put("uploadPicPath",secondpath+"/"+filename);

                        }

		                /*第三方提供的方法直接写到文件中。 
		                 * item.write(new File(path,filename));*/  
		                //收到写到接收的文件中。  
		                OutputStream out = new FileOutputStream(new File(secondDir.getPath(),filename));  
		                InputStream in = item.getInputStream();  
		                
		                int length = 0;  
		                byte[] buf = new byte[1024];
		                //System.out.println("获取文件总量的容量:"+ item.getSize());
		                  
		                while((length = in.read(buf))!=-1){  
		                    out.write(buf,0,length);  
		                }  
		                in.close();  
		                out.close();
	                }  
	            }  
            }
	           
	            
        }catch(Exception e){  
        	LOG.error("FuelBusinessController->uploadGasEquipment方法执行失败！" + e.toString());
        }  	
		
		return result;
	}	
	
	/**
	 * 报修(接口3.4)
	 * @author qianbaohua
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/device/repair", method = RequestMethod.POST)
	public void deviceRepair(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/html;charset=UTF-8");		
			//设备IMEI
			String deviceImei = request.getHeader("deviceId");			
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.head = pachageHead(fuelBusinessService.deviceRepair(deviceImei),"报修成功","报修失败");			
			responseInfo.data = "";
			writerJson(response,JSON.toJSONString(responseInfo));
		}catch(Exception e){
			LOG.error("FuelBusinessController->deviceRepair方法执行失败！" + e.toString());
		}
	}
	
	/**
	 * 返回头信息
	 * @author qianbaohua
	 * @param isSuccess
	 * @param success
	 * @param failed
	 * @return
	 */
	private Head pachageHead(boolean isSuccess,String success,String failed){
		Head head = new Head();
		if(isSuccess){
			head.setSuccess("true");
			head.setMsg(success);
		}else{
			head.setSuccess("false");
			head.setMsg(failed);
		}
		return head;
	}
	
	/**
	 * 版本更新(接口3.5)
	 * @author qianbaohua
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/version/update", method = RequestMethod.POST)
	public void versionUpdate(HttpServletRequest request, HttpServletResponse response){
		
		try{
			response.setContentType("text/html;charset=UTF-8");
			
			//系统类型
			String osType = request.getParameter("osType");
			
			ResponseInfo responseInfo = fuelBusinessService.getAppVersionToResponseInfo(osType);
			
			writerJson(response,JSON.toJSONString(responseInfo));
		}catch(Exception e){
			LOG.error("FuelBusinessController->versionUpdate方法执行失败！" + e.toString());
		}
		
	}	
	
	/**
	 * 数据同步(接口3.6) 
	 */
	@RequestMapping(value="/data/update" , method = RequestMethod.POST)
	public void updateData(HttpServletRequest request, HttpServletResponse res){		
		try {	
			res.setContentType("text/html;charset=UTF-8");		
			ResponseInfo responseInfo = fuelBusinessService.getAllData();
			writerJson(res,JSON.toJSONString(responseInfo));
			
		} catch (Exception e) {
			LOG.error("FuelBusinessController->updateData方法执行失败！" + e.toString());
		}		
	}
	
	/**
	 * 接口3.7(修改用户密码)
	 * @param request
	 * @param res
	 */
	@RequestMapping(value="/user/update/pwd",method=RequestMethod.POST)
	public void userUpdatePwd(HttpServletRequest request, HttpServletResponse res){
		
		boolean isSuccess = false ;
		
		String account = request.getParameter("account");
    	String oldPwd = request.getParameter("password");
    	String newPwd = request.getParameter("newPassword");
    	
		try {
			
			res.setContentType("text/html;charset=UTF-8");		
			
			UserAuthData user = loginService.getUserByAccount(account);
		
			
    	//密码正确
    	if(user.getDobPassword().equals(CommonUtil.getEncryptPwd(oldPwd))){
    		isSuccess = true;
    		//修改用户密码为新密码
    		String newPwdMd5 = CommonUtil.getEncryptPwd(newPwd);
    		
    		this.userAuthManagementService.updateUserPwd(user.getDobUserId(),newPwdMd5);
    		
    		
    	}
    	//密码错误
    	else{
    		isSuccess = false;
    	}
    	
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}finally{
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.head = pachageHead(isSuccess,"修改成功!","修改失败!");		
			responseInfo.data = "";
			writerJson(res,JSON.toJSONString(responseInfo));
		}
		
	}
	
	
	/**
	 * JSON传输的公用方法
	 * @author qianbaohua
	 * @param response
	 */
	private void writerJson( HttpServletResponse response ,String jsonStr) {
		PrintWriter printWriter;
		
		try {			
			printWriter = response.getWriter();			
			printWriter.write(jsonStr);
			printWriter.flush();
			printWriter.close();
		
		}catch(Exception e){
			LOG.error("FuelBusinessController->writerJson方法执行失败！" + e.toString());
		}
		
	}


    /**
     * 通过车牌号,年份获取该车在该年份,每个月的燃气消耗总量
     */
    @RequestMapping(value = "/fuelData/getBusYearData",method = RequestMethod.GET)
    public void getMouthFuelDataByBusNumForYear(HttpServletRequest request, HttpServletResponse res){

        res.setContentType("text/html;charset=UTF-8");


        String year = request.getParameter("year");
        String busNum = request.getParameter("busNum");

        List list =    fuelBusinessService.getFuelMonthDataByBusNumForYear(year,busNum);

        writerJson(res, JSON.toJSONString(list));

    }


    @RequestMapping(value = "/fuelData/readEccel",method = RequestMethod.GET)
    public void readExcel(){
        InputStream stream = null;
        String filePath = "/Users/qiandaxian/Desktop/z/11.xlsx";
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

                for(int i =1;i<=sheet1.getLastRowNum();i++){

                    Row row = sheet1.getRow(i);

                    Cell cell1 = row.getCell(0);
                    Cell cell2 = row.getCell(1);
                    Cell cell3 = row.getCell(2);
                    Cell cell4 = row.getCell(3);

                   String orgId = cell1.getStringCellValue();
                   String lineName = cell2.getStringCellValue();
                   String selfNum = cell3.getStringCellValue();
                   String busNum = cell4.getStringCellValue();

                   if(selfNum==null||selfNum.equals("")){
                    selfNum = "0";
                   }

                   System.out.println(orgId);
                   System.out.println(lineName);
                   System.out.println(selfNum);
                   System.out.println(busNum);

                  //查询ORG
                 Orgs orgs = orgAuthDao.selectByPrimaryKey(orgId);
                    BusInfo bus = new BusInfo();
                    bus.setSelfNum(selfNum);
                    bus.setBusNum(busNum);
                    bus.setBusStatus(Short.parseShort("1"));
                    bus.setCreated(new Date());
                    bus.setOrgId(orgs.getOrgId());
                    bus.setOrgName(orgs.getOrgName());
                    bus.setBusInfoId(UUIDGenerator.genUuidStr());
                    //如果组织机构不是路队
                    if(orgs.getOrgParentId().equals("1")){
                        bus.setOrgParentId(orgs.getOrgId());
                        bus.setOrgParentName(orgs.getOrgName());
                    }
                    else{
                        Orgs parentsOrg =orgAuthDao.getParentOrgByOrgId(orgs.getOrgId());

                        bus.setOrgParentId(parentsOrg.getOrgId());
                        bus.setOrgParentName(parentsOrg.getOrgName());

                    }

                  //查询busLine
                    if(lineName.equals("T1")){
                         bus.setLineId("T1");
                         bus.setLineName("T1");
                    }else{
                        Map map = new HashMap();
                        map.put("lineName",lineName);
                        map.put("orgId",orgId);
                        BusLine busLine =  busLineMapperDao.queryBusLineByNameAndOrg(map);

                        if(busLine == null){
                            System.out.print("新建线路"+orgs.getOrgDescription());

                            busLine = new BusLine();
                            busLine.setLineOrgId(orgs.getOrgId());
                            busLine.setLineName(orgs.getOrgDescription());
                            busLine.setLineId(UUIDGenerator.genUuidStr());
                            busLine.setCreated(new Date());
                            busLine.setLineAlias(orgs.getOrgDescription());
                            busLine.setLineOrgName(orgs.getOrgName());
                            busLine.setLineStatus(1);

                            busLineMapperDao.insert(busLine);

                        }



                      bus.setLineId(busLine.getLineId());
                      bus.setLineName(busLine.getLineName());
                    }

                    busInfoMapperDao.insertSelective(bus);

                }
            }
        } catch(Exception e){
                e.printStackTrace();
            }
        }

}
