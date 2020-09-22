Ext.define('helloext.view.module.reportMgmt.GroupFuelMonth', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'groupfuelmonthId',
    title:'燃油中心燃气类汇总表',
    autoScroll: true,
    alias: 'widget.groupfuelmonth',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    requires:['helloext.store.GroupFuelMonthStore',
              'helloext.view.module.reportMgmt.GroupFuelMonthController',
              'helloext.store.OrgInfoStore',
              'ext.ux.DateTime'

    ],
    controller:'groupMonthController',
    viewModel: {
    	data:{
   		 title:'燃油中心燃气类汇总表',
   	     mainApp:null
    	},
        stores:{
            stationStore:{
                type:'orginfostore',
                filters: [{
                    property: 'orgTypeId',
                    value: '2'
                }],

                listeners:{
                    beforeload:function(){
                    },
                    load:function(){
                        var st = this;
                        //st.clearFilter();
                        var rec = {'orgName':'全部','orgId':'','orgTypeId':'2'};//st.getAt(0);
                        //st.add(rec);
                        st.insert(0,rec);
                        //store.insert(st.getCount(), rec);
                    }
                }
            }
        },
       formulas: {
    	   
    	   isHiddenField : {
   	    	get:function(get){	
   	    		var hidden = false;
   	    		if(this.getView().title=='首页'){
   	    			hidden = true;
   	    		}
   				
   				return hidden;
   			},

   	       set: function (value) {     
   	       }
   	    }

       }
    },
    //bind:{
 	 //  title:'{title}',
    //},
    initComponent: function() {
    	
        var me = this;
        this.yearData = function() {
            var year = [];
            for(var i = 2010; i<=2050; i++) {
            	var array = [];
            	array.push(i);
            	year.push(array);
            }
            return year;
		}
        this.store = Ext.create('helloext.store.GroupFuelMonthStore');
        if(this.title=='首页'){
        	console.log("first page.");
        	var store = Ext.getStore('groupFuelMonthStoreId');
	      	store.proxy.url = './fuelReport/getFuelMonthReportBySubGroup';
	      	store.proxy.method = 'post';
	      	var new_params = {  year:'2015',
	      						month:'7' };    
            Ext.apply(store.proxy.extraParams, new_params);
            store.removeAll();
      	    store.load();
        }


        me.items = [
		   {
		    xtype: 'component',
		    anchor: '100%',
		    margin: '5 0 0 10',
		    html: [
		        '<p>当前位置：报表管理>燃油中心燃气类汇总表</p>'
		    ]
		   },
         {
            xtype: 'fieldset',
            width: '100%',
            padding: '10 10 10 10',
            margin: '10 10 0 10',
            bind:{
		    		 hidden:'{isHiddenField}'
		    	   },
            items: [{
            	xtype: 'container',
            	layout: {
            		type: 'hbox'
            	},
            	bodyPadding: 5,
            	items: [
//            	        {
//            	        	xtype: 'combobox',
//            	        	reference: 'groupfuelmonthyear',
//            	        	publishes: 'value',
//            	        	fieldLabel: '月 份',
//            	        	labelWidth:40,
//            	        	width:120,
//            	        	displayField: 'year',
//            	        	emptyText: '请选择',
//                 			editable: false,
//                 			valueField:'year',
//            	        	store: {
//            	        		 fields: ['year'],
//                				 data: this.yearData()
//            	        	},
//            	        	value:1900+(new Date()).getYear(),
//            	        	minChars: 0,
//            	        	queryMode: 'local',
//            	        	allowBlank:false
//            	        },
//         		        {
//         		           xtype: 'label',
//         		           text: '年',
//         		           margin: '5 10 10 10'
//         		        },
//            	        {
//            	        	xtype: 'combobox',
//            	        	reference: 'groupfuelmonthmonth',
//            	        	publishes: 'value',
//            	        	width:75,
//            	        	displayField: 'month',
//            	        	emptyText: '请选择',
//            	        	valueField:'month',
//                 			editable: false,
//            	        	store: {
//            	        		 fields: ['month'],
//                				 data: [['1'],['2'],['3'],['4'],['5'],['6'],['7'],
//                				 		['8'],['9'],['10'],['11'],['12']]
//            	        	},
//            	        	value:(new Date()).getMonth()+1,
//            	        	minChars: 0,
//            	        	queryMode: 'local',
//            	        	allowBlank:false
//            	        },
//         		        {
//          		           xtype: 'label',
//          		           text: '月',
//          		           margin: '5 10 10 10'
//          		        }




                    {

                                xtype: 'datetimefield',
                                format: 'Y-m-d H:i:s',
                                name: 'groupFuelMonthStartDate',
                                reference:'groupFuelMonthStartDate',
                                fieldLabel: '开始日期',
                                labelWidth:60,
                                margin: '5 10 10 10',
                                value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                                allowBlank: true
                            },
                            {
                                xtype: 'label',
                                text: '--',
                                margin: '5 10 10 10'
                            },
                            {

                                xtype: 'datetimefield',
                                format: 'Y-m-d H:i:s',
                                name: 'groupFuelMonthEndDate',
                                reference:'groupFuelMonthEndDate',
                                fieldLabel: '结束日期',
                                labelWidth:60,
                                margin: '5 10 10 10',
                                value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                                allowBlank: true
                            },
                            {
                                xtype: 'combobox',
                                reference: 'fuelGasStationOrg',
                                publishes: 'rawValue',
                                fieldLabel: '加气公司',
                                margin: '5 10 10 10',
                                labelWidth:60,
                                width:150,
                                displayField: 'orgName',
                                emptyText: '请选择',
                                editable: false,
                                valueField:'orgId',
                                bind:{
                                    store:'{stationStore}'
                                },
                                minChars: 0,
                                queryMode: 'local',
                                value:'',
                                allowBlank:false
                                //allowBlank:false
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
				    	handler:'queryInfo',
				    	margin: '0 10 0 0'
					},
					{
				    	xtype:'button', 
				    	text:"导出excel",
				    	iconCls : 'icon-excel',
				    	handler:'exportToExcel',
				    	width:100,
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
		        '<h2>燃油中心燃气类汇总表</h2>'
		    ]
	    },
	    {
	    	xtype: 'panel',
	        layout: {
	            type: 'hbox',
	            align: 'stretch'
	        },
		    tbar: ['->',
//   		       { xtype: 'label', bind:{text: '{groupfuelmonthyear.value} 年 {groupfuelmonthmonth.value} 月'} },
   		       { xtype: 'tbspacer', width: 20 },
   		       { xtype: 'label', text: '计量单位：天然气，立方米' }
   		    ],
	        margin: '5 10 0 10',
	        flex:1,
	        bodyPadding: 5,
	        items:[
	               {
	                   style: 'padding-top: 10px;',
	                   xtype: 'gridpanel',
	                   columnLines:true,
	                   features: [{
	                       ftype: 'summary',
	                       dock: 'bottom'
	                   }],
	                   columns : {
	                       defaults: {
	                           sortable: true,
	                           menuDisabled: true
	                       },
	                       items: [
	                                //{ text: 'IE', dataIndex: 'data1', renderer: function(v) { return v + '%'; } }
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
										text : '单位',
										align:'center',
										width:'35%',
										dataIndex : 'orgName'
									},
									{
										text : '数量',
										align:'center',
										width:'25%',
										dataIndex : 'sumvolume',
				        	        	summaryType: 'sum',
			        	                	field: {
			        	                     xtype: 'numberfield'
			        	                },
				        	        	summaryRenderer: function(value, summaryData, dataIndex) {   
				        	                  return Ext.util.Format.number(value, '00.00');
				        	            }
									},
									{
										text : '备注',
										align:'center',
										width:'30%',
										dataIndex : 'remark'
									}
	                       ]
	                   },
	                   store: this.store,
	                   flex:1
	               },
	               {
	                   xtype: 'chart',
	                   flex:1,
	                   style: 'background: #fff',
	                   //insetPadding: 40,
	                   animate: true,
	                   shadow: false,
		           	   store: this.store,
	                   axes: [{
	                       type: 'Numeric',
	                       position: 'left',
	                       fields: ['sumvolume'],
	                       label: {
//	                           renderer: function(v) { return v + 'm3'; }
	                       },
	                       grid: true,
	                       minimum: 0
	                   }, {
	                       type: 'Category',
	                       position: 'bottom',
	                       fields: ['orgName'],
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
	                       xField: 'orgName',
	                       yField: 'sumvolume',
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
	                               this.setTitle(storeItem.get('orgName') + ': ' + storeItem.get('sumvolume'));
	                           }
	                       }
	                   }]
	               }
	        ]
	    	
	    }

      ];
      this.callParent();
    }
});
