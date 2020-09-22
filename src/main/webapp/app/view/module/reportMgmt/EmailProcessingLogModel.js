Ext.define('helloext.view.module.reportMgmt.EmailProcessingLogModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.emailProcessingLogModel',
    requires:['helloext.store.EmailProcessingLogStore'],
    stores:{
    	emailProcessingLogStore:{
    		type:'emailProcessingLogStore',
    		autoLoad:true
    	}
    }
	
});
