Ext.define('helloext.view.module.authMgmt.RoleManagerView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.roleManagerView',
    title:'角色管理',
    layout:'fit',
    requires: [
        	  'helloext.view.module.authMgmt.RoleManagerController',
        	  'helloext.view.module.authMgmt.RoleManagerModel'
           ],
    controller:'roleManagerContr',
    viewModel:{
        type:'roleManagerModel'	
    },
    
	tbar:[{
		xtype:'toolbar',	  			
		items:[
		       {
		    	   xtype:'button', 
		    	   text:"新增",
		    	   float:'left',
		    	   iconCls : 'icon-add',
		    	   id:'auth_role_module_add',
		    	   bind:{
  			    		 hidden:'{isHiddenAdd}'
  			    	   },
		    	   handler:'addRoleInfo'
		       },
		       {
		    	   xtype:'button', 
		    	   text:"修改",
		    	   iconCls : 'icon-edit',
		    	   id:'auth_role_module_update',
		    	   float:'left',
		    	   bind:{
  			    		 hidden:'{isHiddenUpdate}'
  			    	   },
		    	   handler:'modifyRoleInfo'
		       },
		       {
		    	   xtype:'button', 
		    	   text:"删除",
		    	   id:'auth_role_module_delete',
		    	   float:'left',
		    	   iconCls : 'icon-delete',
		    	   bind:{
  			    		 hidden:'{isHiddenDelete}'
  			    	   },
		    	   handler:'deleteRoleInfo'
		       },
		       { xtype: 'tbspacer', width: 20 },'-',
    	       { xtype:'textfield', fieldLabel:"角色名称",  //id:'roleName', 
		    	 labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
	    	   { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryRoleInfo' }
		 ]
	}],
	collapsible:true,
  	border:true,
    selModel:'checkboxmodel',
    stripeRows: true,
    columns : [
       			{
       				xtype: 'rownumberer',
       				text:"序号",
       				width:'10%',
       				align:'center'
       			},
       			{
       				dataIndex : 'roleId',
       				hidden:true
       			},
       			{
       				text : '角色名称',
       				align:'center',
       				width:'20%',
       				dataIndex : 'roleName'
       			},
       			{
       				text : '角色描述',
       				align:'center',
       				width:'50%',
       				dataIndex : 'roleDesc'
       			},
       			{
       				text:'详情',
       		      	align:'center',
       		      	width:'20%',
       		      	dataIndex:'',
       		      	renderer:function(value, metaData, record, rowIndex, colIndex, store) {
	        		        var btnId = Ext.id();
	        		        Ext.Function.defer(createGridButton, 1, this, [btnId]);
	        			    function createGridButton(){
		        			       return new Ext.Button({
		        			       		text:'查看权限',
		        			       		scale: 'small',
		        			       		handler:function(button) {
		        			       	        var treeservice = new Ext.tree.TreePanel({
		        			       	            root: { text: '根', expanded: true },
		        			       	            rootVisible: false,
		        			       				store:{
		        			       					storeId:'roleAuthStoreId',
		        			       					proxy: {
		        			       						type : 'ajax',
		        			       						url:'./authorityMgmtCtr/getAuthsByRoleId',
		        			       						extraParams : {
		        			       							roleId: record.data.roleId,
		        			       							editable:false
		        			       						},
		        			       						autoLoad : true,
		        			       						reader : {
		        			       							type : 'json',
		        			       							rootProperty : 'children'
		        			       						}
		        			       					}
		        			       				},
//		        			       				listeners:{ 'itemdblclick' : 'tree_itemDBclick' },
		        			       	        });
		        			       	        var win = new Ext.Window({
		        			       	        	title: record.data.roleName + '角色权限',
		        			       	            width: 476,
		        			       	            height: 574,
		        			       	            resizable: true,
		        			       	            autoScroll: true,  
		        			       	            modal: true,
		        			       	            closable: true,
		        			       	            maximizable: true,
		        			       	            editable:false,
		        			       	            items: treeservice,
		        			       	         /*minimizable: true,
		        			       	         listeners:{
		        			       	        	minimize:function(){
		        			       	        	        if(this.minimizable){
		        			       	        	            this.width=100;
		        			       	        	            this.height=100;
		        			       	        	        }
		        			       	        	    }
		        			       	        	}*/
		        			       	        });
		        			       	        win.show();
		        			       	    }
		        	   			   }).render(document.body, btnId);  
	        			     	}  
	        			    return "<div id="+btnId+"></div>";
       		      },
       		      handler:'viewAuthInfo'
       		    }
       		],
  	    bind:{
	     	store:'{roleStore}'
	    },
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',	        				
      	    bind:{
    	     	store:'{roleStore}'
    	    },
			pageSize : 25,
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据'
		}]
	
});
