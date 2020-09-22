Ext.define('helloext.view.module.systemMgmt.BranchFuelParamSettingOper', {
    extend: 'Ext.panel.Panel',
    //extend:'Ext.window.Window',
	//extend: 'Ext.container.Container',
    alias: 'widget.branchfuelparamsettingoper',
    title:'参数设置',
    layout:'border',
    id:'BranchFuelParamSettingOperId',
    requires: [
        	  'helloext.view.module.systemMgmt.BranchFuelParamSettingOperController',
        	  'helloext.view.module.systemMgmt.BranchFuelParamSettingOperModel',
        	  'Ext.form.Panel',
        	    /*'Ext.ux.form.MultiSelect',
        	    'Ext.ux.form.ItemSelector',
        	    'Ext.tip.QuickTipManager',
        	    'Ext.ux.ajax.JsonSimlet',
        	    'Ext.ux.ajax.SimManager'*/
           ],
    viewModel: {
        type: 'branchfuelparamsettingoper'
    },
           controller:'branchfuelparamsettingoper',    
           items:[{
			           	xtype:'form',
			           	region:'center',
						border: false,
						fieldDefaults: {
			                labelAlign: 'center',
			            },
		                reference:'paramsettingform',   
		                buttonAlign:'center',
		                buttons :[{
		               	xtype:'button',
		               	text:'保存',
		               	iconCls : 'icon-save',
		               	handler:'saveParamSettingInfo'
		               },{
		               	xtype:'button',
		               	text:'关闭',
		               	iconCls : 'icon-cancel',
		               	handler:'closeWindow'
	               }],
            items:[
  					
  				
   					{	
   						xtype:'hiddenfield',
   						
   						name:'settingId',
   						bind:{
  							value : '{settingId}'
   						}		
		   			},	
		   			{
	   				
   						xtype:'hiddenfield',
   						name:'paraKey',
   						bind:{
  							value : '{paraKey}'
   						}
  			
		   			},	
	  					       
	  				{
   						xtype:'textfield',
   						fieldLabel:'参数名称',
   						padding:5,
   						//anchor:'100%',
   						labelWidth:60,
   						width:'350px',
   						readOnly: true ,
   						bind:{
  								value : '{paraKeyCn}'
   							 },
   						name:'paraKeyCn', 
   						labelAlign:'right'

   						
   					},				
   					
   					{
   						xtype:'textfield',
   						fieldLabel:'参数值',
   						padding:5,
   						//anchor:'100%',
   						width:'350px',
   						labelWidth:60,
   						maxLength:10,
		                maxLengthText:"参数过长",
   						name:'paraValue',
   						bind:{
   							value:'{paraValue}'
   						},
   						labelAlign:'right',
   						allowBlank: false
   						
   						
   					}		
  				
   				]

           }]

});