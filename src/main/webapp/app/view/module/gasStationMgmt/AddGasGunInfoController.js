Ext.define('helloext.view.module.gasStationMgmt.AddGasGunInfoController',{
	extend :'Ext.app.ViewController',
	alias:'controller.addGasGunInfoController',

	addGasGunInfo:function(button) {
		
		var form = this.getView().getForm();
		if(form.isValid()) {
			form.submit({
	   		   url:'./gasStationMgmt/addGasGunInfo',
	   		   waitMsg: '保存中...',
	   		   params:form.getValues(),
	   		   success: function(form, action) {
	   			   Ext.getStore('gasGunInfoStoreId').reload();
	   			   Ext.MessageBox.alert("信息", "保存成功！");
	   			   Ext.getCmp('addGasGunInfoId').close();
	   			   var maincenter = Ext.getCmp('mainCenterId');
	   	    	   var gasGunInfoView = maincenter.down("gasGunInfo");
	   	    	   maincenter.setActiveTab(gasGunInfoView);
	   		   },
	           failure:function(form, action) {
	                Ext.MessageBox.alert("错误", "保存失败！");
	           }
	   		});
		}
	    	
	},
	
	closeWindow:function(button){
		this.getView().close();
	}

});