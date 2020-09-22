Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSettingController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchFuelParamSettingController',
    requires:[
      'helloext.view.module.systemMgmt.BranchFuelParamSettingOper',
      'helloext.view.module.systemMgmt.BranchFuelParamSettingOperModel',
      'helloext.view.module.systemMgmt.BranchFuelParamSettingOperController',
      
    ],
    id:'BranchFuelParamSettingControllerId',
  

    changParamKeyToCn:function(value){
		switch(value){
			case 'GasTimeIntervalSet':
				return '同一车连续加气记录时间段设置(小时)';
				break;
			case 'NotOnlineTimeSetting':
				return '设备不上线时间设置(天)';
				break;
			case 'GasAgreementPrice':
				return '加气协议价设置';
				break;
			case 'LicensePlatePrefix':
				return '公交车牌前缀设置';
				break;
			case 'MutiGasTimesSetting':
				return '同一车辆多次加气次数设置';
				break;
				
			default:
    	  console.log('no tab matched!');
      	}
		
    },
    
    
    updateParamSettingInfo:function(button){
    	
    	
    	var me = this;
    	
    	
    	var model = me.getView().getSelection();
    	//var busViewModel = me.getView().getViewModel();
    	if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return ;
    	}
    	
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var paramSettingOper = maincenter.getComponent("BranchFuelParamSettingOperId");
    	
    	 if(!paramSettingOper) {
    	
								     	var paramSettingOper =Ext.create({    		
								    		xtype:'branchfuelparamsettingoper',
								    		closable: true,
								    		viewModel:{
								    		    data:{
								    		    	mainApp:this,
								    		    	selectedParamSetting:model[0],
								    		    	paraKeyCn:this.changParamKeyToCn(model[0].get("paraKey")),
								    		    	title:'参数修改'
								    		    }
								    		}
								  			});    
								     	
								     	maincenter.setActiveTab(maincenter.add(paramSettingOper)); 
								     	paramSettingOper.on('beforeclose', function () { paramSettingOper.destroy(); });
						     	} else {
								    		 maincenter.setActiveTab(paramSettingOper);	
		}
    }
    
    
});