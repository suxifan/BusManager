Ext.define('helloext.store.NewBusFuelReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.newbusfuelreportStore',
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
	storeId:'NewBusFuelReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
