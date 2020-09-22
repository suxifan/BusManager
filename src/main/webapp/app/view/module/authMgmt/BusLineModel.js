Ext.define('helloext.view.module.authMgmt.BusLineModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.busline',
    requires:[
       'helloext.store.BusLineWithPageStore'
    ],
    stores:{
    	
    	lineStore:{
    		type:'buslinewithpagestore'

    	}
    },
	data:{
		 title:'新增',
	     mainApp:null
	},
    formulas: {
	    isHiddenAdd : {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_bus_line_module_add'){
							hidden = pageStore.getAt(i).get('dobIsHidden');
							break;
						}
					}
				}
				return hidden;
			},

	       set: function (value) {     
	       }
	    },
	    isHiddenUpdate: {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_bus_line_module_update'){
							hidden = pageStore.getAt(i).get('dobIsHidden');
							break;
						}
					}
				}
				return hidden;
			},

	       set: function (value) {	                
	       }
	    },
	    isHiddenDelete: {
	    	get:function(get){
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_bus_line_module_delete'){
							hidden = pageStore.getAt(i).get('dobIsHidden');
							break;
						}
					}
				}
				return hidden;
			},
	       set: function (value) {
	       }
	    }
	
    }
});
