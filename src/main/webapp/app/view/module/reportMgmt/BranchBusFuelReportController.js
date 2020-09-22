Ext.define('helloext.view.module.reportMgmt.BranchBusFuelReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchbusfuelreportcontroller',
    requires:[
    ],
    id:'BranchBusFuelReportControllerId',
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
    	if(form.isValid()) {
//		var year = form.lookupReference('year').getValue();
//		var month = form.lookupReference('month').getValue();
        var startDate = form.lookupReference('branchBusFuelStartDate').getRawValue();
        var endDate = form.lookupReference('branchBusFuelEndDate').getRawValue();


    	var branchId = form.lookupReference('branch').getValue();
        var lineOrgId =  form.lookupReference('lineOrg').getValue();
        var lineName = form.lookupReference('line').getValue();
        var selfNum = form.lookupReference('selfNum').getValue();

            var store = Ext.getStore('BusFuelReportStoreId');
      	
      	store.proxy.url = './fuelReport/getBranchBusFuelReport';
      	store.proxy.method = 'post';
      	var new_params = { orgId:branchId,
                            lineOrgId:lineOrgId,
                            lineName:lineName,
                            selfNum:selfNum,
                            queryTimeRangeStart:startDate,
                            queryTimeRangeEnd:endDate,
      						queryType:'QueryBranchBusFuelReport'};
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
        	   console.log('单车油气parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
          
          store.filterBy(function(record) { 
        	  
        	 if(parentType == 'group'){
        		 return true;
        	 }else if(parentType=='branch'){
        		 return record.get('orgId') == branchId;
        	 }else if(parentType=='lineTeam'){
        		 return record.get('lineOrgId') == lineTeamId; 
        	 }   
          });
      	  
  
      	  
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
          	   console.log('单车油气汇总parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
            
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
    	  var form = this.getView();
      	if(form.isValid()) {
//    		var year = form.lookupReference('year').getValue();
//    		var month = form.lookupReference('month').getValue();

            var startDate = form.lookupReference('branchBusFuelStartDate').getRawValue( );
            var endDate = form.lookupReference('branchBusFuelEndDate').getRawValue( );
            var selfNum = form.lookupReference('selfNum').getRawValue( );
            var line = form.lookupReference('line').getRawValue( );
            var lineOrg = form.lookupReference('lineOrg').getRawValue( );

        	var branchId = form.lookupReference('branch').getValue();
        	var store = Ext.getStore('BusFuelReportStoreId');
    	   	Ext.Ajax.request({
    			url :'./fuelReport/getBranchBusFuelReportExcel.action',
    			method : "POST",
    			headers:{
                    'Content-Type': 'application/json; charset=utf-8'
    			},
    			jsonData:{ orgId:branchId,
                        queryTimeRangeStart:startDate,
                        queryTimeRangeEnd:endDate,
                        selfNum:selfNum,
                        lineName:line,
                        lineOrgName:lineOrg,
						queryType:'QueryBranchBusFuelReport'},
    			success : function(response) {
    				var indexUrl = response.responseText;
					window.location.href = decodeURI(indexUrl);//indexUrl;//encodeURI(indexUrl);
    	
    			},
    			failure : function(response) {
    				console.log('操作失败！');
    			}
    		});
      	}else{
    		Ext.MessageBox.alert("警告", "信息填写不完整！");
    	}
      }
    
});