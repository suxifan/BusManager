Ext.define('helloext.view.module.reportMgmt.EmailProcessingLog', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.emailProcessingLog',
    layout:'fit',
    requires: ['helloext.view.module.reportMgmt.EmailProcessingLogModel'],
    viewModel:{
        type:'emailProcessingLogModel'
    },
    columnLines:true,
//
    columns : [
       			{
       				xtype: 'rownumberer',
       				text:"序号",
       				width:'10%',
       				align:'center'
       			},
       			{
					text : '内容',
					width:'60%',
					flex:1,
					align:'center',
					dataIndex : 'content'
				}

       		],
    bind:{
     	store:'{emailProcessingLogStore}'
     	
    }
//	,
//    selModel:'checkboxmodel',
//	dockedItems : [ {
//		xtype : 'pagingtoolbar',
//		dock : 'bottom',
//        				bind:{
// 		       		       store:'{emailProcessingLogStore}'
// 		       		     },
//        				pageSize : 25,
//        				displayInfo : true,
//        				displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
//		emptyMsg : '没有数据'
//	}]
});
