Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSetting', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.branchfuelparamsetting',
    id:'paramSettingView',
    title:'参数设置',
    layout:'fit',
    requires: [
        	  'helloext.view.module.systemMgmt.BranchFuelParamSettingController',
        	  'helloext.view.module.systemMgmt.BranchFuelParamSettingModel',
        	  'helloext.model.FuelParamSettingModel',
        	  'helloext.store.FuelParamSettingStore'
           ], 
	       	viewConfig:{
				forceFit: true
			}
			,
		    viewModel: {
		        type: 'branchfuelparamsettingmodel'
		    },
           controller:'branchFuelParamSettingController',
	  		tbar:[
	  			      
	  			       {
	  			    	   xtype:'button', text:"修改",float:'left',
	  			    	   iconCls : 'icon-edit',
	  			    	   id:'param_setting_update',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenUpdate}'
	  			    	   },
	  			    	   iconCls : 'icon-edit',	  			    	
	  			    	   handler: 'updateParamSettingInfo'
	  			       }
	  		],

		     columns : [
		        			{
		        				xtype: 'rownumberer',
		        				text:"序号",
		        				width:'5%',
		        				align:'center'
		        			},
		        			{
		        				text : '参数名称',
		        				flex:1,
		        				align:'center',
		        				dataIndex: 'paraKey' ,
		        				renderer : 'changParamKeyToCn'
		        			},
		        			{
		        				text : '参数',
		        				width:'30%',
		        				align:'center',
		        				dataIndex : 'paraValue'
		        			}
		        		],
		            	  bind:{
		       		       store:'{paramSettingStore}'
		       		     },
		       		    selModel:'checkboxmodel',
	        			dockedItems : [ {
	        				xtype : 'pagingtoolbar',
	        				dock : 'bottom',	        				
	        				bind:{
	 		       		       store:'{paramSettingStore}'
	 		       		     },
	        				pageSize : 25,
	        				displayInfo : true,
	        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
	        				emptyMsg : '没有数据'
	        			}]
});