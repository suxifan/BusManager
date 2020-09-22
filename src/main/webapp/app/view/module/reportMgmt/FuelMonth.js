Ext.define('helloext.view.module.reportMgmt.FuelMonth', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'fuelmonthId',
    title:'集团天然气材料汇总',
    autoScroll: true,
    alias: 'widget.fuelmonth',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    requires:['helloext.store.OrgInfoStore',
              'helloext.store.FuelMonthStore',
              'helloext.view.module.reportMgmt.FuelMonthController',
              'ext.ux.DateTime'

    ],
    controller:'fuelMonthController',
    
    initComponent: function() {
    	
        var me = this;
        this.yearData = function() {
            var year = [];
            for(var i = 2010; i<=2050;i++) {
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
		        '<p>当前位置：报表管理>集团天然气材料汇总</p>'
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
            	        	xtype: 'combobox',
            	        	reference: 'fuelmonthsubgro',
            	        	publishes: 'rawValue',
            	        	fieldLabel: '单位',
            	        	labelWidth:60,
            	        	width:200,
            	        	displayField: 'orgName',
            	        	emptyText: '请选择',
                 			editable: false,

                            valueField:'orgId',
                 			store:{
                 				type:'orginfostore',
                 				filters: [{
                 					property: 'orgTypeId',                
                 					value: '5'
                 				}],
                 				
                 		         listeners:{ 
                 		 			beforeload:function(){ 
                 		 			},
                 		 			load:function(){
                 		 				
                 		 				var parentType = null;
                 		 		          var branchId = null;
                 		 		          var lineTeamId = null;
                 		 		          var st = Ext.getStore('glb_UserInfo');



                 		 		    	  var resLen = 0;
                 		 					 if(st != null){
                 		 					 	resLen = st.getCount();   
                 		 					 }





                 		 		        	   for(var i=0;i<resLen;i++){
                 		 			        	   var d=st.getAt(i);
                                                   console.log(d);
                 		 			        	   if( d.get('key')== 'parentType'){
                 		 			        		  parentType = d.get('value');
                 		 			        	   }
                 		 			        	  // console.log(d.get('key')+':'+d.get('value'));
                 		 			        	   if( d.get('key')== 'branchParentId'){
                 		 			        		   branchId = d.get('value');
                 		 			        	   }
                 		 			        	  if( d.get('key')== 'lineTeamParentId'){
                 		 			        		 lineTeamId = d.get('value');
                 		 			        	   }
                 		 		        	   }
                 		 		        	   console.log('parentType2:'+parentType+' bid:'+branchId+'ltid:'+lineTeamId);

                 		            	var st = this;

                 		            	st.clearFilter();  
                 		            	st.filterBy(function(record) {

                 		            		if (record.get('orgTypeId')!='5'){
                                                return false;
                 		            		}
                 		            		if(parentType == 'group'){
                 		               		 	return true;
                 		               	 	}else if(parentType=='branch'){
                 		               		 return record.get('orgId') == branchId;
                 		               	 	}else if(parentType=='lineTeam'){
                 		               		// return record.get('lineOrgName') == branchId; 
                 		               	 		return record.get('orgId') == branchId;
                 		               	 	}
                 		            	
                 		            	
                 		            	
                 		            	});

                                        var rec = {'orgName':'全部','orgId':'','orgTypeId':'5'};//st.getAt(0);
                                        st.insert(0,rec);

                                    }
                 		         } 

                 			},
            	        	minChars: 0,
            	        	queryMode: 'remote',
            	        	margin: '0 10 0 0',
            	        	allowBlank:true
            	        },
                        {
                            xtype: 'datetimefield',
                            format: 'Y-m-d H:i:s',
                            name: 'fuelMonthStartDate',
                            reference:'fuelMonthStartDate',
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
                            name: 'fuelMonthEndDate',
                            reference:'fuelMonthEndDate',
                            fieldLabel: '结束日期',
                            labelWidth:60,
                            margin: '0 10 0 0',
                            value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                            allowBlank: true
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
				    	width:100,
				    	handler:'exportToExcel',
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
		        '<h2>集团天然气材料汇总</h2>'
		    ]
	    },
	    {
    		style: 'padding-top: 10px;',
    		xtype: 'gridpanel',
    		columnLines:true,
    		tbar: ['->',
    		       { xtype: 'label', bind:{text: '{fuelmonthyear.value} 年 {fuelmonthmonth.value} 月'} },
    		       { xtype: 'tbspacer', width: 20 },
    		       { xtype: 'label', text: '计量单位：天然气，立方米' }
    		     ],
    		margin: '0 10 0 10',
    		flex:1,
            features: [{
                ftype: 'summary',
                dock: 'bottom'
            }],
    		columns : [
		        {
		            bind:{
                        text: '单位：{fuelmonthsubgro.rawValue}'
		            },
		        	columns: [
		        	          {
		        	        	  xtype: 'rownumberer',
		        	        	  text:"序号",
		        	        	  width:100,
		        	        	  align:'center',
		        	              summaryRenderer: function(value, summaryData, dataIndex) {
		        	                    return '合计';
		        	              }
		        	          },
		        	          {
		        	        	  text : '单位',
		        	        	  align:'center',
		        	        	  width:200,
		        	        	  dataIndex : 'orgName'
		        	          },
		        	          {
		        	        	  text : '路别',
		        	        	  align:'center',
		        	        	  width:200,
		        	        	  dataIndex : 'lineName'
		        	          }
                                ,
                                {
                                    text : '数量',
                                    align:'center',
                                    width:400,
                                    dataIndex : 'sumvolume',
                                    summaryType: 'sum',
                                    field: {
                                        xtype: 'numberfield'
                                    },
                                    summaryRenderer: function(value, summaryData, dataIndex) {
                                        return Ext.util.Format.number(value, '00.00');
                                    }
                                }
                                ,
		        	          {
		        	        	  text : '材料名称',
		        	        	  align:'center',
		        	        	  width:400,
		        	        	  dataIndex : 'fuelName'
		        	          }
		        	         ]
    			 }
//                ,
//    	          {
//    	        	  text: '销售价格',
//    	        	  width:'30%',
//    	        	  columns:[
//    	        	            {
//    	        	            	text : '单价',
//    	        	            	align:'center',
//    	        	            	width:200,
//    	        	            	dataIndex : 'price'
//    	        	            },
//    	        	            {
//    	        	            	text : '金额',
//    	        	            	align:'center',
//    	        	            	width:200,
//    	        	            	dataIndex : 'amount',
//    	        	            	summaryType: 'sum',
//    	        	                field: {
//    	        	                    xtype: 'numberfield'
//    	        	                },
//		        	        		summaryRenderer: function(value, summaryData, dataIndex) {
//		        	                  return Ext.util.Format.number(value, '00.00');
//		        	        		}
////    	        	            	renderer: function(v) { return v + '%'; }
//    	        	            }
//    	        	          ]
//    	          }
		        	          
    		],
    		store: {
    			type:'fuelMonthStore'
    		}
	    }

      ];
      this.callParent();
    }
});
