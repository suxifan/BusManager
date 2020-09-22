Ext.define('helloext.view.module.reportMgmt.BranchModifiedFuelReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.branchmodifiedfuelreportcontroller',
    requires:[
    ],
    id:'BranchModifiedFuelReportControllerId',
    renderIsEnabled:function(value){
    	  if(value){
    		  return "<span>是</span>";
    	  }else{
    		  return "<span'>否</span>";
    	  }
    },
    renderFuelImage:function(value){
    	  //console.log('fuel image:'+value);
          return "<img src=" +  '/fuelimage'+ value + "  height=\'25px\'/>";
    	 //return "<img src=" +  '/fuelimage/fuel.png' + ' height=\'25px\'/>';
    },
    
    queryBranchFuelReport:function(button) {
    	var form = this.getView();
    	if(form.isValid()) {
    	var branchId = form.lookupReference('branch').getValue();
		var sd = form.lookupReference('modifyStartDate').getValue();
		var ed = form.lookupReference('modifyEndDate').getValue();
      	var store = Ext.getStore('ModifiedFuelReportStoreId');
      	store.proxy.url = './fuelReport/getModifiedFuelReportByParams';
      	store.proxy.method = 'post';
      	var new_params = { orgId:branchId,
      						queryTimeRangeStart:sd,
      						queryTimeRangeEnd:ed,
      						queryType:'QueryBranchModifiedFuelReport'};    
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