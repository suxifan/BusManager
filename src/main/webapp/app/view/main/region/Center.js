/**系统界面的主区域，是一个tabpanel，可以有多个tab页面，用来放置各个模块
*/
Ext.define('helloext.view.main.region.Center',{
   extend:'Ext.tab.Panel',
	alias:'widget.maincenter',
	 closeAction:'hide',
	 autoDestroy:false,
	 tabPosition:'top',
	 id:'mainCenterId',
	 requires:['helloext.view.main.region.HomePageReport',
	           'helloext.view.module.reportMgmt.BranchFuelReport',
	           'helloext.view.main.region.HomePageBranchReport'],
	 /*items:[{
			 xtype:'homepagereport',
			 closable: true,
			 listeners:{'beforeclose': function(){ 
				 this.destroy();
			}}
		 }
	 ],*/
	 
	 createHomePage:function(me)
	 {
		 Ext.Ajax.request({
				url :'./org/getParentOrgs.action',
				method : "POST",
				success : function(response) {
					var resText = Ext.util.JSON.decode(response.responseText);
					var data = resText;
					var mystore = Ext.create('Ext.data.Store', {
					    autoLoad: true,
					    storeId:'glb_parentOrgs',
					    model: 'helloext.model.Org',
					    data : data,
					    proxy: {
					        type: 'memory',
					        reader: {
					            type: 'json',
					            rootProperty: 'resText'
					        }
					    }
					});
					 var st = mystore;//Ext.create('helloext.store.ParentOrgInfoStore');
					 var resLen = 0;
					 if(st != null){
					 	resLen = st.getCount();   
					 }
					 //console.log(resLen);
					 var parentType = null;
					 var branchParentName = null;
					 var branchParentId = null;
					 var lineTeamParentId = null;
					 var lineTeamParentName = null;
			        	   for(var i=0;i<resLen;i++){
				        	   var d=st.getAt(i);
			                   if(d.get('orgParentId')=='root' || d.get('orgParentId')==''||d.get('orgParentId')==null){
			                	   parentType = 'group';
			                	   branchParentName = d.get('orgName');
			                	   branchParentId = d.get('orgId');
			                	   break;
			                   }else if(d.get('orgTypeId')=='5'){
			                	   if(parentType == null){
			                		   parentType = 'branch';
			                	   }
			                	   branchParentName = d.get('orgName');
			                	   branchParentId = d.get('orgId');
			                	   //break;
			                   }else if(d.get('orgTypeId')=='6'){
			                	   parentType = 'lineTeam';
			                	   lineTeamParentName = d.get('orgName');
			                	   lineTeamParentId = d.get('orgId');
			                	   //break;
			                   }
				           } 
			        	   console.log(parentType);
			        	   console.log(branchParentName);
			        	   console.log(branchParentId);
			        	   console.log(lineTeamParentName);
			        	   console.log(lineTeamParentId);
			        	   
			        	   var mystore2 =   new Ext.data.ArrayStore({  
			                      fields : ['key', 'value'],  
			                      data : [['parentType', parentType], 
			                              ['branchParentName', branchParentName],
			                              ['branchParentId', branchParentId],
			                              ['lineTeamParentName', lineTeamParentName],
			                              ['lineTeamParentId', lineTeamParentId],
			                              ['userName', me.up('app-main').getViewModel().get('userName')]
			                              ] ,
			                      storeId:'glb_UserInfo',
			                  });
			        	   
			        	   
			        	   
	
			       if(parentType =='group' || parentType == null){
		    		  var view = Ext.create({    		
		    			  xtype:'homepagereport',
		    			  closable: true,
		    			  viewModel:{
		    				  data:{
		    					  mainApp:this
		    				  }
		    			  },
		    			  listeners:{'beforeclose': function(){ 
								 this.destroy();
							}}
		    		  });
		    		  me.setActiveTab(me.add(view));		    		  
			       }else{
			         var view  = Ext.create({    		
			        			  xtype:'homepagebranchreport',
			        			  closable: true,
			        			  viewModel:{
				    				  data:{
				    					  branchId:branchParentId,
				    				  }
				    			  },
			        			  listeners:{'beforeclose': function(){ 
										 this.destroy();
									}}
			        		  });
			        me.setActiveTab(me.add(view));	
			       }
				
				
				},
				failure : function(response) {
					console.log('操作失败！');
				}
			});
	 },
	 initComponent: function() {
		
		 console.log(this.up('app-main').getViewModel().get('userName')); 
		 this.createHomePage(this);
		 this.callParent();
	 }
	  
	  
});
