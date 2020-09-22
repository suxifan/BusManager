Ext.define('helloext.store.RoleManagerStore', {
    extend: 'Ext.data.Store',
    alias: 'store.roleManagerStore',
    requires : ['helloext.model.Role'],
	model: 'helloext.model.Role',
   /* fields: [
             	{ name: 'roleId', type: 'string' },
             	{ name: 'roleName', type: 'string' },
             	{ name: 'roleDesc', type: 'string' }
            ],*/
	proxy: {
			type: 'ajax',
			url:'./authorityMgmtCtr/getAllRoles',
			paramsAsJson : true,
		    reader: {
		    	type: 'json',
		    	rootProperty: 'data',
		        totalProperty: 'totalCount'
		    }
	},
	storeId:'roleManagerStoreId'
});
