/***树状菜单，显示在主界面的左边*/

Ext.define('helloext.view.main.region.MainMenuTree',{
      extend:'Ext.tree.Panel',
	  alias:'widget.mainmenutree',
	  requires: ['helloext.view.main.MainController',
	             'helloext.view.main.MainModel'],
	  controller: 'main',
	  title:"模块列表",
//	  glyph:0xf0c9,
	  root: { text: '根', expanded: true },
	  rootVisible:false,
	  lines:false,
	  useArrows: true,
	  hideHeaders: true,
	  animate : true, // 动画效果
	  
	  viewModel:{
	    type:'main'
	  }, 
   	  listeners: {
	       itemclick: 'onMainMenuClick'
	  },
	  initComponent:function(){
	   
	      this.store = Ext.create('Ext.data.TreeStore',{
	    	    sorters: {property: 'sort', direction: 'ASC'},
				proxy: {
					type : 'ajax',
					url:'./authorityMgmtCtr/getMenus',
					autoLoad : true,
					reader : {
						type : 'json',
						rootProperty : 'children'
					}
				}
		   });
	      this.callParent(arguments);
	   }
});
