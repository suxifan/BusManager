Ext.define('helloext.view.module.authMgmt.OrgOper', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.orgoper',
    title:'新增机构',
    layout:'border',
    requires: [
        	  'helloext.view.module.authMgmt.OrgOperController',
        	  'helloext.view.module.authMgmt.OrgOperModel'
           ],
    viewModel: {
        type: 'orgoper'
    },
    id:'orgOperId',
    controller:'orgoper',
    bind:{
    	title:'{title}'+'机构',
    },
    items:[{
    	
			xtype:'form',
			region:'center',
			border: false,
			fieldDefaults: {
                labelAlign: 'center',
                //labelWidth: 160
            },
            align:'center',
	        reference:'orgform',
	         buttonAlign:'center',
	         buttons :[{
	        	 xtype:'button',
	        	 text:'保存',
	        	 iconCls : 'icon-save',
	        	 handler:'saveOrgInfo'
	         },{
	             xtype:'button',
	             text:'关闭',
	             iconCls : 'icon-cancel',
	             handler:'closeWindow'
	               }
	         ],
           items:[   
  					{
   						xtype:'textfield',
   						fieldLabel:'机构ID',
   						padding:5,
   						
   						name:'orgId',
   						bind:{
   		        			value:'{dobOrgId}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'组织名称',
   						padding:5,
   						name:'orgName',
//   						regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
//   		        		regexText:'不含特殊字符',
   						allowBlank: false,
   						emptyText: '请输入组织名称',
   						bind:{
   		        			value:'{dobOrgName}'
   		        		},
   						labelAlign:'right',
   						
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'组织描述',
   						padding:5,
   						//labelWidth:60,
   						name:'orgDescription',
   						bind:{
   		        			value:'{dobOrgDescription}'
   		        		},
   						labelAlign:'right',
   					},
   					
   					
   					
   					{
   						xtype:'combobox',
   		        		fieldLabel:'组织类型',
   		        		bind:{
   		        			store:'{orgtypes}',
   		        			value:'{getOrgTypeId}'
   		        		},
   		        		displayField:'orgTypeName',
   		        		valueField:'orgTypeId',
   		        		padding:5,
   		        		name:'orgTypeId',
   		        		allowBlank: false,
   						emptyText: '请选择组织类型',
   		        		labelAlign:'right',
   		        		editable: false,
   					},
   					
   					
   					{
   						xtype:'combobox',
   		        		fieldLabel:'上级组织',
   		        		reference:'parentOrgRef',
   		        		
   		        		bind:{
   		        			store:'{orginfo}',
   		        			value:'{dobParentId}'
   		        		},
   		        		displayField:'orgName',//'dobPageElementId',
   		        		valueField:'orgId',//'dobPageElementId',
   		        		reference:'parOrg',
   		        		padding:5,
   		        		name:'orgParentId',
   		        		allowBlank: false,
   						emptyText: '请选择上级机构',
   		        		labelAlign:'right',
   		        		editable: false,
   					},
   					{
   						xtype:'combobox',
   		        		fieldLabel:'是否启用',
   		        		store : new Ext.data.ArrayStore({  
   	                        fields : ['value', 'text'],  
   	                        data : [[true, '是'], [false, '否']]  
   	                    }),
   		        		bind:{
   		        			value:'{isEnabled}'
   		        		},
   		        		displayField:'text',
   		        		valueField:'value',
   		        		padding:5,
   		        		name:'isEnabled',
   		        		allowBlank: false,
   						emptyText: '请选择是否启用',
   		        		labelAlign:'right',
   		        		editable: false,
   					},
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'dobUserId',
   						padding:5,
   						//anchor:'100%',
   						//labelWidth:60,
   						name:'dobUserId',
   						bind:{
   		        			value:'{dobUserId}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					}, 					

   					
   				]

           }],

});