Ext.define('helloext.store.BusLineStore', {
	extend: 'Ext.data.Store',
    alias: 'store.buslinestore',
    requires : ['helloext.model.BusLine'],
	model: 'helloext.model.BusLine',
    autoLoad: true,
    storeId:'buslinestoreId',
    //sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './busLine/getAllBusLines',
		actionMethods : { 
			read : 'POST' 
		},
		
		paramsAsJson : true,
		reader: {
	    	type: 'json',
	    	rootProperty: 'data',
	        totalProperty: 'totalCount'
		}
     }
	
});

