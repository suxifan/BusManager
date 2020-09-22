Ext.define('helloext.view.module.authMgmt.RoleManagerModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.roleManagerModel',
    requires:[
       'helloext.store.RoleManagerStore'
    ],
    stores:{
    	roleStore:{
    		type:'roleManagerStore',
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
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_role_module_add'){
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
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_role_module_update'){
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
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_role_module_delete'){
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
