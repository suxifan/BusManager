Ext.define(
  'helloext.view.login.LoginController',
  {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    id:'loginController',
    requires: [
               //'helloext.view.main.MainModel'
           ],
   //用户登录按钮事件处理
   onLoginbtnClick: function(){
       var form = this.lookupReference('form');   
       if (form.isValid()) {  
         this.login({  
           data: form.getValues(),  
           scope: this,  
           success: 'onLoginSuccess',  
           failure: 'onLoginFailure'  
       })} 
    },
    
    onResetbtnClick: function(){
        var form = this.lookupReference('form');   
        form.reset();
     },

    onLoginKeyEnter: function(field, e) {
      if (e.getKey() == Ext.EventObject.ENTER) {
    	  this.onLoginbtnClick();
      }
    },
    
    onLoginFailure: function() {
        Ext.getBody().unmask();
		Ext.Msg.alert('错误提示', "服务异常，请检查服务器或网络设置！");  

    },

    onLoginSuccess: function(logname, logpass) {
		console.log('登录ok 用户名： ' + logname);
		console.log('登录ok 密  码： ' + logpass);
		//清空背景图片
		Ext.get("bgImg").empty();
        this.fireViewEvent('login', logname);
    },

    login: function(options) {
        Ext.Ajax.request({
            url: './admin/doLogin.action',
            method: 'POST',
            params: options.data,
            scope: this,
            callback: this.onLoginReturn,
            original: options
        });
    },

    onLoginReturn: function(options, success, response) {
        options = options.original;
        console.log('response:'+response.responseText);
        if (success) {
        	if(response.responseText == 'succLogin') {
        			Ext.callback(options.success, options.scope, [options.data.username, options.data.password]);
            		return;
        	}else{
        		 Ext.Msg.alert('错误提示', "您的输入用户名或密码信息有误，请核实后重新输入！");  
        	}
        }
    }
    
  }
);