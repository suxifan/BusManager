Ext.define('helloext.store.EmailProcessingLogStore', {
    extend: 'Ext.data.Store',
    alias: 'store.emailProcessingLogStore',
	fields : [
                {name : 'content', type : 'string'}
			 ],
	proxy: {
			type: 'ajax',
            url:'',
			autoLoad : true,
		    reader: {
		    	type: 'json',
		    	rootProperty: 'data',
		    	totalProperty: 'totalCount'
		    }
	},
	storeId:'emailProcessingLogStoreId'
});
