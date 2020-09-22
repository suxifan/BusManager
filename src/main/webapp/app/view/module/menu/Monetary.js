/***金额单位的管理
*/
Ext.define('helloext.view.module.menu.Monetary',{
   statics :{
   
     values:null,
	 getAllMonetary:function(){
	 
	   if(!this.values){
	      this.values = new Ext.util.MixedCollection();
		  this.values.add('unit',this.createAmonetary('',1,'元'));
		  this.values.add('thousand',this.createAmonetary('千',1000,'千元'));
		  this.values.add('tenthousand',this.createAmonetary('万',10000,'万元'));
		  this.values.add('million',this.createAmonetary('M',100*10000,'百万元'));
		  this.values.add('hundredmillion',this.createAmonetary('亿',10000*10000,'亿元'));
	   }
	   return this.values;
	 },
	//生成菜单中的items
	 getMonetaryMenu :function(){
	 
	   var items = [];
	   this.getAllMonetary().eachKey(function(key,item){
	       
	     items.push({
		      text:item.unitText,
			 value:key
		 });
	   });
	   return items;
	 },
	 createAmonetary :function(monetaryText,monetaryUnit,unitText){
	    return {
		
		   monetaryText:monetaryText,//跟在数值后面的金额单位文字，如：100.00
           monetaryUnit:monetaryUnit,//显示的数值需要除的分子
		   unitText:unitText//跟在字段后面的单位如 合同金额（万元）
		}
	 
	 },
	 getMonetary:function(key){
	   return this.getAllMonetary().get(key);
	 }
   }
});