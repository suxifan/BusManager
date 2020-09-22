Ext.define('helloext.store.ClassFuelStatReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.classfuelstatreportStore',
    requires:[
              'helloext.model.ModifiedFuelReportModel',
              'helloext.model.Org'
           ],
	model: 'helloext.model.ModifiedFuelReportModel',
    
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
	storeId:'ClassFuelStatReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
