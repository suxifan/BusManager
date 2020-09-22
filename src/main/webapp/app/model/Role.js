Ext.define('helloext.model.Role', {
    extend: 'Ext.data.Model',
    alias: 'store.rolemodel',
    fields: [
          	{ name: 'roleId', type: 'string' },
          	{ name: 'roleName', type: 'string' },
          	{ name: 'roleDesc', type: 'string' }
         ]
});