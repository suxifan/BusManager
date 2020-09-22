/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('helloext.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.window.MessageBox',
	    'Ext.window.Toast',
		'helloext.view.module.authMgmt.UserAuthManager',
		'helloext.view.module.authMgmt.RoleManagerView',
		'helloext.view.module.authMgmt.Org',
		'helloext.view.module.reportMgmt.BranchFuelReport',
		'helloext.view.module.reportMgmt.BranchBusFuelReport',
		'helloext.view.module.reportMgmt.BranchDetailFuelReport',
		'helloext.view.module.reportMgmt.BranchModifiedFuelReport',
		'helloext.view.module.reportMgmt.BranchModifiedFuelCheckReport',
		'helloext.view.module.reportMgmt.GroupFuelMonth',
		'helloext.view.module.reportMgmt.FuelMonth',
		'helloext.view.module.reportMgmt.MultiFuelAddCount',
		'helloext.view.module.gasStationMgmt.GasStationInfo',
		'helloext.view.module.sysRemind.RemindMsg',
		'helloext.view.module.systemMgmt.BranchFuelParamSetting',
		'helloext.view.module.systemMgmt.CollectDevice',
		'helloext.view.module.authMgmt.BusLine',
		'helloext.view.module.gasStationMgmt.GasGunInfo',
		'helloext.view.module.reportMgmt.NewBusFuelReport',
		'helloext.view.module.reportMgmt.ClassFuelStatReport' ,
        'helloext.view.module.reportMgmt.EmailInfo',
        'helloext.view.module.authMgmt.BusInfo'
    ],
    alias: 'controller.main',
	// 隐藏顶部和底部的按钮事件  
    hiddenTopBottom:function(){
	  //如果要操纵控件，最好的办法就是根据相对路径来找到该控件，用down或up最好，尽量少用getCmp()函数
	  this.getView().down('maintop').hide();
	  this.getView().down('mainbottom').hide();
	  if(!this.showButton){//显示顶部和底部的一个控件，在顶部和底部隐藏了以后，显示在页面的最右上角
		  this.showButton = Ext.widget('component',{
		  
		    glyph:0xf013,
		    view :this.getView(),
			floating:true,
			x:document.body.clientWidth-32,
			y:0,
			height:4,
			width:26,
			style:'background-color:#cde6c7',
			listeners:{
				 el:{
					 click:function(el){
					   var c = Ext.getCmp(el.target.id);
					   c.view.down('maintop').show();
					   c.view.down('mainbottom').show();
					   c.hide();
					 }
				 
				 }
			 
			}
		  });
	    this.showButton.show();
	  }
	},
	//如果窗口的大小改变了，并且顶部和底部都隐藏了，就要调整显示顶和底那个控件的位置
    onMainResize:function(){
	   if(this.showButton&&!this.showButton.hidden){
	   
	     this.showButton.setX(document.body.clientWidth  -32);
	   }
	},

	onMainMenuClick:function(panel, record) {
	  
      var maincenter = Ext.getCmp('mainCenterId');
      switch(record.data.id)
      {
      case 'auth_org_module':	//组织机构管理
    	  var orgView = maincenter.getComponent("orgId");
    	  if(!orgView) {
    		  
    		  orgView = Ext.create({    		
    			  xtype:'org',
    			  closable: true,
    			  viewModel:{
    				  data:{
    					  mainApp:this
    				  }
    			  }
    		  });
    		  maincenter.setActiveTab(maincenter.add(orgView));
    		  orgView.on('beforeclose', function () { orgView.destroy(); });

    	  } else {
    		  maincenter.setActiveTab(orgView);
    	  }
    	  break;
      case 'auth_user_module'://'用户管理':
    	  var userAuthManagerView = maincenter.getComponent("userAuthManagerView");
    	  if(!userAuthManagerView) {
    		  
    		  userAuthManagerView = Ext.create({    		
    			  xtype:'userauthmanager',
    			  closable: true,
    			  closeAction:'destroy',
    			  viewModel:{
    				  data:{
    					  mainApp:this
    				  }
    			  }
    		  });
    		  maincenter.setActiveTab(maincenter.add(userAuthManagerView));
    		  userAuthManagerView.on('beforeclose', function () { userAuthManagerView.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(userAuthManagerView);
    	  }
    	  break;
      case 'auth_role_module'://'角色管理':
    	  var roleManageView = maincenter.down("roleManagerView");
    	  if (!roleManageView) {
    		  roleManageView  = Ext.create({    		
    			  xtype:'roleManagerView',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(roleManageView));
    		  roleManageView.on('beforeclose', function () { roleManageView.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(roleManageView);
    	  }
    	  break;
    	  
      case 'report_group_fuel_month_module'://'燃油中心燃气汇总表':
    	  var view = maincenter.getComponent("groupfuelmonthId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'groupfuelmonth',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;  
    	  
      case 'report_fleet_fuel_month_module'://'分公司车队油气月终汇总':
    	  var view = maincenter.getComponent("BranchFuelReportId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'branchfuelreport',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
    	 
      case 'report_bus_fuel_month_module'://'分公司单车油气月终汇总':
    	  var view = maincenter.getComponent("BranchBusFuelReportId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'branchbusfuelreport',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
      case 'report_subGroup_fuel_day_module'://'分公司油气明细日表':
    	  var view = maincenter.getComponent("BranchDetailFuelReportId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'branchdetailfuelreport',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
      case 'report_modify_addfueldata_module'://'加气数据修改明细表':
    	  var view = maincenter.getComponent("BranchModifiedFuelReportId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'branchmodifiedfuelreport',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
    	  
    	  
      case 'report_modify_check_module'://'加气修改记录审核':
    	  var view = maincenter.getComponent("BranchModifiedFuelCheckReportId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'branchmodifiedfuelcheckreport',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;	  
      case 'gas_station_info_module'://负责人管理
    	  var view = maincenter.getComponent("gasStationInfoId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'gasstationInfo',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
    	 
      case 'report_fuel_month_module'://'集团天然气材料汇总':
    	  var view = maincenter.getComponent("fuelmonthId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'fuelmonth',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
    	  
      case 'report_addfuel_manytimes_module'://'多次加气统计':
    	  var view = maincenter.getComponent("multiFuelAddCountId");
    	  if (!view) {
    		  view  = Ext.create({    		
    			  xtype:'multiFuelAddCount',
    			  closable: true
    		  });
    		  maincenter.setActiveTab(maincenter.add(view));
    		  view.on('beforeclose', function () { view.destroy(); });
    	  } else {
    		  maincenter.setActiveTab(view);
    	  }
    	  break;
    	
    	case 'sys_param_set_module'://'参数设置':
    	  var paramSettingView = maincenter.getComponent("paramSettingView");
    	  if(!paramSettingView) {
    		  
    		  paramSettingView = Ext.create({    		
    			  xtype:'branchfuelparamsetting',
     			  closable: true,
    			  viewModel:{
    				  data:{
    					  mainApp:this
    				  }
    			  }
    		  });
    		  maincenter.setActiveTab(maincenter.add(paramSettingView));
    		  paramSettingView.on('beforeclose', function () { paramSettingView.destroy(); });

    	  } else {
    		  maincenter.setActiveTab(paramSettingView);
    	  }
    	  break;
    	
    	case 'sys_device_mgmt_module'://'设备管理':
    	  var collectDeviceView = maincenter.getComponent("collectDeviceView");
    	  if(!collectDeviceView) {
    		  
    		  collectDeviceView = Ext.create({    		
    			  xtype:'collectdevice',
     			  closable: true,
    			  viewModel:{
    				  data:{
    					  mainApp:this
    				  }
    			  }
    		  });
    		  maincenter.setActiveTab(maincenter.add(collectDeviceView));
    		  collectDeviceView.on('beforeclose', function () { collectDeviceView.destroy(); });

    	  } else {
    		  maincenter.setActiveTab(collectDeviceView);
    	  }
    	  break;
    	  
    	 case 'auth_bus_line_module'://'线路管理':
       	  var view = maincenter.getComponent("BusLineId");
       	  if (!view) {
       		  view  = Ext.create({    		
       			  xtype:'busline',
       			  closable: true
       		  });
       		  maincenter.setActiveTab(maincenter.add(view));
       		  view.on('beforeclose', function () { view.destroy(); });
       	  } else {
       		  maincenter.setActiveTab(view);
       	  }
       	  break;
       	  
         case 'gas_gun_mgmt_module'://'气枪管理':
       	  var view = maincenter.getComponent("gasGunInfoId");
       	  if (!view) {
       		  view  = Ext.create({    		
       			  xtype:'gasGunInfo',
       			  closable: true
       		  });
       		  maincenter.setActiveTab(maincenter.add(view));
       		  view.on('beforeclose', function () { view.destroy(); });
       	  } else {
       		  maincenter.setActiveTab(view);
       	  }
       	  break;
       	
       	  
         case 'report_new_bus_gas_module'://'新车加气记录':
          	  var view = maincenter.getComponent("NewBusFuelReportId");
          	  if (!view) {
          		  view  = Ext.create({    		
          			  xtype:'newbusfuelreport',
          			  closable: true
          		  });
          		  maincenter.setActiveTab(maincenter.add(view));
          		  view.on('beforeclose', function () { view.destroy(); });
          	  } else {
          		  maincenter.setActiveTab(view);
          	  }
          	  break;
          case 'email_module'://'邮件明细表':
              var view = maincenter.getComponent("emailInfoId");
              if (!view) {
                  view  = Ext.create({
                      xtype:'emailinfo',
                      closable: true
                  });
                  maincenter.setActiveTab(maincenter.add(view));
                  view.on('beforeclose', function () { view.destroy(); });
              } else {
                  maincenter.setActiveTab(view);
              }
              break;



         case 'gas_class_stat_module'://'加气站班次统计':
         	  var view = maincenter.getComponent("ClassFuelStatReportId");
         	  if (!view) {
         		  view  = Ext.create({    		
         			  xtype:'classfuelstatreport',
         			  closable: true
         		  });
         		  maincenter.setActiveTab(maincenter.add(view));
         		  view.on('beforeclose', function () { view.destroy(); });
         	  } else {
         		  maincenter.setActiveTab(view);
         	  }
         	  break;

          case 'auth_bus_module'://'车辆管理':
              var view = maincenter.getComponent("BusInfoId");
              if (!view) {
                  view  = Ext.create({
                      xtype:'businfo',
                      closable: true
                  });
                  maincenter.setActiveTab(maincenter.add(view));
                  view.on('beforeclose', function () { view.destroy(); });
              } else {
                  maincenter.setActiveTab(view);
              }
              break;


      default:
    	  console.log('no tab matched:'+record.data.id);
      }
    	  	 
	},
	//显示菜单条，隐藏左边菜单区域和顶部的按钮菜单
	showMainMenuToolbar:function(button){
	   this.getView().getViewModel().set('menuType.value','toolbar');
	},
	//显示左边菜单区域，隐藏菜单条和顶部的菜单按钮
	showLeftMenuRegion:function(button){
	  this.getView().getViewModel().set('menuType.value','tree');
	},
	//显示顶部的按钮菜单，隐藏菜单条和左边菜单区域
	showButtonMenu:function(button){
	  this.getView().getViewModel().set('menuType.value','button');
	},

	init:function(){
		this.getNotReadRemindMsgCount();
		
		mytask = {
	    	    getNotReadRemindMsgCountReturn: function(options, success, response) {
	    	    	if (success) {
	    	    		var maincenter = Ext.getCmp('app-header');
	    	    		var bellBtn = maincenter.getComponent("notReadMsgBellId");
	    	    		var count = response.responseText;
	    	    		bellBtn.setText(count);
	    	    		if(count > 0) {
	    	    			bellBtn.setIconCls('icon-bell-ring');
	    	    		}
	    	    	}
	    	    },
	    	    run:this.getNotReadRemindMsgCount,
		        interval: 60000 * 5
		};
	    Ext.TaskManager.start(mytask);
	},
	
	logout: function() {
		Ext.MessageBox.confirm('退出系统', '确认退出吗?', function(button,text){
			
			if(button == 'yes'){
				 Ext.Ajax.request({
		        	url:'./admin/logout.action',
		        	method: 'POST',
		        	scope: this,
		        	 success: function(response){
				        var text = response.responseText;
					        if(text == 'success'){
					        	//页面跳转
					        	document.location.href="./admin/login";
						        }	
				        }
				 });
			}	
		}); 
    },
	
    changePsw:function(){
    	var win = new Ext.Window({
			title : '修改密码',
			width : 325,
			height : 200,
			resizable : true,
			autoScroll : true,
			
			
			
				fieldDefaults: {
	                labelAlign: 'center'
	            },
	            reference:'userPwdform',
	               buttonAlign:'center',
	               buttons :[{
	               	xtype:'button',
	               	text:'保存',
	               	iconCls : 'icon-save',
	               	handler: this.saveNewPwd
	               }],
			
			
			
			items : [{
				
				xtype:'form',
	           	region:'center',
				border: false,
				
	                
	                items:[
		                	
	                		{
			   						xtype:'textfield',
			   						fieldLabel:'原密码',
			   						inputType: 'password', 
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'oldPwd',
			   		        		allowBlank: false, 
			   		        		maxLength:32,
		                			maxLengthText:"密码过长",
			   		        		emptyText: '原密码',
			   						labelAlign:'right'
			   				},
			   				{
			   						xtype:'textfield',
			   						fieldLabel:'新密码',
			   						padding:5,
			   						inputType: 'password', 
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'newPwd',
			   		        		allowBlank: false, 
			   		        		maxLength:32,
		                			maxLengthText:"新密码过长",
			   		        		emptyText: '新密码',
			   						labelAlign:'right'
			   				},
			   				{
			   						xtype:'textfield',
			   						fieldLabel:'确认新密码',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						inputType: 'password', 
			   						name:'secondNewPwd',
			   		        		allowBlank: false, 
			   		        		maxLength:32,
		                			maxLengthText:"新密码过长",
			   		        		emptyText: '再次输入新密码',
			   						labelAlign:'right'
			   				}
	                ]
			  }
			]
		});
		win.show();
    },
    
    saveNewPwd:function(button){
		var win   = button.up('window');   
 		var form   = win.down('form'); 
		//var form =this.getView().lookupReference('userPwdform').getForm();
		 var newPdw = form.getForm().findField("newPwd").getValue();
		 var secondNewPdw = form.getForm().findField("secondNewPwd").getValue();
		 if(newPdw == secondNewPdw ){
			if(form.isValid()) {
				form.submit({
					url:'./AuthManagement/changePwd.action',
					params:form.getValues(),
					success: function(form, action) {
						   Ext.MessageBox.alert("警告","修改成功!")
					       win.close();
					},
					failure : function(form, action) {
						
						Ext.MessageBox.alert("警告","密码错误!")
		            }
				});
			}else{
				Ext.MessageBox.alert("警告", "信息填写不完整！");
			}
		}else{
			Ext.MessageBox.alert("警告", "新密码和确认新密码输入不一致！");
		}
	},
	getNotReadRemindMsgCount: function() {
        Ext.Ajax.request({
        	url:'./systemRemind/getNotReadRemindMsgCount',
            method: 'POST',
            scope: this,
            callback: this.getNotReadRemindMsgCountReturn
        });
    },

    getNotReadRemindMsgCountReturn: function(options, success, response) {
    	if (success) {
    		var maincenter = Ext.getCmp('app-header');
    		var bellBtn = maincenter.getComponent("notReadMsgBellId");
    		var count = response.responseText;
    		console.log(count);
    		bellBtn.setText(count);
    	}
    },
    
	queryRemindMsg:function(button) {
		var win = new Ext.Window({
			title : '系统提醒消息',
			width : '90%' ,
			height : '90%' ,
			resizable : true,
			autoScroll : true,
			modal : true,
			closable : true,
			layout : {
				type : 'vbox',
				pack : 'start',
				align : 'stretch'
			},
			items : [{
				xtype:'remindMsg'
			  }
			],
			listeners: {
				close:function() {
			        Ext.Ajax.request({
			        	url:'./systemRemind/updateRemindMsgToRead',
			            method: 'POST',
			            scope: this,
			    		success:function(response) {
			    		},
			    		failure:function(response){
			    		}
			        });
			    }
			}
		});
		win.show();
		button.setText("");
		button.setIconCls('icon-bell');
	}
	
});