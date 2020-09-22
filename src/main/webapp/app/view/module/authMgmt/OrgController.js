Ext.define('helloext.view.module.authMgmt.OrgController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.org',
    requires:[
      'helloext.view.module.authMgmt.OrgOper'
    ],
    renderIsEnabled:function(value){
  	  if(value){
  		  return "<span>是</span>";
  	  }else{
  		  return "<span'>否</span>";
  	  }
  },
    addOrgInfo:function(button){
    	var me = this;
    	var  mainApp = me.getView().getViewModel().get('mainApp');
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	
    	 var userOper = maincenter.getComponent("orgOperId");
    	
    	 if(userOper) {
	    	 if(userOper.title=='新增机构'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	 
    	 if(!userOper) {
    		  userOper =Ext.create({    		
    	    		xtype:'orgoper',
    	    		closable: true,
    	    		viewModel:{
    	    		    data:{
    	    		    	mainApp:this,
    	    		    	title:'新增'
    	    		    },
    	    		}
    	  			});
    	    	maincenter.setActiveTab(maincenter.add(userOper));
    	    	userOper.on('beforeclose', function () { userOper.destroy(); });

    	 } else {
    		 maincenter.setActiveTab(userOper);	
    	 }

    },
    queryOrgInfo:function(button) { 
      	console.log('queryOrginfo clicked, but do nothing');
      },
    updateOrgInfo:function(button){
    	var me = this;    	    	
    	var model = me.getView().getChecked();
    	if(model.length!=1){
    		Ext.Msg.alert('提示', '请选择一条记录进行修改！');
    		return ;
    	}
    	var maincenter = Ext.getCmp('mainView').down('maincenter');
    	var userOper = maincenter.getComponent("orgOperId");
   	 
    	 if(userOper) {
	    	 if(userOper.title=='修改机构'){
	 			
	 		}else{
	 			maincenter.remove(userOper);
	 			userOper.destroy(); 
	 			userOper = null;
	 		}
    	 }
    	
    	
    	if(!userOper) {
     	var userOper =Ext.create({    		
    		xtype:'orgoper',
    		closable: true,
    		viewModel:{
    		    data:{
    		    	mainApp:this,
    		    	selectedOrgInfo:model[0],
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
     
    deleteOrgInfo:function(button){
    	var me = this;
    	var view = me.getView();
    	var model = view.getChecked();

    	var idArray = [];
        Ext.each(model, function (item) {
        	idArray.push(item.get('id'));
        });
        
    	Ext.Msg.confirm('提示', '确定要删除?', function(btn, text){
    	    if (btn == 'yes'){
    	    	//alert(model[0].get('dobPassword'));
    	    	Ext.Ajax.request({
    	    		url:'./org/deleteOrgInfo',
    	    		method:'POST',
    	    	    params:{
    	    	    	orgIds:idArray//model[0].get('id')
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