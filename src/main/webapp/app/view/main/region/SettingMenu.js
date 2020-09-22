/****显示在顶部的按钮菜单，可以切换至标准菜单，菜单树
 **/

 Ext.define('helloext.view.main.region.SettingMenu',{
     extend:'helloext.ux.ButtonTransparent',
	 alias:'widget.settingmenu',
	 requires:['helloext.view.module.menu.Monetary'],
     text:'设置',
	 glyph:0xf013,
	 tooltip:'偏好设置',
     initComponent:function(){
	     this.menu =[];
		 this.menu.push({
		    text:'菜单样式',
			menu:[{
			  xtype:'segmentedbutton',
			  reference:'menuType',
			  value:'toolbar',
			 items:[{
			    text:'标准菜单',
				value:'toolbar'
			  },{
			    text:'树形菜单',
				value:'tree'
			  },{
			   text:'按钮菜单',
			   value :'button'
			  }]
			}]
		 },{
			    text:'金额单位',
				menu:[{
				     xtype:'segmentedbutton',
					 reference:'monetary',//加入这一句，在改变数据的时候可以触发bind绑定的事件
				    defaultUI:'default',
					value:'tenthousand',
					items:helloext.view.module.menu.Monetary.getMonetaryMenu()
				
				}]
			  });
		this.callParent();
	 }
 });