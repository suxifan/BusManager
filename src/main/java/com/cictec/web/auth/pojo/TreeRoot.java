package com.cictec.web.auth.pojo;

import java.util.ArrayList;
import java.util.List;

public class TreeRoot  {
	
	private List<BaseTreeNode> children = new ArrayList<BaseTreeNode>();
	
	public void addChildren(BaseTreeNode node){
		children.add(node);
	}
	
	public boolean isLeaf(){
		return children.size() == 0;
	}
	
	public List<BaseTreeNode> getChildren() {
		return children;
	}

	public String getChildType() {		
		return "";
	}
	
	

}
