Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSettingOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.branchfuelparamsettingoper',
    requires:[
       
       
       'helloext.store.FuelParamSettingStore'
    ],
	data:{
		 title:'新增',
	     mainApp:null
	},

    
    
    formulas:{
	    
	    settingId:function(get){
    		return get('selectedParamSetting.settingId');
    	},
    	paraKey:function(get){
    		return get('selectedParamSetting.paraKey');
    	},
    	paraKeyCn:function(get){
    		console.log(get('selectedParamSetting'));
    		return get('paraKeyCn');
    	},
    	paraValue:function(get){
    		return get('selectedParamSetting.paraValue');
    	}
    	
    	
    }
});
