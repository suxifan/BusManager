Ext.define('helloext.view.module.authMgmt.UserAuthManagerOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.userauthmanageroper',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
    id:'UserAuthManagerOperControllerId',
    
    sayHi:function(){
    	alert('hi');
    }, 
	closeWindow:function(button){
		this.getView().close();
	},
	saveUserInfo:function(button){
		var me = this;
		var form =me.getView().lookupReference('userform').getForm();
		
		if(form.isValid()) {
			form.submit({
				submitEmptyText:false,
				waiMsg:'正在处理中...',
				url:'./AuthManagement/saveUserInfo',
				params:form.getValues(),
				success: function(form, action) {
				       me.getView().close();
				       Ext.getStore('authManagerStoreID').reload();
				},
				failure : function(form, action) {  
					console.log("error..."); 
					me.getView().close();
	            }
			});
		}else{
			Ext.MessageBox.alert("警告", "信息填写不完整！");
		}
	}
    
});