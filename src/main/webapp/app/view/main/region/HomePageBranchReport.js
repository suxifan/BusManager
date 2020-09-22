Ext.define('helloext.view.main.region.HomePageBranchReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'HomePageBranchReportId',
    title:'首页',
    autoScroll: true,
    alias: 'widget.homepagebranchreport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    viewModel: {
    	data:{
   	     branchId:null,
   	     branchName:null
    	}
    },
    initComponent: function() {
    	
    	
    	
        this.store = Ext.create('helloext.store.HomePageBranchReportStore');
          	var store = Ext.getStore('HomePageBranchReportStoreId');
          	store.proxy.url = './fuelReport/getFuelReportByParams';
          	store.proxy.method = 'post';
	      	var curYear = 1900+(new Date()).getYear();
	      	var curMonth = (new Date()).getMonth()+1;
	      	var org = this.getViewModel().get('branchId');
	      	console.log(this.getViewModel().get('branchId'));
          	var new_params = { orgId:org,
          						year:curYear,
          						month:curMonth,
          						queryType:'QueryBranchFuelReport'};    
               Ext.apply(store.proxy.extraParams, new_params);
             //Ext.apply(store.proxy.params, new_params);
              store.removeAll();
          	  store.load();
      	    
          	  
          	  
          	var parentType = null;
            var branchId = null;
            var lineTeamId = null;
            var st = Ext.getStore('glb_UserInfo');
      	  
      	  	var resLen = 0;
  			 if(st != null){
  			 	resLen = st.getCount();   
  			 }
          	   for(var i=0;i<resLen;i++){
  	        	   var d=st.getAt(i);
  	        	   if( d.get('key')== 'parentType'){
  	        		   parentType = d.get('value');
  	        	   }
  	        	  // console.log(d.get('key')+':'+d.get('value'));
  	        	   if( d.get('key')== 'branchParentId'){
  	        		   branchId = d.get('value');
  	        	   }
  	        	   if( d.get('key')== 'lineTeamParentId'){
  	        		   lineTeamId = d.get('value');
  	        	   }
          	   }
            
            store.filterBy(function(record) { 
          	  
          	 if(parentType == 'group'){
          		 return true;
          	 }else if(parentType=='branch'){
          		 return record.get('orgId') == branchId;
          	 }else if(parentType=='lineTeam'){
          		 return record.get('lineOrgId') == lineTeamId; 
          	 }   
            });
	
      this.items =[
{
    xtype: 'component',
    anchor: '100%',
    margin: '5 0 0 10',
    html: [
        '<p>首页</p>'
    ]
   },
{
    xtype: 'component',
    anchor: '100%',
    margin: '10 0 0 10',
    padding: '0 0 0 500',
    html: [
        '<h2>分公司车队油气月终汇总表</h2>'
    ]
},

{
		xtype: 'panel',
	    layout: {
	        type: 'hbox',
	        align: 'stretch'
	    },
        tbar: ['->',
               { xtype: 'label', bind:{text:'单位:{branch.rawValue}'} },
   		       { xtype: 'label', text: 1900+(new Date()).getYear()+'年 '+((new Date()).getMonth()+1)+'月' },
   		       { xtype: 'tbspacer', width: 20 },
   		       { xtype: 'label', text: '计量单位：天然气(升)' }
   		    ],
	    margin: '5 10 0 10',
	    flex:1,
	    bodyPadding: 5,

	items:[{
	               xtype: 'gridpanel',
	               columnLines:true,
                   features: [{
                       ftype: 'summary',
                       dock: 'bottom'
                   }],
	               /*bind:{
	    		       store:'{branchfuelStore}'
	    		     },	*/
                   store: this.store,
	                   defaults: {
	                       sortable: true,
	                       menuDisabled: true
	                   },

	                	   columns:[
								{
									xtype: 'rownumberer',
									text:"序号",
									width:'10%',
									align:'center',
			        	            summaryRenderer: function(value, summaryData, dataIndex) {
			        	                  return '总计';
			        	            },
								},
								{
									text : '分公司',
									align:'center',
									width:'20%',
									dataIndex : 'orgName'
								},
								{
									text : '路队',
									align:'center',
									width:'30%',
									dataIndex : 'lineOrgName'
								},
								{
									text : '线路',
									align:'center',
									width:'20%',
									dataIndex : 'lineName'
								},
								{
									text : '数量',
									align:'center',
									width:'20%',
									dataIndex : 'allVolume',
			        	        	summaryType: 'sum',
			        	        	summaryRenderer: function(value, summaryData, dataIndex) {   
			        	                  return Ext.util.Format.number(value, '00.00');
			        	            },
	        	                	field: {
	        	                     xtype: 'numberfield'
	        	                	}
								},
								/*{
									text : '备注',
									align:'center',
									width:'20%',
									dataIndex : 'remark'
								}*/
								],

	              // ],
	               flex:2
	           },
	           ,
	           {
	               xtype: 'chart',
	               flex:1,
	               style: 'background: #fff',
	               //insetPadding: 40,
	               animate: true,
	               shadow: false,
	               /*store : new Ext.data.ArrayStore({  
	                      fields : ['value', 'text'],  
	                      data : [["1", '男'], ["0", '女']]  
	                  }),*/
	               /*bind:{
	    		       store:'{branchfuelStore}'
	    		     },	*/
	               store: this.store,
	              /* store: Ext.create('Ext.data.JsonStore', {
	                   fields: ['month', 'data1' ],
	                   data: [
	                       { month: 'Jan', data1: 20 },
	                       { month: 'Feb', data1: 20 },
	                       { month: 'Mar', data1: 19 },
	                       { month: 'Apr', data1: 18 },
	                       { month: 'May', data1: 18 },
	                       { month: 'Jun', data1: 17 },
	                       { month: 'Jul', data1: 16 },
	                       { month: 'Aug', data1: 16 },
	                       { month: 'Sep', data1: 16 },
	                       { month: 'Oct', data1: 16 },
	                       { month: 'Nov', data1: 15 },
	                       { month: 'Dec', data1: 15 }
	                   ]
	               }),*/
	               axes: [{
	                   type: 'Numeric',
	                   position: 'left',
	                   fields: ['allVolume'],
	                   label: {
	                       //renderer: function(v) { return v + '%'; }
	                   },
	                   grid: true,
	                   minimum: 0
	               }, {
	                   type: 'Category',
	                   position: 'bottom',
	                   fields: ['lineName'],
	                   grid: true,
	                   label: {
	                       rotate: {
	                           degrees: -45
	                       }
	                   }
	               }],
	               series: [{
	                   type: 'column',
	                   axis: 'left',
	                   xField: 'lineName',
	                   yField: 'allVolume',
	                   style: {
	                       opacity: 0.80
	                   },
	                   highlight: {
	                       fill: '#000',
	                       'stroke-width': 20,
	                       stroke: '#fff'
	                   },
	                   tips: {
	                       trackMouse: true,
	                       style: 'background: #FFF',
	                       height: 20,
	                       renderer: function(storeItem, item) {
	                           this.setTitle(storeItem.get('lineName') + ': ' + storeItem.get('allVolume'));
	                       }
	                   }
	               }]
	           }]
           
}
],
      this.callParent();
    }
});
