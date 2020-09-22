Ext.define('helloext.view.module.gasStationMgmt.GasStationController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.gasStationContr',
    requires:['helloext.view.module.gasStationMgmt.AddGasStationInfo',
              'helloext.view.module.gasStationMgmt.ModifyGasStationInfo'
             ],
              
    queryGasStationInfo:function(button) {
	  
    	var gasStationName = button.up('toolbar').down('textfield').getValue();
    	var store = Ext.getStore('gasStationStoreId');
    	store.proxy.url = './gasStationMgmt/getGasStationInfoByName';
    	store.proxy.method = 'post';
    	var new_params = { gasStationName : encodeURI(gasStationName) };    
        Ext.apply(store.proxy.extraParams, new_params);
        store.removeAll();
    	store.load();
    },
    
    addGasStationInfo:function(button) {
      
      var maincenter = Ext.getCmp('mainView').down('maincenter');
  	  var addGasStationInfoView = maincenter.getComponent("addGasStationInfoId");
	  if(!addGasStationInfoView) {
	  		var addGasStationInfoView =Ext.create({    		
	    		xtype:'addGasStationInfo',
	    		closable: true
	  			});
		  maincenter.setActiveTab(maincenter.add(addGasStationInfoView));
		  addGasStationInfoView.on('beforeclose', function () { addGasStationInfoView.destroy(); });
	  } else {
		  maincenter.setActiveTab(addGasStationInfoView);
	  }
    },
    
    modifyGasStationInfo:function(button) {
    	
    	var records = this.getView().getSelection();
    	var selectedRecord = records[0];
    	if(records.length!=1) {
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return;
    	}
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var modifyView = maincenter.getComponent("modifyGasStationInfoId");
    	if(!modifyView) {
    		modifyView =Ext.create({    		
    			xtype:'modifyGasStationInfo',
    			closable: true,
    			viewModel:{
    				data:{
    					selectedData:selectedRecord
    				}
    			}
    		});
    		maincenter.setActiveTab(maincenter.add(modifyView));
    		modifyView.on('beforeclose', function () { modifyView.destroy(); });
    	} else {
    		maincenter.setActiveTab(modifyView);
    	}
    },
    
    deleteGasStationInfo:function(button) {
    	
    	var records = this.getView().getSelection();
    	if(records.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行删除！');
    		return ;
    	}
		var idArray = [];
        Ext.each(records, function (item) {
        	idArray.push(item.get('principalId'));
        });
		Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
		    if (btn == 'yes'){
		    	Ext.Ajax.request({
		    		url:'./gasStationMgmt/deleteGasStationInfoById',
		    	    params:{
		    	    	principalIds:idArray
		    	    },
		    		success:function(response) {
		   			    Ext.MessageBox.alert("信息", "删除成功！");
		   			    Ext.getStore('gasStationStoreId').reload();
		    		},
		    		failure:function(response){
		    			Ext.Msg.alert('警告', '删除失败！');
		    		}
		    	});
		    }
		});
    }
    
});
