Ext.define('helloext.view.module.gasStationMgmt.ModifyGasStationInfoController',{
	extend :'Ext.app.ViewController',
	alias:'controller.modifyGasStationInfoController',

	modifyGasStationInfo:function(button) {
		
		var form = this.getView().getForm();
		
		
		if(form.isValid()) {
			
			form.submit({
	   		   url:'./gasStationMgmt/modifyGasStationInfo',
	   		   waitMsg: '保存中...',
	   		   params:form.getValues(),
	   		   success: function(form, action) {
	   			   Ext.getStore('gasStationStoreId').reload();
	   			   Ext.MessageBox.alert("信息", "更新成功！");
	   			   Ext.getCmp('modifyGasStationInfoId').close();
	   			   var maincenter = Ext.getCmp('mainCenterId');
	   	    	   var gasstationInfoView = maincenter.down("gasstationInfo");
	   	    	   maincenter.setActiveTab(gasstationInfoView);
	   		   },
	           failure:function(form, action) {
	                Ext.MessageBox.alert("错误", "保存失败！");
	           }
	   		});
		}
	    	
	}
//    ,
//	openMap:function( field, e ){
//
//
//		if (e.getKey() == e.ENTER) {
//	                    	var win = new Ext.Window({
//							title : '加气站经纬度',
//							width : 800,
//							height : 600,
//							resizable : true,
//							autoScroll : true,
//							fieldDefaults: {
//					                labelAlign: 'center',
//					            },
//				            buttonAlign:'center',
//							buttons :[{
//					               	xtype:'button',
//					               	text:'拾取',
//					               	iconCls : 'icon-save',
//					               	handler: this.getLatiLongForMap
//					               }],
//
//				           	region:'center',
//							border: false,
//				        	html: '<iframe id="mapIframe" name="mapIframe"  scrolling="auto" frameborder="0" width="100%" height="100%" src="map.html"></iframe><input type="hidden" id="fatherLatiLongValue" value='+field.getValue()+' />'
//
//
//
//						});
//						win.show();
//                    }
//
//	},
//
//	getLatiLongForMap:function(button){
//
//		var win   = button.up('window');
//
//		var latiLongVal  =	Ext.get("mapIframe").dom.contentWindow.document.getElementById("latiLongId").value;
//		Ext.getCmp("latiLong").setValue(latiLongVal);
//		win.close();
//	},
//
//	closeWindow:function(button){
//		this.getView().close();
//	}

});