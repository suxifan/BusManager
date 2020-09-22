Ext.define('helloext.store.UserTypesStore', {
	extend: 'Ext.data.Store',
    alias: 'store.usertypesstore',
    requires : ['helloext.model.UserTypes'],
    autoLoad: true,
    storeId:'usertypesstoreId',
    model: 'helloext.model.UserTypes',
   proxy:{
   	type: 'ajax',
      	url: './AuthManagement/getAllUserTypes',
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
	
});

