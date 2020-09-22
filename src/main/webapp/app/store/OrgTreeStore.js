Ext.define('helloext.store.OrgTreeStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.orgtreestore',
    root: {
	    text:"",
        expanded: true
   },
    autoLoad: true,
    storeId:'orgtreestoreId',
   proxy:{
   	type: 'ajax',
      	url: './org/getOrgTree',
      	node:'id',
		actionMethods : { 
			read : 'POST' 
		},
		paramsAsJson : true,
		reader: {
		   	type: 'json',
		    rootProperty: 'children'
		}
     },
	
});
