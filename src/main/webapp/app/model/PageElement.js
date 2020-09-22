Ext.define('helloext.model.PageElement', {
    extend: 'Ext.data.Model',
    alias: 'store.pageelementmodel',
    fields: [
   	         { name: 'dobPageElementId', type: 'string' },
   	         { name: 'dobPageElementName', type: 'string' },
   	         { name: 'dobPageElementDesc', type: 'string' },
   	         { name: 'dobIsHidden', type: 'bool'}
         ]
});