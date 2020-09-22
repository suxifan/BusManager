Ext.define('helloext.view.module.authMgmt.UserAuthManagerModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.userauthmanager',
    requires:[
       'helloext.store.UserAuthManagerStore'
    ],
    stores:{
    	
    	userStore:{
    		type:'userauthmanagerstore',
    		/*filters: [{
	             property: 'dobAccount',                
	             value:  /^[^admin]/
	         }],*/
    	}
    },
	data:{
		 title:'新增',
	     mainApp:null
	},
    formulas: {
	    isHiddenAdd : {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_add'){
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
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_update'){
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
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_delete'){
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
