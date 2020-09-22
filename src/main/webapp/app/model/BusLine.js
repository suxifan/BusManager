Ext.define('helloext.model.BusLine', {
    extend: 'Ext.data.Model',
    alias: 'store.buslinemodel',
    fields: [
   	         { name: 'lineId', type: 'string' },
   	         { name: 'lineName', type: 'string' },
   	         { name: 'lineAlias', type: 'string' },
   	         { name: 'lineOrgId', type: 'string'},
   	         { name: 'lineOrgName', type: 'string'},
   	         { name: 'lineStatus', type: 'int'},
   	         { name: 'created', type: 'date'},
   	      { name: 'createdStr', type: 'string'},
   	      
         ]
});