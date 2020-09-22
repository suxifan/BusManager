Ext.define('helloext.view.main.region.Header', {
	extend : 'Ext.Container',
	xtype : 'appHeader',
	id : 'app-header',
	height : 52,
	layout : {
		type : 'hbox',
		align : 'middle'
	},
	requires : [ 'helloext.ux.ButtonTransparent'],
	items : [ {
		xtype : 'component',
		id : 'app-header-title',
		html : '<h2><font color=#ffffff>包头公交燃料管理系统</font></h2>',
		flex : 1
	}, {
		xtype : 'buttontransparent',
		bind : {
			text : '欢迎您：' + '{getUserName}'
		}
	}, {
		xtype : 'buttontransparent',
		float : 'left',
		iconCls : 'icon-bell',
		id : 'notReadMsgBellId',
		handler: 'queryRemindMsg'
	},{
		xtype : 'buttontransparent',
		float : 'left',
		iconCls : 'icon-help',
		text : "修改密码",
		handler:'changePsw'
	},
	{
		xtype : 'buttontransparent',
		float : 'left',
		glyph : 0xf011,
		text : "退出",
		handler:'logout'
	}
	]

});
