Ext.define('helloext.view.module.authMgmt.RoleManagerController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.roleManagerContr',
    requires:['helloext.view.module.authMgmt.AddRoleView',
              'helloext.view.module.authMgmt.ModifyRoleView'
             ],
    
              
    queryRoleInfo:function(button) {
	  
    	var roleName = button.up('toolbar').down('textfield').getValue();
    	var store = Ext.getStore('roleManagerStoreId');
    	store.proxy.url = './authorityMgmtCtr/getRolesByRoleName';
    	store.proxy.method = 'post';
    	var new_params = { roleName : encodeURI(roleName) };    
        Ext.apply(store.proxy.extraParams, new_params);
        store.removeAll();
    	store.load();
    },
    
    addRoleInfo:function(button) {
      
      var maincenter = Ext.getCmp('mainView').down('maincenter');
  	  var addRoleView = maincenter.getComponent("addRoleViewId");
	  if(!addRoleView) {
	  		var addRoleView =Ext.create({    		
	    		xtype:'addRoleView',
	    		closable: true
	  			});
		  maincenter.setActiveTab(maincenter.add(addRoleView));
		  addRoleView.on('beforeclose', function () { addRoleView.destroy(); });
	  } else {
		  maincenter.setActiveTab(addRoleView);
	  }
    },
    
    modifyRoleInfo:function(button) {
    	
    	var records = this.getView().getSelection();
    	var selectedRecord = records[0];
    	if(records.length!=1) {
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return;
    	}
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var modifyView = maincenter.getComponent("modifyRoleViewId");
    	if(!modifyView) {
    		modifyView =Ext.create({    		
    			xtype:'modifyRoleView',
    			closable: true,
    			viewModel:{
    				data:{
    					roleName:selectedRecord.data.roleName,
    					roleDesc:selectedRecord.data.roleDesc,
    					roleId:selectedRecord.data.roleId
    				}
    			}
    		});
    		maincenter.setActiveTab(maincenter.add(modifyView));
    		modifyView.on('beforeclose', function () { modifyView.destroy(); });
    	} else {
    		maincenter.setActiveTab(modifyView);
    	}
    	var store = Ext.getStore('roleAuthStoreId');
    	store.proxy.method = 'post';
    	store.proxy.url = './authorityMgmtCtr/getAuthsByRoleId';
    	var new_params = { roleId:selectedRecord.data.roleId, editable:true};    
        Ext.apply(store.proxy.extraParams, new_params);
        store.removeAll();
    	store.load();
    },
    
    deleteRoleInfo:function(button) {
    	
    	var records = this.getView().getSelection();
		var idArray = [];
        Ext.each(records, function (item) {
        	idArray.push(item.get('roleId'));
        });
		Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
		    if (btn == 'yes'){
		    	Ext.Ajax.request({
		    		url:'./authorityMgmtCtr/deleteRoleByRoleId',
		    	    params:{
		    	    	roleIds:idArray
		    	    },
		    		success:function(response) {
		   			    Ext.MessageBox.alert("信息", "删除成功！");
		   			    Ext.getStore('roleManagerStoreId').reload();
		    		},
		    		failure:function(response){
		    			Ext.Msg.alert('警告', '删除失败！');
		    		}
		    	});
		    }
		});
    },
    
});
