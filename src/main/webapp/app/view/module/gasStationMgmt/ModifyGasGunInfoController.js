Ext.define('helloext.view.module.gasStationMgmt.ModifyGasGunInfoController',{
	extend :'Ext.app.ViewController',
	alias:'controller.modifyGasGunInfoController',

	modifyGasGunInfo:function(button) {
		
		var form = this.getView().getForm();
		if(form.isValid()) {
			form.submit({
	   		   url:'./gasStationMgmt/modifyGasGunInfo',
	   		   waitMsg: '保存中...',
	   		   params:form.getValues(),
	   		   success: function(form, action) {
	   			   Ext.getStore('gasGunInfoStoreId').reload();
	   			   Ext.MessageBox.alert("信息", "更新成功！");
	   			   Ext.getCmp('modifyGasGunInfoId').close();
	   			   var maincenter = Ext.getCmp('mainCenterId');
	   	    	   var gasGunInfoView = maincenter.down("modifyGasGunInfo");
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