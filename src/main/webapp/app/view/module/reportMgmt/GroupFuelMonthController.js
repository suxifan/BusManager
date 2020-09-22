Ext.define('helloext.view.module.reportMgmt.GroupFuelMonthController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.groupMonthController',
              
    queryInfo:function(button) {
	  
    	var form = this.getView();
		if(form.isValid()) {
            var startDate = form.lookupReference('groupFuelMonthStartDate').getRawValue( );
            var endDate = form.lookupReference('groupFuelMonthEndDate').getRawValue( );
            var fuelGasStationOrgId = form.lookupReference('fuelGasStationOrg').getValue();
            if(fuelGasStationOrgId=='全部'){
                fuelGasStationOrgId = null;
            }

            var store = Ext.getStore('groupFuelMonthStoreId');
	      	store.proxy.url = './fuelReport/getFuelMonthReportBySubGroup';
	      	store.proxy.method = 'post';
	      	var new_params = {
                                startDate:startDate,
                                endDate:endDate ,
                                fuelGasStationOrgId:fuelGasStationOrgId
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
  			 console.log(resLen);
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
          	   console.log('集团油气月终汇总parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
            
            store.filterBy(function(record) { 
          	  
          	 if(parentType == 'group'){
          		 return true;
          	 }else if(parentType=='branch'){
          		 return record.get('orgId') == branchId;
          	 }else if(parentType=='lineTeam'){
          		return record.get('orgId') == branchId;//即使是属于车队，但集团公司统计都是以分公司为单位统计，此处也返回分公司统计结果。
          		// return record.get('lineOrgId') == lineTeamId; 
          	 }
          	  
          	  
          	  
        		   
            });
      	    
      	    
      	    
      	    
      	    
      	    
      	    
      	    
      	    
		}
    },
    exportToExcel:function(button) {
    	  
    	var form = this.getView();
		if(form.isValid()) {
			var store = Ext.getStore('groupFuelMonthStoreId');
			var listParam = [];
			for (var i = 0; i < store.getCount(); i++) {
				listParam.push(store.getAt(i).data);
			}

            var fuelGasStationName = form.lookupReference('fuelGasStationOrg').getDisplayValue();

            var liData = Ext.util.JSON.encode(listParam)
			Ext.Ajax.request({
				url :'./fuelReport/exportGroupFuelMonthDataToExcel',
				method : "POST",
				params:{
					data:liData,
                    fuelGasStationName:fuelGasStationName
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
