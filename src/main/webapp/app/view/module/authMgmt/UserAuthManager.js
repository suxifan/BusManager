Ext.define('helloext.view.module.authMgmt.UserAuthManager', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userauthmanager',
    id:'userAuthManagerView',
    title:'用户管理',
    layout:'fit',

    requires: [
        	  'helloext.view.module.authMgmt.UserAuthManagerController',
        	  'helloext.view.module.authMgmt.UserAuthManagerModel',
        	  'helloext.model.UserAuthManagerMode',
        	  'helloext.store.UserAuthManagerStore'
           ], 
       	viewConfig:{
			forceFit: true
		},
    viewModel: {
        type: 'userauthmanager'
    },
           controller:'userauthmanager',
	  		tbar:[
	  			       {
	  			    	   xtype:'button', 
	  			    	   text:"新增",
	  			    	   iconCls : 'icon-add',
	  			    	   id:'auth_user_module_add',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenAdd}'
	  			    	   },
	  			    	   //hidden:'{isHidden}',
	  			    	   float:'left',
	  			    	   iconCls : 'icon-add',
	  			    		handler:'addUserInfo',
	  			    		//flex:1
	  			       },
	  			       {
	  			    	   xtype:'button', text:"修改",float:'left',
	  			    	   iconCls : 'icon-edit',
	  			    	   id:'usr_mgmt_module_update',
	  			    	 iconCls : 'icon-edit',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenUpdate}'
	  			    	   },
	  			    	   handler:'updateUserInfo',
	  			    		 hidden:false,   
	  			       },
	  			       {
	  			    	   xtype:'button', text:"删除",float:'left',
	  			    	   iconCls : 'icon-delete',
	  			    	   id:'usr_mgmt_module_delete',
	  			    	 iconCls : 'icon-delete',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenDelete}'
	  			    	   },
	  			    	   handler:'deleteUserInfo',
	  			       },
	  			       { xtype: 'tbspacer', width: 20 },'-',
	  	    	       { xtype:'textfield', fieldLabel:"用户姓名",  //id:'roleName', 
	  			    	 labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
	  		    	   { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryUserInfo' }
	  		],

		     columns : [
		        			{
		        				xtype: 'rownumberer',
		        				text:"序号",
		        				width:'5%',
		        				align:'center'
		        			},
		        			{
		        				text : '账号',
		        				width:'15%',
		        				align:'center',
		        				dataIndex : 'dobAccount'
		        			},
		        			{
		        				text : '员工编号',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'employeeNum'
		        			},
		        			{
		        				text : '用户类型',
		        				width:'5%',
		        				align:'center',
		        				dataIndex : 'userTypeName'
		        			},
		        			{
		        				text : '真实姓名',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'dobRealName'
		        			},
		        			{
		        				text : '性别',
		        				width:'5%',
		        				align:'center',
		        				dataIndex : 'dobSex',
		        				renderer:'renderSex'
		        			},
		        			{
		        				text : '所属机构',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'orgName'
		        			},
	

		        			{
		        				text : '包含角色',
		        				flex:1,
		        				//dataIndex : 'roleString',
		        				dataIndex:'liHasRoles',
		        				renderer:'renderRoles',
		        				align:'center',
		        			},
		        			{
		        				text : '是否启用',
		        				width:'5%',
		        				align:'center',
		        				dataIndex : 'isEnabled',
		        				renderer:'renderIsEnabled',
		        				
		        			},
		        			{
			       				text:'重置密码',
			       		      	align:'center',
			       		      	width:'10%',
			       		      	renderer:'resetPwdbtn'
			       		      	

		        			}
		        			
		        		],
						listeners:{
						
						 'cellclick':function(obj,td, cellIndex, record, tr, rowIndex, e){
    						var userId = record.get("dobUserId");
							if(cellIndex==10){
								var win = new Ext.Window({
											title : '重置密码',
											width : 325,
											height : 200,
											resizable : true,
											autoScroll : true,
											
												fieldDefaults: {
									                labelAlign: 'center',
									            },
									            reference:'userPwdform',
									               buttonAlign:'center',
									               buttons :[{
									               	xtype:'button',
									               	text:'保存',
									               	iconCls : 'icon-save',
									              
									               	handler: function(){
									            	   var form   = win.down('form'); 
									            	   if(form.isValid()) {
									            		   form.submit({
																	url:'./AuthManagement/resetPwd.action',
																	params:form.getValues(),
																	success: function(form, action) {
																		   Ext.MessageBox.alert("警告","修改成功!")
																	       win.close();
																	},
																	failure : function(form, action) {
																		
																		Ext.MessageBox.alert("警告","修改失败!")
														            }
																});
									            	   }
									               	}  
									               		
									               }],
											
											
											
											items : [{
												
												xtype:'form',
									           	region:'center',
												border: false,
												
									                
									                items:[
										                	
									                		{
									                			xtype:'hiddenfield',
									                			name:'userId',
									                			value:userId
									                		},
											   				{
											   						xtype:'textfield',
											   						fieldLabel:'新密码',
											   						padding:5,
											   						inputType: 'password', 
											   						//anchor:'100%',
											   						labelWidth:80,
											   						name:'resetPwd',
											   		        		allowBlank: false, 
											   		        		maxLength:32,
										                			maxLengthText:"新密码过长",
											   		        		emptyText: '新密码',
											   						labelAlign:'right'
											   				}
									                ]
											  }
											]
										});
										win.show();
			       		    }
							
						}
						
						},
		        		
		            	  bind:{
		       		       store:'{userStore}'
		       		     },
		       		    selModel:'checkboxmodel',
	        			dockedItems : [ {
	        				xtype : 'pagingtoolbar',
	        				dock : 'bottom',	        				
	        				bind:{
	 		       		       store:'{userStore}'
	 		       		     },
	        				pageSize : 5,
	        				displayInfo : true,
	        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
	        				emptyMsg : '没有数据'
	        			}]
});