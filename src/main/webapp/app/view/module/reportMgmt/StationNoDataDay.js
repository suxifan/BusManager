Ext.define('helloext.view.module.reportMgmt.StationNoDataDay', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.stationNoDataDay',
    layout:'fit',
    requires: ['helloext.view.module.reportMgmt.StationNoDataDayModel'],
    viewModel:{
        type:'stationNoDataDayModel'
    },
    columnLines:true,

    
    columns : [
       			{
       				xtype: 'rownumberer',
       				text:"序号",
       				width:'10%',
       				align:'center'
       			},
       			{
					text : '加气站',
					width:'20%',
					align:'center',
					dataIndex : 'gasStation'
				},
       			{
					text : '日期',
					width:'60%',
					flex:1,
					align:'center',
					dataIndex : 'gasTimeStr'

				}
       		],
    bind:{
     	store:'{stationNoDataDayStore}'
     	
    }

});
