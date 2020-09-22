Ext.define('helloext.view.module.authMgmt.BusInfoModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.businfo',
    requires:[
       'helloext.store.BusInfoStore'
    ],
    stores:{
    	
    	busInfoStore:{
    		type:'businfostore'
    	}
    },
	data:{
		 title:'新增',
	     mainApp:null
	}

});
