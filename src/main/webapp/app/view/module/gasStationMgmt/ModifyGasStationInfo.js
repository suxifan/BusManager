Ext.define('helloext.view.module.gasStationMgmt.ModifyGasStationInfo', {
    extend: 'Ext.form.Panel',
    alias: 'widget.modifyGasStationInfo',
    title:'修改加气站信息',
    bodyPadding: 10,
    id:'modifyGasStationInfoId',
    requires:['helloext.view.module.gasStationMgmt.ModifyGasStationInfoModel',
              'helloext.view.module.gasStationMgmt.ModifyGasStationInfoController'],
    controller:'modifyGasStationInfoController',
    viewModel: {
        type: 'modifyGasStationInfoModel'
    },
    items: [{
    	xtype: 'combobox',
    	reference: 'gasStationCombox',
    	name:'gasStationId',
    	publishes: 'rawValue',
    	fieldLabel: '加气站名称',
    	displayField: 'orgName',
    	emptyText: '请选择加气站名称',
		editable: false,
		valueField:'orgId',
		width:600,
		bind:{value:'{gasStationId}'},
		store:{
			type:'orginfostore',
			filters: [{
	             property: 'orgTypeId',                
	             value: '3'
	         }]
		},
    	minChars: 0,
    	queryMode: 'remote',
    	margin: '0 0 10 0',
    	allowBlank:false
    }, {
        xtype: 'hiddenfield',
        name: 'gasStation',
        bind:{
        	value: '{gasStationCombox.rawValue}'
        }
    }, {
        xtype: 'hiddenfield',
        name: 'principalId',
        bind:{
        	value: '{principalId}'
        }
    }, {
        xtype: 'textfield',
        name: 'gasStationAddr',
        width:600,
        emptyText: '请输入加气站地址',
        margin: '0 0 10 0',
        allowBlank: false,
        bind:{value:'{gasStationAddr}'},
        fieldLabel: '加气站地址'
    }, {
        xtype: 'container',
        width:600,
        layout: {
            type: 'hbox'
        },
        items: [
		{
		    xtype: 'timefield',
		    name: 'dayTimeStart',
		    reference: 'dayTimeStart',
		    publishes: 'rawValue',
		    fieldLabel: '白班',
		    minValue: '00:00 AM',
		    maxValue: '11:45 PM',
		    bind:{value:'{dayTimeStart}'},
		    increment: 30,
		    allowBlank: false,
		    format: 'H:i'
		},
		{
            xtype: 'label',
	        text: '~',
	        margin: '5 10 10 10'
		},
		{
		    xtype: 'timefield',
		    name: 'dayTimeEnd',
		    reference: 'dayTimeEnd',
		    publishes: 'rawValue',
		    minValue: '00:00 AM',
		    maxValue: '11:45 PM',
		    bind:{value:'{dayTimeEnd}'},
		    increment: 30,
		    allowBlank: false,
		    format: 'H:i'
		}]
   }, {
        xtype: 'hiddenfield',
        name: 'dayTime',
        bind:{
       	value: '{dayTimeStart.rawValue}-{dayTimeEnd.rawValue}'
       }
   }, {
      xtype: 'container',
      width:600,
      layout: {
          type: 'hbox'
      },
      items: [
		{
		    xtype: 'timefield',
		    name: 'nightTimeStart',
		    reference: 'nightTimeStart',
		    publishes: 'rawValue',
		    fieldLabel: '晚班',
		    bind:{value:'{nightTimeStart}'},
		    minValue: '00:00 AM',
		    maxValue: '11:45 PM',
		    increment: 30,
		    allowBlank: false,
		    format: 'H:i'
		},
		{
          xtype: 'label',
	      text: '~',
	      margin: '5 10 10 10'
		},
		{
		  xtype: 'timefield',
		  name: 'nightTimeEnd',
		  reference: 'nightTimeEnd',
		  publishes: 'rawValue',
		  minValue: '00:00 AM',
		  maxValue: '11:45 PM',
		  bind:{value:'{nightTimeEnd}'},
		  increment: 30,
		  allowBlank: false,
		  format: 'H:i'
		}]
  	 }, {
         xtype: 'hiddenfield',
         name: 'nightTime',
         bind:{
        	value: '{nightTimeStart.rawValue}-{nightTimeEnd.rawValue}'
        }
    }, {
	    xtype: 'datefield',
	    reference: 'gasDate',
	    fieldLabel: '签约到期日期',
	    width:600,
	    name: 'expiredStr',
	    margin: '0 0 10 0',
	    format: 'Y-m-d',
	    bind:{value:'{expiredStr}'},
	    allowBlank: false
	 }, 

	 
	{
        xtype: 'textfield',
        name: 'latiLong',
        id:'latiLong',
        width:600,
        margin: '0 0 10 0',
//        emptyText: '请输入加气站经纬度',
//        allowBlank: false,
        bind:{value:'{latiLong}'},
        fieldLabel: '加气站经纬度'
//        ,
//        listeners: {
//        	 specialkey: 'openMap' ,
//        	  render : function(field) {
//                    Ext.QuickTips.init();
//                    Ext.QuickTips.register({
//                        target : field.el,
//                        text : '按回车键从地图拾取经纬度'
//                    })
//                }
//
//        }
	
  	},
    {
        xtype: 'textfield',
        name: 'principal',
        width:600,
        margin: '0 0 10 0',
        emptyText: '请输入加气站负责人',
        bind:{value:'{principal}'},
        allowBlank: false,
        fieldLabel: '加气站负责人'
    }, {
        xtype: 'textfield',
        name: 'fixedTel',
        width:600,
        margin: '0 0 10 0',
        bind:{value:'{fixedTel}'},
//        emptyText: '请输入加气站负责人固定电话',
//        allowBlank: false,
        fieldLabel: '固定电话'
    }, {
        xtype: 'textfield',
        name: 'mobilePhone',
        width:600,
        margin: '0 0 10 0',
//        emptyText: '请输入加气站负责人移动电话',
//        allowBlank: false,
        bind:{value:'{mobilePhone}'},
        fieldLabel: '移动电话'
    }, {
        xtype: 'textfield',
        name: 'email',
        vtype:'email',
        width:600,
        margin: '0 0 10 0',
//        emptyText: '请输入加气站负责人电子邮件',
//        allowBlank: false,
        bind:{value:'{email}'},
        fieldLabel: '电子邮件'
    }, {
            xtype: 'textfield',
            name: 'excelParam',
            width:600,
            margin: '0 0 10 0',
            emptyText: '加气站模板参数,参数为字段所在列,顺序为:读取行数,车牌,加气量,加气时间.如:5,2,8',
//        allowBlank: false,
            bind:{value:'{excelParam}'},
            fieldLabel: '模板参数'
        },



        {
    	xtype: 'combobox',
    	fieldLabel: '是否启用',
    	name:'enable',
    	reference: 'isEnableCombox',
    	publishes: 'value',
    	width:600,
    	displayField: 'display',
    	emptyText: '请选择',
    	valueField:'enable',
		editable: false,
		bind:{value:'{enable}'},
    	store: {
    		 fields: ['enable', 'display'],
			 data: [[true, '启用'],[false, '不启用']]
    	},
    	minChars: 0,
    	queryMode: 'local',
    	allowBlank:false
    }],
    
    buttonAlign:'center',
    buttons :[{
    	xtype:'button',
    	text:'保存',
    	iconCls : 'icon-save',
    	handler:'modifyGasStationInfo'
    },{
    	xtype:'button',
    	text:'关闭',
    	iconCls : 'icon-cancel',
    	handler:'closeWindow'
    }]
    
});
