Ext.define('helloext.store.BusFuelReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.busfuelreportStore',
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
	storeId:'BusFuelReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
