Ext.define('helloext.store.OrgLineStore', {
	extend: 'Ext.data.Store',
    alias: 'store.orglinestore',
    requires : ['helloext.model.Org'],
	model: 'helloext.model.Org',
    autoLoad: true,
    storeId:'orglinestoreId',
    sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './org/getOrgLine',
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

