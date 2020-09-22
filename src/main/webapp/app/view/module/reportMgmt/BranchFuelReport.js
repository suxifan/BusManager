Ext.define('helloext.view.module.reportMgmt.BranchFuelReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'BranchFuelReportId',
    requires:[
'helloext.view.module.reportMgmt.BranchFuelReportModel',
'helloext.view.module.reportMgmt.BranchFuelReportController',
        'ext.ux.DateTime'


    ],
    title:'分公司燃气类汇总表',
    autoScroll: true,
    alias: 'widget.branchfuelreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'branchfuelreportmodel'
    },
    controller:'branchfuelreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>分公司燃气类汇总表</p>'
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
                xtype: 'datetimefield',
                format: 'Y-m-d H:i:s',
                name: 'branchFuelStartDate',
                reference:'branchFuelStartDate',
                fieldLabel: '开始日期',
                labelWidth:60,
                margin: '0 10 0 0',
                value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                allowBlank: true
            },
            {
                xtype: 'label',
                text: '--',
                margin: '0 10 0 0'
            },
            {
                xtype: 'datetimefield',
                format: 'Y-m-d H:i:s',
                name: 'branchFuelEndDate',
                reference:'branchFuelEndDate',
                fieldLabel: '结束日期',
                labelWidth:60,
                margin: '0 10 0 0',
                value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                allowBlank: true
            },

