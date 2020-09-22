package com.cictec.web.auth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cictec.web.auth.pojo.BaseTreeNode;
import com.cictec.web.auth.pojo.Fields;
import com.cictec.web.auth.pojo.Modules;
import com.cictec.web.auth.pojo.PagingResult;
import com.cictec.web.auth.pojo.Roles;
import com.cictec.web.auth.pojo.TreeNode;
import com.cictec.web.auth.pojo.TreeRoot;
import com.cictec.web.auth.pojo.UserAuthData;
import com.cictec.web.auth.service.IRoleAuthorityService;
import com.cictec.web.auth.service.OrgService;
import com.cictec.web.auth.service.UserAuthManagementService;
import com.cictec.web.auth.util.ToolsUtil;
import com.cictec.web.fuel.util.DBLogger;


@Controller
@RequestMapping("/authorityMgmtCtr")
public class RoleMgmtController extends BaseController {
	
	private static final Logger LOG = Logger.getLogger(RoleMgmtController.class);
	
	@Autowired
	@Qualifier("roleAuthorityService")
	private IRoleAuthorityService roleAuthorityService;
	
	@Autowired
	@Qualifier("userAuthManagementService")
    public UserAuthManagementService userAuthManagementService;
	
	@Resource(name="orgService")
    public OrgService orgService;
	
	@RequestMapping(value="/addRole")
	public void addRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String nodeList = request.getParameter("nodeList");
		Roles role = new Roles(roleName, roleDesc);
		boolean result1 = roleAuthorityService.addRole(role);
		DBLogger.log("增加角色名称:"+roleName+" ID:"+role.getRoleId());
		List<Map<String, Object>> roleModuleList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> roleFieldList = new ArrayList<Map<String,Object>>();
		setMapInfo(role.getCurrentRoleId(), nodeList, roleModuleList, roleFieldList);
		boolean result2 = roleAuthorityService.updateRoleModule(roleModuleList);
		boolean result3 = roleAuthorityService.updateRoleField(roleFieldList);
		
