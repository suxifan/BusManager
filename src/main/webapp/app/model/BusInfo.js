Ext.define('helloext.model.BusInfo', {
    extend: 'Ext.data.Model',
    alias: 'store.businfomodel',
    fields: [
   	         { name: 'orgId', type: 'string' },
   	         { name: 'orgName', type: 'string' },
   	         { name: 'lineId', type: 'string' },
   	         { name: 'lineName', type: 'string' },
   	         { name: 'busNum', type: 'string' },
   	         { name: 'selfNum', type: 'string' },
   	         { name: 'busStatus', type: 'int' },
             { name: 'orgParentId', type: 'string' },
             { name: 'orgParentName', type: 'string' }

    ]
});