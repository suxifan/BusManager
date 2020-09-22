Ext.define('helloext.view.main.region.HomePageReportController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.homepagereportController',
              
    queryInfo:function(button) {
	  
    	var form = this.getView();
		if(form.isValid()) {
			var year = form.lookupReference('groupfuelmonthyear').getValue();
			var month = form.lookupReference('groupfuelmonthmonth').getValue();
			
	      	var store = Ext.getStore('groupFuelMonthStoreId');
	      	store.proxy.url = './fuelReport/getFuelMonthReportBySubGroup';
	      	store.proxy.method = 'post';
	      	var new_params = {  year:year,
	      						month:month };    
            Ext.apply(store.proxy.extraParams, new_params);
            store.removeAll();
      	    store.load();
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
			var liData = Ext.util.JSON.encode(listParam)
			Ext.Ajax.request({
				url :'./fuelReport/exportGroupFuelMonthDataToExcel',
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
