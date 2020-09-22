Ext.define('helloext.view.module.reportMgmt.BranchFuelReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchfuelreportcontroller',
    requires:[
      //'helloext.view.module.reportMgmt.BranchFuelReport'
      'helloext.store.FuelMonthStore'
    ],
    id:'BranchFuelReportControllerId',
    renderIsEnabled:function(value){
    	  if(value){
    		  return "<span>是</span>";
    	  }else{
    		  return "<span'>否</span>";
    	  }
    },
    renderSex:function(value){
    	  if(value == '1'){
    		  return "<span>男</span>";
    	  }else if(value == '0'){
    		  return "<span'>女</span>";
    	  }
    },
    
    queryBranchFuelReport:function(button) {
    	var form = this.getView();
      	//var branchId = button.up('fieldset').down('combobox[name="branch"]').getValue();
      	//var year = button.up('fieldset').down('combobox[name="year"]').getValue();
      	//var month = button.up('fieldset').down('combobox[name="month"]').getValue();
    	if(form.isValid()) {
    	var branchId = form.lookupReference('branch').getValue();
		var startDate = form.lookupReference('branchFuelStartDate').getRawValue( );
		var endDate = form.lookupReference('branchFuelEndDate').getRawValue( );
      	var store = Ext.getStore('FuelReportStoreId');
      	store.proxy.url = './fuelReport/getBranchFuelReport';
      	store.proxy.method = 'post';
      	var new_params = { orgId:branchId,
                            queryTimeRangeStart:startDate,
                            queryTimeRangeEnd:endDate,
      						queryType:'QueryBranchFuelReport'};    
           Ext.apply(store.proxy.extraParams, new_params);
         //Ext.apply(store.proxy.params, new_params);
          store.removeAll();
      	  store.reload();
      	  
      	  
      	  
      	  
      	  
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
      	   console.log('车队油气汇总parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
        
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
      
      exportBranchFuelReportExcel:function(button){
    	  console.log("ok");
    	  var form = this.getView();
    	  var branchName = form.lookupReference('branch').getDisplayValue();
//  		  var year = form.lookupReference('year').getValue();
//  		  var month = form.lookupReference('month').getValue();

          var store = Ext.getStore('FuelReportStoreId');
          var resLen = store.getCount(); 
              var listParam = [];
              for (var i = 0; i < store.getCount(); i++) {
            	  listParam.push(store.getAt(i).data);
              }
              var liData = Ext.util.JSON.encode(listParam)
              //console.log(liData);
    	   	Ext.Ajax.request({
    			url :'./fuelReport/getFuelMonthReportExcel.action',
    			method : "POST",
    			params:{
    				excelType:'QueryBranchFuelReport',
    				data:liData
    			},
    			success : function(response) {
    				var indexUrl = response.responseText;
					window.location.href = decodeURI(indexUrl);//indexUrl;//encodeURI(indexUrl);
    	
    			},
    			failure : function(response) {
    				console.log('操作失败！');
    			}
    		});
      }
    
});