Ext.define('helloext.model.FuelReportModel', {
	extend: 'Ext.data.Model',
	alias: 'store.fuelreportmodel',
    fields: [
	         { name: 'gasDetailId', type: 'string' },
	         { name: 'gasId', type: 'string' },
	         { name: 'orgId', type: 'string' },
	         { name: 'orgName', type: 'string' },
	         { name: 'lineOrgName', type: 'string'},
			 { name: 'lineOrgId', type: 'string' },
			 { name: 'busNum', type: 'string'},
			 { name: 'selfNum', type: 'string' },
			 { name: 'operateType', type: 'int' },
			 { name: 'gasVolume', type: 'float'},
			 { name: 'gasUserId', type: 'string' },
			 { name: 'gasUserName', type: 'string' },
			 { name: 'driverId', type: 'string'},
			 { name: 'driver', type: 'string'},
			 { name: 'gasStationId', type: 'string'},
			 { name: 'gasStation', type: 'string'},
			 { name: 'gasTime', type: 'date'},
			 { name: 'gasGunNum', type: 'string'},
			 { name: 'lineName', type: 'string'},
			 { name: 'lineId', type: 'string'},
			 { name: 'gasTimeStr', type: 'string'},
			 { name: 'createdStr', type: 'string'},
			 { name: 'gasDateStr', type: 'string'},
			 { name: 'remark', type: 'string'},
             { name: 'gasStatus',type:'string'}
         ]
	});
	
