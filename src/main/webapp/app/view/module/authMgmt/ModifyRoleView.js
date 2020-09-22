Ext.define('helloext.view.module.authMgmt.ModifyRoleView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.modifyRoleView',
    requires:['helloext.view.module.authMgmt.ModifyRoleController'],
    controller:'modifyRoleController',
    bind:{
    	title:'修改{roleName}角色'
    },
    id:'modifyRoleViewId',
    layout:'border',
    items:{	    
  	  	  xtype: "form",
  	  	  autoScroll: true, 
     	  region:'center',
     	  reference:'addRoleForm',
          margin: 5,
          region:'center',
          border: false,
          fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 60
          },
          items:[
    	       	   {
    	       		   tbar:[{
    	       			   xtype:'toolbar',
    	       			   items:[
    	       			          { xtype:'textfield', fieldLabel:"角色名称", name:"roleName", labelWidth:80, width:300, bind:{value:'{roleName}'},
    	       			        	regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
    		   						regexText:'不含特殊字符',},
    	       			          { xtype: 'tbspacer', width: 20 },
    	       			          { xtype:'textfield', fieldLabel:"角色描述", name:"roleDesc", labelWidth:80, width:300, bind:{value:'{roleDesc}'} },
    	       			          { xtype: 'tbspacer', width: 20 },
    	       			          { xtype:'button', text:"保存", iconCls : 'icon-save', handler: 'modifyRole' },
    	       	      			  { xtype:'textfield', name:'roleId', bind:{value:'{roleId}'}, hidden:true }
    	       			         ]	
    	       		   }],
    	       		   xtype:"treepanel",
    	       		   region:'center',
    	       		   root: { text: '根', expanded: true },
    	       		   rootVisible: false,
    	       		   store:{
    	       			   storeId:'roleAuthStoreId',
    	       			   proxy: {
    	       				   type : 'ajax',
    	       				   url:'./authorityMgmtCtr/getAuthsByRoleId',
    	       				   extraParams : {
    	       					   roleId:''
    	       				   },
    	       				   autoLoad : true,
    	       				   reader : {
    	       					   type : 'json',
    	       					   rootProperty : 'children'
    	       				   }
    	       			   }
    	       		   },
    	       		   height:'90%',
    	       		   layout:'fit',
    	       		   
    	       		   
    	       		   
    	       		   
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
    	       	        if (checked == true) {
    	       	          //将当前节点的所有未选中的父节点选中
    	       	          for (;pNode != null && !pNode.get("checked");pNode = pNode.parentNode) {
    	       	            pNode.set("checked", true);
    	       	          }
    	       	        }
    	       	      }
    	       	    }
    	       	 
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       		   
    	       	   }
    	  ]
       }
});
