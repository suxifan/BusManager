Ext.define('helloext.view.module.reportMgmt.NewBusFuelReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'NewBusFuelReportId',
    requires:[
'helloext.view.module.reportMgmt.NewBusFuelReportModel',
'helloext.view.module.reportMgmt.NewBusFuelReportController',

              ],
    title:'新车加气明细表',
    autoScroll: true,
    alias: 'widget.newbusfuelreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'newbusfuelreportmodel'
    },
    controller:'newbusfuelreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>新车加气明细表</p>'
    ]
   },
 {
    xtype: 'fieldset',
    width: '100%',
    padding: '10 10 10 10',
    margin: '10 10 0 10',
    items: [{
    	xtype: 'container',
    	layout: {
    		type: 'hbox'
    	},
    	bodyPadding: 5,
    	items: [
    	        {
						    xtype: 'datefield',
						    reference: 'modifyStartDate',
						    fieldLabel: '加气日期',
						    labelWidth:60,
						    margin: '0 5 0 0',
						    editable:false,
						    format: 'Y-m-d',
						    value:new Date(),
						    allowBlank: false
				},
 		        {
 		           xtype: 'label',
 		           text: '--',
 		           margin: '5 10 10 10'
 		        },
 		       {
				    xtype: 'datefield',
				    reference: 'modifyEndDate',
				    //fieldLabel: '加气日期',
				    labelWidth:60,
				    margin: '0 5 0 0',
				    editable:false,
				    format: 'Y-m-d',
				    value:new Date(),
				    allowBlank: false
 		       },
  		        
  		      {
    	        	xtype: 'combobox',
    	        	reference: 'branch',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '加气站',
    	        	labelWidth:60,
    	        	width:200,
    	        	displayField: 'orgName',
    	        	emptyText: '请选择',
         			editable: false,
         			valueField:'orgId',
         			bind:{
	    		       store:'{branchStore}'
	    		     },	
    	        	minChars: 0,
    	        	queryMode: 'local',
    	        	allowBlank:true
    	        },
    	      ]
      },
	  {
	        xtype: 'container',
	        layout: {
	            type: 'hbox',
	            pack: 'center'
	        },
	        items: [
	        {
		    	xtype:'button', 
		    	text:"查询",
		    	iconCls : 'icon-search',
		    	width:100,
		    	handler:'queryBranchFuelReport',
		    	margin: '0 10 0 0'
			}/*,
			{
		    	xtype:'button', 
		    	text:"导出excel",
		    	iconCls : 'icon-excel',
		    	width:100,
		    	handler:'exportBranchFuelReportExcel',
		    	margin: '0 0 0 10'
			}*/]
		}
    ]
	
},
{
    xtype: 'component',
    anchor: '100%',
    margin: '10 0 0 10',
    padding: '0 0 0 500',
    html: [
        '<h2>新车加气明细表</h2>'
    ]
},

