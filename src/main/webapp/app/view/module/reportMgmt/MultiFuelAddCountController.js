Ext.define('helloext.view.module.reportMgmt.MultiFuelAddCountController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.multiFuelAddCountController',
              
    queryInfo:function(button) {
	  
    	var form = this.getView();
		if(form.isValid()) {
			var gasDate = form.lookupReference('gasDate').getRawValue();
			var busNo = form.lookupReference('busNoCom').getValue();
			var selfNo = form.lookupReference('selfNoCom').getValue();
	      	var store = Ext.getStore('multiFuelAddCountStoreId');
	      	store.proxy.url = './fuelReport/getMultiFuelAddRecord';
	      	store.proxy.method = 'post';
	      	var new_params = {  gasDate:gasDate,
	      						busNo:busNo,
	      						selfNo:selfNo};    
            Ext.apply(store.proxy.extraParams, new_params);
            store.removeAll();
      	    store.load();
		}
    },
    exportToExcel:function(button) {
  	  
    	var form = this.getView();
		if(form.isValid()) {
			var store = Ext.getStore('multiFuelAddCountStoreId');
			var listParam = [];
			for (var i = 0; i < store.getCount(); i++) {
				listParam.push(store.getAt(i).data);
			}
			var liData = Ext.util.JSON.encode(listParam)
			Ext.Ajax.request({
				url :'./fuelReport/exportMultiFuelAddCountDataToExcel',
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
