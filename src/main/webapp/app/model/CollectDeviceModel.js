Ext.define('helloext.model.CollectDeviceModel', {
	extend: 'Ext.data.Model',
	alias: 'model.collectdevicemodel',
    fields: [
		         { name: 'deviceId', type: 'string' },
				 { name: 'deviceName', type: 'string'},
		         { name: 'deviceNum', type: 'string' },
		         { name: 'status', type:'int'},
         		 { name: 'gasStationId', type: 'string' },
				 { name: 'gasStation', type: 'string'},
		         { name: 'created', type: 'string' },
		         { name:'deviceImei',type:'string'}
	      	]
	});
	


