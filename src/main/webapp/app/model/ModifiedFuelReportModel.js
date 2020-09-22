Ext.define('helloext.model.ModifiedFuelReportModel', {
	extend: 'Ext.data.Model',
	alias: 'store.modifiedfuelreportmodel',
    fields: [
             
             
             { name: 'gasModifiedId', type: 'string' },
             { name: 'gasStationId', type: 'string' },
             { name: 'gasStation', type: 'string' },
             { name: 'gasUserName', type: 'string' },
             { name: 'gasUserId', type: 'string' },
             { name: 'preGasVolume', type: 'float' },
             { name: 'postGasVolume', type: 'float' },
             { name: 'gasTime', type: 'date' },
             { name: 'modified', type: 'date' },
             { name: 'created', type: 'date' },
             { name: 'gasEquipmentUrl', type: 'string' },
             
             { name: 'gasTimeStr', type: 'string' },
             { name: 'modifiedStr', type: 'string' },
             { name: 'createdStr', type: 'string' },
             { name: 'gasStatus', type: 'int' },
             { name: 'auditOptinion', type: 'string' },
         ]
	});
	
