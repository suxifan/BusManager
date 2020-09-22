Ext.define('helloext.store.FuelMonthStore', {
    extend: 'Ext.data.Store',
    alias: 'store.fuelMonthStore',
    requires:['helloext.model.FuelMonthModel'],
	model: 'helloext.model.FuelMonthModel',
    sorters: {property: 'lineName', direction: 'ASC'},
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
	storeId:'fuelMonthStoreId'
});
