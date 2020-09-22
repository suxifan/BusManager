Ext.define('helloext.model.UserAuthManagerModeSub', {
	//extend: 'helloext.view.module.Base',
	extend: 'Ext.data.Model',
	alias: 'store.userauthmanagermodesub',
	fields: [
	     	{ name : 'dobRoleId',type : 'string' }, 
	     	{ name : 'dobRoleName',type : 'string' }, 
	     	{ name : 'dobRoleDesc',type : 'string' }
	     	]
	});
	
