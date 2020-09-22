Ext.define('helloext.view.module.reportMgmt.BranchModifiedFuelCheckReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchmodifiedfuelcheckreportcontroller',
    requires:[
    ],
    id:'BranchModifiedFuelCheckReportControllerId',
    renderIsEnabled:function(value){
    	  if(value){
    		  return "<span>是</span>";
    	  }else{
    		  return "<span'>否</span>";
    	  }
    },
    renderGasStatus:function(value){
    	if(value==0){
    		  return "<span>未定义</span>";
    	 }else if(value==1){
  		  return "<span>正常</span>";
    	 }else if(value==2){
  		  return "<span>未审核</span>";
    	 }else if(value==3){
  		  return "<span>已审核</span>";
    	 }else if(value==4){
  		  return "<span>审核未通过</span>";
    	 }
  },
    renderFuelImage:function(value){
    	  //console.log('fuel image:'+value);
          //return "<img src=" +  '/fuelimage'+ value + " />";
    		return "<img src=" +  '/fuelimage'+ value + " height=\'25px\'/>";
          
    },
    
    queryBranchFuelReport:function(button) {
    	var form = this.getView();
    	if(form.isValid()) {
    	var branchId = form.lookupReference('branch').getValue();
		var sd = form.lookupReference('modifyStartDate').getValue();
		var ed = form.lookupReference('modifyEndDate').getValue();
      	var store = Ext.getStore('ModifiedFuelCheckReportStoreId');
      	store.proxy.url = './fuelReport/getModifiedFuelCheckReportByParams';
      	store.proxy.method = 'post';
      	var new_params = { orgId:branchId,
      						queryTimeRangeStart:sd,
      						queryTimeRangeEnd:ed,
      						queryType:'QueryBranchModifiedFuelCheckReport'};    
           Ext.apply(store.proxy.extraParams, new_params);
         //Ext.apply(store.proxy.params, new_params);
          store.removeAll();
      	  store.load();
    	}else{
    		Ext.MessageBox.alert("警告", "信息填写不完整！");
    	}
      },
      
      exportBranchFuelReportExcel:function(button){
    	  console.log("ok");
    	  var form = this.getView();
    	  if(form.isValid()) {
    	  	var branchId = form.lookupReference('branch').getValue();
  			var sd = form.lookupReference('modifyStartDate').getValue();
  			var ed = form.lookupReference('modifyEndDate').getValue();
    	   	Ext.Ajax.request({
    			url :'./fuelReport/getBranchModifiedFuelReportExcel.action',
    			method : "POST",
    			headers:{
                    'Content-Type': 'application/json; charset=utf-8'
    			},
    			/*params:{
    					orgId:branchId,
						queryTimeRangeStart:sd,
						queryTimeRangeEnd:ed
    			},*/
    			jsonData:{orgId:branchId,queryTimeRangeStart:sd,queryTimeRangeEnd:ed,queryType:'QueryBranchModifiedFuelReport'},
    			
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