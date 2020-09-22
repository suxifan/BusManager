Ext.define('helloext.model.Org', {
    extend: 'Ext.data.Model',
    alias: 'store.orgmodel',
    fields: [
   	         { name: 'orgId', type: 'string' },
   	         { name: 'orgName', type: 'string' },
   	         { name: 'orgDescription', type: 'string' },
   	         { name: 'orgParentId', type: 'string'},
   	         { name: 'orgTypeId', type: 'string'},
   	         { name: 'orgTypeName', type: 'string'},
   	         {name:'isEnabled', type:'boolean'}
         ]
});