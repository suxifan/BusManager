/***
模块数据的主显示区域，继承自Grid
**/
Ext.define('helloext.view.module.region.Grid',{
    extend:'Ext.grid.Panel',
	alias:'widget.modulegrid',
	requires:['helloext.view.module.widget.GridSchemeCombo', 'Ext.selection.CellModel','Ext.grid.*', 'Ext.data.*',
			'Ext.util.*'],
	uses:[
	  'helloext.view.module.region.GridToolbar',
		'helloext.view.module.factory.ColumnsFactory'
	   
	],
	buid:{
	  title:'{tf_title}',
	  gridSchemeId:'{gridSchemeId}'//属性gridSchemeId，设置绑定和GridSchemeCombo是value绑定是一样的  
	},
	dockedItems:[{
	    xtype:'gridtoolbar',
		dock:'top'
	},{
	  xtype:'pagingtoolbar',//grid数据分页
	  store:this.store,
	  displayInfo:true,
	  prependButtons:true,
	  dock:'bottom',
	  items:[{
	    xtype:'gridschemecombo',
		listeners:{
		   'change':'gridSchemeChange'
		}
	  }]
	}],
		columnLines:true,//加上表格线
	  multiSelect:true,
	
	 initComponent:function(){
	   
	   var viewModel = this.up('modulepanel').getViewModel();
	   this.gridSchemeId = viewModel.get('tf_gridSchemes')[0].tf_schemeOrder;
	   //创建grid列
	   this.columns = helloext.view.module.factory.ColumnsFactory.getColumns(
		  viewModel,10);
	   this.cellEditing = new Ext.grid.plugin.CellEditing({
	      clicksToEdit :2
	   });
		  this.plugins = [this.cellEditing];
	/**/ this.viewConfig={
	 
	   stripeRows:true,//奇偶行不同底色
	   enableTextSelection:false,
		  //加入允许拖动功能
	   plugins:[{
	     ptype:'gridviewdragdrop',
		 ddGroup:'DD_grid_'+viewModel.get('tf_moduleName'), // 拖动分组必须设置，这个分组名称为:DD_grid_Global  
         enableDrop:false//设为false,不允许在本grid中拖动
	   }]
	 };
		  this.listeners={
		   selectionChange :'selectionChange'
		  }
	    this.callParent();
	  },

	/**
	 * 重新选择了列表方案之后，替换方案中的字段
	 */
	setGridSchemeId : function(value) {
		if (this.gridSchemeId != value) {
			this.gridSchemeId = value;
			Ext.suspendLayouts();
			this.columns = helloext.view.module.factory.ColumnsFactory.getColumns(this
							.up('modulepanel').getViewModel(), value);
			this.reconfigure(this.store, this.columns);
			Ext.resumeLayouts(true);
			//this.columnAutoSize();
		}
	},
	/**
	 * 重新适应所有列的宽度
	 */
	columnAutoSize : function() {
		
		Ext.Array.forEach(this.columnManager.getColumns(), function(column) {
					if (!column.autoSizeDisabled) {
						column.autoSize();
					}
				})
	}

	  /*,
		//自定义字段的还没有做，先放几个固定的
	columns:[{
	   dataIndex:'tf_name',
	   text:'工程项目名称',
		width:250
	},{
	  dataIndex:'tf_budget',
		 text:'投资总额'
	 }],
	store:new Ext.data.Store({
	    fields:['tf_name',{name:'tf_budget',type:'float'}],
		data:[{
		  tf_name : '安居房建设工程',  
          tf_budget : 1230000  
		},{
		  tf_name : '道路建设工程',  
          tf_budget : 453092  
		}]
	 })*/
});