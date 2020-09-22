Ext.define('helloext.view.module.gasStationMgmt.ModifyGasGunInfoModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.modifyGasGunInfoModel',
    
    formulas: {

    	gasGunId:function(get){
    		return get('selectedData.gasGunId');
    	},
    	gasStationId:function(get){
    		return get('selectedData.gasStationId');
    	},
    	gasStation:function(get){
    		return get('selectedData.gasStation');
    	},
    	gasGunNum:function(get){
    		return get('selectedData.gasGunNum');
    	}
    }
});
