Ext.define('helloext.view.module.systemMgmt.CollectDevice', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.collectdevice',
    id:'collectDeviceView',
    title:'设备管理',
    layout:'fit',
    requires: [
        	  'helloext.view.module.systemMgmt.CollectDeviceController',
        	  'helloext.view.module.systemMgmt.CollectDeviceModel',
        	  'helloext.model.CollectDeviceModel',
        	  'helloext.store.CollectDeviceStore'
           ], 
       	viewConfig:{
			forceFit: true
		},
    viewModel: {
        type: 'collectdevicemodel'
    },
           controller:'collectdevicecontroller',
	  		tbar:[
	  			       {
	  			    	   xtype:'button', 
	  			    	   text:"新增",
	  			    	   iconCls : 'icon-add',
	  			    	   id:'device_mgmt_module_add',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenAdd}'
	  			    	   },
	  			    	   float:'left',
	  			    	   iconCls : 'icon-add',
	  			    		handler:'addCollectDevice',
	  			    		//flex:1
	  			       },
	  			       {
	  			    	   xtype:'button', text:"修改",float:'left',
	  			    	   iconCls : 'icon-edit',
	  			    	   id:'device_mgmt_module_update',
	  			    	 iconCls : 'icon-edit',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenUpdate}'
	  			    	   },
	  			    	   handler:'updateCollectDevice',
	  			    		 hidden:false,   
	  			       		}
	  			       ,
	  			       {
	  			    	   xtype:'button', text:"删除",float:'left',
	  			    	   iconCls : 'icon-delete',
	  			    	   id:'device_mgmt_module_delete',
	  			    	 iconCls : 'icon-delete',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenDelete}'
	  			    	   },
	  			    	   handler:'deleteCollectDevice',
	  			       },
	  			       { xtype: 'tbspacer', width: 20 },'-',
	  	    	       { xtype:'textfield', fieldLabel:"设备名称",  //id:'roleName', 
	  			    	 labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
	  		    	   { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryCollectDevice' }
	  		],

		     columns : [
		        			{
		        				xtype: 'rownumberer',
		        				text:"序号",
		        				width:'5%',
		        				align:'center'
		        			},
		        			{
		        				text : '设备名称',
		        				width:'15%',
		        				flex: 1 ,
		        				align:'center',
		        				dataIndex : 'deviceName'
		        			},
		        			{
		        				text : '设备编号',
		        				width:'20%',
		        				align:'center',
		        				dataIndex : 'deviceNum'
		        			},
		        			{
		        				text : '所属加气站',
		        				width:'20%',
		        				align:'center',
		        				dataIndex : 'gasStation'
		        			},
		        			{
		        				text : '设备状态',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'status',
		        				renderer:'renderStatus'
		        			},
		        			{
		        				text : '设备IMEI',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'deviceImei'
		        			}
		        		],
		            	  bind:{
		       		       store:'{collectDeviceStore}'
		       		     },
		       		    selModel:'checkboxmodel',
	        			dockedItems : [ {
	        				xtype : 'pagingtoolbar',
	        				dock : 'bottom',	        				
	        				bind:{
	 		       		       store:'{collectDeviceStore}'
	 		       		     },
	        				pageSize : 25,
	        				displayInfo : true,
	        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
	        				emptyMsg : '没有数据'
	        			}]
});