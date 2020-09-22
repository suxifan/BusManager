Ext.define('helloext.view.module.authMgmt.BusLineOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.buslineopercontroller',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
    id:'BusLineOperControllerId',
    
    
	closeWindow:function(button){
		this.getView().close();
	},
	saveBusLine:function(button){
		var me = this;
		var form =me.getView().lookupReference('buslineform').getForm();
		
		if(form.isValid()) {
			form.submit({
				url:'./busLine/saveBusLine',
				params:form.getValues(),
				success: function(form, action) {
					   console.log("ok.k..");
				       me.getView().close();
				       Ext.getStore('buslinestoreId').reload();
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