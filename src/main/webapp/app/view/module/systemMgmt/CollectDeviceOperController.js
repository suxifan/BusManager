Ext.define('helloext.view.module.systemMgmt.CollectDeviceOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.collectdeviceopercontroller',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
    id:'CollectDeviceOperControllerId',
    
    
	closeWindow:function(button){
		this.getView().close();
	},
	saveCollectDevice:function(button){
		var me = this;
		var form =me.getView().lookupReference('collectdeviceform').getForm();
		
		if(form.isValid()) {
			form.submit({
				url:'./fuelSystemManager/saveCollectDeviceInfo',
				params:form.getValues(),
				success: function(form, action) {
					   console.log("ok.k..");
				       me.getView().close();
				       Ext.getStore('collectDeviceStoreId').reload();
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