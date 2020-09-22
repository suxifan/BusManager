Ext.define('helloext.view.module.authMgmt.BusLine', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.busline',
    id:'BusLineId',
    title:'线路管理',
    layout:'fit',
    requires: [
        	  'helloext.view.module.authMgmt.BusLineController',
        	  'helloext.view.module.authMgmt.BusLineModel',
           ], 
       	viewConfig:{
			forceFit: true
		},
    viewModel: {
        type: 'busline'
    },
           controller:'busline',
	  		tbar:[
	  			       {
	  			    	   xtype:'button', 
	  			    	   text:"新增",
	  			    	   iconCls : 'icon-add',
	  			    	   id:'busline_add',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenAdd}'
	  			    	   },
	  			    	   //hidden:'{isHidden}',
	  			    	   float:'left',
	  			    	   iconCls : 'icon-add',
	  			    		handler:'addBusLine',
	  			    		//flex:1
	  			       },
	  			       {
	  			    	   xtype:'button', text:"修改",float:'left',
	  			    	   iconCls : 'icon-edit',
	  			    	   id:'busline_update',
	  			    	 iconCls : 'icon-edit',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenUpdate}'
	  			    	   },
	  			    	   handler:'updateBusLine',
	  			    		 hidden:false,   
	  			       },
	  			       {
	  			    	   xtype:'button', text:"删除",float:'left',
	  			    	   iconCls : 'icon-delete',
	  			    	   id:'busline_delete',
	  			    	 iconCls : 'icon-delete',
	  			    	   bind:{
	  			    		 hidden:'{isHiddenDelete}'
	  			    	   },
	  			    	   handler:'deleteBusLine',
	  			       },
	  			       { xtype: 'tbspacer', width: 20 },'-',
	  	    	       { xtype:'textfield', fieldLabel:"线路名称",  //id:'roleName', 
	  			    	 labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
	  		    	   { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryBusLine' }
	  		],

		     columns : [
		        			{
		        				xtype: 'rownumberer',
		        				text:"序号",
		        				width:'10%',
		        				align:'center'
		        			},
		        			{
		        				text : '编号',
		        				width:'15%',
		        				align:'center',
		        				dataIndex : 'lineId',
		        				hidden:true
		        			},
		        			{
		        				text : '名称',
		        				width:'20%',
		        				align:'center',
		        				dataIndex : 'lineName'
		        			},
		        			{
		        				text : '别名',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'lineAlias'
		        			},
		        			{
		        				text : '所属机构',
		        				width:'20%',
		        				align:'center',
		        				dataIndex : 'lineOrgName',
		        				
		        			},
		        			{
		        				text : '线路状态',
		        				width:'10%',
		        				align:'center',
		        				dataIndex : 'lineStatus',
		        				renderer:'renderBusLineStatus',
		        			},
		        			{
		        				text : '创建时间',
		        				width:'30%',
		        				align:'center',
		        				dataIndex : 'createdStr',
		        			},
		        		],
		            	  bind:{
		       		       store:'{lineStore}'
		       		     },
		       		    selModel:'checkboxmodel',
	        			dockedItems : [ {
	        				xtype : 'pagingtoolbar',
	        				dock : 'bottom',	        				
	        				bind:{
	 		       		       store:'{lineStore}'
	 		       		     },
	        				pageSize : 25,
	        				displayInfo : true,
	        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
	        				emptyMsg : '没有数据'
	        			}]
});