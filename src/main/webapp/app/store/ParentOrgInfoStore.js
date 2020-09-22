Ext.define('helloext.store.ParentOrgInfoStore', {
	extend: 'Ext.data.Store',
    alias: 'store.parentorginfostore',
    requires : ['helloext.model.Org'],
	model: 'helloext.model.Org',
    autoLoad: true,
    storeId:'parentorginfostoreId',
    sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './org/getParentOrgs',
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

