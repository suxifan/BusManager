Ext.define('helloext.view.module.authMgmt.AddRoleView', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.addRoleView',
    title:'新增角色',
    layout:'border',
    id:'addRoleViewId',
    requires:['helloext.view.module.authMgmt.AddRoleController'],
    controller:'addRoleController',
    items:{	    
        	  xtype: "form",
           	  region:'center',
           	  reference:'addRoleForm',
              margin: 5,
              autoScroll: true, 
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
		      		    	          { xtype:'textfield', fieldLabel:"角色名称", name:"roleName", allowBlank: false, emptyText: '请输入角色名称',
		      		    	        	regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
				   						regexText:'不含特殊字符'},
		      		    	          { xtype:'tbspacer', width: 20 },
		      		    	          { xtype:'textfield', fieldLabel:"角色描述", name:"roleDesc", allowBlank: false, emptyText: '请输入角色描述'},
		      		    	          { xtype:'tbspacer', width: 20 },
		      		    	          { xtype:'button', text:"保存", iconCls : 'icon-save', handler:'addRole' }
	      		    	         ],
	      	       		    }],
	      	           		xtype:"treepanel",
		       	            root: { text: '根', expanded: true },
		       	            rootVisible: false,
		       				store:{
		       					proxy: {
		       						type : 'ajax',
		       						url:'./authorityMgmtCtr/getAllAuths',
		       						autoLoad : true,
		       						extraParams : {
		       							editable:false
		       						},
		       						reader : {
		       							type : 'json',
		       							rootProperty : 'children'
		       						}
		       					}
		       				},
		       				listeners:{ 
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
		       				
		       				
		       				},
	
	      	        		height:'90%',
	      	        		layout:'fit',
	      	       	   }
              ]

    	}
    
});
