Ext.define('helloext.view.module.gasStationMgmt.GasGunInfoController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.gasGunInfoContr',
    requires:['helloext.view.module.gasStationMgmt.AddGasGunInfo',
              'helloext.view.module.gasStationMgmt.ModifyGasGunInfo'
             ],

    addGasGunInfo:function(button) {
      
      var maincenter = Ext.getCmp('mainView').down('maincenter');
  	  var addGasGunInfoView = maincenter.getComponent("addGasGunInfoId");
	  if(!addGasGunInfoView) {
	  		var addGasGunInfoView =Ext.create({    		
	    		xtype:'addGasGunInfo',
	    		closable: true
	  			});
		  maincenter.setActiveTab(maincenter.add(addGasGunInfoView));
		  addGasGunInfoView.on('beforeclose', function () { addGasGunInfoView.destroy(); });
	  } else {
		  maincenter.setActiveTab(addGasGunInfoView);
	  }
    },
    
    modifyGasGunInfo:function(button) {
    	
    	var records = this.getView().getSelection();
    	var selectedRecord = records[0];
    	if(records.length!=1) {
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return;
    	}
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var modifyView = maincenter.getComponent("modifyGasGunInfoId");
    	if(!modifyView) {
    		modifyView =Ext.create({    		
    			xtype:'modifyGasGunInfo',
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
    
    deleteGasGunInfo:function(button) {
    	
    	var records = this.getView().getSelection();
    	if(records.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行删除！');
    		return ;
    	}
		var idArray = [];
        Ext.each(records, function (item) {
        	idArray.push(item.get('gasGunId'));
        });
		Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
		    if (btn == 'yes'){
		    	Ext.Ajax.request({
		    		url:'./gasStationMgmt/deleteGasGunInfoById',
		    	    params:{
		    	    	gasGunIds:idArray
		    	    },
		    		success:function(response) {
		   			    Ext.MessageBox.alert("信息", "删除成功！");
		   			    Ext.getStore('gasGunInfoStoreId').reload();
		    		},
		    		failure:function(response){
		    			Ext.Msg.alert('警告', '删除失败！');
		    		}
		    	});
		    }
		});
    }
    
});
