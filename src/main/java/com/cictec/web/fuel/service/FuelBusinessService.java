package com.cictec.web.fuel.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cictec.web.fuel.dao.*;
import com.cictec.web.fuel.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cictec.web.auth.dao.IOrgAuthDao;
import com.cictec.web.auth.dao.IUserAuthManagementDao;
import com.cictec.web.auth.pojo.ClientQueryByParam;
import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.util.CommonUtil;
import com.cictec.web.auth.util.UUIDGenerator;
import com.cictec.web.fuel.constant.Constants;
import com.cictec.web.fuel.util.ToolsUtil;

@Service("fuelBusinessService")
public class FuelBusinessService implements IFuelBusinessService{
	private static final Logger LOG = Logger.getLogger(FuelBusinessService.class);
	
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
	@Qualifier("gasRemindMapperImpl")
	private GasRemindMapper gasRemindDao;
	
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

	@Override
	public UserAuthData checkLogin(String account, String pwd) {
		
        UserAuthData user = null;
        try{
        	//用户名密码不为空
        	if( (!"".equals(account))&&(account!=null)&&(!"".equals(pwd))&&(pwd!=null)){
        		user = userAuthManagementDao.getUserByAccount(account);
        	}
        } catch (Exception e){
            LOG.error(e.toString());
        }        
        
        // 用户存在
        if (user!=null) {        	
        	//加密
        	String enPwd = CommonUtil.getEncryptPwd(pwd);
            
            LOG.warn("checkLogin...... "+pwd +":"+enPwd);
        	//密码匹配
            if (user.getDobPassword().equals(enPwd)) {
                return user;
            }
        }
        return null;
	}

	@Override
	public ResponseInfo packageDataToResponseInfo(UserAuthData user) {
		ResponseInfo responseInfo = new ResponseInfo();
		Head head = new Head(); 
		
		if(user==null){
			head.setMsg("用户名或密码错误");
			head.setSuccess("false");
			responseInfo.data = "";

		}else{
			String userType = user.getUserTypeId();
			
			if (Constants.USER_TYPE_GAS.equals(userType)) {
				if (user.getIsEnabled()) {
					Map <String,String>userMap = getUserInfoToMap(user);				
					head.setMsg("登录成功");
					head.setSuccess("true");
					responseInfo.data =  userMap;
				} else {
					head.setMsg("该加气员账户已经被停用，请联系管理员！");
					head.setSuccess("false");
					responseInfo.data = "";
				}
				
				
			} else {
				head.setMsg("非加气员不能登录！");
				head.setSuccess("false");
				responseInfo.data = "";
			}			

		}
		responseInfo.head = head;
		return responseInfo;
	}
	
	/**
	 * 封装需要返回信息到map
	 * @param user
	 * @return
	 */
	private Map<String,String> getUserInfoToMap(UserAuthData user){
		Map<String,String> result = new HashMap<String,String>();
		//用户Id
		result.put("userId", user.getDobUserId());
		//用户账户
		result.put("account", user.getDobAccount());
		//用户名
		result.put("realName", user.getDobRealName());
		//sex
		result.put("sex", String.valueOf(user.getDobSex()));
		//加气站标识
		result.put("orgId", user.getOrgId());
		//加气站名称
		result.put("orgName", user.getOrgName());
		//车牌前缀
		ParamSetting param =paramSettingDao.getParamBykey(Constants.LICENSE_PLATE_PREFIX);
		result.put("prefixBusNum", param!=null?param.getParaValue():"");
		
		
		GasStationPrincipal gasStationPrincipal = gasStationPrincipalDao.selectByGasStationId(user.getOrgId());
		
		//白班时间
		result.put("dayTime", gasStationPrincipal != null?gasStationPrincipal.getDayTime():"");
		//晚班时间
		result.put("nightTime", gasStationPrincipal != null?gasStationPrincipal.getNightTime():"");
		
		
		
		return result;
	}

	@Override
	public boolean deviceRepair(String deviceImei) {
		//通过IMEI查询设备信息
		CollectDevice collectDevice;	
		
		if((!("").equals(deviceImei))&&(deviceImei!=null)){
			collectDevice = collectDeviceDao.selectByDeviceImei(deviceImei);
			
			//设置消息提醒
			if(collectDevice!=null){
				
				insertCollectDeviceRemind(collectDevice);
				return true;
			}
		}
		
		return false;
	}
	
