Ext.define('helloext.store.ModifiedFuelReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.modifiedfuelreportStore',
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
	storeId:'ModifiedFuelReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
