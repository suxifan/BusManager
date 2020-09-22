Ext.define('helloext.view.module.authMgmt.BusInfoOper', {
    extend: 'Ext.panel.Panel',
    //extend:'Ext.window.Window',
	//extend: 'Ext.container.Container',
    alias: 'widget.businfooper',
    title:'新增车辆',
    layout:'border',
    id:'BusInfoOperId',
    requires: [
        	  'helloext.view.module.authMgmt.BusInfoOperController',
        	  'helloext.view.module.authMgmt.BusInfoOperModel',
        	  'Ext.form.Panel'
           ],
    viewModel: {
        type: 'businfoopermodel'
    },
           controller:'businfoopercontroller',
           bind:{
        	   title:'{title}'+'车辆'
           },
           items:[{
	           	xtype:'form',
	           	region:'center',
				border: false,
				fieldDefaults: {
	                labelAlign: 'center',
	            },
	            reference:'businfoform',
	               buttonAlign:'center',
	               buttons :[{
	               	xtype:'button',
	               	text:'保存',
	               	iconCls : 'icon-save',
	               	handler:'saveBusInfo'
	               },{
	               	xtype:'button',
	               	text:'关闭',
	               	iconCls : 'icon-cancel',
	               	handler:'closeWindow'
	               }],
           items:[
  					
	  				 
	  					       
//
			  				{
			   						xtype:'textfield',
			   						fieldLabel:'车牌号',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'busNum',
			   						bind:{
			   		        			value:'{busNum}'
			   		        		},
			   		        		allowBlank: false,
			   		        		regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
			   		        		maxLength:32,
		                			maxLengthText:"车牌号过长",
			   		        		emptyText: '车牌号',
			   						labelAlign:'right'
			   				},
                           {
                                   xtype:'textfield',
                                   fieldLabel:'自编号',
                                   padding:5,
                                   //anchor:'100%',
                                   labelWidth:80,
                                   name:'selfNum',
                                   bind:{
                                       value:'{selfNum}'
                                   },
                                   allowBlank: false,
                                   maxLength:32,
                                   maxLengthText:"自编号号过长",
                                   emptyText: '自编号',
                                   labelAlign:'right'
                           },
			   				   {


				    	        	xtype: 'combobox',
				    	        	fieldLabel: '所属路队',
				    	        	labelAlign:'right',
				    	        	padding:5,
			   		        		labelWidth:80,
				    	        	displayField: 'orgName',
				    	        	emptyText: '请选择',
				    	        	editable: true,
				         			valueField:'orgId',
				         			bind:{
					    		       store:'{orgStore}',
					    		       value:'{orgId}'

					    		     },
				    	        	minChars: 0,
				    	        	queryMode: 'local',
				    	        	name:'orgId',


				    	        	 listeners:{
				    	                 select : function(combo, record,index)
				    	                 {
                                             var pid = this.getValue();
                                             lineStore = this.up('form').down('combobox[reference="refBusInfoLine"]').store;
                                             lineStore.clearFilter();
                                             lineStore.filterBy(function(record) {

                                                 return record.get('lineOrgId') == pid || record.get('lineId')=='';
                                             });
//
				    	                	// this.up('form').down('textfield[reference="refInfoOrgName"]').clearValue();
				    	                	 var zid = this.getRawValue();
				    	                	 this.up('form').down('textfield[reference="refBusOrgName"]').setValue(zid);
				    	                 }
				    	            }

				    	      },
                               {


                                   xtype: 'combobox',
                                   fieldLabel: '线路',
                                   labelAlign:'right',
                                   reference:'refBusInfoLine',
                                   padding:5,
                                   labelWidth:80,
                                   displayField: 'lineName',
                                   emptyText: '请选择',
                                   editable: true,
                                   valueField:'lineId',
                                   bind:{
                                       store:'{busLineStore}',
                                       value:'{lineId}'

                                   },
                                   minChars: 0,
                                   queryMode: 'local',
                                   name:'lineId',


                                   listeners:{

                                       select : function(combo, record,index)
                                       {

                                           // this.up('form').down('textfield[reference="refInfoOrgName"]').clearValue();
                                           var pid = this.getRawValue();
                                           this.up('form').down('textfield[reference="refBusLineName"]').setValue(pid);
                                       }
                                   }

                               },
				    	      {
			   						xtype:'textfield',
			   						reference: 'refBusOrgName',
			   						name:'orgName',
                                    bind:{
                                          value:'{orgName}'
                                    },
			   						padding:5,
			   						hidden:true
			   				},
                               {
                                   xtype:'textfield',
                                   reference: 'refBusLineName',
                                   name:'lineName',
                                   bind:{
                                       value:'{lineName}'
                                   },
                                   padding:5,
                                   hidden:true
                               },
				    	      {
			   						xtype:'textfield',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:60,
			   						name:'busInfoId',
			   						bind:{
			   		        			value:'{busInfoId}'
			   		        		},
			   						labelAlign:'right',
			   						hidden:true
		   					   }

   				]

           }]

});