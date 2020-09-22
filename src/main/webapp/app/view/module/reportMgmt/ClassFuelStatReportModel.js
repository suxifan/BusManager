Ext.define('helloext.view.module.reportMgmt.ClassFuelStatReportModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.classfuelstatreportmodel',
    requires:[
       'helloext.store.ClassFuelStatReportStore',
       'helloext.store.OrgInfoStore'   
    ],
    stores:{
    	classfuelstatStore:{
    		type:'classfuelstatreportStore'
    	},
		branchStore:{
			type:'orginfostore',
			filters: [{
	             property: 'orgTypeId',                
	             value: '3'
	         }],
			
		}
    },
    formulas: {
	    isHiddenQuery : {
	    	get:function(get){			
				var pageStore = Ext.getStore('glb_pageElement');
				var hidden = true;
				if(pageStore!=null){
					for(var i = 0; i < pageStore.getCount();i ++){
						if(pageStore.getAt(i).get('dobPageElementId')=='auth_user_module_add'){
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
