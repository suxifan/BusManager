Ext.define('helloext.model.FuelParamSettingModel', {
	extend: 'Ext.data.Model',
	alias: 'model.fuelparamsettingmodel',
    fields: [
	         { name: 'settingId', type: 'string' },
			 { name: 'paraKey', type: 'string'},
	         { name: 'paraValue', type: 'string' },
	         { name: 'paraKeyCn', type:'string'}
         ]
	});
	
