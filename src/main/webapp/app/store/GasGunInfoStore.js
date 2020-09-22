Ext.define('helloext.store.GasGunInfoStore', {
    extend: 'Ext.data.Store',
    alias: 'store.gasGunInfoStore',
    requires : ['helloext.model.GasGunInfo'],
	model: 'helloext.model.GasGunInfo',

	proxy: {
			type: 'ajax',
			url:'./gasStationMgmt/getAllGasGunInfo',
			paramsAsJson : true,
		    reader: {
		    	type: 'json',
		    	rootProperty: 'data',
		        totalProperty: 'totalCount'
		    }
	},
	storeId:'gasGunInfoStoreId'
});
