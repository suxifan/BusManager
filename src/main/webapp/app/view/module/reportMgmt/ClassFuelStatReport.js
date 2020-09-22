Ext.define('helloext.view.module.reportMgmt.ClassFuelStatReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'ClassFuelStatReportId',
    requires:[
'helloext.view.module.reportMgmt.ClassFuelStatReportModel',
'helloext.view.module.reportMgmt.ClassFuelStatReportController',

              ],
    title:'加气站班次日汇总',
    autoScroll: true,
    alias: 'widget.classfuelstatreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'classfuelstatreportmodel'
    },
    controller:'classfuelstatreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>加气站班次日汇总</p>'
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
			},
/*			{
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
        '<h2>加气站班次日汇总</h2>'
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
	    		       store:'{classfuelstatStore}'
	    		     },	
	               defaults: {
	                       sortable: true,
	                       menuDisabled: true
	               },

	               columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'15%',
									align:'center'
								},
								{
									text : '上传时间',
									align:'center',
									width:'20%',
									dataIndex : 'createdStr'
								},
								{
									text : '加气站',
									align:'center',
									width:'15%',
									dataIndex : 'gasStation'
								},
								{
									text : '上传人',
									align:'center',
									width:'15%',
									dataIndex : 'gasUserName'
								},
								{
									text : '照片',
									align:'center',
									width:'25%',
									dataIndex : 'gasStatUrl',
									renderer:'renderFuelImage'
								},
								{
									text : '备注',
									align:'center',
									width:'10%',
									dataIndex : 'remark'
								},
/*								{
									text : 'imei',
									align:'center',
									width:'10%',
									dataIndex : 'deviceImei'
								},*/
					],
					
					listeners:{
						
						 'cellclick':function(obj,td, cellIndex, record, tr, rowIndex, e){
   						var status = record.get("gasStatus");
   						var opinion = record.get("auditOptinion");
   						
            	      	var gasModifiedId = record.get("gasModifiedId");
            	      	var gasDetailId = record.get("gasDetailId");
   						
   						var pView = this.getView().up('panel').up('basic-column');//.down('fieldset');//this.getView().up('fieldset');
   						console.log('index:'+cellIndex+':'+gasModifiedId+':'+gasDetailId);
   						if(cellIndex == 4){
			            			   var data = record.get('gasStatUrl');   
			            			   window.open('/fuelimage'+data,"_blank");
			            	}
							
						}
						
						},
					
					
					
					
					
					
					
					
					
					
        			dockedItems : [ {
        				xtype : 'pagingtoolbar',
        				dock : 'bottom',	        				
        				bind:{
 		       		       store:'{classfuelstatStore}'
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
