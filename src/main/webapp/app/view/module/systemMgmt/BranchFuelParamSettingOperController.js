Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSettingOperController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchfuelparamsettingoper',
    requires:[
      //'helloext.view.module.UserAuthManagerOper'
      
    ],
    id:'BranchFuelParamSettingOperControllerId',
    
    sayHi:function(){
    	alert('hi');
    }, 
	closeWindow:function(button){
		this.getView().close();
	},
	saveParamSettingInfo:function(button){
		var me = this;
		var form =me.getView().lookupReference('paramsettingform').getForm();
		
		if(form.isValid()) {
			form.submit({
				url:'./fuelSystemManager/saveParamSetinInfo',
				params:form.getValues(),
				success: function(form, action) {
					   console.log("ok.k..");
				       me.getView().close();
				       
				       Ext.getStore('FuelParamSettingStoreId').reload();
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