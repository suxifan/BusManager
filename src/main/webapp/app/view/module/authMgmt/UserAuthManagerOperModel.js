Ext.define('helloext.view.module.authMgmt.UserAuthManagerOperModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.userauthmanageroper',
    requires:[
       //'helloext.view.module.UserAuthManagerStore'
       'helloext.store.OrgInfoStore',
       'helloext.store.UserTypesStore'
    ],
	data:{
		 title:'新增',
	     mainApp:null
	},
	stores: {
    	orginfo:{
    		type:'orginfostore'
    	},
    	usertypes:{
    		type:'usertypesstore'
    	},
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
	    dobUuid:function(get){
    		return get('selectedUserInfo.dobUuid');
    	},
    	dobUserId:function(get){
    		return get('selectedUserInfo.dobUserId');
    	},
    	dobRealName:function(get){
    		return get('selectedUserInfo.dobRealName');
    	},

    	dobAccount:function(get){
    		return get('selectedUserInfo.dobAccount');
    	},
    	employeeNum:function(get){
    		return get('selectedUserInfo.employeeNum');
    	},
    	cardNum:function(get){
    		return get('selectedUserInfo.cardNum');
    	},
    	dobPassword:function(get){
    		return get('selectedUserInfo.dobPassword');
    	},

    	dobSex:function(get){
    		return get('selectedUserInfo.dobSex');
    	},
    	dobEmail:function(get){
    		return get('selectedUserInfo.dobEmail');
    	},
    	dobMobile:function(get){
    		return get('selectedUserInfo.dobMobile');
    	},
    	dobOfficePhone:function(get){
    		return get('selectedUserInfo.dobOfficePhone');
    	},
    	dobIdCard:function(get){
    		return get('selectedUserInfo.dobIdCard');
    	},
    	dobErrorCount:function(get){
    		return get('selectedUserInfo.dobErrorCount');
    	},
    	dobLastLoginTime:function(get){
    		return get('selectedUserInfo.dobLastLoginTime');
    	},
    	dobLastLoginIp:function(get){
    		return get('selectedUserInfo.dobLastLoginIp');
    	},
    	dobRemark:function(get){
    		return get('selectedUserInfo.dobRemark');
    	},
	
    	getOrgId:function(get){
     		return get('selectedUserInfo.orgId');
     	},
     	getUserTypeId:function(get){
     		return get('selectedUserInfo.userTypeId');
     	},
     	isEnabled:function(get){
     		return get('selectedUserInfo.isEnabled');
     	},
		isHidden:function(get){
     		if(get('selectedUserInfo.dobPassword')==null||get('selectedUserInfo.dobPassword')==""){
     			return false;
     		}else{
     			return true
     		}
		}
    }
});
