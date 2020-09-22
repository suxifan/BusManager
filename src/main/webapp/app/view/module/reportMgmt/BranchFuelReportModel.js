Ext.define('helloext.view.module.reportMgmt.BranchFuelReportModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.branchfuelreportmodel',
    requires:[
       'helloext.store.FuelReportStore',
       'helloext.store.OrgInfoStore'
       
    ],
    stores:{
    	
    	branchfuelStore:{
    		type:'fuelreportStore'
    	},
		branchStore:{
			type:'orginfostore',
			filters: [{
	             property: 'orgTypeId',                
	             value: '5'
	         }],
	         
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
		            		if (record.get('orgTypeId')!='5'){
		            			return false;
		            		}
		               
		            		if(parentType == 'group'){
		               		 	return true;
		               	 	}else if(parentType=='branch'){
		               		 return record.get('orgId') == branchId;
		               	 	}else if(parentType=='lineTeam'){
		               		// return record.get('lineOrgName') == branchId; 
		               	 		return record.get('orgId') == branchId;
		               	 	}
		            	
		            	
		            	
		            	});



                    }
		         } 
	         
	         
	         
	         
	         
	         
	         
	         
	         
	         
			
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
