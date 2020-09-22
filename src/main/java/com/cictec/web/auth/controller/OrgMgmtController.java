package com.cictec.web.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cictec.web.auth.pojo.BaseTreeNode;
import com.cictec.web.auth.pojo.ClientTreeQuery;
import com.cictec.web.auth.pojo.OrgTypes;
import com.cictec.web.auth.pojo.Orgs;
import com.cictec.web.auth.pojo.TreeNode;
import com.cictec.web.auth.pojo.TreeRoot;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.service.LoginService;
import com.cictec.web.auth.service.OrgService;
import com.cictec.web.fuel.util.DBLogger;

@Controller
@RequestMapping(value="/org")
public class OrgMgmtController extends BaseController {

	
	private static final Logger LOG = LoggerFactory.getLogger(OrgMgmtController.class);

	@Resource(name="orgService")
    public OrgService orgService;
	
	@Resource(name="loginService")
    public LoginService loginService;
	@RequestMapping(value = "/getOrgInfo", method = RequestMethod.POST)  
	public @ResponseBody  List<Orgs> getOrgInfo(String branchId){
		List<Orgs> liOrg = null;
		if(branchId==null||branchId.equals("")){	
			try{
				liOrg  = orgService.getAllOrgInfo();
			}catch (Exception e){
				LOG.error(e.toString());
			}
			LOG.warn("getting org info:"+liOrg.size());
		}
		return liOrg;
	}
	
	@RequestMapping(value = "/getOrgLine", method = RequestMethod.POST)  
	public @ResponseBody  List<Orgs> getOrgLine(String branchId){
		List<Orgs> liOrg = null;
		if(branchId==null||branchId.equals("")){	
			try{
				liOrg  = orgService.getAllOrgLine();
			}catch (Exception e){
				LOG.error(e.toString());
			}
			LOG.warn("getting org info:"+liOrg.size());
		}
		return liOrg;
	}
	
	
	@RequestMapping(value = "/getParentOrgs", method = RequestMethod.POST)  
	public @ResponseBody  List<Orgs> getParentOrgs(String userAccount){
		List<Orgs> liOrg = null;
		List<Orgs> liParentOrg = null;
		String queryUser = null;
		UserAuthData userInfo = null;
		if(StringUtils.isEmpty(userAccount)){	
			
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        	queryUser = (String)request.getSession().getAttribute("userName");

		}else{
			queryUser = userAccount;
		}
		try{
			liOrg  = orgService.getAllOrgInfo();
			LOG.warn("getting org info:"+liOrg.size());
			userInfo = loginService.getUserByAccount(queryUser);
			
			String parentId = userInfo.getOrgId();
			
			if(!StringUtils.isEmpty(parentId) && liOrg.size() > 0){
				boolean cont = true;
				liParentOrg  = new ArrayList<Orgs>();
				while(cont){
					boolean isFind = false;
					for(Orgs org:liOrg){
						if(org.getOrgId().equals(parentId)){
							isFind = true;
							liParentOrg.add(org);
							if(StringUtils.isEmpty(org.getOrgParentId()) || org.getOrgParentId().equals("root") || org.getOrgTypeId().equals("5")){
								cont = false;
							}else{
								parentId = org.getOrgParentId();
							}
							break;
						}
					}
					if(!isFind){
						cont = false;
						break;
					}
				}
				LOG.warn("getting org parent info:"+liParentOrg.size());
			}
			
		}catch (Exception e){
			LOG.error(e.toString());
		}
		
		
		
		 String parentType = null;
		 String branchParentName = null;
		 String branchParentId = null;
		 String lineTeamParentId = null;
		 String lineTeamParentName = null;
       	   for(Orgs org:liParentOrg){
                  if(StringUtils.isEmpty(org.getOrgParentId())|| org.getOrgParentId().equals("root")){
	               	   parentType = "group";
	               	   branchParentName = org.getOrgName();
	               	   branchParentId = org.getOrgId();
	               	   break;
                  }else if(org.getOrgTypeId().equals("5")){
               	   if(StringUtils.isEmpty(parentType)){
               		   parentType = "branch";
               	   }
               	   branchParentName = org.getOrgName();
               	   branchParentId = org.getOrgId();
               	   //break;
                  }else if(org.getOrgTypeId().equals("6")){
               	   parentType = "lineTeam";
               	   lineTeamParentName = org.getOrgName();
               	   lineTeamParentId = org.getOrgId();
                  }
	           } 
       	   
       	   
       	   
       	   
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        	
        	request.getSession().setAttribute("parentType", parentType);
        	request.getSession().setAttribute("branchParentName", branchParentName);
        	request.getSession().setAttribute("branchParentId", branchParentId);
        	request.getSession().setAttribute("lineTeamParentName", lineTeamParentName);
        	request.getSession().setAttribute("lineTeamParentId", lineTeamParentId);

        	LOG.error("............."+parentType);
        	LOG.error("............."+branchParentName);
        	LOG.error("............."+branchParentId);
        	LOG.error("............."+lineTeamParentName);
        	LOG.error("............."+lineTeamParentId);
        	
		
		
		
		
		
		
		return liParentOrg;
	}
	
	
	
