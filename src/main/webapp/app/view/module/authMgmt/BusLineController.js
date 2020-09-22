Ext.define('helloext.view.module.authMgmt.BusLineController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.busline',
    requires:[
      'helloext.view.module.authMgmt.BusLineOper'
    ],
    id:'BusLineControllerId',
    sayHi:function(){
    	alert('hi');
    },
    renderBusLineStatus:function(value){
    	  if(value == '1'){
    		  return "<span>启用</span>";
    	  }else{
    		  return "<span'>停用</span>";
    	  }
    },
  
    addBusLine:function(button){
    	var me = this;
    	var  mainApp = me.getView().getViewModel().get('mainApp');
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	 var userOper = maincenter.getComponent("BusLineOperId");
    	
    	 if(userOper) {
	    	 if(userOper.title=='新增线路'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	 
    	 if(!userOper) {
    		 
    		  userOper =Ext.create({    		
    	    		xtype:'buslineoper',
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
    
    
    
    queryBusLine:function(button) {
  	  
      	var busLineName = button.up('toolbar').down('textfield').getValue();
      	var store = Ext.getStore('buslinewithpagestoreId');
      	store.proxy.url = './busLine/queryBusLineByKeywords';
      	store.proxy.method = 'post';
      	var new_params = { lineName : encodeURI(busLineName) };    
          Ext.apply(store.proxy.extraParams, new_params);
          store.removeAll();
      	store.load();
      },
    
    updateBusLine:function(button){
    	var me = this;
    	
    	
    	var model = me.getView().getSelection();
    	//var busViewModel = me.getView().getViewModel();
    	if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return ;
    	}
    	
    	
    	
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	
    	
    	
    	var userOper = maincenter.getComponent("BusLineOperId");
   	 
    	 if(userOper) {
	    	 if(userOper.title=='修改线路'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	
    	
    	if(!userOper) {
     	var userOper =Ext.create({    		
    		xtype:'buslineoper',
    		closable: true,
    		viewModel:{
    		    data:{
    		    	mainApp:this,
    		    	selectedBusLine:model[0],
    		    	title:'修改'
    		    }
    		}
  			});
    	maincenter.setActiveTab(maincenter.add(userOper)); 
    	userOper.on('beforeclose', function () { userOper.destroy(); });
   	 } else {
   		maincenter.setActiveTab(userOper);
   	 }
    	
    },
     
    deleteBusLine:function(button){
    	var me = this;
    	var view = me.getView();
    	//alert(view.getSelectionCount());
    	var model = view.getSelection();
    	
    	var idArray = [];
        Ext.each(model, function (item) {
        	idArray.push(item.get('lineId'));
        });

    	Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
    	    if (btn == 'yes'){
    	    	//alert(model[0].get('dobPassword'));
    	    	Ext.Ajax.request({
    	    		url:'./busLine/deleteBusLine',
    	    		method:'POST',
    	    	    params:{
    	    	    	lineIds:idArray//model[0].get('lineId')
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