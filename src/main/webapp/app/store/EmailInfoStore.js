Ext.define('helloext.store.EmailInfoStore', {
    extend: 'Ext.data.Store',
    alias: 'store.emailinfostore',
//    fields : [ {name : 'remindContent', type : 'string'},
//        {name: 'remindStatus', type : 'string'},
//        {name: 'createdStr', type: 'string'}
//    ],
    model: 'helloext.model.EmailInfoModel',
    proxy: {
        type: 'ajax',
        url:'./fuelEmail/getEmailInfo',
        autoLoad : true,
        reader: {
            type: 'json',
            rootProperty: 'data',
            totalProperty: 'totalCount'
        }
    },
    storeId:'emailInfoStoreId'
	
});
