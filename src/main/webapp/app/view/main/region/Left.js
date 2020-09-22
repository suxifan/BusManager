/****左边的菜单区域，可以放置树形菜单和折叠式菜单
*/
Ext.define('helloext.view.main.region.Left',{
	extend:'Ext.panel.Panel',
	alias:'widget.mainmenuregion',
	uses:[
	    'helloext.view.main.region.MainMenuTree',
		'helloext.view.main.region.AccordionMainMenu'
	],
	layout:{
	   type:'accordion',
	   animate:true,
       hideCollapseTool: true,  
	},
	glyph:0xf0c9,
//	tools:[
//	      {
//		      type:'unpin',
//			  tooltip:'树状方式显示菜单',
//			  hidden:true,
//			  listeners:{
//			    click:function(tool){
//				  var panel = tool.up('mainmenuregion');
//				  panel.insert(0,{
//				     xtype:'mainmenutree'
//				  });
//				  panel.items.items[0].expand();
//				  Ext.each(panel.query('accordionmainmenu'),function(accordion){
//				     panel.remove(accordion,true);
//				  });
//				  tool.hide();
//				  tool.previousSibling().show();
//				
//				}
//			  }
//	      }
//	      ],
	initComponent:function(){
	  /**/this.items = [{
	    xtype:'mainmenutree'
//	   xtype:'accordionmainmenu'
	  }];
	  this.callParent();
	}
});
