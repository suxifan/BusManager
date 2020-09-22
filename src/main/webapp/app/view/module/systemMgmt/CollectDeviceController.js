Ext.define('helloext.view.module.systemMgmt.CollectDeviceController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.collectdevicecontroller',
    requires:[
      'helloext.view.module.systemMgmt.CollectDeviceOper',
      'helloext.view.module.systemMgmt.CollectDeviceModel'
    ],
    id:'collectDeviceControllerId'
    	,
    renderStatus:function(value){
		if(value == 0){
			return "未使用";
		}else if(value == 1){
			return "使用";
		}else{
			return "";	
		}
		
    },


    addCollectDevice:function(button){
    	var me = this;
    	var  mainApp = me.getView().getViewModel().get('mainApp');
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var collectDeviceOper = maincenter.getComponent("CollectDeviceOperId");
    	
    	 if(collectDeviceOper) {
	    	 if(collectDeviceOper=='新增用户权限'){
	 			
	 		}else{
	 			maincenter.remove(collectDeviceOper);
	 			collectDeviceOper.destroy(); 
	 			collectDeviceOper = null;
	 		}
    	 }
    	 
    	 
    	 if(!collectDeviceOper) {
    		 
    		  collectDeviceOper =Ext.create({    		
    	    		xtype:'collectdeviceoper',
    	    		closable: true,
    	    		viewModel:{
    	    		    data:{
    	    		    	mainApp:this,
    	    		    	title:'新增设备'
    	    		    }
    	    		}
    	  			});
    	    	maincenter.setActiveTab(maincenter.add(collectDeviceOper));
    	    	collectDeviceOper.on('beforeclose', function () { collectDeviceOper.destroy(); });

    	 } else {
    		 maincenter.setActiveTab(collectDeviceOper);	
    	 }
    	
    	
    	
    	
    	
    	
    },
    
    
    
    queryCollectDevice:function(button) {
  	  
      	var deviceName = button.up('toolbar').down('textfield').getValue();
      	var store = Ext.getStore('collectDeviceStoreId');
      	store.proxy.url = './fuelSystemManager/getCollectDevice';
      	store.proxy.method = 'post';
      	var new_params = { deviceName : encodeURI(deviceName) };   

         Ext.apply(store.proxy.extraParams, new_params);
         
         store.removeAll();
      	 store.load();
      },
    
    updateCollectDevice:function(button){
    	var me = this;
    	
    	
    	var model = me.getView().getSelection();
    	//var busViewModel = me.getView().getViewModel();
    	if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return ;
    	}
    	
    	
    	
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	
    	
    	
    	var collectDeviceOper = maincenter.getComponent("CollectDeviceOperId");
   	 
    	 if(collectDeviceOper) {
	    	 if(collectDeviceOper.title=='修改设备'){
	 			
	 		}else{
	 			maincenter.remove(collectDeviceOper);
	 			collectDeviceOper.destroy(); 
	 			collectDeviceOper = null;
	 		}
    	 }
    	
    	
    	if(!collectDeviceOper) {
     	var collectDeviceOper =Ext.create({    		
    		xtype:'collectdeviceoper',
    		closable: true,
    		viewModel:{
    		    data:{
    		    	mainApp:this,
    		    	selectedCollectDevice:model[0],
    		    	title:'修改设备'
    		    }
    		}
  			});
    	maincenter.setActiveTab(maincenter.add(collectDeviceOper)); 
    	collectDeviceOper.on('beforeclose', function () { collectDeviceOper.destroy(); });
	  
   	 } else {
   		maincenter.setActiveTab(collectDeviceOper);
   	 }
    	
    },
     
    deleteCollectDevice:function(button){
    	var me = this;
    	var view = me.getView();
    	//alert(view.getSelectionCount());
    	var model = view.getSelection();
    	
    	
    	if(model.length==0){
    		Ext.Msg.alert('提示', '请选择要删除的记录！');
    		return ;
    	}
    	
    	
    	var arrayObj = new Array();

    	for(var i in model){
    		arrayObj.push(model[i].get("deviceId"));
    	}
		console.log(arrayObj.join(","));
    	Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
    	    if (btn == 'yes'){
    	    	//alert(model[0].get('dobPassword'));
    	    	Ext.Ajax.request({
    	    		url:'./fuelSystemManager/deleteCollectDevice',
    	    		method:'POST',
    	    	    params:{
    	    				deviceId:arrayObj.join(",")
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