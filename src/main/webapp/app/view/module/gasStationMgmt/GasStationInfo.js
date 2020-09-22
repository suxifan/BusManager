Ext.define('helloext.view.module.gasStationMgmt.GasStationInfo', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gasstationInfo',
	id : 'gasStationInfoId',
	title : '负责人管理',
	layout : 'fit',
    requires: [
         	  'helloext.view.module.gasStationMgmt.GasStationController',
         	  'helloext.view.module.gasStationMgmt.GasStationModel'
            ],
    controller:'gasStationContr',
    viewModel:{
         type:'gasStationModel'	
    },
	tbar : [ {
		xtype : 'toolbar',
		items : [ {
			xtype : 'button',
			text : "新增",
			float : 'left',
			iconCls : 'icon-add',
			id : 'gas_station_module_add',
			bind : {
				hidden : '{isHiddenAdd}'
			},
			handler : 'addGasStationInfo'
		}, {
			xtype : 'button',
			text : "修改",
			iconCls : 'icon-edit',
			id : 'gas_station_module_update',
			float : 'left',
			bind : {
				hidden : '{isHiddenUpdate}'
			},
			handler : 'modifyGasStationInfo'
		}, {
			xtype : 'button',
			text : "删除",
			id : 'gas_station_module_delete',
			float : 'left',
			iconCls : 'icon-delete',
			bind : {
				hidden : '{isHiddenDelete}'
			},
			handler : 'deleteGasStationInfo'
		}, {
			xtype : 'tbspacer',
			width : 20
		}, '-', {
			xtype : 'textfield',
			fieldLabel : "加气站名称",
			labelWidth : 80,
			width : 300,
			emptyText : '请输入关键字进行查询'
		}, {
			xtype : 'button',
			text : "查询",
			iconCls : 'icon-search',
			handler : 'queryGasStationInfo'
		} ]
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
		dataIndex : 'principalId',
		hidden:true
	},{
		text : '名称',
		width : '15%',
		align : 'center',
		dataIndex : 'gasStation'
	}, {
		text : '到期时间',
		width : '15%',
		align : 'center',
		dataIndex : 'expiredStr'
	}, {
		text : '加气站负责人',
		width : '15%',
		align : 'center',
		dataIndex : 'principal'
	}, {
		text : '邮箱',
		width : '20%',
        flex:1,
		align : 'center',
		dataIndex : 'email'
	}, {
		text : '模板参数',
		width : '10%',
		align : 'center',
		dataIndex : 'excelParam'
	}, {
		text : '是否启用',
		width : '15%',
		align : 'center',
		dataIndex : 'enable',
		renderer: function(value) {
			if(value) {
				return '<span style="color:green;font-weight:bold;">' + '启用' + '</span>';
			} else {
			    return '<span style="color:red;font-weight:bold;">' + '未启用' + '</span>';
			}
		}
	} ],
	bind:{
	 store:'{gasStationInfoStore}'
	},
	selModel : 'checkboxmodel',
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		dock : 'bottom',
		bind:{
			 store:'{gasStationInfoStore}'
		},
		pageSize : 25,
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据'
	} ]
});