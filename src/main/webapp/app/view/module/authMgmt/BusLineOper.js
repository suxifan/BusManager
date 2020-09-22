Ext.define('helloext.view.module.authMgmt.BusLineOper', {
    extend: 'Ext.panel.Panel',
    //extend:'Ext.window.Window',
	//extend: 'Ext.container.Container',
    alias: 'widget.buslineoper',
    title:'新增线路',
    layout:'border',
    id:'BusLineOperId',
    requires: [
        	  'helloext.view.module.authMgmt.BusLineOperController',
        	  'helloext.view.module.authMgmt.BusLineOperModel',
        	  'Ext.form.Panel'
           ],
    viewModel: {
        type: 'buslineopermodel'
    },
           controller:'buslineopercontroller',    
           bind:{
        	   title:'{title}'+'线路',
           },
           items:[{
	           	xtype:'form',
	           	region:'center',
				border: false,
				fieldDefaults: {
	                labelAlign: 'center',
	            },
	            reference:'buslineform',
	               buttonAlign:'center',
	               buttons :[{
	               	xtype:'button',
	               	text:'保存',
	               	iconCls : 'icon-save',
	               	handler:'saveBusLine'
	               },{
	               	xtype:'button',
	               	text:'关闭',
	               	iconCls : 'icon-cancel',
	               	handler:'closeWindow'
	               }],
           items:[
  					
	  				 
	  					       
				  					       
			  				{
			   						xtype:'textfield',
			   						fieldLabel:'线路名称',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:80,
			   						name:'lineName',
			   						bind:{
			   		        			value:'{lineName}'
			   		        		},
			   		        		allowBlank: false, 
			   		        		regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
			   		        		maxLength:32,
		                			maxLengthText:"线路名称过长",
			   		        		emptyText: '线路名称',
			   						labelAlign:'right',
			   				},
			   				{
		   						xtype:'textfield',
		   						fieldLabel:'线路别名',
		   						padding:5,
		   						//anchor:'100%',
		   						labelWidth:80,
		   						name:'lineAlias',
		   						regex: /^(?=[0-9a-zA-Z@_.\u4e00-\u9fa5]+$)/,
		   						regexText:'不含特殊字符',
		   						bind:{
		   		        			value:'{lineAlias}'
		   		        		},
		   		        		allowBlank: true, 
		   		        		maxLength:32,
	                			maxLengthText:"线路别名过长",
		   		        		emptyText: '线路别名',
		   						labelAlign:'right',
		   				},
			   				 {
			   						xtype:'combobox',
			   		        		fieldLabel:'线路状态',
			   		        		labelAlign:'right',
			   		        		store : new Ext.data.ArrayStore({  
			   	                        fields : ['value', 'text'],  
			   	                        data : [["1", '启用'], ["0", '停用']]  
			   	                    }),
			   		        		bind:{
			   		        			//store:'{orginfo}',
			   		        			value:'{lineStatus}'
			   		        		},
			   		        		displayField:'text',//'dobPageElementId',
			   		        		valueField:'value',//'dobPageElementId',
			   		        		padding:5,
			   		        		labelWidth:80,
			   		        		name:'lineStatus',
			   		        		allowBlank: false, 
			   		        		emptyText: '请选择线路状态'
			   		        		
					    		    	
			   				  },
			   				   {
			   					  
			   					  
				    	        	xtype: 'combobox',
				    	        	fieldLabel: '所属机构',
				    	        	labelAlign:'right',
				    	        	padding:5,
			   		        		labelWidth:80,
				    	        	displayField: 'orgName',
				    	        	emptyText: '请选择',
				    	        	editable: true,
				         			valueField:'orgId',
				         			bind:{
					    		       store:'{orgStore}',
					    		       value:'{lineOrgId}'
					    		    	   
					    		     },	
				    	        	minChars: 0,
				    	        	queryMode: 'local',
				    	        	name:'lineOrgId',
				    	        	
				    	        	
				    	        	 listeners:{       
				    	                 select : function(combo, record,index)  
				    	                 {    
				    	                	 
				    	                	// this.up('form').down('textfield[reference="refLineOrgName"]').clearValue();
				    	                	 var pid = this.getRawValue();	
				    	                	 this.up('form').down('textfield[reference="refLineOrgName"]').setValue(pid);
				    	                 }     
				    	            }, 
				    	        	
				    	        	
				    	        	
				    	        	
				    	        	
				    	      },
				    	      {
			   						xtype:'textfield',
			   						fieldLabel:'所属机构ming',
			   						reference: 'refLineOrgName',
			   						name:'lineOrgName',
			   						padding:5,
			   						hidden:true,
			   				},
				    	      
				    	      
				    	      
				    	      {
			   						xtype:'textfield',
			   						fieldLabel:'dobLastLoginIp',
			   						padding:5,
			   						//anchor:'100%',
			   						labelWidth:60,
			   						name:'lineId',
			   						bind:{
			   		        			value:'{lineId}'
			   		        		},
			   						labelAlign:'right',
			   						hidden:true
		   					   }
                 
   				]

           }]

});