Ext.define('helloext.view.module.gasStationMgmt.GasGunInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gasGunInfo',
	id : 'gasGunInfoId',
	title : '气枪管理',
	layout : 'fit',
    requires: [
         	  'helloext.view.module.gasStationMgmt.GasGunInfoController',
         	  'helloext.view.module.gasStationMgmt.GasGunInfoModel'
            ],
    controller:'gasGunInfoContr',
    viewModel:{
         type:'gasGunInfoModel'	
    },
	tbar : [ {
		xtype : 'toolbar',
		items : [ {
			xtype : 'button',
			text : "新增",
			float : 'left',
			iconCls : 'icon-add',
			id : 'gas_gun_mgmt_module_add',
			bind : {
				hidden : '{isHiddenAdd}'
			},
			handler : 'addGasGunInfo'
		}, {
			xtype : 'button',
			text : "修改",
			iconCls : 'icon-edit',
			id : 'gas_gun_mgmt_module_update',
			float : 'left',
			bind : {
				hidden : '{isHiddenUpdate}'
			},
			handler : 'modifyGasGunInfo'
		}, {
			xtype : 'button',
			text : "删除",
			id : 'gas_gun_mgmt_module_delete',
			float : 'left',
			iconCls : 'icon-delete',
			bind : {
				hidden : '{isHiddenDelete}'
			},
			handler : 'deleteGasGunInfo'
		}]
	} ],
	collapsible : true,
	border : true,
	selModel : 'checkboxmodel',
	stripeRows : true,
	columns : [ {
		xtype : 'rownumberer',
		text : "序号",
		width : '5%',
		align : 'center'
	}, {
		dataIndex : 'gasGunId',
		hidden:true
	},{
		text : '加气站名称',
		width : '45%',
		align : 'center',
		dataIndex : 'gasStation'
	}, {
		text : '气枪编号',
		width : '45%',
		align : 'center',
		dataIndex : 'gasGunNum'
	}],
	bind:{
	 store:'{gasGunInfoStore}'
	},
	selModel : 'checkboxmodel',
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		dock : 'bottom',
		bind:{
			 store:'{gasGunInfoStore}'
		},
		pageSize : 25,
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据'
	} ]
});