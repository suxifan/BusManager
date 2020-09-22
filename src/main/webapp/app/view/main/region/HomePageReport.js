Ext.define('helloext.view.main.region.HomePageReport', {
    extend: 'Ext.form.Panel',
    xtype: 'basic-column',
    id:'homepagereportId',
    title:'首页',
    autoScroll: true,
    alias: 'widget.homepagereport',
    bodyStyle: 'background: transparent !important',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    requires:['helloext.store.HomePageReportStore',
              'helloext.view.main.region.HomePageReportController'],
    controller:'homepagereportController',
    viewModel: {
    	data:{
   	     mainApp:null
    	},
       formulas: {
    	   
    	   isHiddenField : {
   	    	get:function(get){	
   	    		var hidden = true;
   	    		/*if(this.getView().title=='首页'){
   	    			hidden = true;
   	    		}*/
   				
   				return hidden;
   			},

   	       set: function (value) {     
   	       }
   	    },
	   
       },
    },
    //bind:{
 	 //  title:'{title}',
    //},
    initComponent: function() {
    	
        var me = this;
        this.yearData = function() {
            var year = [];
            for(var i = 2010; i<=2050; i++) {
            	var array = [];
            	array.push(i);
            	year.push(array);
            }
            return year;
		}
        this.store = Ext.create('helloext.store.HomePageReportStore');
        	var store = Ext.getStore('homepagereportStoreId');
	      	store.proxy.url = './fuelReport/getFuelMonthReportBySubGroup';
	      	store.proxy.method = 'post';
	      	var curYear = 1900+(new Date()).getYear();
	      	var curMonth = (new Date()).getMonth()+1;
	      	console.log(curYear);
	      	console.log(curMonth);
	      	var new_params = {  year:curYear,
	      						month:curMonth};    
            Ext.apply(store.proxy.extraParams, new_params);
            store.removeAll();
      	    store.load();
        me.items = [
		   {
		    xtype: 'component',
		    anchor: '100%',
		    margin: '5 0 0 10',
		    html: [
		        '<p>当前位置：首页</p>'
		    ]
		   },
		{
		    xtype: 'component',
		    anchor: '100%',
		    margin: '10 0 0 10',
		    padding: '0 0 0 500',
		    html: [
		        '<h2>集团油气月终汇总表</h2>'
		    ]
	    },
	    {
	    	xtype: 'panel',
	        layout: {
	            type: 'hbox',
	            align: 'stretch'
	        },
		    tbar: ['->',
   		       { xtype: 'label', text: 1900+(new Date()).getYear()+'年 '+((new Date()).getMonth()+1)+'月' },
   		       { xtype: 'tbspacer', width: 20 },
   		       { xtype: 'label', text: '计量单位：天然气，立方米' }
   		    ],
	        margin: '5 10 0 10',
	        flex:1,
	        bodyPadding: 5,
	        items:[
	               {
	                   style: 'padding-top: 10px;',
	                   xtype: 'gridpanel',
	                   columnLines:true,
	                   features: [{
	                       ftype: 'summary',
	                       dock: 'bottom'
	                   }],
	                   columns : {
	                       defaults: {
	                           sortable: true,
	                           menuDisabled: true
	                       },
	                       items: [
	                                //{ text: 'IE', dataIndex: 'data1', renderer: function(v) { return v + '%'; } }
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
										text : '单位',
										align:'center',
										width:'35%',
										dataIndex : 'orgName'
									},
									{
										text : '数量',
										align:'center',
										width:'25%',
										dataIndex : 'sumvolume',
				        	        	summaryType: 'sum',
				        	        	summaryRenderer: function(value, summaryData, dataIndex) {   
				        	                  return Ext.util.Format.number(value, '00.00');
				        	            },
			        	                	field: {
			        	                     xtype: 'numberfield'
			        	                }
									},
									{
										text : '备注',
										align:'center',
										width:'30%',
										dataIndex : 'remark'
									}
	                       ]
	                   },
	                   store: this.store,
	                   flex:1
	               },
	               {
	                   xtype: 'chart',
	                   flex:1,
	                   style: 'background: #fff',
	                   //insetPadding: 40,
	                   animate: true,
	                   shadow: false,
		           	   store: this.store,
	                   axes: [{
	                       type: 'Numeric',
	                       position: 'left',
	                       fields: ['sumvolume'],
	                       label: {
//	                           renderer: function(v) { return v + 'm3'; }
	                       },
	                       grid: true,
	                       minimum: 0
	                   }, {
	                       type: 'Category',
	                       position: 'bottom',
	                       fields: ['orgName'],
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
	                       xField: 'orgName',
	                       yField: 'sumvolume',
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
	                               this.setTitle(storeItem.get('orgName') + ': ' + storeItem.get('sumvolume'));
	                           }
	                       }
	                   }]
	               }
	        ]
	    	
	    }

      ];
      this.callParent();
    }
});
