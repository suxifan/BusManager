Ext.define('helloext.view.module.reportMgmt.BranchDetailFuelReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'BranchDetailFuelReportId',
    requires:[
'helloext.view.module.reportMgmt.BranchDetailFuelReportModel',
'helloext.view.module.reportMgmt.BranchDetailFuelReportController',
        'ext.ux.DateTime'
              ],
    title:'分公司油气明细表',
    autoScroll: true,
    alias: 'widget.branchdetailfuelreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'branchdetailfuelreportmodel'
    },
    controller:'branchdetailfuelreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>分公司油气明细表</p>'
    ]
   },
 {
    xtype: 'fieldset',
    width: '100%',
    padding: '10 10 10 10',
    margin: '10 10 0 10',
    //layout:{type:"column"},  
    items: [
            
       {
    	xtype: 'container',
    	layout: {
    		type: 'hbox'	
    	},
    	bodyPadding: 5,
    	items: [
    	        

  		      {
    	        	xtype: 'combobox',
    	        	reference: 'gasStation',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '加气站',
    	        	labelWidth:60,
    	        	width:200,
                  margin: '0 10 0 0',

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
    	      },
  		      {
    	        	xtype: 'combobox',
    	        	reference: 'branch',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '分公司',
    	        	labelWidth:60,
                  margin: '0 10 0 0',

                  width:200,
    	        	//multiSelect:true,
    	        	displayField: 'orgName',
    	        	emptyText: '请选择',
    	        	editable: false,
         			valueField:'orgId',
         			bind:{
	    		       store:'{branchStore}'
	    		     },	
    	        	minChars: 0,
    	        	queryMode: 'local',
    	        	allowBlank:false,
    	        	 listeners:{  
    	        		 
    	        		beforeRender:function(){
    	        			 this.value='';
    	        			 },
    	        	          /*afterRender: function(combo) {
    	        	        　　		//var firstValue = store.reader.jsonData[0].text;
    	        	        　　		//combo.setValue(firstValue);//同时下拉框会将与name为firstValue值对应的 text显示
    	        	        　　		
    	        	        	  var store = combo.getStore();
    	        	        	  console.log(store);
    	        	        	  console.log(store.getCount());
    	        	        	  var rec = store.getAt(0);
    	        	        	  rec.set('orgName','全部');
    	        	        	  rec.set('orgId','dd');
    	        	        	  store.add(rec);
    	        	        	  store.add(new Record({
    	        	        　　		    orgName: '全部',
    	        	          		  orgId:'dd'
    	        	          		}));
    	        	          
    	        	          } , */

    	        	      
    	        		 
    	        		 
    	        		 
    	        		 
    	                 select : function(combo, record,index)  
    	                 {    
    	                	 
    	                	 //this.up('fieldset').down('combobox[reference="lineOrg"]').clearValue();;
    	                	var pid = this.getValue();
    	                	lineOrgStore = this.up('fieldset').down('combobox[reference="lineOrg"]').store;
    	                	
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
  	  	 		        	   console.log('parentType3:'+parentType+' bid:'+branchId+'ltid:'+lineTeamId);
  	  	 		          
  	                		
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	
    	                	lineOrgStore.clearFilter();  
    	                	lineOrgStore.filterBy(function(record) { 
    	                		if(pid==''){
    	                			return record.get('orgTypeId')==6 || record.get('orgTypeId')==99;
    	                		}else
    	                		if(parentType == 'group'){
    	                			return record.get('orgParentId') == pid && record.get('orgTypeId')==6|| record.get('orgId')=='';
    		               	 	}else if(parentType=='branch'){
    		               	 		return record.get('orgParentId') == pid && record.get('orgTypeId')==6|| record.get('orgId')=='';
    		               	 	}else if(parentType=='lineTeam'){
    		               		// return record.get('lineOrgName') == branchId; 
    		               	 		return record.get('orgParentId') == pid && record.get('orgTypeId')==6 && record.get('orgId') == lineTeamId|| record.get('orgId')=='';
    		               	 	}	
    	                			
    	                		
    	                		
    	                		
    	                		
    	                		
    	                		
    	                		
    	                		
    	                		
    	                		   
    	                    }); 	    
    	                 }     
    	            }
    	        },
            {
                xtype: 'combobox',
                reference: 'lineOrg',
                publishes: 'rawValue',
                fieldLabel: '路队',
                labelWidth:60,
                width:200,
                margin: '0 10 0 0',

                displayField: 'orgName',
                emptyText: '请选择',

                valueField:'orgId',
                bind:{
                    store:'{lineOrgStore}'
                },
                minChars: 0,
                queryMode: 'local',
                editable: false,
                allowBlank:false,
                listeners:{
                    beforeRender:function(){
                        this.value='';
                    },
                    select : function(combo, record,index)
                    {

                        // this.up('fieldset').down('combobox[reference="line"]').clearValue();;
                        var pid = this.getValue();
                        lineStore = this.up('fieldset').down('combobox[reference="line"]').store;
                        lineStore.clearFilter();
                        lineStore.filterBy(function(record) {
                            return record.get('lineOrgId') == pid || record.get('lineId')=='';
                        });

                    }
                }
            },
            {
                xtype: 'combobox',
                reference: 'line',
                publishes: 'rawValue',
                fieldLabel: '线路',
                labelWidth:60,
                width:200,
                margin: '0 10 0 0',

                displayField: 'lineName',
                emptyText: '请选择',

                valueField:'lineId',
                bind:{
                    store:'{lineStore}'
                },
                minChars: 0,
                queryMode: 'local',
                editable: false,
                allowBlank:false,
                listeners:{
                    beforeRender:function(){
                        this.value='';
                    },
                    select : function(combo, record,index)
                    {

                        // this.up('fieldset').down('combobox[reference="plate"]').clearValue();;
                        var pid = this.getValue();
                        busNumStore = this.up('fieldset').down('combobox[reference="plate"]').store;
                        busNumStore.clearFilter();
                        busNumStore.filterBy(function(record) {
                            if(pid != ''){
                                return record.get('lineId') == pid ;
                            }else{
                                return true;
                            }
                        });

                    }
                }
            }]},
    	        {
    	        	xtype: 'container',
                    margin: '10 10 0 0',
                    layout: {
    	        		type: 'hbox'	
    	        	},
    	        	bodyPadding: 5,
    	        	items: [

//
//                        {
//                            xtype: 'datefield',
//                            reference: 'modifyStartDate',
//                            fieldLabel: '加气日期',
//                            labelWidth:60,
//                            margin: '0 10 0 0',
//
//                            editable:false,
//                            format: 'Y-m-d',
//                            value:new Date()
//                            //allowBlank: false
//                        },
//
//                        {
//                            xtype: 'label',
//                            text: '--',
//                            margin: '5 10 10 10'
//                        },
//
//                        {
//                            xtype: 'datefield',
//                            reference: 'modifyEndDate',
//                            //fieldLabel: '加气日期',
//                            labelWidth:60,
//                            margin: '0 10 0 0',
//
//                            editable:false,
//                            format: 'Y-m-d',
//                            value:new Date()
//                            //allowBlank: false
//                        },
                        {
                            xtype: 'datetimefield',
                            reference: 'modifyStartDate',
                            width:235,
                            fieldLabel: '加气日期',
                            labelWidth:60,
                            margin: '0 10 0 0',
                            format: 'Y-m-d H:i:s',
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
                            reference: 'modifyEndDate',
                            width:235,
//                            fieldLabel: '加气日期',
                            labelWidth:60,
                            margin: '0 10 0 0',
                            format: 'Y-m-d H:i:s',
                            value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                            allowBlank: true
                        },





                        {
                            xtype: 'combobox',
                            reference: 'oper',
                            publishes: 'rawValue',
                            fieldLabel: '运营类型',
                            labelWidth:60,
                            width:200,
                            emptyText: '请选择',
                            store : new Ext.data.ArrayStore({
                                fields : ['value', 'text'],
                                data : [[0, '全部'],[1, '运营'], [2, '非运营'], [3, '停驶']]
                            }),
                            displayField:'text',//'dobPageElementId',
                            valueField:'value',//'dobPageElementId',
                            minChars: 0,
                            queryMode: 'local',
                            editable: false,
                            value:0,
                            allowBlank:false
                        },
    	        {
    	        	xtype: 'combobox',
    	        	reference: 'plate',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '车牌号',
    	        	labelWidth:60,
    	        	width:200,
    	        	displayField: 'busNum',
    	        	emptyText: '请选择',
    	        	editable: true,
         			valueField:'busNum',
                    hidden:true,
         			bind:{
	    		       store:'{busInfoStore}'
	    		     },
         			/*bind:{
	    		       store:'{stationStore}'
	    		     },	*/
    	        	minChars: 0,
    	        	queryMode: 'local'
    	        	//allowBlank:false
    	        },
    	        
    	        {
    	        	xtype: 'textfield',
    	        	reference: 'selfNum',
    	        	publishes: 'rawValue',
    	        	fieldLabel: '自编号',
    	        	labelWidth:60,
    	        	width:200,
    	        	displayField: 'selfNum',
    	        	emptyText: '请选择',
         			valueField:'selfNum',
         			/*bind:{
         				store:'{busInfoStore}'
	    		     },*/	
    	        	minChars: 0,
    	        	queryMode: 'local'
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
        '<h2>分公司油气明细表</h2>'
    ]
},

{
		xtype: 'panel',
	    layout: {
	        type: 'hbox',
	        align: 'stretch'
	    },
        tbar: ['->',
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
	    		       store:'{branchdetailfuelStore}'
	    		     },	
	               defaults: {
	                       sortable: true,
	                       menuDisabled: true
	               },
//	               listeners: {
//	            	   cellclick: function( grid, td, cellIndex, record, tr, rowIndex, e, eOpts ) {
//		            		   console.log('当前选中的数据是'+cellIndex+':'+rowIndex);
//		            		   if(cellIndex == 14){
//		            			   var data = record.get('gasEquipmentUrl');
//		            			   window.open('/fuelimage'+data,"_blank");
//		            		   }/*else if(cellIndex == 15){
//		            			   var data = record.get('busNumUrl');
//		            			   window.open('/fuelimage'+data,"_blank");
//		            		   }*/else if(cellIndex == 5){
//		            			   var data = record.get('busNumUrl');
//		            			   window.open('/fuelimage'+data,"_blank");
//		            		   }
//	            		   }
//	               },
	               columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'5%',
									align:'center'
								},
								{
									text : '加气日期',
									align:'center',
									width:'15%',
                                    flex:1,
									dataIndex : 'gasTimeStr',
									renderer:'renderDate'
								},
                               {
                                   text : '加气时间',
                                   align:'center',
                                   width:'10%',
                                   dataIndex : 'gasTimeStr',
                                   renderer:'renderTime'
                               },
								{
									text : '分公司',
									align:'center',
									width:'10%',
									dataIndex : 'orgName'
								},
								{
									text : '路队',
									align:'center',
									width:'8%',
									dataIndex : 'lineOrgName'
								},
								{
									text : '线路',
									align:'center',
									width:'5%',
									dataIndex : 'lineName'
								},
								{
									text : '车牌号',
									align:'center',
									width:'10%',
									dataIndex : 'busNum'
								},
								{
									text : '自编号',
									align:'center',
									width:'8%',
									dataIndex : 'selfNum'
								},
								{
									text : '运营类型',
									align:'center',
									width:'8%',
									dataIndex : 'operateType',
									renderer:'renderOperType'
								
								},
								{
									text : '数量',
									align:'center',
									width:'8%',
									dataIndex : 'gasVolume'
								},
//								{
//									text : '司机',
//									align:'center',
//									width:'8%',
//									dataIndex : 'driver'
//								},
//								{
//									text : '加气员',
//									align:'center',
//									width:'8%',
//									dataIndex : 'gasUserName'
//								},
								{
									text : '加气站',
									align:'center',
									width:'12%',
									dataIndex : 'gasStation'
								},{
                                     text : '数据来源',
                                   align:'center',
                                   width:'8%',
                                   dataIndex : 'gasStatus',
                                   renderer:'renderGasStatusType'

                               }
//								{
//									text : '枪号',
//									align:'center',
//									width:'5%',
//									dataIndex : 'gasGunNum'
//								},

//								{
//									text : '加气量照片',
//									align:'center',
//									width:'9%',
//									dataIndex : 'gasEquipmentUrl',
//									renderer:'renderFuelImage'
//								},
								
								
								/*{
									text : '车牌照片',
									align:'center',
									width:'5%',
									dataIndex : 'busNumUrl',
									renderer:'renderFuelImage'
								},*/
								
//								{
//
//								text : '是否刷卡',
//								align:'center',
//								width:'5%',
//								dataIndex : 'driverId',
//								renderer:'renderAuthType'
//								},
//								{
//									text : '备注',
//									align:'center',
//									width:'5%',
//									dataIndex : 'remark'
//								},
					],
        			dockedItems : [ {
        				xtype : 'pagingtoolbar',
        				dock : 'bottom',	        				
        				bind:{
 		       		       store:'{branchdetailfuelStore}'
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
