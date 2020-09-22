Ext.define('helloext.model.EmailInfoModel', {
	extend: 'Ext.data.Model',
	alias: 'model.emailinfomodel',
    fields: [
		         { name: 'emailId', type: 'string' },
				 { name: 'sendAddress', type: 'string'},
		         { name: 'downloadTimeStr', type: 'string' },
                 { name: 'sendTimeStr', type: 'string' },
		         { name: 'downloadType', type:'int'},
                 { name: 'processingState', type:'int'},
                 { name: 'customItem1',type:'string'}
	      	]
	});