    /**
     * 保存设备报修记录
     * @param collectDevice
     */
	private void insertCollectDeviceRemind(CollectDevice collectDevice){

		GasRemind remind = new GasRemind();
		
		remind.setRemindId(UUIDGenerator.genUuidStr());
		remind.setCreated(new Date());
		remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
		remind.setRemindType(Constants.DEVICE_REPAIR_REMIND_TYPE);
		remind.setRemindContent(collectDevice.getGasStation()+" "+collectDevice.getDeviceName()+"设备报修");
		
		gasRemindDao.insert(remind);
		
	}
	
	/**
	 * 数据中断异常提醒
	 */
	public void insertErrorRemind(){

		GasRemind remind = new GasRemind();
		
		remind.setRemindId(UUIDGenerator.genUuidStr());
		remind.setCreated(new Date());
		remind.setRemindStatus(Constants.NOT_READ_REMIND_MSG_STATUS);
		remind.setRemindType(Constants.EXCEPTION_ERROR_REMIND_TYPE);
		remind.setRemindContent(ToolsUtil.DateToStr((new SimpleDateFormat("yyyy-MM-dd HH:mm")), new Date())+"加气记录上传失败");
		
		gasRemindDao.insert(remind);
		
	}
	
	/**
	 * 数据同步
	 * @return
	 */
	public ResponseInfo getAllData(){
		ResponseInfo responseInfo = new ResponseInfo();
		Head head = new Head(); 
		try {
			ResponseData responseData = new ResponseData();
			List<BusInfoVo> busInfoVos = new ArrayList<BusInfoVo>();
			List<UserVo> userVos = new ArrayList<UserVo>();
			//得到车辆信息列表
			List<BusInfo> infos =busInfoDao.selectAllBusByParams(null);
			for(BusInfo busInfo:infos){
				BusInfoVo busInfoVo = new BusInfoVo();
				busInfoVo.setBusId(busInfo.getBusInfoId());
				busInfoVo.setBusNum(busInfo.getBusNum());
				busInfoVo.setSelfNum(busInfo.getSelfNum());
				busInfoVo.setBusStatus(String.valueOf(busInfo.getBusStatus()));
				busInfoVo.setLineId(busInfo.getLineId());
				busInfoVo.setLineName(busInfo.getLineName());
				busInfoVo.setOrgId(busInfo.getOrgId());
				busInfoVo.setOrgName(busInfo.getOrgName());				
				//Orgs org = orgService.getParentOrgByOrgId(busInfo.getOrgId());				
				busInfoVo.setOrgParentId(busInfo.getOrgParentId());
				busInfoVo.setOrgParentName(busInfo.getOrgParentName());
				busInfoVos.add(busInfoVo);
			}
			//得到用户信息列表(加气员、司机、加气司机)
			List<UserAuthData> userAuthDatas = userAuthManagementDao.getUserByType(null);
			for(UserAuthData userAuthData :userAuthDatas){
				UserVo userVo = new UserVo();
				userVo.setRealName(userAuthData.getDobRealName());
				userVo.setAccount(userAuthData.getDobAccount());
				userVo.setSex(String.valueOf(userAuthData.getDobSex()));
				userVo.setEmployeeNum(userAuthData.getEmployeeNum());
				userVo.setCardNum(userAuthData.getCardNum());
				userVo.setOrgId(userAuthData.getOrgId());
				userVo.setOrgName(userAuthData.getOrgName());
				
				Orgs org = orgAuthDao.getParentOrgByOrgId(userAuthData.getOrgId());
				userVo.setOrgParentId(org!=null?org.getOrgId():"");
				userVo.setOrgParentName(org!=null?org.getOrgName():"");
				userVo.setIsEnabled(String.valueOf(userAuthData.getIsEnabled()));
				userVo.setUserId(userAuthData.getDobUserId());
				userVos.add(userVo);
			}
			//得到气枪信息列表
			List<GasGun> gasGuns= gasGunDao.selectAllGasGun(null);			
			
			responseData.setBusInfos(busInfoVos);
			responseData.setUsers(userVos);
			responseData.setGasGuns(gasGuns);
			
			head.setSuccess("true");
			head.setMsg("获取数据成功");
			responseInfo.code = 200;
			responseInfo.data = responseData;
			responseInfo.head = head;
			
		} catch (Exception e) {
			responseInfo.data = "";
			e.printStackTrace();
		}
		return responseInfo;
	}
	
