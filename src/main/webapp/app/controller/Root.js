Ext.define('helloext.controller.Root',  
    {  
      extend: 'Ext.app.Controller',  
      uses: ['helloext.view.login.Login','helloext.view.main.Main'],  
      requires:[
                'helloext.model.PageElement',
                'helloext.store.RoleManagerStore'
             ],
      /** 
     * 初始化事件 
     */  
      onLaunch: function () {  
        var session = this.session = new Ext.data.Session();  
        if (Ext.isIE8) {  
          Ext.Msg.alert('亲，本例子不支持IE8哟');  
          return;  
        };  
          
        this.login = new helloext.view.login.Login({  
            session: session,  
            listeners: {  
                scope: this,  
                login: 'onLogin'  
            }});  
      },
    /** 
     * logincontroller 的 "login" 事件回调. 
     * @param user 
     * @param loginManager 
     */  
    onLogin: function (username, loginController) {  
        this.login.destroy();  
        this.user = username;  
        console.log('username: ' + username);  
        this.getPageElement(username);  
    },  
  
    getPageElement:function(user){
  	  //var st2 = Ext.create('helloext.view.module.authMgmt.RoleManagerModel');
  	var st2 = Ext.create('helloext.store.RoleManagerStore');
  	st2.load();
    	Ext.Ajax.request({
			url :'./admin/getPageElement.action',
			method : "POST",
			success : function(response) {
				var resText = Ext.util.JSON.decode(response.responseText);
				var data = resText;
				var mystore = Ext.create('Ext.data.Store', {
				    autoLoad: true,
				    storeId:'glb_pageElement',
				    model: 'helloext.model.PageElement',
				    data : data,
				    proxy: {
				        type: 'memory',
				        reader: {
				            type: 'json',
				            rootProperty: 'resText'
				        }
				    }
				});
				this.viewport =  Ext.create('helloext.view.main.Main',{          
			          viewModel:{  
				          data:{  
				        	  userName:user
				          }  
			          }  
		     	});
			},
			failure : function(response) {
				console.log('操作失败！');
			}
		});
    },
    
 
  });  