//    	        {
//    	        	xtype: 'combobox',
//    	        	reference: 'year',
//    	        	publishes: 'rawValue',
//    	        	fieldLabel: '时间',
//    	        	labelWidth:40,
//    	        	width:120,
//    	        	displayField: 'year',
//    	        	emptyText: '请选择',
//         			editable: false,
//         			valueField:'year',
//         			value:1900+(new Date()).getYear(),
//    	        	store: {
//    	        		 fields: ['year'],
//        				 data: [['2011'],['2012'],['2013'],['2014'],['2015'],['2016'],['2017'],
//              				 		['2018'],['2019'],['2020'],['2021'],['2022']]
//    	        	},
//    	        	minChars: 0,
//    	        	queryMode: 'local',
//    	        	allowBlank:false
//    	        },
// 		        {
// 		           xtype: 'label',
// 		           text: '年',
// 		           margin: '5 10 10 10'
// 		        },
//    	        {
//    	        	xtype: 'combobox',
//    	        	reference: 'month',
//    	        	publishes: 'rawValue',
//    	        	width:75,
//    	        	displayField: 'month',
//    	        	emptyText: '请选择',
//    	        	valueField:'month',
//    	        	value:(new Date()).getMonth()+1,
//         			editable: false,
//    	        	store: {
//    	        		 fields: ['month'],
//        				 data: [['1'],['2'],['3'],['4'],['5'],['6'],['7'],
//        				 		['8'],['9'],['10'],['11'],['12']]
//    	        	},
//    	        	minChars: 0,
//    	        	queryMode: 'local',
//    	        	allowBlank:false
//    	        },
// 		        {
//  		           xtype: 'label',
//  		           text: '月',
//  		           margin: '5 10 10 10'
//  		        },
  		        
  		      {
    	        	xtype: 'combobox',
    	        	reference: 'branch',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '分公司',
    	        	labelWidth:60,
    	        	width:200,
    	        	displayField: 'orgName',
    	        	emptyText: '请选择',
         			editable: false,
         			valueField:'orgId',
         			bind:{
	    		       store:'{branchStore}'
	    		     },	
         			/*store:Ext.create('Ext.data.Store',{
    				    autoLoad: true,
    				    //model: 'helloext.model.Org',
    				    params: {
    				    	orgTypeId:'5',
                        },
    				    fields: [
    				   	         { name: 'orgId', type: 'string' },
    				   	         { name: 'orgName', type: 'string' },
    				   	         { name: 'orgDescription', type: 'string' },
    				   	         { name: 'orgParentId', type: 'string'},
    				   	         { name: 'orgTypeId', type: 'string'},
    				   	         { name: 'orgTypeName', type: 'string'},
    				   	         {name:'isEnabled', type:'boolean'}
    				         ],
    				         filters: [{
    				             property: 'orgTypeId',                
    				             value: '5'
    				         }],
         				proxy:{
         					   	type: 'ajax',
         					      	url: './org/getOrgInfo',
         							actionMethods : { 
         								read : 'POST' 
         							},
         							paramsAsJson : true,
         							reader: {
         							   	type: 'json',
         						        rootProperty: 'items',
         							}
         				},
        		   }),*/
    	        	minChars: 0,
    	        	queryMode: 'local',
    	        	allowBlank:false
    	        }
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
        '<h2>分公司燃气类汇总表</h2>'
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
	               columnLines:true,
                   features: [{
                       ftype: 'summary',
                       dock: 'bottom'
                   }],
	               bind:{
	    		       store:'{branchfuelStore}'
	    		     },	
	                   defaults: {
	                       sortable: true,
	                       menuDisabled: true
	                   },
	               //columns:[

	                	   columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'10%',
									align:'center',
			        	            summaryRenderer: function(value, summaryData, dataIndex) {
			        	                  return '总计';
			        	            }
								},
								{
									text : '分公司',
									align:'center',
									width:'20%',
									dataIndex : 'orgName'
								},
								{
									text : '路队',
									align:'center',
									width:'30%',
									dataIndex : 'lineOrgName'
								},
								{
									text : '线路',
									align:'center',
									width:'20%',
									dataIndex : 'lineName'
								},
								{
									text : '数量',
									align:'center',
									width:'20%',
									dataIndex : 'allVolume',
			        	        	summaryType: 'sum',
			        	        	summaryRenderer: function(value, summaryData, dataIndex) {   
			        	                  return Ext.util.Format.number(value, '00.00');
			        	            },
	        	                	field: {
	        	                     xtype: 'numberfield'
	        	                	}
								}
								/*{
									text : '备注',
									align:'center',
									width:'20%',
									dataIndex : 'remark'
								}*/
								],

	              // ],
	               flex:2
	           },

	           {
	               xtype: 'chart',
	               flex:1,
	               style: 'background: #fff',
	               //insetPadding: 40,
	               animate: true,
	               shadow: false,
	               /*store : new Ext.data.ArrayStore({  
	                      fields : ['value', 'text'],  
	                      data : [["1", '男'], ["0", '女']]  
	                  }),*/
	               bind:{
	    		       store:'{branchfuelStore}'
	    		     },	
	              /* store: Ext.create('Ext.data.JsonStore', {
	                   fields: ['month', 'data1' ],
	                   data: [
	                       { month: 'Jan', data1: 20 },
	                       { month: 'Feb', data1: 20 },
	                       { month: 'Mar', data1: 19 },
	                       { month: 'Apr', data1: 18 },
	                       { month: 'May', data1: 18 },
	                       { month: 'Jun', data1: 17 },
	                       { month: 'Jul', data1: 16 },
	                       { month: 'Aug', data1: 16 },
	                       { month: 'Sep', data1: 16 },
	                       { month: 'Oct', data1: 16 },
	                       { month: 'Nov', data1: 15 },
	                       { month: 'Dec', data1: 15 }
	                   ]
	               }),*/
	               axes: [{
	                   type: 'Numeric',
	                   position: 'left',
	                   fields: ['allVolume'],
	                   label: {
	                       //renderer: function(v) { return v + '%'; }
	                   },
	                   grid: true,
	                   minimum: 0
	               }, {
	                   type: 'Category',
	                   position: 'bottom',
	                   fields: ['lineName'],
	                   grid: true,
	                   label: {
	                       rotate: {
	                           degrees: -45
	                       }
	                   }
	               }],
	               series: [{
	                   type: 'column',
	                   axis: 'left',
	                   xField: 'lineName',
	                   yField: 'allVolume',
	                   style: {
	                       opacity: 0.80
	                   },
	                   highlight: {
	                       fill: '#000',
	                       'stroke-width': 20,
	                       stroke: '#fff'
	                   },
	                   tips: {
	                       trackMouse: true,
	                       style: 'background: #FFF',
	                       height: 20,
	                       renderer: function(storeItem, item) {
	                           this.setTitle(storeItem.get('lineName') + ': ' + storeItem.get('allVolume'));
	                       }
	                   }
	               }]
	           }]
           
}

           
           ],
    initComponent: function() {
      this.callParent();
    }
});
