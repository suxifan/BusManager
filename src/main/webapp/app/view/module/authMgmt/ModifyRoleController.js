Ext.define('helloext.view.module.authMgmt.ModifyRoleController',{
	extend :'Ext.app.ViewController',
	alias:'controller.modifyRoleController',

	modifyRole:function(button) {
		var me = this;
		var treePanel = button.up("treepanel");
        var selNodes = treePanel.getChecked();
		var nodeList = new Array();
		Ext.each(selNodes, function (node) {
			var info = node.data.id + '-' + node.data.type;
			nodeList.push(info);
		});
		var form = this.getView().lookupReference('addRoleForm').getForm();
		if(form.isValid()) {
			var values = form.getValues();
			form.submit({
	   		   url:'./authorityMgmtCtr/modifyRole',
	   		   waitMsg: '保存中...',
	   		   params:{'roleId':values['roleId'], 'roleName':values['roleName'], 'roleDesc':values['roleDesc'], 
	   			   'nodeList':nodeList.join(',') },
	   		   success: function(form, action) {
	   			   Ext.getStore('roleManagerStoreId').reload();
	   			   Ext.MessageBox.alert("信息", "保存成功！");
	   			   me.getView().close();
	   			   
	   		   },
	           failure:function(form, action) {
	                Ext.MessageBox.alert("错误", "保存失败！");
	           }
	   		});
		} else {
			 Ext.MessageBox.alert("警告", "信息填写不完整！");
  		}
	    	
	},
	
   tree_itemDBclick:function (node, event) {
	   
        var id = event.data.id ;
        alert(node);
   }
   
});