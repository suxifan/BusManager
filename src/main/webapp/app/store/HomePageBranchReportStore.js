Ext.define('helloext.store.HomePageBranchReportStore', {
    extend: 'Ext.data.Store',
    alias: 'store.homepagebranchreportStore',
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
	storeId:'HomePageBranchReportStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
