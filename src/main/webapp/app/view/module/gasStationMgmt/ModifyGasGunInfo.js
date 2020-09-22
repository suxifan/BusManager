Ext.define('helloext.view.module.gasStationMgmt.ModifyGasGunInfo', {
    extend: 'Ext.form.Panel',
    alias: 'widget.modifyGasGunInfo',
    title:'修改加气站信息',
    bodyPadding: 10,
    id:'modifyGasGunInfoId',
    requires:['helloext.view.module.gasStationMgmt.ModifyGasGunInfoController',
              'helloext.view.module.gasStationMgmt.ModifyGasGunInfoModel'],
    controller:'modifyGasGunInfoController',
    viewModel: {
        type: 'modifyGasGunInfoModel'
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
	         },{
	             property: 'isEnabled',                
	             value: true
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
        	value: '{gasStationCombox.rawValue}',
        }
    }, {
        xtype: 'hiddenfield',
        name: 'gasGunId',
        bind:{
        	value: '{gasGunId}',
        }
	}, {
        xtype: 'textfield',
        name: 'gasGunNum',
        width:600,
        emptyText: '请输入气枪编号',
        margin: '0 0 10 0',
        allowBlank: false,
        bind:{value:'{gasGunNum}'},
        fieldLabel: '气枪编号'
    }],
    buttonAlign:'center',
    buttons :[{
    	xtype:'button',
    	text:'保存',
    	iconCls : 'icon-save',
    	handler:'modifyGasGunInfo'
    },{
    	xtype:'button',
    	text:'关闭',
    	iconCls : 'icon-cancel',
    	handler:'closeWindow'
    }]
    
});
