Ext.define(
  'helloext.view.login.Login',
  {
	requires:['helloext.view.login.LoginController'],
    extend: 'Ext.window.Window',
    alias: 'widget.login',
    controller: 'login',
	closable: false,
	resizable : false,
	modal: false,
	autoShow: true,
	
	title: '用户登录',
	glyph: 'xf007@FontAwesome',
	y:document.body.clientHeight*0.40,
	items:[{
		xtype:'form',//父窗体
		reference: 'form',
		bodyPadding: 20,
		items:[{
			xtype: 'textfield',
			name: 'username',
			labelWidth: 50,
		    fieldLabel: '用户名',
		    //value:'admin',
			allowBlank: false,
			emptyText: '请输入用户名',
            listeners : {
                specialkey : 'onLoginKeyEnter'
            }
		  },{
			xtype: 'textfield',
			name: 'password',
			labelWidth: 50,
			inputType: 'password', 
		    fieldLabel: '密  码',
		    //value:'admin',
			allowBlank: false,
			emptyText: '请输入密码',
            listeners : {
                specialkey : 'onLoginKeyEnter'
            }
		  }]
	}],
    buttons: [{
                    name: 'loginbutton',
                    text: '登录',
                    glyph: 'xf110@FontAwesome',
                    //region: 'center',
                    listeners:{
                        click: 'onLoginbtnClick'//单击事件 调用LoginConroller.js中的onLoginbtnClick函数
                    }
                },{
			    name: 'registbutton',
			    text: '重置',
				glyph: 'xf118@FontAwesome',
				region: 'left',
				listeners:{
					  click: 'onResetbtnClick'//单击事件 调用LoginConroller.js中的onLoginbtnClick函数
				}
			  }]
  }
);