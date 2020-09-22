Ext.define('helloext.view.module.reportMgmt.EmailInfo', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.emailinfo',
    id:'emailInfoId',
    title:'邮件明细',
    layout:'fit'
    ,
    requires: [
        	  'helloext.view.module.reportMgmt.EmailInfoController',
        	  'helloext.view.module.reportMgmt.EmailInfoModel',
        	  'helloext.model.EmailInfoModel',
        	  'helloext.store.EmailInfoStore',
              'helloext.view.module.reportMgmt.EmailProcessingLog',
              'helloext.store.EmailProcessingLogStore',
              'helloext.view.module.reportMgmt.AddFuelExcelWin'

           ],
       	viewConfig:{
			forceFit: true
		} ,
    viewModel: {
        type: 'emailinfoviewmodel'
    },
    controller:'emailinfocontroller' ,

    listeners: {
	            	   cellclick: function( grid, td, cellIndex, record, tr, rowIndex, e, eOpts ) {
		            		   if(cellIndex == 4){
		            			   var data = record.get('excelPath');
                                   if(data!=null&&data!=''){
                                       window.open('/fuelimage'+data,"_blank");
                                   }

		            		   }
                               else if(cellIndex == 6){
                                   var data = record.get('emailId');

                                    if(data!=null&&data!=''){
                                        var win = new Ext.Window({
                                            title : '异常日志',
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
                                                xtype:'emailProcessingLog'

                                            }
                                            ]
                                        });

                                        win.show();

                                        var  emailProcessingLogStroe = Ext.getStore('emailProcessingLogStoreId');

                                        emailProcessingLogStroe.proxy.url = './fuelEmail/getEmailProcessingLog';
                                        emailProcessingLogStroe.proxy.method = 'post';
                                        var new_params = {
                                            emailId:data
                                        };


                                        Ext.apply(emailProcessingLogStroe.proxy.extraParams, new_params);
                                        emailProcessingLogStroe.removeAll();
                                        emailProcessingLogStroe.load();

                                    }



                               }
	            		   }
	               },

	  		tbar:[
                {
                    xtype:'button',
                    text:"模板下载",
                    iconCls : 'icon-excel',
                    handler:'showFuelExcelWin'
                },
                {
                    xtype: 'datefield',
                    name: 'emailStartDate',
                    reference:'emailStartDate',
                    fieldLabel: '开始日期',
                    labelWidth:60,
                    margin: '0 5 0 0',
                    format: 'Y-m-d',
                    value:new Date(),
                    allowBlank: true
                },
                ,'~',
                {
                    xtype: 'datefield',
                    name: 'emailEndDate',
                    reference:'emailEndDate',
                    fieldLabel: '结束日期',
                    labelWidth:60,
                    margin: '0 5 0 0',
                    format: 'Y-m-d',
                    value:new Date(),
                    allowBlank: true
                },
                {
    						xtype:'combobox',
    						fieldLabel:'邮件是否发送',
	  			    	 	labelWidth:120,
	  			    	 	width:200,
  			    	 		displayField:'text',//'dobPageElementId',
	   		        		valueField:'value',//'dobPageElementId',
	   		        		store : new Ext.data.ArrayStore({
			   	                        fields : ['value', 'text'],
			   	                        data : [
			   	                        			["0", '已发送'],
			   	                        			["1", '未发送']
			   	                        	   ]
			   	                    }),
			   	            name:'emailSendType',
                            reference:'emailSendType'

	  			       },
                { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryEmailInfo' },
                { xtype:'button', text:"下载邮件", iconCls : 'icon-email_go', handler:'downloadEmail' },
                { xtype:'button', text:"无数据日期统计",iconCls:'icon-grid', handler:'queryNoDataDay' }
	  		],

		     columns : [
		        			{
		        				xtype: 'rownumberer',
		        				text:"序号",
		        				width:'5%',
		        				align:'center'
		        			},
		        			{
		        				text : '加气站',
		        				width:'15%',
		        				flex: 1 ,
		        				align:'center',
		        				dataIndex : 'customItem1'
		        			},
                            {
                                 text : '发送时间',
                                 width:'15%',
                                 align:'center',
                                 dataIndex : 'sendTimeStr'
                            },
                             {
                                 text : '附件',
                                 width:'25%',
                                 align:'center',
                                 dataIndex : 'excelPath',
                                 renderer:function(value){
                                     if(value!=null&&value!=''){
                                         return "<span>下载</span>"
                                     }
                                     else{
                                         return '无';
                                     }
                                 }
                             },
                             {
                                 text : '状态',
                                 width:'20%',
                                 align:'center',
                                 dataIndex : 'processingState',
                                 renderer:'procexsingStateValue'
                             }
                             ,
                             {
                                 text : '异常日志',
                                 width:'10%',
                                 align:'center',
                                 dataIndex : 'processingState',
                                 renderer:'emailProcessingLogWin'
                             }

		        		]

                        ,
		            	  bind:{
		       		       store:'{emailInfoStore}'
		       		     },
		       		    selModel:'checkboxmodel',
	        			dockedItems : [ {
	        				xtype : 'pagingtoolbar',
	        				dock : 'bottom',
	        				bind:{
	 		       		       store:'{emailInfoStore}'
	 		       		     },
	        				pageSize : 25,
	        				displayInfo : true,
	        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
	        				emptyMsg : '没有数据'
	        			}]
});