		ToolsUtil.writeJSON(response, "{success:" + (result1 && result2 && result3) + "}");
	}
	
	@RequestMapping(value="/modifyRole")
	public void modifyRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String nodeList = request.getParameter("nodeList");
		DBLogger.log("修改角色:"+roleName+" ID:"+roleId);
		Roles role = new Roles(roleName, roleDesc);
		role.setRoleId(roleId);
		
		List<Map<String, Object>> roleModuleList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> roleFieldList = new ArrayList<Map<String,Object>>();
		setMapInfo(roleId, nodeList, roleModuleList, roleFieldList);
		
		boolean result1 = roleAuthorityService.updateRole(role);
		boolean result2 = roleAuthorityService.updateRoleModule(roleModuleList);
		boolean result3 = roleAuthorityService.updateRoleField(roleFieldList);
		
		ToolsUtil.writeJSON(response, "{success:" + (result1 && result2 && result3) + "}");
	}

	@RequestMapping(value="/getAllRoles")
	public @ResponseBody PagingResult<Roles> getAllRoles(HttpServletRequest request, HttpServletResponse response) {
		
		List<Roles> roles = roleAuthorityService.getAllRoles();
		PagingResult<Roles> result = new PagingResult<Roles>();
		result.setTotalCount(roles.size());
		String strPage = request.getParameter("page");
		String strStart = request.getParameter("start");
		String strLimit = request.getParameter("limit");
		
		
		LOG.warn("getAllRoles..........:"+strPage+":"+strStart+":"+strLimit);
		
		
		if(roles!=null && roles.size() > 0){
	 		int page = Integer.parseInt(strPage);
			int limit = Integer.parseInt(strLimit);
			int start = Integer.parseInt(strStart);
			int retStart = 0, retFin = 0;
			retFin = page*limit > roles.size() ? roles.size():page*limit;
			retStart = (page-1)*limit > roles.size()-1 ? roles.size()-1:(page-1)*limit;
			List<Roles> n_list = roles.subList(retStart, retFin);
			result.setData(n_list);
	 		//pr.setData(userInfolist);
 		}
		return result;
	}
	
	@RequestMapping(value="/getRolesByRoleName")
	public @ResponseBody PagingResult<Roles> getRolesByRoleName(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		
		String roleName = request.getParameter("roleName");
		roleName = URLDecoder.decode(roleName, "UTF-8");
		List<Roles> roles = roleAuthorityService.getRolesByRoleName(roleName);
		PagingResult<Roles> result = new PagingResult<Roles>();
		result.setTotalCount(roles.size());
		result.setData(roles);
		return result;
	}
	
	@RequestMapping(value="/getAllAuths")
	public @ResponseBody TreeRoot getAllAuths(HttpServletRequest request, HttpServletResponse response) {
		
		TreeRoot root = null;
		try {
			boolean editable = Boolean.parseBoolean(request.getParameter("editable"));
			root = getAuths(response, Collections.<String> emptyList(), Collections.<String> emptyList(), editable);
		} catch (IOException e) {
			LOG.error("AuthorityMgmtController->getAllModules 执行失败！" + e.toString());
		}
		return root;
	}
	
	@RequestMapping(value="/getAuthsByRoleId")
	public @ResponseBody TreeRoot getAuthsByRoleId(HttpServletRequest request, HttpServletResponse response) {
		
		TreeRoot root = null;
		try {
			boolean editable = Boolean.parseBoolean(request.getParameter("editable"));
			String roleId = (request.getParameter("roleId"));
			List<Modules> modulesByRoleId = roleAuthorityService.getModulesByRoleId(roleId);
			List<String> moduleIds = new ArrayList<String>();
			for (Modules module : modulesByRoleId) {
				moduleIds.add(module.getModuleId());
			}
			List<Fields> fieldsByRoleId = roleAuthorityService.getFieldsByRoleId(roleId);
			List<String> fieldIds = new ArrayList<String>();
			for (Fields field : fieldsByRoleId) {
				fieldIds.add(field.getFieldId());
			}
			root =  getAuths(response, moduleIds, fieldIds, editable);
		} catch (IOException e) {
			LOG.error("AuthorityMgmtController->getModulesByRoleId 执行失败！" + e.toString());
		}
		return root;
	}
	
	@RequestMapping(value="/deleteRoleByRoleId")
	public void deleteRoleByRoleId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		boolean result = true;
		
		String[] roleIds = request.getParameterValues("roleIds");
    	for (String roleId : roleIds) {
    		DBLogger.log("删除角色 ID:"+roleId);
    		result = roleAuthorityService.deleteRoleByRoleId(roleId);
		}
		ToolsUtil.writeJSON(response, "{success:" + result + "}");
	}
	
	@RequestMapping(value="/getMenus")
	public @ResponseBody TreeRoot getMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getSession().getAttribute("userId").toString();
		List<Roles> roles = userAuthManagementService.getRoleByUserId(userId);
		List<BaseTreeNode> treeNodes = new ArrayList<BaseTreeNode>();
		Set<Modules> modules = new HashSet<Modules>();
		for (Roles role : roles) {
			List<Modules> modulesByRoleId = roleAuthorityService.getModulesByRoleId(role.getRoleId());
			modules.addAll(modulesByRoleId);
		}
		for(Modules module : modules) {
			
			BaseTreeNode modNode = new BaseTreeNode();
			modNode.setText(module.getModuleName());
			modNode.setLeaf(false);
			modNode.setExpanded(false);
			modNode.setId(module.getModuleId());
			modNode.setParentId(module.getParentId());
			modNode.setSort(module.getSort());
			treeNodes.add(modNode);
		}
		TreeRoot root = new TreeRoot();
		for(BaseTreeNode subRoot:buildListToTree(treeNodes)) {
			root.addChildren(subRoot);
		}
		return root;
	}
	
	private TreeRoot getAuths(HttpServletResponse response, List<String> moduleIds, List<String> fieldIds, boolean editable)
			throws IOException {
		
		List<BaseTreeNode> treeNodes = new ArrayList<BaseTreeNode>();
		List<Modules> modules = roleAuthorityService.getAllModules();
		for(Modules module : modules) {
			
			TreeNode modNode = new TreeNode();
			modNode.setText(module.getModuleName());
			modNode.setChecked(moduleIds.contains(module.getModuleId())?true:false);
			modNode.setLeaf(false);
			modNode.setExpanded(true);
			modNode.setType("module");
			modNode.setId(module.getModuleId());
			modNode.setParentId(module.getParentId());
			modNode.setEditable(editable);

			List<Fields> fields = roleAuthorityService.getFieldsByModuleId(module.getModuleId());
			if(fields!=null && !fields.isEmpty()) {
				for(Fields field : fields) {
					
					TreeNode fieldNode = new TreeNode();
					fieldNode.setText(field.getFieldName());
					fieldNode.setChecked(fieldIds.contains(field.getFieldId())?true:false);
					fieldNode.setLeaf(true);
					fieldNode.setExpanded(false);
					fieldNode.setType("field");
					fieldNode.setId(field.getFieldId());
					fieldNode.setParentId(module.getModuleId());
					fieldNode.setEditable(editable);
					treeNodes.add(fieldNode);
				}
			}
			treeNodes.add(modNode);
		}
		TreeRoot root = new TreeRoot();
		for(BaseTreeNode subRoot:buildListToTree(treeNodes)){
			root.addChildren(subRoot);
		}
		return root;
	}
	
	private void setMapInfo(String roleId, String nodeList,
			List<Map<String, Object>> roleModuleList,
			List<Map<String, Object>> roleFieldList) {
		if(!StringUtils.isEmpty(nodeList)){
			for (String node : nodeList.split(",")) {
				String[] infos = node.split("-");
				if(infos[1].equals("module")) {
					Map<String, Object> roleModuleMap = new HashMap<String, Object>();
					roleModuleMap.put("roleModId", roleId + "_" + infos[0]);
					roleModuleMap.put("roleId", roleId);
					roleModuleMap.put("modId", infos[0]);
					roleModuleList.add(roleModuleMap);
				} else if(infos[1].equals("field")) {
					Map<String, Object> roleFieldMap = new HashMap<String, Object>();
					roleFieldMap.put("roleFieldId", roleId + "_" + infos[0]);
					roleFieldMap.put("roleId", roleId);
					roleFieldMap.put("fieldId", infos[0]);
					roleFieldList.add(roleFieldMap);
				}
			}
		}
	}
	
}
