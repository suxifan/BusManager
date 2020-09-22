Ext.define('helloext.view.module.systemMgmt.CollectDeviceOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.collectdeviceopermodel',
    requires:[
       'helloext.store.OrgInfoStore',
    ],
	data:{
		 title:'设备管理',
	     mainApp:null
	},
	stores: {
		 stationStore:{
			type:'orginfostore',
			filters: [{
	             property: 'orgTypeId',                
	             value: '3'
	         },{
	             property: 'isEnabled',                
	             value: true
	         }
			],
		}
    },
    
    
    formulas: {
	    testname:function(get){
	    	return '222';
	    },
	    
	    
	    
	    createUserLastLoginTime : {
	           get: function (get) {
	        	   return get('selectedUserInfo.dobLastLoginTime');
	            },

	            set: function (value) {
	                var dt = this.get('selectedUserInfo.dobLastLoginTime');
	                dt.setHours(value.getHours());
	                dt.setMinutes(value.getMinutes());
	                dt.setSeconds(value.getSeconds());
	                
	            }
	    	},
	    	deviceId:function(get){
	    		return get('selectedCollectDevice.deviceId');
	    	},
			deviceName:function(get){
	    		return get('selectedCollectDevice.deviceName');
	    	},
	    	deviceNum:function(get){
	    		return get('selectedCollectDevice.deviceNum');
	    	},
	    	status:function(get){
	    		return get('selectedCollectDevice.status');
	    	},
	    	created:function(get){
	    		return get('selectedCollectDevice.created');
	    	},
	    	gasStation:function(get){
	    		return get('selectedCollectDevice.gasStation');
	    	},
	    	gasStationId:function(get){
	    		return get('selectedCollectDevice.gasStationId');
	    	},
	    	deviceImei:function(get){
	    		return get('selectedCollectDevice.deviceImei');
	    	}
	
    }
});
