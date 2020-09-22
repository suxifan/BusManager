Ext.define('helloext.store.StationNoDataDayStore', {
    extend: 'Ext.data.Store',
    alias: 'store.stationNoDataDayStore',
	fields : [
                {name : 'gasStation', type : 'string'},
                {name : 'gasTimeStr', type : 'string'}
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
	storeId:'stationNoDataDayStoreId'
});
