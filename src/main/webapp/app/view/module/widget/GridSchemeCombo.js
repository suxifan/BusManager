/****
 如果一个模块有多个Grid方案，那么在pading上增加一个可以选择切换方案的Combo
**/
Ext.define('helloext.view.module.widget.GridSchemeCombo',{
   extend:'Ext.form.field.ComboBox',
   alias:'widget.gridschemecombo',
   fieldLabel:'方案',
   editable:false,
	labelWidth:40,
	labelAlign:'right',
	width:200,
	queryMode:'local',
	displayField:'tf_schemeName',//data中列表方案的名称
    valueField:'tf_schemeOrder',//data中列表方案的序号
	hidden:true,//默认不显示，如果GridScheme的个数大于1则显示
	bind:{
	  hidden :'{gridSchemeHidden}',//这是data中的一个计算字段，根据gridscheme个数来判断此空间是否显示
	  value:'{gridSchemeId}'//绑定gridSchemeId的值，在grid中，也绑定此值，这里改变后，会去执行grid中的绑定事件
	},
	initComponent:function(){
	 //取得最顶层容器的viewModel，如果有更好的办法取得，请告知，谢谢
	 var viewModel = this.up('modulepanel').getViewModel();
	 this.store = Ext.create('Ext.data.Store',{
	   fields:['tf_schemeOrder','tf_schemeName'],
		  data:viewModel.get('tf_gridSchemes')
	 });
	 this.value = viewModel.get('tf_gridSchemes')[0].tf_schemeOrder;//默认的方案选第一个
	 this.callParent(arguments);
	}

});