Ext.define('helloext.view.module.authMgmt.UserAuthManagerController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.userauthmanager',
    requires:[
      'helloext.view.module.authMgmt.UserAuthManagerOper'
    ],
    id:'UserAuthManagerControllerId',
    sayHi:function(){
    	alert('hi');
    },
    renderIsEnabled:function(value){
    	  if(value){
    		  return "<span>是</span>";
    	  }else{
    		  return "<span>否</span>";
    	  }
    },
    resetPwdbtn:function(value){
    	
    	return "<span>重置</span>";
    }, 
    renderSex:function(value){
    	  if(value == '1'){
    		  return "<span>男</span>";
    	  }else if(value == '0'){
    		  return "<span'>女</span>";
    	  }
    },
    renderRoles:function(value){
    	var str = null;
    	for(var i = 0; i < value.length; i ++){
    		str += value[i].roleName;
    		str += "，";
    	}
    	if(str != null){
    		str = str.substring(4, str.length-1);
    	}
    	return str;
  	 // return value.length;
  },
  

    addDriverInfo:function(button){
    	var me = this;
    	var busViewModel = me.getView().getViewModel();
    	busViewModel.set('title', '修改changed');
    },
    addUserInfo:function(button){
    	var me = this;
    	var  mainApp = me.getView().getViewModel().get('mainApp');
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	 var userOper = maincenter.getComponent("UserAuthManagerOperId");
    	
    	 if(userOper) {
	    	 if(userOper.title=='新增用户'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	 
    	 if(!userOper) {
    		 
    		  userOper =Ext.create({    		
    	    		xtype:'userauthmanageroper',
    	    		closable: true,
    	    		viewModel:{
    	    		    data:{
    	    		    	mainApp:this,
    	    		    	title:'新增'
    	    		    }
    	    		}
    	  			});
    	    	maincenter.setActiveTab(maincenter.add(userOper));
    	    	userOper.on('beforeclose', function () { userOper.destroy(); });

    	 } else {
    		 maincenter.setActiveTab(userOper);	
    	 }
    	
    	
    	
    	
    	
    	
    	//var busViewModel = me.getView().getViewModel();	
    },
    
    
    
    queryUserInfo:function(button) {
  	  
      	var userName = button.up('toolbar').down('textfield').getValue();
      	var store = Ext.getStore('authManagerStoreID');
      	store.proxy.url = './AuthManagement/getUserInfoByUserName';
      	store.proxy.method = 'post';
      	var new_params = { userName : encodeURI(userName) };    
          Ext.apply(store.proxy.extraParams, new_params);
          store.removeAll();
      	store.load();
      },
    
    updateUserInfo:function(button){
    	var me = this;
    	
    	
    	var model = me.getView().getSelection();
    	//var busViewModel = me.getView().getViewModel();
    	if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return ;
    	}
    	
    	
    	
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	
    	
    	
    	var userOper = maincenter.getComponent("UserAuthManagerOperId");
   	 
    	 if(userOper) {
	    	 if(userOper.title=='修改用户'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	
    	
    	if(!userOper) {
     	var userOper =Ext.create({    		
    		xtype:'userauthmanageroper',
    		closable: true,
    		viewModel:{
    		    data:{
    		    	mainApp:this,
    		    	selectedUserInfo:model[0],
    		    	title:'修改'
    		    }
    		}
  			});
    	maincenter.setActiveTab(maincenter.add(userOper)); 
    	userOper.on('beforeclose', function () { userOper.destroy(); });
	    var checkitems = Ext.getCmp('insTypeCb').items;
	       for(var j = 0; j < model[0].data.containRoleId.length; j ++){
		       for (var i = 0; i < checkitems.length; i++) {
		    	   if(checkitems.items[i].inputValue == model[0].data.containRoleId[j]){
		    		   checkitems.items[i].setValue(true);
		    	   }
		       }  
	       }
   	 } else {
   		maincenter.setActiveTab(userOper);
   	 }
    	
    },
     
    deleteUserInfo:function(button){
    	var me = this;
    	var view = me.getView();
    	//alert(view.getSelectionCount());
    	var model = view.getSelection();
    	var idArray = [];
        Ext.each(model, function (item) {
        	idArray.push(item.get('dobUserId'));
        });
    	
    	
    	
    	/*if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行删除！');
    		return ;
    	}*/
    	Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
    	    if (btn == 'yes'){
    	    	//alert(model[0].get('dobPassword'));
    	    	Ext.Ajax.request({
    	    		url:'./AuthManagement/deleteUserInfo',
    	    		method:'POST',
    	    	    params:{
    	    	    	dobUserIds:idArray//model[0].get('dobUserId')
    	    	    },
    	    		success:function(response){
    	    			var json = Ext.util.JSON.decode(response.responseText); ;
    	    			if(json.success){
    	    				Ext.Msg.alert('提示', '删除成功！');
    	    				me.getView().getStore().reload();
    	    			}else{
    	    				Ext.Msg.alert('提示', '删除失败！');
    	    			}
    	    			
    	    		},
    	    		failure:function(response){
    	    			Ext.Msg.alert('提示', '删除失败！');
    	    		}
    	    	});
    	    }
    	});
    	
    }
    
});