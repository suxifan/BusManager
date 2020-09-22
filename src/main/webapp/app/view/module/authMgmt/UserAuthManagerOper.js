Ext.define('helloext.view.module.authMgmt.UserAuthManagerOper', {
    extend: 'Ext.panel.Panel',
    //extend:'Ext.window.Window',
	//extend: 'Ext.container.Container',
    alias: 'widget.userauthmanageroper',
    title:'新增用户',
    layout:'border',
    id:'UserAuthManagerOperId',
    requires: [
        	  'helloext.view.module.authMgmt.UserAuthManagerOperController',
        	  'helloext.view.module.authMgmt.UserAuthManagerOperModel',
        	  'Ext.form.Panel',
        	    /*'Ext.ux.form.MultiSelect',
        	    'Ext.ux.form.ItemSelector',
        	    'Ext.tip.QuickTipManager',
        	    'Ext.ux.ajax.JsonSimlet',
        	    'Ext.ux.ajax.SimManager'*/
           ],
    viewModel: {
        type: 'userauthmanageroper'
    },
           controller:'userauthmanageroper',    
           bind:{
        	   title:'{title}'+'用户',
           },
           items:[{
	           	xtype:'form',
	           	region:'center',
				border: false,
				fieldDefaults: {
	                labelAlign: 'center',
	            },
	               reference:'userform',
	                  listeners: {
	                      render: function() { 
	                    var st = Ext.getStore('roleManagerStoreId');
	                    var resLen = 0;
	                    if(st != null){
	                    	resLen = st.getCount();   
	                    }
	                    var CheckBoxGroupTypes = new Ext.form.CheckboxGroup(
	       		  	               {
	       		  	                xtype: 'checkboxgroup',  
	       		  	                fieldLabel: '包含角色',  
	       		  	                id:'insTypeCb',
	       		  	                name :'insTypeCb', 
	       		  	                autoFlex:true,
	       		  	                columns: resLen,
	       		  	                itemCls : 'x-check-group-alt',
	       		  	                anchor:"70%",
	       		  	                msgTarget:"side",
	       		  	                padding:5,
	       		  	                labelWidth:60,
	       		  	                width:250
	       		  	               }); 
	                   	   for(var i=0;i<resLen;i++){
			  	        	   var d=st.getAt(i);
			  	        	   var checkboxModule = new Ext.form.field.Checkbox({
			                        id:'roleCheckBox' + d.get('roleId'),
			                        name:'containRoleId',
			                        boxLabel : d.get('roleName'),
			                        inputValue : d.get('roleId'),
			                        checked:false});
			  	        	   CheckBoxGroupTypes.add(checkboxModule);
					  	       CheckBoxGroupTypes.doLayout(true);  
			  	           }  
	                   	   this.add(CheckBoxGroupTypes);
	                   	   this.doLayout(true);
	                      },

	                  },
	               buttonAlign:'center',
	               buttons :[{
	               	xtype:'button',
	               	text:'保存',
	               	iconCls : 'icon-save',
	               	handler:'saveUserInfo'
	               },{
	               	xtype:'button',
	               	text:'关闭',
	               	iconCls : 'icon-cancel',
	               	handler:'closeWindow'
	               }],
           items:[
  					
  				{	
				layout : 'column',  
				frame : true,  
				baseCls : 'my-panel-no-border',  //去掉边框  
  				items:[
  					       
  					       
  					       
  				{
   						xtype:'textfield',
   						fieldLabel:'姓名',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobRealName',
   						bind:{
   		        			value:'{dobRealName}'
   		        		},
   		        		allowBlank: false, 
   		        		regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
   		        		regexText:'不含特殊字符',
   		        		emptyText: '请输入姓名',
   						labelAlign:'right',
   					},				
   					{
   						xtype:'combobox',
   		        		fieldLabel:'性别',
   		        		labelAlign:'right',
   		        		store : new Ext.data.ArrayStore({  
   	                        fields : ['value', 'text'],  
   	                        data : [["1", '男'], ["0", '女']]  
   	                    }),
   	                    value:'1',
   	                 editable: false,
   		        		bind:{
   		        			//store:'{orginfo}',
   		        			value:'{dobSex}'
   		        		},
   		        		displayField:'text',//'dobPageElementId',
   		        		valueField:'value',//'dobPageElementId',
   		        		padding:5,
   		        		labelWidth:60,
   		        		name:'dobSex',
   		        		allowBlank: false, 
   		        		emptyText: '请选择性别',
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'账号',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobAccount',
   						regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
   		        		regexText:'不含特殊字符',
   						bind:{
   		        			value:'{dobAccount}'
   		        		},
   						labelAlign:'right',
   						allowBlank: false, 
   		        		emptyText: '请输入账号',
   					},
   					
   					
   					]},		//end
   					
   					
   					
   					
   					{	
   						layout : 'column',  
   						frame : true,  
   						baseCls : 'my-panel-no-border',  //去掉边框  
   		  				items:[
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'用户编号',
   						padding:5,
   						labelWidth:60,
   						regex: /^(?=[0-9a-zA-Z@_.]+$)/,
   		        		regexText:'不含特殊字符',
   						name:'employeeNum',
   						bind:{
   		        			value:'{employeeNum}'
   		        		},
   						labelAlign:'right',
   						allowBlank: false, 
   		        		emptyText: '请输入用户编号',
   					},
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'卡号',
   						padding:5,
   						labelWidth:60,
   						regex: /^(?=[0-9a-zA-Z@_.]+$)/,
   		        		regexText:'不含特殊字符',
   						name:'cardNum',
   						bind:{
   		        			value:'{cardNum}'
   		        		},
   						labelAlign:'right',
   						allowBlank: false, 
   		        		emptyText: '请输入物理卡号',
   		        		
   					},
   					{
   						xtype:'combobox',
   		        		fieldLabel:'上级机构',
   		        		bind:{
   		        			store:'{orginfo}',
   		        			value:'{getOrgId}'
   		        		},
   		        		displayField:'orgName',//'dobPageElementId',
   		        		valueField:'orgId',//'dobPageElementId',
   		        		reference:'parOrg',
   		        		padding:5,
   		        		labelWidth:60,
   		        		name:'orgId',
   		        		allowBlank: false, 
   		        		emptyText: '请选择机构',
   		        		editable: false,
   					},
   					
   					]},		//end
   					
   					
  					{	//start
   						layout : 'column',  
   						frame : true,  
   						baseCls : 'my-panel-no-border',  //去掉边框  
   		  				items:[
   					
   					
   					{
   						xtype:'combobox',
   		        		fieldLabel:'用户类型',
   		        		bind:{
   		        			store:'{usertypes}',
   		        			value:'{getUserTypeId}'
   		        		},
   		        		displayField:'userTypeName',//'dobPageElementId',
   		        		valueField:'userTypeId',//'dobPageElementId',
   		        		padding:5,
   		        		labelWidth:60,
   		        		name:'userTypeId',
   		        		allowBlank: false, 
   		        		emptyText: '请选择类型',
   		        		editable: false,
   					},
   					
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'密码',
   						padding:5,
   						inputType: 'password',
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobPassword',
   						bind:{
   		        			value:'{dobPassword}',
   		        			hidden:'{isHidden}'
   		        		},
   						labelAlign:'right',
   						allowBlank: false, 
   		        		emptyText: '请输入密码',
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'电子邮箱',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobEmail',
   						bind:{
   		        			value:'{dobEmail}'
   		        		},
   						labelAlign:'right',
   						allowBlank: true, 
   		        		emptyText: '请输入邮箱',
   		        		regex:/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,
        				regexText:'请输入合法电子邮箱'
   					},
   					
   					]},		//end
   					
   					
   					{	//start
   						layout : 'column',  
   						frame : true,  
   						baseCls : 'my-panel-no-border',  //去掉边框  
   		  				items:[
   					{
   						xtype:'textfield',
   						fieldLabel:'手机',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobMobile',
   						bind:{
   		        			value:'{dobMobile}'
   		        		},
   						labelAlign:'right',
   						allowBlank: true, 
   		        		emptyText: '请输入手机号',
   		        		regex:/(^0?[1][35][0-9]{9}$)/,
   		        		regexText:'请输入正确的手机号码'
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'办公电话',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobOfficePhone',
   						bind:{
   		        			value:'{dobOfficePhone}'
   		        		},
   						labelAlign:'right',
   						allowBlank: true, 
   		        		emptyText: '请输入办公电话',
   		        		regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,
   		        		regexText:'请输入正确的电话号码,如:0920-29392929'
   					},
   					{
   						xtype:'textarea',
   						fieldLabel:'备注',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobReMark',
   						bind:{
   		        			value:'{dobRemark}'
   		        		},
   						labelAlign:'right',
   					},
   					
   					]},		//end
   					
   					{	//start
   						layout : 'column',  
   						frame : true,  
   						baseCls : 'my-panel-no-border',  //去掉边框  
   		  				items:[
   					{
   						xtype:'textfield',
   						fieldLabel:'身份证号',
   						padding:5,
   						labelWidth:60,
   						name:'dobIdCard',
   						bind:{
   		        			value:'{dobIdCard}'
   		        		},
   						labelAlign:'right',
   						allowBlank: true, 
   		        		emptyText: '请输入身份证号码',
   		        		regex:/(^\d{15}$)|(^\d{17}([0-9]|X)$)/,
        				regexText:'请输入合法身份证号码'
   		        		
   					},
   					{
   						xtype:'combobox',
   		        		fieldLabel:'是否启用',
   		        		value:true,
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
   		        		labelWidth:60,
   		        		name:'isEnabled',
   		        		allowBlank: false,
   						emptyText: '请选择是否启用',
   		        		labelAlign:'right',
   		        		editable: false,
   					},
   					
   					]},		//end
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'Uuid',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobUuid',
   						bind:{
   		        			value:'{dobUuid}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'dobUserId',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobUserId',
   						bind:{
   		        			value:'{dobUserId}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					},
   					{
   						xtype:'numberfield',
   						fieldLabel:'dobErrorCount',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobErrorCount',
   						bind:{
   		        			value:'{dobErrorCount}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					},
   					{
   						xtype:'textfield',
   						fieldLabel:'dobLastLoginIp',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						name:'dobLastLoginIp',
   						bind:{
   		        			value:'{dobLastLoginIp}'
   		        		},
   						labelAlign:'right',
   						hidden:true
   					},  					
                  //第二个panel
                  /*{
                	  xtype:'form',
                	  title: 'ItemSelector Test',
                      width: 700,
                      bodyPadding: 10,
                      height: 400,
                      //renderTo: 'itemselector',
                      layout: 'fit',
                      items:[{
                          xtype: 'itemselector',
                          name: 'itemselector',
                          id: 'itemselector-field',
                          anchor: '100%',
                          fieldLabel: 'ItemSelector',
                          imagePath: 'http://localhost:8080/dev/ext/src/ux/images/',
                          store: new Ext.data.ArrayStore({    
                              data: [['1', 'One'], ['2', 'Two'], ['3', 'Three']],    
                              fields: ['value','text'],    
                              sortInfo: {    
                                   field: 'value',    
                                  direction: 'ASC'    
                             }    
                        }),
                          displayField: 'text',
                          valueField: 'value',
                          value: ['3', '2', '6'],
                          allowBlank: false,
                          msgTarget: 'side',
                          fromTitle: 'Available',
                          toTitle: 'Selected'
                      }],
                  }*///第二个panel结束	
   				]

           }],

});