/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('helloext.Application', {
    extend: 'Ext.app.Application',
    requires: [
               'helloext.store.PageElementStore'
           ],
    name: 'helloext',

    controllers: [  
                  'Root'  
                  // TODO: add controllers here  
              ], 
    stores:[
        // TODO: add global / shared stores here

    
    ],
    launch: function () {
    	console.log('auth.Application launched! ');

    }
});










/*
Ext.define('oaSystem.Application', {  
    extend: 'Ext.app.Application',  
      
    name: 'oaSystem',  
    uses:['oaSystem.SimData', 'Ext.ux.ajax.*'],  
    views: [  
        // TODO: add views here  
    ],  
  
    controllers: [  
        'Root'  
        // TODO: add controllers here  
    ],  
  
    stores: [  
        // TODO: add stores here  
    ],  
      
    launch: function () {  
        // TODO - Launch the application  
        //服务器傀儡，模拟真实世界中服务器交换  
        //oaSystem.SimData.init();  
        //console.log('launch begin');  
        //this.callParent()  
        Ext.ux.ajax.SimManager.init().register({  
          '/authenticate':  
          {  
            type: 'json',  
            data: function(ctx){  
              return Ext.apply({}, true);  
            }  
          }  
        });  
    }  
});  */
