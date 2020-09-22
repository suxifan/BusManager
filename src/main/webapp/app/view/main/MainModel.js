
Ext.define('helloext.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',

    data: {
        name: 'app',
	    //系统信息
		system:{
		  name    : '包头公交燃料管理系统',
	      version : '',
		  iconUrl : '',
		},
		userName:'user',
//		remindMsgCount:'4'
    },
    formulas: {
	    getUserName : {
	    	get:function(get){	
	    			return get('userName');
			}
	    }
    }

});