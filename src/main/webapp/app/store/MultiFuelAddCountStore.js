Ext.define('helloext.store.MultiFuelAddCountStore', {
    extend: 'Ext.data.Store',
    alias: 'store.multiFuelAddCountStore',
    requires:['helloext.model.FuelReportModel'],
	model: 'helloext.model.FuelReportModel',
	proxy: {
		type: 'ajax',
		url:'',
		paramsAsJson : true,
	    reader: {
	    	type: 'json',
	    	rootProperty: 'data',
	        totalProperty: 'totalCount'
	    }
    },
	storeId:'multiFuelAddCountStoreId'
});
