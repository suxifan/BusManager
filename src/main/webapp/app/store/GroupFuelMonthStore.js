Ext.define('helloext.store.GroupFuelMonthStore', {
    extend: 'Ext.data.Store',
    alias: 'store.groupFuelMonthStore',
    requires:['helloext.model.FuelMonthModel'],
	model: 'helloext.model.FuelMonthModel',
	sorters: {property: 'orgName', direction: 'ASC'},
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
	storeId:'groupFuelMonthStoreId'
});
