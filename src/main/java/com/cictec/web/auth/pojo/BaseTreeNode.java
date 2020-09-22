package com.cictec.web.auth.pojo;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

public class BaseTreeNode implements Comparable<BaseTreeNode>{

	private String id;//树节点id

	private String text;//树节点名称

	private String parentId;//树节点父节点id

	private int sort;//排序号

    private String url;//树节点url资源

    private String iconUrl;//树节点图标路径

    private String type;//树节点类型，[mudule, field]
	
    private String description;//树节点描述信息

	private Set<BaseTreeNode> children = new TreeSet<BaseTreeNode>();//树节子点集合
	
	private boolean leaf;//是否叶子节点
	
	private boolean expanded;//节点是否展开
	
	private boolean editable;//是否已可编辑

	private Hashtable<String,Object> attributes = new Hashtable<String,Object>();//树节点扩展属性集合
	
    private String orgTypeId;
    
    private String orgTypeName;
    
    private boolean isEnabled;
    
	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	
	public void addChildren(BaseTreeNode node) {
		
		this.children.add(node);
	}
	
    public Set<BaseTreeNode> getChildren() {
        return children;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }	

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
	public int compareTo(BaseTreeNode node) {
		int result = sort > node.getSort() ? 1 : -1;
		return result;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Hashtable<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Hashtable<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setChildren(Set<BaseTreeNode> children) {
		this.children = children;
	}
	
}
