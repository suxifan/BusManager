Ext.define('helloext.view.module.gasStationMgmt.ModifyGasStationInfoModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.modifyGasStationInfoModel',
    
    formulas: {

    	principalId:function(get){
    		return get('selectedData.principalId');
    	},
    	gasStationId:function(get){
    		return get('selectedData.gasStationId');
    	},
    	gasStation:function(get){
    		return get('selectedData.gasStation');
    	},
    	email:function(get){
    		return get('selectedData.email');
    	},
    	enable:function(get){
    		return get('selectedData.enable');
    	},
    	gasStationAddr:function(get){
    		return get('selectedData.gasStationAddr');
    	},
        excelParam:function(get){
          return get('selectedData.excelParam');
        },
    	latiLong:function(get){
    		return get('selectedData.latiLong');
    	},
    	principal:function(get){
    		return get('selectedData.principal');
    	},
    	expiredStr:function(get){
    		return get('selectedData.expiredStr');
    	},
    	mobilePhone:function(get){
    		return get('selectedData.mobilePhone');
    	},
    	fixedTel:function(get){
    		return get('selectedData.fixedTel');
    	},
    	dayTimeStart:function(get){
    		var value = get('selectedData.dayTime');
    		return value.split('-')[0];
    	},
    	dayTimeEnd:function(get){
    		var value = get('selectedData.dayTime');
    		return value.split('-')[1];
    	},
    	nightTimeStart:function(get){
    		var value = get('selectedData.nightTime');
    		return value.split('-')[0];
    	},
    	nightTimeEnd:function(get){
    		var value = get('selectedData.nightTime');
    		return value.split('-')[1];
    	}
    }
});
