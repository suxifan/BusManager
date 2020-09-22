Ext.define('helloext.store.OrgTypesStore', {
	extend: 'Ext.data.Store',
    alias: 'store.orgtypesstore',
    requires : ['helloext.model.OrgTypes'],
    autoLoad: true,
    storeId:'orgtypesstoreId',
    model: 'helloext.model.UserTypes',
   proxy:{
   	type: 'ajax',
      	url: './org/getAllOrgTypes',
		actionMethods : { 
			read : 'POST' 
		},
		paramsAsJson : true,
		reader: {
		   	type: 'json',
		   	rootProperty: 'items',
		}
     },
	
});

