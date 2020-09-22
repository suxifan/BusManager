Ext.define('helloext.view.module.reportMgmt.MultiFuelAddCountModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.multiFuelAddCountModel',
    requires:[
       'helloext.store.BusInfoStore',
       'helloext.store.MultiFuelAddCountStore'
    ],
    stores:{
		busInfoStore:{
			type:'businfostore',
//			filters: [{
//	             property: 'lineId',                
// 	             value: '-1'
// 	         }],
			
	         listeners:{ 
		 			beforeload:function(){ 
		 			},
		 			load:function(){
		 				
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
		 		        	   console.log('parentType2:'+parentType+' bid:'+branchId+'ltid:'+lineTeamId);
		 		          
		 				
		 				
		 				
		 				
		 				
		            	var st = this;
		            	st.clearFilter();  
		            	st.filterBy(function(record) { 
		            		if(parentType == 'group'){
		               		 	return true;
		               	 	}else if(parentType=='branch'){
		               		 return record.get('orgParentId') == branchId;
		               	 	}else if(parentType=='lineTeam'){
		               	 		return record.get('orgId') == lineTeamId; 
		               	 		//return record.get('orgParentId') == branchId;
		               	 	}
		            	
		            	
		            	
		            	});
		 			}
		         }
			
			
			
			
		},
		multiFuelAddCountStore:{
			type:'multiFuelAddCountStore'
		}
    }

});
