Ext.define('helloext.model.GasGunInfo', {
    extend: 'Ext.data.Model',
    alias: 'store.gasGunInfomodel',
    fields: [
            { name: 'gasGunId', type: 'string' },
          	{ name: 'gasStation', type: 'string' },
          	{ name: 'gasGunNum', type: 'string' }
         ]
});