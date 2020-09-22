Ext.define('helloext.store.BusInfoStore', {
	extend: 'Ext.data.Store',
    alias: 'store.businfostore',
    requires : ['helloext.model.BusInfo'],
	model: 'helloext.model.BusInfo',
    autoLoad: true,
    storeId:'businfostoreId',
    //sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './fuelReport/getBusInfo',
		actionMethods : { 
			read : 'POST' 
		},
		paramsAsJson : true,
	    reader: {
	    	type: 'json',
	    	rootProperty: 'data',
	        totalProperty: 'totalCount'
	    }
     },
	
});

