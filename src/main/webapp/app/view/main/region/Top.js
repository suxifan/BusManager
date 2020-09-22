/**系统主页的
*/
Ext.define('helloext.view.main.region.Top',{
   extend:'Ext.toolbar.Toolbar',
   alias :'widget.maintop',//定义了这个组件的xtype类型为maintop
    uses:[
	    'helloext.ux.ButtonTransparent',
		'helloext.view.main.region.ButtonMainMenu'
      ],
	  defaults:{
	    xtype:'buttontransparent'
	  },
	 style:'background-color:#cde6c7',
   items:[{
      xtype:'image',
	  bind:{//数据绑定到MainModel中data的system.iconUrl
	  hidden:'{!system.iconUrl}',//如果system.iconUrl未设定，则此image不显示
       src:'{system.iconUrl}'//根据system.iconUrl的设置来加载图片
	 }
     },{
	   xtype:'label',
	   bind:{
	    text:'{system.name}'//text值绑定到system.name
	   },
	   style:'font-size:20px;color:blue;'
	 },{
	   xtype:'label',
		bind:{
	      text:'{system.version}'
	   }
	 },'->','','',
	 {
		   //text:'欢迎回来',
		   bind:{
			   text:'欢迎回来,'+'{getUserName}'
		   }
		 }
	 ,{
	   text:'帮助',  
        glyph : 0xf059,
        handler:function(button){
        	window.location.href="http://www.cictec.cn/"; 
        }
	 },{
	   text:'关于',  
       glyph : 0xf06a,
       handler:function(button){
       	window.location.href="http://www.cictec.cn/"; 
       }
	   }, 
	   {  
          text : '注销',  
           glyph : 0xf011,
           handler:function(button){
        	   Ext.Ajax.request({
               url: './admin/logout.action',//'/authenticate',
               method: 'POST',
               scope: this,
   			success : function(response) {
   				Ext.MessageBox.alert("警告", "注销成功！"+response.responseText);
   				window.location.href="./login.jsp"; 

			},
			failure:function(response){
				Ext.MessageBox.alert("警告", "注销失败！");
			}
           });}
       }]

});