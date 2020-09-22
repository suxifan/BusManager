Ext.define('helloext.store.ModifiedFuelCheckReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.modifiedfuelcheckreportStore',
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
	storeId:'ModifiedFuelCheckReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
