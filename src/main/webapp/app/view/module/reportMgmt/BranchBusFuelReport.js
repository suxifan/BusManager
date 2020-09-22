Ext.define('helloext.view.module.reportMgmt.BranchBusFuelReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'BranchBusFuelReportId',
    requires:[
'helloext.view.module.reportMgmt.BranchBusFuelReportModel',
'helloext.view.module.reportMgmt.BranchBusFuelReportController',
        'ext.ux.DateTime'


    ],
    title:'分公司路队单车油气汇总表',
    autoScroll: true,
    alias: 'widget.branchbusfuelreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
        type: 'branchbusfuelreportmodel'
    },
    controller:'branchbusfuelreportcontroller', 
    items:[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>当前位置：报表管理>分公司路队单车油气汇总表</p>'
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
//    		      {
//    	        	xtype: 'combobox',
//    	        	reference: 'branch',
//    	        	publishes: 'rawValue',
//    	        	fieldLabel: '分公司',
//    	        	labelWidth:60,
//    	        	width:200,
//    	        	displayField: 'orgName',
//    	        	emptyText: '请选择',
//         			editable: false,
//         			valueField:'orgId',
//         			bind:{
//	    		       store:'{branchStore}'
//	    		     },
//    	        	minChars: 0,
//    	        	queryMode: 'local',
//    	        	allowBlank:true
//    	        }
            {
                xtype: 'combobox',
                reference: 'branch',
                publishes: 'rawValue',
                fieldLabel: '分公司',
                margin: '0 10 0 0',
                labelWidth:60,
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
            }
            ,
            {
                xtype: 'combobox',
                reference: 'lineOrg',
                publishes: 'rawValue',
                fieldLabel: '路队',
                margin: '0 10 0 0',
                labelWidth:60,
                width:200,
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
            }
            ,
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

                valueField:'lineName',
                bind:{
                    store:'{lineStore}'
                },
                minChars: 0,
                queryMode: 'local',
                editable: false,
                allowBlank:false,
                listeners:{
                	beforeRender:function(){
                        this.value='全部';
                    },
//                    select : function(combo, record,index)
//                    {
//                        var pid = this.getValue();
//                    }
                }
            },

            {
                xtype: 'textfield',
                reference: 'selfNum',
                publishes: 'rawValue',
                fieldLabel: '自编号',
                margin: '0 10 0 0',
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
//            padding: '10 10 10 10',
            margin: '10 10 0 0',
            layout: {
                type: 'hbox'
            },
            bodyPadding: 5,
            items: [
                {
                    xtype: 'datetimefield',
                    format: 'Y-m-d H:i:s',
                    name: 'branchBusFuelStartDate',
                    reference:'branchBusFuelStartDate',
                    fieldLabel: '开始日期',
                    labelWidth:60,
                    width:235,
                    margin: '0 10 0 0',
                    value: new Date(new Date(new Date().toLocaleDateString()).getTime()+8*60*60*1000),
                    allowBlank: true
                },
                {
                    xtype: 'datetimefield',
                    format: 'Y-m-d H:i:s',
                    name: 'branchBusFuelEndDate',
                    reference:'branchBusFuelEndDate',
                    fieldLabel: '结束日期',
                    labelWidth:60,
                    width:235,
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
        '<h2>分公司路队单车油气汇总表</h2>'
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
	    		       store:'{branchbusfuelStore}'
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
								/*{
									text : '分公司',
									align:'center',
									width:'20%',
									dataIndex : 'orgName'
								},*/
								{
									text : '车牌号',
									align:'center',
									width:'30%',
									dataIndex : 'busNum'
								},
								{
									text : '自编号',
									align:'center',
									width:'20%',
									dataIndex : 'selfNum'
								},
								{
									text : '数量',
									align:'center',
									width:'20%',
									dataIndex : 'allVolume'
								}
					],
        			dockedItems : [ {
        				xtype : 'pagingtoolbar',
        				dock : 'bottom',	        				
        				bind:{
 		       		       store:'{branchbusfuelStore}'
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
