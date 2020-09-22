Ext.define('helloext.store.UserAuthManagerStore', {
    extend: 'Ext.data.Store',
    alias: 'store.userauthmanagerstore',
    requires:[
              'helloext.model.UserAuthManagerMode'
           ],
	model: 'helloext.model.UserAuthManagerMode',
    
	proxy: { 
		type: 'ajax',
	    url: './AuthManagement/getUserInfo',
	    actionMethods : { 
			read : 'POST'
	    }, 
		    paramsAsJson : true,
	    reader: {
	    	type: 'json',
	    	rootProperty: 'data',
	        totalProperty: 'totalCount'
	    }
	},
	autoLoad: true,
	storeId:'authManagerStoreID',
	
	listeners:{ 
			beforeload:function(){ 
			},
			load:function(){
				//msgTip.hide();
				//consolg.log("get userinfo:"+)
		    	//console.log('get userinfo :'+this.getCount()+":"+this.getAt(2).get('dobUserId'));
		    	//console.log('get userinfo :'+this.getCount()+":"+this.getAt(2).get('dobUserId'));
		    	//var listRole = this.getAt(2).get('liHasRoles');
		    	//console.log('listrole:'+listRole);
				//console.log('get user role :'+this.getCount());
				//console.log(listRole[0].dobRoleId);
				//console.log(listRole.length);
		    	//alert(this.getView());
				//Ext.app.Controller.getController('UserAuthManagerControllerId').sayHi();
			}
	} 

	
});
