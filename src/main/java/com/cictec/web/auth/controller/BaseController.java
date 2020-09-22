package com.cictec.web.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cictec.web.auth.pojo.BaseTreeNode;


public class BaseController {
	
	@SuppressWarnings("unchecked")
	public List<BaseTreeNode> buildListToTree(List<BaseTreeNode> dirs) {
        List<BaseTreeNode> roots = findRoots(dirs);
        List<BaseTreeNode> notRoots = (List<BaseTreeNode>) CollectionUtils.subtract(dirs, roots);
        for (BaseTreeNode root : roots) {
        	List<BaseTreeNode> chd = findChildren(root, notRoots);
        	for(BaseTreeNode tn:chd){
        		root.addChildren(tn);
        	}
        }
        return roots;
    }
	
	private List<BaseTreeNode> findRoots(List<BaseTreeNode> allNodes) {
        List<BaseTreeNode> results = new ArrayList<BaseTreeNode>();
        for (BaseTreeNode node : allNodes) {
            if(node.getParentId() == null || node.getParentId().isEmpty()||node.getParentId().equals("root")) {
            	results.add(node);
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
	private  List<BaseTreeNode> findChildren(BaseTreeNode root, List<BaseTreeNode> allNodes) {
        List<BaseTreeNode> children = new ArrayList<BaseTreeNode>();
        for (BaseTreeNode comparedOne : allNodes) {
            if (comparedOne.getParentId().equals(root.getId())) {
                children.add(comparedOne);
            }
        }
        List<BaseTreeNode> notChildren = (List<BaseTreeNode>) CollectionUtils.subtract(allNodes, children);
        for (BaseTreeNode child : children) {
            List<BaseTreeNode> tmpChildren = findChildren(child, notChildren);
            for(BaseTreeNode tn:tmpChildren){
            	child.addChildren(tn);
            }
        }
        return children;
    }

}
