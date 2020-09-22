Ext.define('helloext.view.module.gasStationMgmt.AddGasGunInfo', {
    extend: 'Ext.form.Panel',
    alias: 'widget.addGasGunInfo',
    title:'新增加气站信息',
    bodyPadding: 10,
    id:'addGasGunInfoId',
    requires:['helloext.view.module.gasStationMgmt.AddGasGunInfoController',
              'helloext.store.OrgInfoStore'],
    controller:'addGasGunInfoController',
    
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
    },
    {
        xtype: 'textfield',
        name: 'gasGunNum',
        width:600,
        emptyText: '请输入气枪编号',
        margin: '0 0 10 0',
        allowBlank: false,
        fieldLabel: '气枪编号'
    }],
    buttonAlign:'center',
    buttons :[{
    	xtype:'button',
    	text:'保存',
    	iconCls : 'icon-save',
    	handler:'addGasGunInfo'
    },{
    	xtype:'button',
    	text:'关闭',
    	iconCls : 'icon-cancel',
    	handler:'closeWindow'
    }]
    
});
