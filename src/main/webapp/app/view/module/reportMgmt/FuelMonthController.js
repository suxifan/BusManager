Ext.define('helloext.view.module.reportMgmt.FuelMonthController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.fuelMonthController',
              
    queryInfo:function(button) {

    	var form = this.getView();
		if(form.isValid()) {
			var subGroup = form.lookupReference('fuelmonthsubgro').getValue();
			var startDate = form.lookupReference('fuelMonthStartDate').getRawValue( );
			var endDate = form.lookupReference('fuelMonthEndDate').getRawValue( );

	      	var store = Ext.getStore('fuelMonthStoreId');
	      	store.proxy.url = './fuelReport/getFuelMonthReport';
	      	store.proxy.method = 'post';
	      	var new_params = {  orgId:subGroup,
	      						startDate:startDate,
                                 endDate:endDate
                             };
            Ext.apply(store.proxy.extraParams, new_params);
            store.removeAll();
      	    store.load();
      	    
      	    
      	    
            var parentType = null;
            var branchId = null;
            var lineTeamId = null;
            var st = Ext.getStore('glb_UserInfo');
      	  
      	  var resLen = 0;
  			 if(st != null){
  			 	resLen = st.getCount();   
  			 }
          	   for(var i=0;i<resLen;i++){
  	        	   var d=st.getAt(i);
  	        	   if( d.get('key')== 'parentType'){
  	        		   parentType = d.get('value');
  	        	   }
  	        	  // console.log(d.get('key')+':'+d.get('value'));
  	        	   if( d.get('key')== 'branchParentId'){
  	        		   branchId = d.get('value');
  	        	   }
  	        	   if( d.get('key')== 'lineTeamParentId'){
  	        		   lineTeamId = d.get('value');
  	        	   }
          	   }
          	   console.log('天然气材料月终汇总parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
            
            store.filterBy(function(record) { 
          	  
          	 if(parentType == 'group'){
          		 return true;
          	 }else if(parentType=='branch'){
          		 return record.get('orgId') == branchId;
          	 }else if(parentType=='lineTeam'){
          		 return record.get('lineOrgId') == lineTeamId; 
          	 }   
            });
	    
		}else{
			Ext.MessageBox.alert("警告", "信息填写不完整！");
		}
    },
    
    exportToExcel:function(button) {
  	  
    	var form = this.getView();
		if(form.isValid()) {
			var store = Ext.getStore('fuelMonthStoreId');
			var listParam = [];
			for (var i = 0; i < store.getCount(); i++) {
				listParam.push(store.getAt(i).data);
			}
			var liData = Ext.util.JSON.encode(listParam)
			Ext.Ajax.request({
				url :'./fuelReport/exportFuelMonthDataToExcel',
				method : "POST",
				params:{
					data:liData
				},
				success : function(response) {
					var indexUrl = response.responseText;
					window.location.href = decodeURI(indexUrl);
				},
				failure : function(response) {
					Ext.MessageBox.alert("信息", "导出excel失败！");
					console.log('导出excel失败！'+ response.responseText);
				}
			});
		}
    }
    
});