	public ResponseInfo getAppVersionToResponseInfo(String osType){
		
		ResponseInfo responseInfo = new ResponseInfo();
		Head head = new Head();
		try{
			int osTypeInt = Integer.parseInt(osType);			
			AppVersion app = appVersionDao.selectByOsType(osTypeInt);
			if(app!=null){
				Map<String,String> data = new HashMap<String,String>();
				data.put("versionName",app.getVersionName());
				data.put("downloadUrl", app.getDownloadUrl());
				data.put("versionCode",String.valueOf(app.getVersionCode()));
				
				responseInfo.data = data;				
				head.setMsg("获取成功");
				head.setSuccess("true");
			}
			else{
				head.setMsg("应用系统类型错误,获取失败");
				head.setSuccess("false");
			}
			
		}catch(Exception e){
			head.setMsg("应用系统类型错误,获取失败");
			head.setSuccess("false");
			responseInfo.data = "";
		}
		
		responseInfo.head = head;
		
		return responseInfo;
	}

	
	public GasDetail getGasDetailByGasUUid(String gasUUid){
		List<GasDetail> list = gasDetailDao.queryByGasUUid(gasUUid);
		return list!=null&&list.size()>0?list.get(0):null;
	}


    public boolean saveGasRecord(GasRecord gasRecord){

        int num = gasRecordDao.insert(gasRecord);

        return num==1?true:false;
    }

    @Override
    public boolean saveGasDetail(GasDetail gasDetail) {
        int num = gasDetailDao.insert(gasDetail);

        return num==1?true:false;
    }

    @Override
    public boolean checkGasMsg(GasMsg gasMsg) {
        List<GasDetail> list = gasDetailDao.getGasDetailByGasMsg(gasMsg);
        return list.size()==0?true:false;
    }

    @Override
    public void saveEmailInfo(EmailInfo emailInfo) {

        emailInfoMapperDao.insert(emailInfo);

    }

    @Override
    public EmailInfo getEmailInfoById(String emailId) {
        return emailInfoMapperDao.selectByPrimaryKey(emailId);
    }


    public List<EmailInfo> queryEmailInfo(Map param){
        return emailInfoMapperDao.queryEmailInfo(param);
    }

    @Override
    public GasStationPrincipal getGasStationByEmail(String email) {
        return null;
    }

    public ResponseInfo saveUploadGasRecordInfo(GasRecordUpload gasRecordUpload)  throws Exception  {
		List<GasDetail> list = gasDetailDao.queryByGasUUid(gasRecordUpload.getUuid());
		//if UUID对应记录存在，则修改记录
		if(gasUUidExists(list)){
			GasDetail gasDetail = list.get(0);
			if(gasDetail.getGasStatus()!=Constants.GAS_STATUS_AUDIT_SUCCESS){
				//加气量转换
				String gasVolumeStr = gasRecordUpload.getGasVolume();
				BigDecimal gasVolume = new BigDecimal(gasVolumeStr);	
				
				String preGasVolumeStr = gasRecordUpload.getPreGasVolume();
				BigDecimal preGasVolume = new BigDecimal(preGasVolumeStr);	
				
				//修改加气记录表
				GasRecord gasRecord = gasRecordDao.selectByPrimaryKey(gasDetail.getGasId());
				gasRecord.setGasVolume(gasVolume);
				gasRecordDao.updateByPrimaryKeySelective(gasRecord);
				
				//修改加气记录明细
				//修改审核状态未审核
				gasDetail.setGasStatus(Constants.GAS_STATUS_NOT_AUDIT);
				gasDetail.setGasVolume(gasVolume); 
				gasDetailDao.updateByPrimaryKeySelective(gasDetail);
				
				//修改加气记录修改表数据
				GasModified gasModified = gasModifiedDao.selectByGasDetailId(gasDetail.getGasDetailId());
				//如果加气修改记录存在
				if(gasModified!=null){
					gasModified.setPreGasVolume(preGasVolume);
					gasModified.setPostGasVolume(gasVolume);
					gasModified.setGasStatus(Constants.GAS_STATUS_NOT_AUDIT);
					gasModifiedDao.updateByPrimaryKeySelective(gasModified);
				}else{
					setGasModifiedByData(gasRecordUpload,gasDetail);
				}
				
				
			}else{
				GasModified gasModified = gasModifiedDao.selectByGasDetailId(gasDetail.getGasDetailId());
					
				Map data = new HashMap();
				data.put("preGasVolume", gasModified.getPreGasVolume().toString());
				data.put("gasVolume", gasModified.getPostGasVolume().toString());
				
				//如果已审核记录不能修改
				ResponseInfo responseInfo = new ResponseInfo();
				
				Head head = new Head();
				head.setMsg("不允许上传");
				head.setSuccess("false");
				responseInfo.head =head;
				responseInfo.data = data;
				
				return responseInfo;
			}
			
			
		}
		//新建
		else{
		
				GasRecord gasRecord = setGasRecordByData(gasRecordUpload);		
				GasDetail gasDetail = setGasDetailByData(gasRecordUpload,gasRecord);		
				GasModified gasModified = setGasModifiedByData(gasRecordUpload,gasDetail);
			}
		return null;
	}
	
	
	
