Ext.define('helloext.view.module.reportMgmt.BranchDetailFuelReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchdetailfuelreportcontroller',
    requires:[
    ],
    id:'BranchDetailFuelReportControllerId',
    renderIsEnabled:function(value){
    	  if(value){
    		  return "<span>是</span>";
    	  }else{
    		  return "<span'>否</span>";
    	  }
    },
    renderOperType:function(value){
    	  if(value == 1){
    		  return "<span>运营</span>";
    	  }else if(value == 2){
    		  return "<span>非运营</span>";
    	  }else if(value == 3){
    		  return "<span>停驶</span>";
    	  }else if(value == 4){
    		  return "<span>报废</span>";
    	  }else if(value == 5){
    		  return "<span>在用(非运营组织)</span>";
    	  }else{
    		  return "<span>其他</span>";
    	  }
    },
    renderGasStatusType:function(value){
        if(value == 1){
            return "<span>加气站上传</span>";
        }else if(value == 2){
            return "<span>系统导入</span>";
        }
    },
    renderDate:function(value){
    	var str = null;
    	if(value != null && value != ''){
    		str = value.substring(0, 10);
    	}
    	return str;
    },
    renderTime:function(value){
    	var str = null;
    	if(value != null && value != ''){
    		str = value.substring(11, value.length);
    	}
    	return str;
    },
    renderFuelImage:function(value){
  	  //console.log('fuel image:'+value);
        //return "<img src=" +  '/fuelimage'+ value + " />";
    	return "<img src=" +  '/fuelimage'+ value + " height=\'25px\'/>";
        
    },
    
    renderAuthType:function(value){
    	  
    	 if(value == 'sign'){
   		  	return "<span>签字</span>";
    	 }else{
    		 return "<span>刷卡</span>"; 
    	 }
          
      },
    queryBranchFuelReport:function(button) {
    	var form = this.getView();
    	if(form.isValid()) {
    	var branchId = form.lookupReference('branch').getValue();
    	var gasStationId = form.lookupReference('gasStation').getValue();
    	var lineOrgId = form.lookupReference('lineOrg').getValue();
    	var lineId = form.lookupReference('line').getValue();
    	var busNum = form.lookupReference('plate').getValue();
    	var selfNum = form.lookupReference('selfNum').getValue();
    	var operType = form.lookupReference('oper').getValue();
    	
    	var sd = form.lookupReference('modifyStartDate').getValue();
		var ed = form.lookupReference('modifyEndDate').getValue();
		
		//var month = form.lookupReference('month').getValue();
      	var store = Ext.getStore('DetailFuelReportStoreId');
      	store.proxy.url = './fuelReport/getBranchDetailFuelReport';
      	store.proxy.method = 'post';
      	var new_params = { orgId:branchId,
      			queryTimeRangeStart:sd,
      			queryTimeRangeEnd:ed,
						gasStationId:gasStationId,
						lineOrgId:lineOrgId,
						lineId:lineId,
						busNum:busNum,
						selfNum:selfNum,
						operType:operType,
      						queryType:'QueryBranchDetailFuelReport'};    
           Ext.apply(store.proxy.extraParams, new_params);
         //Ext.apply(store.proxy.params, new_params);
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
        	   console.log('燃料明细parentType:'+parentType+'branch id:'+branchId+'lineTeamId:'+lineTeamId);
          
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
    	    	var branchId = form.lookupReference('branch').getValue();
    	    	var gasStationId = form.lookupReference('gasStation').getValue();
    	    	var lineOrgId = form.lookupReference('lineOrg').getValue();
    	    	var lineId = form.lookupReference('line').getValue();
    	    	var busNum = form.lookupReference('plate').getValue();
    	    	var selfNum = form.lookupReference('selfNum').getValue();
    	    	var operType = form.lookupReference('oper').getValue();
    	    	
    	    	var sd = form.lookupReference('modifyStartDate').getValue();
    			var ed = form.lookupReference('modifyEndDate').getValue();
    	   	Ext.Ajax.request({
    			url :'./fuelReport/getBranchDetailFuelReportExcel.action',
    			method : "POST",
    			headers:{
                    'Content-Type': 'application/json; charset=utf-8'
    			},
    			jsonData:{ orgId:branchId,
          					queryTimeRangeStart:sd,
          					queryTimeRangeEnd:ed,
    						gasStationId:gasStationId,
    						lineOrgId:lineOrgId,
    						lineId:lineId,
    						busNum:busNum,
    						selfNum:selfNum,
    						operType:operType,
          					queryType:'QueryBranchDetailFuelReport'},

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