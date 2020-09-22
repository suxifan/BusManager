Ext.define('helloext.view.module.authMgmt.Org', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.org',
    requires: [
               'helloext.view.module.authMgmt.OrgModel',
               'helloext.view.module.authMgmt.OrgController',
           ],
    /*controller: 'dailyoperatree',*/ 
   controller: 'org', 
    viewModel: {
        type: 'org'
    },  
    root: {
	    text:"",
        expanded: true
   },
    id:'orgId',
    title:"机构管理",
    width: 300,
    columnLines:true,
    rowLines:true,
		 //事件监听器
	    listeners: {
	      //监听复选框的选中属性改变事件
	     checkchange : function(node,checked){
	        //获得父节点
	        pNode = node.parentNode;
	        //改变当前节点的选中状态
	        node.checked = checked;
	        //判断当前节点是否为叶子节点
	        var isLeaf = node.isLeaf();
	        //当该节点有子节点时,将所有子节点的选中状态同化
	        if (!isLeaf){
	          //cascade是指从当前节点node开始逐层下报，即遍历当前节点的每一个节点(无论有多少层级结构,详参API)
	          node.cascadeBy(function(node){
	            node.set("checked", checked);
	          });
	        }
	        //如果当前节点是选中状态
	        /*if (checked == true) {
	          //将当前节点的所有未选中的父节点选中
	          for (;pNode != null && !pNode.get("checked");pNode = pNode.parentNode) {
	            pNode.set("checked", true);
	          }
	        }*/
	      }
	    },
    
    
    
    
    
	tbar:[
	       {
	    	   xtype:'button', 
	    	   text:"新增",
	    	   float:'left',
	    	   iconCls : 'icon-add',
	    		handler:'addOrgInfo',
	    		id:'auth_org_module_add',
	    		
		    	bind:{
		    		 hidden:'{isHiddenAdd}'
		    	},
	       },
	       {
	    	   xtype:'button', text:"修改",float:'left',
	    	   iconCls : 'icon-edit',
	    	   handler:'updateOrgInfo',
	    	   id:'auth_org_module_update',
	    	   iconCls : 'icon-edit',
	    	   bind:{
	    		 hidden:'{isHiddenUpdate}'
	    	   },   
	       },
	       {
	    	   xtype:'button', text:"删除",float:'left',
	    	   iconCls : 'icon-delete',
	    	   handler:'deleteOrgInfo',
	    	   id:'auth_org_module_delete',
	    	   iconCls : 'icon-delete',
	    	   bind:{
	    		 hidden:'{isHiddenDelete}'
	    	   }, 
	       },
	       { xtype: 'tbspacer', width: 20 },'-',
  	       { xtype:'textfield', fieldLabel:"组织名称",  //id:'roleName', 
		    	 labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
	    	   { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryOrgInfo' }
],

	columns: [
	 {
		xtype: 'treecolumn',
	    text: '组织名称',
	    flex: 1,
	    dataIndex: 'text',
	    sortable: true
	}
	, {
	    text: '组织类型',
	    flex: 1,
	    dataIndex: 'orgTypeName',
	    sortable: true
	}, {
	    text: '组织描述',
	    flex: 1,
	    dataIndex: 'description',
	    sortable: true
	},
	{
	    text: '是否启用',
	    flex: 1,
	    dataIndex: 'isEnabled',
	    renderer:'renderIsEnabled',
	    sortable: true
	}
	],
    bind:{
    	store:'{orgtree}'
    }
});



