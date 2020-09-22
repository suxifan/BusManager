Ext.define('helloext.view.module.authMgmt.BusInfoOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.businfoopercontroller',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
    id:'BusInfoOperControllerId',
    
    
	closeWindow:function(button){
		this.getView().close();
	},
	saveBusInfo:function(button){
		var me = this;
		var form =me.getView().lookupReference('businfoform').getForm();
		
		if(form.isValid()) {
			form.submit({
				url:'./fuelReport/saveBusInfo',
				params:form.getValues(),
				success: function(form, action) {
					   console.log("ok.k..");
				       me.getView().close();
				       Ext.getStore('businfostoreId').reload();
				},
				failure : function(form, action) {  
					console.log("error...");
                    Ext.MessageBox.alert("提示", "保存失败！");
					me.getView().close();
	            }
			});
		}else{
			Ext.MessageBox.alert("警告", "信息填写不完整！");
		}
	}
    
});