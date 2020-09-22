Ext.define('helloext.view.module.reportMgmt.StationNoDataDayModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.stationNoDataDayModel',
    requires:['helloext.store.StationNoDataDayStore'],
    stores:{
        stationNoDataDayStore:{
    		type:'stationNoDataDayStore',
    		autoLoad:true
    	}
    }
	
});
