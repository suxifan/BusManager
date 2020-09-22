Ext.define('helloext.store.PageElementStore', {
    extend: 'Ext.data.Store',
    alias: 'store.pageelementstore',
    requires:[
              'helloext.model.PageElement'
           ],
   		model: 'helloext.model.PageElement',
		/*fields: [
	   	         { name: 'dobPageElementId', type: 'string' },
	   	         { name: 'dobPageElementName', type: 'string' },
	   	         { name: 'dobPageElementDesc', type: 'string' },
	   	         { name: 'dobIsHidden', type: 'bool'}
	         ],*/ 
	    
	   
        proxy:{
            type:'ajax',
            url:'./admin/getPageElement.action',
            actionMethods : { 
    			read : 'POST'
    	    }, 
    	    paramsAsJson : true,
            reader:{
                //type:'json',
                rootProperty: 'items'
                //totalProperty:'totalCount',
                //root:'data'
            }
        },
	autoLoad: true,
	//async:false,
	id:'globle_pageDataId',
	storeId:'globel_pageElementStoreId',
	listeners:{ 
		beforeload:function(){ 
		},
		load:function(){
			console.log('At main init component load page ok');
			for(var i = 0; i < this.getCount();i ++){
				//console.log('count:'+this.getCount());
				console.log('at '+i+":"+this.getAt(i).get('dobPageElementId'));
				console.log('at '+i+":"+this.getAt(i).get('dobIsHidden'));
			}
		}
	} 
	
});
