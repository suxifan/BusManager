Ext.define('helloext.view.module.sysRemind.RemindMsg', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.remindMsg',
    layout:'fit',
    requires: ['helloext.view.module.sysRemind.RemindMsgModel'],
    viewModel:{
        type:'remindMsgModel'	
    },
    columnLines:true,
    bind:{title:'{remindContent}'},
    
    tbar:[
    	
//	  	    	       {
//    						xtype:'combobox',
//    						fieldLabel:'提醒类型',
//	  			    	 	labelWidth:80,
//	  			    	 	width:300,
//  			    	 		displayField:'text',//'dobPageElementId',
//	   		        		valueField:'value',//'dobPageElementId',
//	   		        		store : new Ext.data.ArrayStore({
//			   	                        fields : ['value', 'text'],
//			   	                        data : [
////			   	                        			["0", '提醒1'],
//			   	                        			["1", '加气站数据未上传提醒']
//			   	                        		//	["2", '停驶车辆加气提醒'],
//			   	                        		//	["3", '报修提醒'],
////			   	                        			["4", '提醒5'],
//			   	                        		//	["5", '在用设备长时间不上线提醒'],
//			   	                        		//	["6", '多次加气提醒'],
//			   	                        		//	["7", '中断提醒']
//			   	                        	   ]
//			   	                    }),
//			   	            name:'remindType'
//
//	  			       },
	  			       {
						    xtype: 'datefield',
						    name: 'startDate',
						    id:'startDate',
						    fieldLabel: '开始日期',
						    labelWidth:60,
						    margin: '0 5 0 0',
						    format: 'Y-m-d',
//						    value:new Date(),
						    allowBlank: true
						},
						,'~',
						{
						    xtype: 'datefield',
						    name: 'endDate',
						    id:'endDate',
						    fieldLabel: '结束日期',
						    labelWidth:60,
						    margin: '0 5 0 0',
						    format: 'Y-m-d',
//						    value:new Date(),
						    allowBlank: true
						},
	  		    	   { 
	  			    		 xtype:'button',
	  			    		 text:"查询", 
	  			    		 iconCls : 'icon-search', 
	  			    		 handler:function(button){
								
								var startDate =  button.up('toolbar').getComponent('startDate').getValue();
								var endDate   =  button.up('toolbar').getComponent('endDate').getValue();  

								 var store = Ext.getStore("remindMsgStoreId");

								 
							  	store.proxy.method = 'post';
							  	
							  	var new_params = {  startDate : startDate , endDate : endDate };
							
							     Ext.apply(store.proxy.extraParams, new_params);
							     
							     store.removeAll();
							  	 store.load();
								 
	  			    		 } 
	  			       }
	  			       
	  		],
    
    columns : [
       			{
       				xtype: 'rownumberer',
       				text:"序号",
       				width:'10%',
       				align:'center'
       			},
       			{
					text : '创建时间',
					width:'20%',
					align:'center',
					dataIndex : 'createdStr'
				},
       			{
					text : '内容',
					width:'60%',
					flex:1,
					align:'center',
					dataIndex : 'remindContent'
//					renderer: function(value, metaData, record){
//						metaData.tdAttr = 'style="white-space:normal;"';
//						return value;
//					}
				},
       			{
					text : '状态',
					width:'10%',
					align:'center',
					dataIndex : 'remindStatus',
					renderer: function(value) {
						return value == 0?'未读':'已读';
					}
				}
       		],
    bind:{
     	store:'{remindStore}'
     	
    }
	,
    selModel:'checkboxmodel',
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		dock : 'bottom',	        				
        				bind:{
 		       		       store:'{remindStore}'
 		       		     },
        				pageSize : 25,
        				displayInfo : true,
        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据'
	}]
});
