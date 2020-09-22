Ext.define('helloext.view.module.authMgmt.BusLineOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.buslineopermodel',
    requires:[
       'helloext.store.OrgInfoStore',
    ],
	data:{
		 title:'线路管理',
	     mainApp:null
	},
	stores: {
		 orgStore:{
			type:'orginfostore',
			filters: [{
	             property: 'isEnabled',                
	             value: true
	         }],
		}
    },
    
    
    formulas: {	    

    	lineId:function(get){
	    		return get('selectedBusLine.lineId');
	    	},
	    	lineName:function(get){
	    		return get('selectedBusLine.lineName');
	    	},
	    	lineAlias:function(get){
	    		return get('selectedBusLine.lineAlias');
	    	},
	    	lineStatus:function(get){
	    		return get('selectedBusLine.lineStatus');
	    	},
	    	lineOrgId:function(get){
	    		return get('selectedBusLine.lineOrgId');
	    	},
	
    }
});
