Ext.define('helloext.store.OrgInfoStore', {
	extend: 'Ext.data.Store',
    alias: 'store.orginfostore',
    requires : ['helloext.model.Org'],
	model: 'helloext.model.Org',
    autoLoad: true,
    storeId:'orginfostoreId',
//    sorters: {property: 'orgName', direction: 'ASC'},
    proxy:{
   	 type: 'ajax',
      	url: './org/getOrgInfo',
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

