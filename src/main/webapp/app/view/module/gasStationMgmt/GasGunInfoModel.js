Ext.define('helloext.view.module.gasStationMgmt.GasGunInfoModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.gasGunInfoModel',
    requires:[
              'helloext.store.GasGunInfoStore'
           ],
           stores:{
        	   gasGunInfoStore:{
           		  type:'gasGunInfoStore',
           		  autoLoad:true
           	}
           },
           formulas: {
       	    isHiddenAdd : {
       	    	get:function(get){			
       				var pageStore = Ext.getStore('glb_pageElement');
       				var hidden = true;
       				if(pageStore!=null){
       					for(var i = 0; i < pageStore.getCount();i ++){
       						if(pageStore.getAt(i).get('dobPageElementId')=='gas_gun_mgmt_module_add'){
       							hidden = pageStore.getAt(i).get('dobIsHidden');
       							break;
       						}
       					}
       				}
       				return hidden;
       			},

       	       set: function (value) {     
       	       }
       	    },
       	    isHiddenUpdate: {
       	    	get:function(get){			
       				var pageStore = Ext.getStore('glb_pageElement');
       				var hidden = true;
       				if(pageStore!=null){
       					for(var i = 0; i < pageStore.getCount();i ++){
       						if(pageStore.getAt(i).get('dobPageElementId')=='gas_gun_mgmt_module_update'){
       							hidden = pageStore.getAt(i).get('dobIsHidden');
       							break;
       						}
       					}
       				}
       				return hidden;
       			},

       	       set: function (value) {	                
       	       }
       	    },
       	    isHiddenDelete: {
       	    	get:function(get){
       				var pageStore = Ext.getStore('glb_pageElement');
       				var hidden = true;
       				if(pageStore!=null){
       					for(var i = 0; i < pageStore.getCount();i ++){
       						if(pageStore.getAt(i).get('dobPageElementId')=='gas_gun_mgmt_module_delete'){
       							hidden = pageStore.getAt(i).get('dobIsHidden');
       							break;
       						}
       					}
       				}
       				return hidden;
       			},
       	       set: function (value) {
       	       }
       	    }
       	
     }
});
