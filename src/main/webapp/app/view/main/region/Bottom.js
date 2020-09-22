/**系统主页的底部区域，主要放置用户单位信息，服务单位和服务人员信息
*/
Ext.define('helloext.view.main.region.Bottom',{
  extend:'Ext.toolbar.Toolbar',
	  alias:'widget.mainbottom',
	  uses:[
	    'helloext.ux.ButtonTransparent'
      ],
	  defaults:{
	    xtype:'buttontransparent'
	  },
	  items:[{
        bind:{
		  text:'使用单位:{user.name}'
		},  
        glyph : 0xf0f7
      },{
	    bind:{
		 text:'用户:{user.name}'
		},  
         glyph : 0xf007  
	  },'->',{
	    bind:{
		  text:'服务单位:{service.company}'
		},  
        glyph : 0xf059  
	  },{
	   bind:{
	    text:'服务人员:{service.name}'
	   }
	  },{
	   bind:{
	     text:'tel:{service.phonenumber}'
	   },  
        glyph : 0xf095  
	  },{
	   bind:{
	    hidden:'{!service.email}',
			text:'eMail:{service.email}'
	   },  
        glyph : 0xf003  
	  },{
	    bind:{
		 text:'@{service.copyright}'
		}
	  }]
});