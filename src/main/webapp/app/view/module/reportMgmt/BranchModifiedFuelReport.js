Ext.define('helloext.view.module.reportMgmt.BranchModifiedFuelReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'BranchModifiedFuelReportId',
    requires:[
'helloext.view.module.reportMgmt.BranchModifiedFuelReportModel',
'helloext.view.module.reportMgmt.BranchModifiedFuelReportController',

              ],
    title:'加气数据修改明细表',
    autoScroll: true,
    alias: 'widget.branchmodifiedfuelreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'branchmodifiedfuelreportmodel'
    },
    controller:'branchmodifiedfuelreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>加气数据修改明细表</p>'
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
			{
		    	xtype:'button', 
		    	text:"导出excel",
		    	iconCls : 'icon-excel',
		    	width:100,
		    	handler:'exportBranchFuelReportExcel',
		    	margin: '0 0 0 10'
			}]
		}
    ]
	
},
{
    xtype: 'component',
    anchor: '100%',
    margin: '10 0 0 10',
    padding: '0 0 0 500',
    html: [
        '<h2>加气数据修改明细表</h2>'
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
	    		       store:'{branchmodifiedfuelStore}'
	    		     },	
	               defaults: {
	                       sortable: true,
	                       menuDisabled: true
	               },
	               listeners: {
	            	   cellclick: function( grid, td, cellIndex, record, tr, rowIndex, e, eOpts ) {   
		            		   console.log('当前选中的数据是'+cellIndex+':'+rowIndex);
		            		   if(cellIndex == 6){
		            			   var data = record.get('gasEquipmentUrl');   
		            			   window.open('/fuelimage'+data,"_blank");
		            		   }
	            		   } 
	                      },
	               columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'10%',
									align:'center'
								},
								{
									text : '日期',
									align:'center',
									width:'20%',
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
									text : '原始加气量',
									align:'center',
									width:'10%',
									dataIndex : 'preGasVolume'
								},
								{
									text : '修改后加气量',
									align:'center',
									width:'10%',
									dataIndex : 'postGasVolume'
								},
								{
									text : '加气量照片',
									align:'center',
									width:'20%',
									dataIndex : 'gasEquipmentUrl',
									renderer:'renderFuelImage'
								},
								{
									text : '备注',
									align:'center',
									width:'10%',
									dataIndex : 'remark'
								},
					],
        			dockedItems : [ {
        				xtype : 'pagingtoolbar',
        				dock : 'bottom',	        				
        				bind:{
 		       		       store:'{branchmodifiedfuelStore}'
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