	private boolean gasUUidExists(List list){
		boolean isExists  =  (list!=null&&list.size()>0)?true:false;
		return isExists;
	}
	
	/**
	 * 封装加气记录
	 * @return
	 */
	private GasRecord setGasRecordByData(GasRecordUpload gasRecordUpload){
		
		GasRecord gasRecord = new GasRecord();
		
		//加气时间格式转换
		String gasTimeStr = gasRecordUpload.getGasTime();
		if(gasTimeStr!=null&&(!"".equals(gasTimeStr))){	
			Long longGasTime = Long.decode(gasTimeStr);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(longGasTime);
			gasRecord.setGasTime(cal.getTime());
		}
		gasRecord.setGasTime(new Date());
		//加气量转换
		String gasVolumeStr = gasRecordUpload.getGasVolume();
		BigDecimal gasVolume = new BigDecimal(gasVolumeStr);	
		gasRecord.setGasVolume(gasVolume);
		
		gasRecord.setBusNum(gasRecordUpload.getBusNum());
		gasRecord.setSelfNum(gasRecordUpload.getSelfNum());
		gasRecord.setCreated(new Date());
		gasRecord.setDriver(gasRecordUpload.getDriver());
		gasRecord.setDriverId(gasRecordUpload.getDriverId());
		gasRecord.setGasStationId(gasRecordUpload.getGasStationId());
		gasRecord.setGasStationName(gasRecordUpload.getGasStationName());
		gasRecord.setGasUserId(gasRecordUpload.getGasUserId());
		gasRecord.setGasUserName(gasRecordUpload.getGasUserName());
		gasRecord.setDeviceImei(gasRecordUpload.getDeviceImei());
		gasRecord.setGasUuid(UUIDGenerator.genUuidStr());
		
		gasRecordDao.insert(gasRecord);
		
		return gasRecord;
	}
	
	/**
	 * 封装加气详细信息
	 * @return
	 * @throws Exception 
	 */
	private GasDetail setGasDetailByData(GasRecordUpload gasRecordUpload,GasRecord gasRecord) throws Exception{
		
		GasDetail gasDetail = new GasDetail();
		gasDetail.setGasDetailId(UUIDGenerator.genUuidStr());
		
		gasDetail.setBusNum(gasRecordUpload.getBusNum());
		gasDetail.setDriver(gasRecordUpload.getDriver());

		gasDetail.setCreated(new Date());
		gasDetail.setDriverId(gasRecordUpload.getDriverId());
		gasDetail.setGasEquipmentUrl(gasRecordUpload.getUploadPicPath());
        gasDetail.setBusNumUrl(gasRecordUpload.getUploadBusNumPath());
		gasDetail.setGasGunNum(gasRecordUpload.getGasGunNum());
		gasDetail.setGasId(gasRecord.getGasUuid());	
		gasDetail.setGasStation(gasRecordUpload.getGasStationName());
		gasDetail.setGasStationId(gasRecordUpload.getGasStationId());
		gasDetail.setGasTime(gasRecord.getGasTime());
		gasDetail.setGasUserId(gasRecordUpload.getGasUserId());
		gasDetail.setGasUserName(gasRecordUpload.getGasUserName());
		gasDetail.setGasVolume(gasRecord.getGasVolume());
		gasDetail.setSelfNum(gasRecordUpload.getSelfNum());
		gasDetail.setGasUUid(gasRecordUpload.getUuid());
		//查询车牌对应的车辆信息	
		
		ClientQueryByParam param = new ClientQueryByParam();
		param.setBusNum(gasDetail.getBusNum());
		param.setLimit(1);
		List<BusInfo> busList = busInfoDao.selectByParams(param);
		
		
		if(busList.size()==1){
			BusInfo bus= busList.get(0);
			gasDetail.setLineId(bus.getLineId());					
			gasDetail.setLineName(bus.getLineName());				
			gasDetail.setLineOrgId(bus.getOrgId());			
			gasDetail.setLineOrgName(bus.getOrgName());		
			gasDetail.setOperateType(bus.getBusStatus());			
			
			//通过车辆所在路队，查询路队的父节点，即分公司信息。
			gasDetail.setOrgId(bus.getOrgParentId());
			gasDetail.setOrgName(bus.getOrgParentName());
			
		}
	
		//初始化审核状态
		if(checkGasRecordUploadModified(gasRecordUpload)){
			//修改加气量设置状态为未审核
			gasDetail.setGasStatus(Constants.GAS_STATUS_NOT_AUDIT);
		}else{
			gasDetail.setGasStatus(Constants.GAS_STATUS_NORMAL);
		}
		
		gasDetailDao.insert(gasDetail);
		
		return gasDetail;
	}
	
