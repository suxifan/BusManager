Ext.define('helloext.store.GasStationStore', {
    extend: 'Ext.data.Store',
    alias: 'store.gasStationStore',
    requires : ['helloext.model.GasStation'],
	model: 'helloext.model.GasStation',

	proxy: {
			type: 'ajax',
			url:'./gasStationMgmt/getAllGasStationInfo',
			paramsAsJson : true,
		    reader: {
		    	type: 'json',
		    	rootProperty: 'data',
		        totalProperty: 'totalCount'
		    }
	},
	storeId:'gasStationStoreId'
});
