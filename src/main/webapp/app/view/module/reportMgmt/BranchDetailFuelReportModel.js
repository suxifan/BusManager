Ext.define('helloext.view.module.reportMgmt.BranchDetailFuelReportModel', {
    extend: 'Ext.app.ViewModel',
    alias:'viewmodel.branchdetailfuelreportmodel',
    requires:[
       'helloext.store.DetailFuelReportStore',
       'helloext.store.OrgInfoStore',
       'helloext.store.BusInfoStore',
       'helloext.store.BusLineStore' 
    ],
    stores:{
    	branchdetailfuelStore:{
    		type:'detailfuelreportStore'
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
	 		          
	 				
	 				
	        	        	  
	        	        	  /*store.add(new Record({
	        	        　　		    orgName: '全部',
	        	          		  orgId:'dd'
	        	          		}));*/
	 				
	 				
	            	var st = this;
	            	st.clearFilter();  
	            	var rec = {'orgName':'全部','orgId':'','orgTypeId':'5'};//st.getAt(0);
  	        	  	//rec.set('orgName','全部');
  	        	  	//rec.set('orgId','');
  	        	  	//rec.set('orgTypeId','99');
  	        	  	//st.add(rec);
  	        	  	st.insert(0,rec);
	            	st.filterBy(function(record) { 
	            		
	            		if (record.get('orgTypeId')=='99'){
	            			return true;
	            		}
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
			
		},
		

		stationStore:{
			type:'orginfostore',
			filters: [{
	             property: 'orgTypeId',                
	             value: '3'
	         }],
	         
	         listeners:{ 
		 			beforeload:function(){ 
		 			},
		 			load:function(){
		 				var st = this;
		            	//st.clearFilter();  
		            	var rec = {'orgName':'全部','orgId':'','orgTypeId':'3'};//st.getAt(0);
		            	//st.add(rec);
		            	st.insert(0,rec);
		            	//store.insert(st.getCount(), rec);
		 			}
	 	        }
		},
	         
	         lineOrgStore:{
	 			type:'orginfostore',
	 			filters: [{
	 	             property: 'orgTypeId',                
	 	             value: '6'
	 	         }],
	 	         
	 	        listeners:{ 
		 			beforeload:function(){ 
		 			},
		 			load:function(){
		 				var st = this;
		            	//st.clearFilter();  
		            	var rec = {'orgName':'全部','orgId':'','orgTypeId':'6'};//st.getAt(0);
		            	//st.add(rec);
		            	st.insert(0,rec);
		            	//store.insert(st.getCount(), rec);
		 			}
	 	        }
	         },
	         
	         /*lineStore:{
		 			type:'orginfostore',
		 			filters: [{
		 	             property: 'orgTypeId',                
		 	             value: '7'
		 	         },{
		 	             property: 'orgParentId',                
		 	             value: '-1'
		 	         }],
		    },*/
	         
	         
	         lineStore:{
		 			type:'buslinestore',
/*		 			filters: [{
		 	             property: 'orgTypeId',                
		 	             value: '99'
		 	         }],*/
		 	        listeners:{ 
			 			beforeload:function(){ 
			 			},
			 			load:function(){
			 				var st = this;
			            	//st.clearFilter();  
			            	var rec = {'lineName':'全部','lineId':'','orgTypeId':'99'};//st.getAt(0);
			            	st.insert(0,rec);
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
