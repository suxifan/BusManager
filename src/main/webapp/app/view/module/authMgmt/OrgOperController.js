Ext.define('helloext.view.module.authMgmt.OrgOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.orgoper',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
 
	closeWindow:function(button){
		this.getView().close();
	},
	saveOrgInfo:function(button){
		
		var me = this;
		var form =me.getView().lookupReference('orgform').getForm();
		
		if(form.isValid()) {
		form.submit({
			url:'./org/saveOrgInfo',
			params:form.getValues(),
			success: function(form, action) {
			       me.getView().close();
			       Ext.getStore('orgtreestoreId').reload();
			},
			failure : function(form, action) {  
				console.log("error..."); 
				me.getView().close();
            }
		});
		}else{
			Ext.MessageBox.alert("警告", "信息填写不完整！");
		}
		
		
		console.log(form.getValues());

	}
    
});