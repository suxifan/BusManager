Ext.define('helloext.view.module.reportMgmt.EmailInfoModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.emailinfoviewmodel',
    requires:[
       'helloext.store.EmailInfoStore'
    ],
    stores:{
    	
    	emailInfoStore:{
    		type:'emailinfostore',
            autoLoad:true
    	}
    }

});
