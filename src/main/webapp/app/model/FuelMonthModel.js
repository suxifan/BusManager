Ext.define('helloext.model.FuelMonthModel', {
	extend: 'Ext.data.Model',
	alias: 'store.fuelMonthModel',
    fields: [
            	{ name: 'lineName', type: 'string' },
            	{ name: 'orgName', type: 'string' },
            	{ name: 'fuelName', type: 'string', defaultValue: '天然气' },
            	{ name: 'unit',  type: 'string', defaultValue: 'm3' },
            	{ name: 'sumvolume' },
            	{ name: 'price' },
            	{ name: 'amount' },
            	{ name: 'remark', type: 'string' }
           ]
	});
	