	@RequestMapping(value = "/getAllOrgTypes", method = RequestMethod.POST)  
	public @ResponseBody  List<OrgTypes> getAllOrgTypes(){
		List<OrgTypes> liOrg = null;//new ArrayList<Branch>();
		try{
			liOrg  = orgService.getAllOrgTypes();
		}catch (Exception e){
			LOG.error(e.toString());
		}
		return liOrg;
	}
	
	
	
	@RequestMapping(value = "/getOrgTree", method = RequestMethod.POST)  
	public @ResponseBody  TreeRoot getOrgTree(@RequestBody ClientTreeQuery ctq){
		
		TreeRoot tr = new TreeRoot();
		List<BaseTreeNode> liResBranch = null;
		List<BaseTreeNode> liBranch = new ArrayList<BaseTreeNode>();
		List<Orgs> liOrg = null;
		try{
			liOrg  = orgService.getAllOrgInfo();
			if(liOrg != null){
				for(Orgs org:liOrg){
					TreeNode tn = new TreeNode();
					tn.setId(org.getOrgId());
					tn.setText(org.getOrgName());
					tn.setChecked(false);
					tn.setExpanded(true);
					tn.setParentId(org.getOrgParentId());
					tn.setDescription(org.getOrgDescription());
					tn.setOrgTypeId(org.getOrgTypeId());
					tn.setOrgTypeName(org.getOrgTypeName());
					tn.setIsEnabled(org.getIsEnabled());
					liBranch.add(tn);
				}
			}
			liResBranch = buildListToTree(liBranch);
			for(BaseTreeNode br:liResBranch){
				tr.addChildren(br);
			}
		}catch (Exception e){
			LOG.error(e.toString());
		}
		LOG.warn("getOrgTree:"+liOrg.size());
		return tr;
	}
	
    @ResponseBody  
    @RequestMapping(value="/saveOrgInfo")
    public Map<String, Object> saveUserInfo(@ModelAttribute Orgs orgInfo, BindingResult result){ 
    	boolean flag =false;
    	
    	LOG.warn("saving orginfo :"+orgInfo.getOrgId()+orgInfo.getOrgName());
    	
        try{
        	if(orgInfo.getOrgId()==null || "".equals(orgInfo.getOrgId())){
        		flag = orgService.saveOrgInfo(orgInfo);
        		DBLogger.log("增加组织名称:"+orgInfo.getOrgName()+ " ID:"+orgInfo.getOrgId());
        	}else{
        		flag = orgService.updateOrgInfo(orgInfo);
        		DBLogger.log("修改组织名称:"+orgInfo.getOrgName()+ " ID:"+orgInfo.getOrgId());
        	}
       	 
        } catch (Exception e){
            LOG.error(e.toString());
        }
    	
    	
    	Map<String,Object> map = new HashMap<String, Object>();
        map.put("success", flag);
        return map;//"success"; 
	}
	
    @ResponseBody  
    @RequestMapping(value="/deleteOrgInfo")
	public Map<String, Object>deleteOrgInfo(String[] orgIds){
    	boolean flag =false;
    	for(String orgId:orgIds){
	    	LOG.warn("controller deleteOrgInfo"+orgId);
	    	DBLogger.log("删除组织 ID:"+orgId);
		    try {
				flag = orgService.deleteOrgInfo(orgId);
			}catch (Exception e) {
				e.printStackTrace();
			}
    	}
	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("success", flag);
        return map;//"success"; 
	}
	
}
