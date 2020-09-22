Ext.define('helloext.store.DetailFuelReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.detailfuelreportStore',
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
	storeId:'DetailFuelReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
