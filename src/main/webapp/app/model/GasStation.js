Ext.define('helloext.model.GasStation', {
    extend: 'Ext.data.Model',
    alias: 'store.gasStationmodel',
    fields: [
            { name: 'principalId', type: 'string' },
          	{ name: 'gasStation', type: 'string' },
          	{ name: 'expiredStr', type: 'string' },
          	{ name: 'principal', type: 'string' },
          	{ name: 'fixedTel', type: 'string' },
          	{ name: 'mobilePhone', type: 'string' },
          	{ name: 'enable', type: 'boolean'}
         ]
});