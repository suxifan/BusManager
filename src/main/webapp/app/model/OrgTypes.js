Ext.define('helloext.model.OrgTypes', {
    extend: 'Ext.data.Model',
    alias: 'store.usertypemodel',
    fields: [
   	         { name: 'orgTypeId', type: 'string' },
   	         { name: 'orgTypeName', type: 'string' }
         ]
});