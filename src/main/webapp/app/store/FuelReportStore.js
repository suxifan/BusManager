Ext.define('helloext.store.FuelReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.fuelreportStore',
    requires:[
              'helloext.model.FuelReportModel',
              'helloext.model.Org'
           ],
	model: 'helloext.model.FuelReportModel',
    
	proxy: { 
		type: 'ajax',
	    url: '',
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
	autoLoad: true,
	storeId:'FuelReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
