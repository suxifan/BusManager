Ext.define('helloext.store.FuelParamSettingStore', {
    extend: 'Ext.data.Store',
    alias: 'store.fuelparamsettingstore',
    requires:[
              'helloext.model.FuelParamSettingModel'
           ],
	model: 'helloext.model.FuelParamSettingModel',
    sorters: {property: 'settingId', direction: 'ASC'},
    storeId:'fuelParamSettingStoreId',
	proxy: { 
		type: 'ajax',
	    url: './fuelSystemManager/getFuelParamSetting',
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
	storeId:'FuelParamSettingStoreId',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
			}
	} 

	
});
