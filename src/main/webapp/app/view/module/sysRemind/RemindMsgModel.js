Ext.define('helloext.view.module.sysRemind.RemindMsgModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.remindMsgModel',
    requires:['helloext.store.RemindMsgStore'],
    stores:{
    	remindStore:{
    		type:'remindMsgStore',
    		autoLoad:true
    	}
    }
	
});
