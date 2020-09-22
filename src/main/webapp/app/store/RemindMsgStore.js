Ext.define('helloext.store.RemindMsgStore', {
    extend: 'Ext.data.Store',
    alias: 'store.remindMsgStore',
	fields : [ {name : 'remindContent', type : 'string'},
			   {name: 'remindStatus', type : 'string'},
			   {name: 'createdStr', type: 'string'}
			 ],
	proxy: {
			type: 'ajax',
			url:'./systemRemind/getRemindMsg',
			autoLoad : true,
		    reader: {
		    	type: 'json',
		    	rootProperty: 'data',
		    	totalProperty: 'totalCount'
		    }
	},
	storeId:'remindMsgStoreId'
});
