/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('helloext.view.main.Main', {
    extend: 'Ext.container.Viewport',
    requires:[
        'helloext.view.main.MainController',
        'helloext.view.main.MainModel',
        'helloext.model.PageElement',
        'helloext.store.PageElementStore'
    ],
    uses:[ 'helloext.view.main.region.Top',
           'helloext.view.main.region.Bottom',
           'helloext.view.main.region.Center',
           'helloext.view.main.region.Left',
           'helloext.view.main.region.Header'
	],
    xtype: 'app-main',
    id: 'mainView',
    controller: 'main',
    viewModel: {
        type: 'main'
    },
    layout: {
        type: 'border'
    },
    listeners:{
	  resize:function(container){
	    container.getController().onMainResize();
	  }
	},
    items: [
            {
//               xtype:'maintop',
               xtype:'appHeader',
			   region:'north',
			   height:50
            },
            {  
               xtype : 'mainbottom',  
               region : 'south'
            },
            {
			  xtype:'mainmenuregion',
			  region:'west',
			  title:'导航菜单',
			  width:220,
			  collapsible:true,
			  split:true,
			  hidden:false,
			  margin:'0 0 3 0',
			  bind:{
				  title:'{mytitle}'
			  }
			},
			{
			  xtype:'maincenter',
			  region:'center',
			  margin:'0 0 3 0'
			}],
			
	initComponent:function(){
		Ext.setGlyphFontFamily('FontAwesome');
		this.callParent();	
    }
});