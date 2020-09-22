Ext.define('helloext.store.CollectDeviceStore', {
    extend: 'Ext.data.Store',
    alias: 'store.collectdevicestore',
    requires:[
              'helloext.model.CollectDeviceModel'
           ],
	model: 'helloext.model.CollectDeviceModel',
    sorters: {property: 'deviceId', direction: 'ASC'},
    storeId:'collectDeviceStoreId',
	proxy: { 
		type: 'ajax',
	    url: './fuelSystemManager/getCollectDevice',
//	    actionMethods : { 
//			read : 'POST'
//	    }, 
		paramsAsJson : true,
		reader: {
	    	type: 'json',
	    	rootProperty: 'data',
	        totalProperty: 'totalCount'
	    }
	},
	autoLoad: true,
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