	/**
	 * 封装加气修改信息
	 * @return
	 */
	private GasModified setGasModifiedByData(GasRecordUpload gasRecordUpload,GasDetail gasDetail){
		String isEquipmentStr = gasRecordUpload.getIsEquipment();
		
		//设备未识别成功，返回加气修改记录对象
		if(checkGasRecordUploadModified(gasRecordUpload)){
			
			GasModified gasModified = new GasModified();
			
			gasModified.setGasModifiedId(UUIDGenerator.genUuidStr());
			
			//修改时间转换
			String modifiedStr = gasRecordUpload.getModified();
			if(modifiedStr!=null&&(!"".equals(modifiedStr))){	
				Long longGasTime = Long.decode(modifiedStr);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(longGasTime);
				gasModified.setModified(cal.getTime());
			}
			
			//修改前加气量
			String preGasVolumeStr = gasRecordUpload.getPreGasVolume();
			BigDecimal preGasVolume = new BigDecimal(preGasVolumeStr);	
			gasModified.setPreGasVolume(preGasVolume);
			
			gasModified.setCreated(new Date());
			gasModified.setGasStation(gasRecordUpload.getGasStationName());
			gasModified.setGasStationId(gasRecordUpload.getGasStationId());
			gasModified.setGasTime(gasDetail.getGasTime());
			gasModified.setPostGasVolume(gasDetail.getGasVolume());
			gasModified.setGasEquipmentUrl(gasDetail.getGasEquipmentUrl());
			
			//加气修改人
			gasModified.setGasUserId(gasRecordUpload.getPreUserId());
			gasModified.setGasUserName(gasRecordUpload.getPreUserName());
			
			//未审核
			gasModified.setGasStatus(Constants.GAS_STATUS_NOT_AUDIT);
			gasModified.setGasDetailId(gasDetail.getGasDetailId());
			
			gasModifiedDao.insert(gasModified);
			
			return gasModified;
		}
		return null;
	}
	
	/**
	 * 判断是否修改过加气量
	 * @param gasRecordUpload
	 * @return
	 */
	private boolean checkGasRecordUploadModified(GasRecordUpload gasRecordUpload){
		if(gasRecordUpload.getPreGasVolume()==null||(("").equals(gasRecordUpload.getPreGasVolume()))||
				gasRecordUpload.getModified()==null||("").equals(gasRecordUpload.getModified())){
			
			return false;
		}
		
		
		return true;
	}



    @Override
    public boolean saveClassFuleStat(Map<String,String> map){
        ClassFuelStat classFuelStat  = new ClassFuelStat();
        classFuelStat.setGasStatUrl(map.get("gasStatUrl"));
        classFuelStat.setCreated(new Date());
        classFuelStat.setGasUserName(map.get("userName"));
        classFuelStat.setGasUserId(map.get("account"));
        classFuelStat.setGasStationId(map.get("gasStationId"));
        classFuelStat.setGasStation(map.get("gasStationName"));
        classFuelStat.setDeviceImei(map.get("deviceImei"));
        classFuelStat.setCreatedStr("");
        classFuelStat.setRemark("");
        classFuelStat.setStatId(UUIDGenerator.genUuidStr());


        return classFuelStatDao.insert(classFuelStat)==1?true:false;
    }



    @Override
    public List getFuelMonthDataByBusNumForYear(String year, String busNum){

        Map paraMap = new HashMap();
        paraMap.put("busNum",busNum);
        paraMap.put("january",year+"-01");
        paraMap.put("february",year+"-02");
        paraMap.put("march",year+"-03");
        paraMap.put("april",year+"-04");
        paraMap.put("may",year+"-05");
        paraMap.put("june",year+"-06");
        paraMap.put("july",year+"-07");
        paraMap.put("august",year+"-08");
        paraMap.put("september",year+"-09");
        paraMap.put("october",year+"-10");
        paraMap.put("november",year+"-11");
        paraMap.put("december",year+"-12");

        return  gasDetailDao.getMonthFuleByBusNum(paraMap);
    }

}
