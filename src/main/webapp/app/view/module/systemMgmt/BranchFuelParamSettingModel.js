Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSettingModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.branchfuelparamsettingmodel',
    requires:[
       'helloext.store.FuelParamSettingStore'
    ],
    stores:{
    	
    	paramSettingStore:{
    		type:'fuelparamsettingstore'
    	}
    },
    
    formulas: {
	   
	    isHiddenUpdate: {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='param_setting_update'){
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
//	,
//	data:{
//		 title:'新增',
//	     mainApp:null
//	},
//    formulas: {
//	    isHiddenAdd : {
//	    	get:function(get){			
//				var pageStore = Ext.getStore('glb_pageElement');
//				var hidden = true;
//				if(pageStore!=null){
//					for(var i = 0; i < pageStore.getCount();i ++){
//						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_add'){
//							hidden = pageStore.getAt(i).get('dobIsHidden');
//							break;
//						}
//					}
//				}
//				return hidden;
//			},
//
//	       set: function (value) {     
//	       }
//	    },
//	    isHiddenUpdate: {
//	    	get:function(get){			
//				var pageStore = Ext.getStore('glb_pageElement');
//				var hidden = true;
//				if(pageStore!=null){
//					for(var i = 0; i < pageStore.getCount();i ++){
//						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_update'){
//							hidden = pageStore.getAt(i).get('dobIsHidden');
//							break;
//						}
//					}
//				}
//				return hidden;
//			},
//
//	       set: function (value) {	                
//	       }
//	    },
//	    isHiddenDelete: {
//	    	get:function(get){
//				var pageStore = Ext.getStore('glb_pageElement');
//				var hidden = true;
//				if(pageStore!=null){
//					for(var i = 0; i < pageStore.getCount();i ++){
//						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_delete'){
//							hidden = pageStore.getAt(i).get('dobIsHidden');
//							break;
//						}
//					}
//				}
//				return hidden;
//			},
//	       set: function (value) {
//	       }
//	    }
//	
//    }
});
