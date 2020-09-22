Ext.define('helloext.store.BusLineWithPageStore', {
	extend: 'Ext.data.Store',
    alias: 'store.buslinewithpagestore',
    requires : ['helloext.model.BusLine'],
	model: 'helloext.model.BusLine',
    autoLoad: true,
    storeId:'buslinewithpagestoreId',
    //sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './busLine/getAllBusLinesWithPage',
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

