Ext.define('helloext.view.module.authMgmt.BusInfoOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.businfoopermodel',
    requires:[
       'helloext.store.OrgLineStore',
       'helloext.store.BusInfoStore'
    ],
	data:{
		 title:'车辆管理',
	     mainApp:null
	},
	stores: {
		 orgStore:{
			type:'orglinestore',
			filters: [{
	             property: 'isEnabled',                
	             value: true
	         }]
		},
        busLineStore:{
            type:'buslinestore'
        }
    },
    
    
    formulas: {	    

    	    lineId:function(get){
	    		return get('selectedBusInfo.lineId');
	    	},
	    	lineName:function(get){
	    		return get('selectedBusInfo.lineName');
	    	},
            busNum:function(get){
                return get('selectedBusInfo.busNum')
            },
            selfNum:function(get){
                return get('selectedBusInfo.selfNum')
            },
            busInfoId:function(get){
                return get('selectedBusInfo.busInfoId')
            },
            orgId:function(get){
                return get('selectedBusInfo.orgId')
            },
            orgName:function(get){
                return get('selectedBusInfo.orgName')
            }

    }
});
