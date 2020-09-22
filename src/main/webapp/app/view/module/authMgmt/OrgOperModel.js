Ext.define('helloext.view.module.authMgmt.OrgOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.orgoper',
    requires:[
       //'helloext.store.UserAuthManagerStore'
       'helloext.store.OrgInfoStore',
       'helloext.store.OrgTypesStore',
    ],
	data:{
		 title:'新增',
	     mainApp:null
	},
	
	stores: {
    	orginfo:{
    		type:'orginfostore'
    	},
    	orgtypes:{
    		type:'orgtypesstore'
    	}
    },
    formulas: { 
    	
    	
    	dobOrgId:function(get){
     		return get('selectedOrgInfo.id');
     	},
     	dobOrgName:function(get){
     		return get('selectedOrgInfo.text');
     	},
     	dobOrgDescription:function(get){
     		return get('selectedOrgInfo.description');
     	},
     	getOrgTypeId:function(get){
     		return get('selectedOrgInfo.orgTypeId');
     	},
     	isEnabled:function(get){
     		return get('selectedOrgInfo.isEnabled');
     	},
     	dobParentId:function(get){
     		return get('selectedOrgInfo.parentId');
     	},
    	

	
    }
});
