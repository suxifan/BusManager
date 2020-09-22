Ext.define('helloext.view.module.reportMgmt.MultiFuelAddCount', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'multiFuelAddCountId',
    title:'多次加气统计',
    autoScroll: true,
    alias: 'widget.multiFuelAddCount',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    requires:['helloext.view.module.reportMgmt.MultiFuelAddCountModel',
              'helloext.view.module.reportMgmt.MultiFuelAddCountController'],
              
    controller:'multiFuelAddCountController',
    
    viewModel: {
        type: 'multiFuelAddCountModel'
    },
    
    initComponent: function() {
    	
        var me = this;
        this.yearData = function() {
            var year = [];
            for(var i = 1; i<=30;i++) {
            	var array = [];
            	array.push(i);
            	year.push(array);
            }
            return year;
		};
        me.items = [
		   {
		    xtype: 'component',
		    anchor: '100%',
		    margin: '5 0 0 10',
		    html: [
		        '<p>当前位置：报表管理>多次加气统计</p>'
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
						    reference: 'gasDate',
						    fieldLabel: '加气日期',
						    labelWidth:60,
						    margin: '0 5 0 0',
						    format: 'Y-m-d',
						    value:new Date(),
						    allowBlank: true
						},
         		        {
	          		        xtype: 'label',
	          		        margin: '5 10 10 10'
	          		    },
            	        {
            	        	xtype: 'combobox',
            	        	reference: 'busNoCom',
            	        	publishes: 'value',
            	        	fieldLabel: '车牌号',
            	        	labelWidth:50,
            	        	width:160,
            	        	displayField: 'busNum',
            	        	emptyText: '请选择',
                 			valueField:'busNum',
                 			bind:{
         	    		       store:'{busInfoStore}'
         	    		    },	
            	        	minChars: 0,
            	        	queryMode: 'local',
            	        	allowBlank:true
            	        },
         		        {
	          		        xtype: 'label',
	          		        margin: '5 10 10 10'
	          		    },
            	        {
            	        	xtype: 'textfield',
            	        	reference: 'selfNoCom',
            	        	fieldLabel: '自编号',
            	        	labelWidth:50,
            	        	width:160,
            	        	emptyText: '请输入自编号',
            	        	allowBlank:true
//            	        	publishes: 'value',
//            	        	displayField: 'selfNo',
//            	        	valueField:'selfNo',
//            	        	store: {
//            	        		 fields: ['selfNo'],
//                				 data: [['1'],['22'],['3'],['4'],['5'],['6'],['7'],
//                				 		['8'],['9'],['10'],['11'],['12']]
//            	        	},
//            	        	minChars: 0,
//            	        	queryMode: 'local',
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
				    	handler:'queryInfo',
				    	margin: '10 10 0 0'
					},
					{
				    	xtype:'button', 
				    	text:"导出excel",
				    	iconCls : 'icon-excel',
				    	handler:'exportToExcel',
				    	width:100,
				    	margin: '10 0 0 10'
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
		        '<h2>多次加气统计表</h2>'
		    ]
	    },
	    {
    		style: 'padding-top: 5px;',
    		xtype: 'gridpanel',
    		tbar: ['->',
    		       { xtype: 'label', text: '计量单位：汽柴油，元' }
    		     ],
    		margin: '0 10 0 10',
        	columns: [
        	          {
        	        	  xtype: 'rownumberer',
        	        	  text:"序号",
        	        	  width:'8%',
        	        	  align:'center'
        	          },
        	          {
        	        	  text : '加气日期',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'gasDateStr'
        	          },
        	          {
        	        	  text : '加气时间',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'gasTimeStr'
        	          },
        	          {
        	        	  text : '车牌号',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'busNum'
        	          },
        	          {
        	        	  text : '自编号',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'selfNum'
        	          },
        	          {
        	        	  text : '加气量',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'gasVolume'
        	          },
        	          {
        	        	  text : '备注',
        	        	  align:'center',
        	        	  width:'15%',
        	        	  dataIndex : 'remark'
        	          }
        	         ],
  			bind:{
		       store:'{multiFuelAddCountStore}'
		     }
//    		store: {
//    			type:'multiFuelAddCountStore'
//    		}
	    }

      ];
      this.callParent();
    }
});
