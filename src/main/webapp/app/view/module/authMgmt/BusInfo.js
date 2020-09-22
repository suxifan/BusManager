Ext.define('helloext.view.module.authMgmt.BusInfo', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.businfo',
    id:'BusInfoId',
    title:'车辆管理',
    layout:'fit',
    requires: [
        'helloext.view.module.authMgmt.BusInfoController',
        'helloext.view.module.authMgmt.BusInfoModel'
    ],
    viewConfig:{
        forceFit: true
    },
    viewModel: {
        type: 'businfo'
    },
    controller:'businfo',
    tbar:[
        {
            xtype:'button',
            text:"新增",
            iconCls : 'icon-add',
            id:'businfo_add',

            float:'left',
            iconCls : 'icon-add',
            handler:'addBusInfo'
            //flex:1
        },
        {
            xtype:'button', text:"修改",float:'left',
            iconCls : 'icon-edit',
            id:'businfo_update',
            iconCls : 'icon-edit',

            handler:'updateBusInfo',
            hidden:false
        },
        {
            xtype:'button', text:"删除",float:'left',
            iconCls : 'icon-delete',
            id:'businfo_delete',
            iconCls : 'icon-delete',

            handler:'deleteBusInfo'
        },
        { xtype: 'tbspacer', width: 20 },'-',
        { xtype:'textfield', fieldLabel:"车牌号",  //id:'roleName',
            labelWidth:80, width:300, emptyText: '请输入关键字进行查询' },
        { xtype:'button', text:"查询", iconCls : 'icon-search', handler:'queryBusInfo' }
    ],

    columns : [
        {
            xtype: 'rownumberer',
            text:"序号",
            width:'10%',
            align:'center'
        },

        {
            text : '路队',
            width:'20%',
            align:'center',
            dataIndex : 'orgName'
        },
        {
            text : '线路',
            width:'20%',
            align:'center',
            dataIndex : 'lineName'
        },
        {
            text : '车牌号',
            width:'25%',
            align:'center',
            dataIndex : 'busNum'

        },
        {
            text : '自编号',
            width:'25%',
            align:'center',
            dataIndex : 'selfNum'
        }

    ],
    bind:{
        store:'{busInfoStore}'
    },
    selModel:'checkboxmodel',
    dockedItems : [ {
        xtype : 'pagingtoolbar',
        dock : 'bottom',
        bind:{
            store:'{busInfoStore}'
        },
        pageSize : 25,
        displayInfo : true,
        displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
        emptyMsg : '没有数据'
    }]
});