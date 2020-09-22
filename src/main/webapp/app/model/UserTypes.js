Ext.define('helloext.model.UserTypes', {
    extend: 'Ext.data.Model',
    alias: 'store.usertypemodel',
    fields: [
   	         { name: 'userTypeId', type: 'string' },
   	         { name: 'userTypeName', type: 'string' }
         ]
});