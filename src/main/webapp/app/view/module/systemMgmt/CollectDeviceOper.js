Ext.define('helloext.view.module.systemMgmt.CollectDeviceOper', {
    extend: 'Ext.panel.Panel',
    //extend:'Ext.window.Window',
	//extend: 'Ext.container.Container',
    alias: 'widget.collectdeviceoper',
    title:'新增设备',
    layout:'border',
    id:'CollectDeviceOperId',
    requires: [
        	  'helloext.view.module.systemMgmt.CollectDeviceOperController',
        	  'helloext.view.module.systemMgmt.CollectDeviceOperModel',
        	  'Ext.form.Panel'
           ],
    viewModel: {
        type: 'collectdeviceopermodel'
    },
           controller:'collectdeviceopercontroller',    
           bind:{
        	   title:'{title}',
           },
           items:[{
	           	xtype:'form',
	           	region:'center',
				border: false,
				fieldDefaults: {
	                labelAlign: 'center',
	            },
	            reference:'collectdeviceform',
	               buttonAlign:'center',
	               buttons :[{
	               	xtype:'button',
	               	text:'保存',
	               	iconCls : 'icon-save',
	               	handler:'saveCollectDevice'
	               },{
	               	xtype:'button',
	               	text:'关闭',
	               	iconCls : 'icon-cancel',
	               	handler:'closeWindow'
	               }],
           items:[
  					
	  				 
	  					       
				  					       
			  				{
			   						xtype:'textfield',
			   						fieldLabel:'设备名称',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'deviceName',
			   						bind:{
			   		        			value:'{deviceName}'
			   		        		},
			   		        		allowBlank: false, 
			   		        		maxLength:32,
			   		        		regex:/^[\w\u4E00-\u9FFF]+$/,
		                			maxLengthText:"设备名称过长",
			   		        		emptyText: '设备名称',
			   						labelAlign:'right',
			   				},
			   				{
			   						xtype:'textfield',
			   						fieldLabel:'设备编号',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'deviceNum',
			   						maxLength:32,
			   						regex:/^[\w\u4E00-\u9FFF]+$/,
		                			maxLengthText:"设备编号过长",
			   						bind:{
			   		        			value:'{deviceNum}'
			   		        		},
			   		        		allowBlank: false, 
			   		        		emptyText: '设备编号',
			   						labelAlign:'right',
			   				 },	
			   				 {
			   						xtype:'combobox',
			   		        		fieldLabel:'设备状态',
			   		        		labelAlign:'right',
			   		        		store : new Ext.data.ArrayStore({  
			   	                        fields : ['value', 'text'],  
			   	                        data : [["1", '使用'], ["0", '未使用']]  
			   	                    }),
			   		        		bind:{
			   		        			//store:'{orginfo}',
			   		        			value:'{status}'
			   		        		},
			   		        		displayField:'text',//'dobPageElementId',
			   		        		valueField:'value',//'dobPageElementId',
			   		        		padding:5,
			   		        		labelWidth:80,
			   		        		editable: false,
			   		        		name:'status',
			   		        		allowBlank: false, 
			   		        		emptyText: '请选择设备状态'
			   		        		
					    		    	
			   				  },
			   				   {
			   					  
			   					  
				    	        	xtype: 'combobox',
				    	        	fieldLabel: '所属加气站',
				    	        	labelAlign:'right',
				    	        	padding:5,
			   		        		labelWidth:80,
				    	        	displayField: 'orgName',
				    	        	emptyText: '请选择',
				    	        	editable: false,
				         			valueField:'orgId',
				         			bind:{
					    		       store:'{stationStore}',
					    		       value:'{gasStationId}'
					    		    	   
					    		     },	
				    	        	minChars: 0,
				    	        	queryMode: 'local',
				    	        	name:'gasStationId',
//				    	        	allowBlank:false,
				    	        	listeners: {
					    		    	 select:function(){
					    		    	 		
					    		    	 		Ext.getCmp('gasStation').setValue(this.getRawValue());
					    		    	
					    		    	 	}
			   		        		}
				    	        	 
				    	        	
				    	      },
				    	       {
			   						xtype:'textfield',
			   						fieldLabel:'设备IMEI',
			   						padding:5,
			   						regex:/^[\w\u4E00-\u9FFF]+$/,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'deviceImei',
			   						maxLength:32,
		                			maxLengthText:"设备IMEI过长",
			   						bind:{
			   		        			value:'{deviceImei}'
			   		        		},
			   		        		allowBlank: false, 
			   		        		emptyText: '设备IMEI',
			   		        		labelAlign:'right'
			   						
			   				 },	
				    	      {
			   						xtype:'textfield',
			   						fieldLabel:'dobLastLoginIp',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:60,
			   						name:'created',
			   						bind:{
			   		        			value:'{created}'
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
			   						name:'gasStation',
			   						id:'gasStation',
			   						bind:{
			   		        			value:'{gasStation}'
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
			   						name:'deviceId',
			   						bind:{
			   		        			value:'{deviceId}'
			   		        		},
			   						labelAlign:'right',
			   						hidden:true
		   						}
                 
   				]

           }]

});