{
		xtype: 'panel',
	    layout: {
	        type: 'hbox',
	        align: 'stretch'
	    },
        tbar: ['->',
               { xtype: 'label', bind:{text:'单位:{branch.rawValue}'} },
   		       { xtype: 'label', bind:{text: '{year.rawValue} 年 {month.rawValue} 月'} },
   		       { xtype: 'tbspacer', width: 20 },
   		       { xtype: 'label', text: '计量单位：天然气(升)' }
   		    ],
	    margin: '5 10 0 10',
	    flex:1,
	    bodyPadding: 5,

	items:[{
	               xtype: 'gridpanel',
	               flex:2,
	               bind:{
	    		       store:'{newfuelreportStore}'
	    		     },	
	               defaults: {
	                       sortable: true,
	                       menuDisabled: true
	               },

	               columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'10%',
									align:'center'
								},
								{
									text : '车辆名',
									align:'center',
									width:'10%',
									dataIndex : 'busName'
								},
								{
									text : '所属机构',
									align:'center',
									width:'10%',
									dataIndex : 'orgName'
								},
								{
									text : '加气日期',
									align:'center',
									width:'10%',
									dataIndex : 'gasTimeStr'
								},
								{
									text : '加气站',
									align:'center',
									width:'10%',
									dataIndex : 'gasStation'
								},
								{
									text : '加气员',
									align:'center',
									width:'10%',
									dataIndex : 'gasUserName'
								},
								{
									text : '加气量',
									align:'center',
									width:'10%',
									dataIndex : 'gasVolume'
								},
								{
									text : '司机',
									align:'center',
									width:'10%',
									dataIndex : 'gasUserName'
								},
								{
									text : '气枪编号',
									align:'center',
									width:'10%',
									dataIndex : 'gasGunNum'
								},
								{
									text : '备注',
									align:'center',
									width:'10%',
									dataIndex : 'remark'
								},

					],
					
					listeners:{
						
						 'cellclick':function(obj,td, cellIndex, record, tr, rowIndex, e){
   						var status = record.get("gasStatus");
   						var opinion = record.get("auditOptinion");
   						
            	      	var gasModifiedId = record.get("gasModifiedId");
            	      	var gasDetailId = record.get("gasDetailId");
   						
   						var pView = this.getView().up('panel').up('basic-column');//.down('fieldset');//this.getView().up('fieldset');
   						console.log('index:'+cellIndex+':'+gasModifiedId+':'+gasDetailId);
							if(cellIndex==7 || cellIndex==8){
								var win = new Ext.Window({
											title : '加气记录修改审核',
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
									            		  // console.log(form.down('combobox'));
									            	      	var gasStatus = form.down('combobox').getValue();
									            	      	var auditOptinion = form.down('textfield').getValue();
									            	      	
									            	      	form.submit({
																	url:'./fuelReport/updateModifiedFuelCheckReport.action',
																	params:{ 
																		gasModifiedId:gasModifiedId,
																		gasDetailId:gasDetailId,
																		gasStatus:gasStatus,
																		auditOptinion:auditOptinion
														                }, 
																	success: function(form, action) {
																		   Ext.MessageBox.alert("警告","修改成功!")
																	       win.close();
																		   
																	    	var meform = pView;
																	    	console.log(meform);
																	    	if(meform.isValid()) {
																	    	var branchId = meform.lookupReference('branch').getValue();
																			var sd = meform.lookupReference('modifyStartDate').getValue();
																			var ed = meform.lookupReference('modifyEndDate').getValue();
																	      	var store = Ext.getStore('ModifiedFuelCheckReportStoreId');
																	      	store.proxy.url = './fuelReport/getModifiedFuelCheckReportByParams';
																	      	store.proxy.method = 'post';
																	      	var new_params = { orgId:branchId,
																	      						queryTimeRangeStart:sd,
																	      						queryTimeRangeEnd:ed,
																	      						queryType:'QueryBranchModifiedFuelCheckReport'};    
																	           Ext.apply(store.proxy.extraParams, new_params);
																	         //Ext.apply(store.proxy.params, new_params);
																	          store.removeAll();
																	      	  store.load();
																	    	}else{
																	    		//Ext.MessageBox.alert("警告", "信息填写不完整！");
																	    	}
					   
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
										   						xtype:'combobox',
										   		        		fieldLabel:'审核状态',
										   		        		labelAlign:'right',
										   		        		reference:'gasStatus',
										   		        		store : new Ext.data.ArrayStore({  
										   	                        fields : ['value', 'text'],  
										   	                        data : [["2", '未审核'], ["3", '已审核'], ["4", '审核未通过']]  
										   	                    }),
										   	                    value:status,
										   	                    editable: false,
										   		        		displayField:'text',
										   		        		valueField:'value',
										   		        		padding:5,
										   		        		labelWidth:80,
										   		        		name:'gasStatus',
										   		        		allowBlank: false, 
										   		        		emptyText: '请选择状态',
										   					},
											   				{
										   						xtype:'textfield',
										   						fieldLabel:'审核意见',
										   						reference:'auditOptinion',
										   						padding:5, 
										   						labelWidth:80,
										   						name:'auditOptinion',
										   						value:opinion,
										   		        		maxLength:50,
									                			maxLengthText:"新意见过长",
										   						labelAlign:'right'
										   				},
										   					
										   					
									                ]
											  }
											]
										});
										win.show();
			       		    }else if(cellIndex == 6){
			            			   var data = record.get('gasEquipmentUrl');   
			            			   window.open('/fuelimage'+data,"_blank");
			            	}
							
						}
						
						},
					
					
					
					
					
					
					
					
					
					
        			dockedItems : [ {
        				xtype : 'pagingtoolbar',
        				dock : 'bottom',	        				
        				bind:{
 		       		       store:'{newfuelreportStore}'
 		       		     },
        				pageSize : 25,
        				displayInfo : true,
        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
        				emptyMsg : '没有数据'
        			}]
	}]
           
}

           
           ],
    initComponent: function() {
      this.callParent();
    }
});
