Ext.define('helloext.view.module.authMgmt.OrgModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.org',
    
    requires: [
                 'helloext.store.OrgTreeStore'
             ],
    stores: {
    	orgtree:{
    		type:'orgtreestore'
    	}
    },
    formulas:{
    	
	    isHiddenAdd : {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_org_module_add'){
							hidden = pageStore.getAt(i).get('dobIsHidden');
							break;
						}
					}
				}
				return hidden;
			},

	       set: function (value) {
	    	   	console.log('setting....');
	                
	       }
	    },
	    isHiddenUpdate: {
	    	get:function(get){	
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_org_module_update'){
							hidden = pageStore.getAt(i).get('dobIsHidden');
							break;
						}
					}
				}
				return hidden;
			},

	       set: function (value) {
	    	   	console.log('setting....');
	                
	       }
	    },
	    isHiddenDelete: {
	    	get:function(get){				
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_org_module_delete'){
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
