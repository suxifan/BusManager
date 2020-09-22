/**
 * Created by qiandaxian on 16/4/29.
 */
Ext.define('helloext.view.module.reportMgmt.AddFuelExcelWin', {
    extend: 'Ext.window.Window',
    xtype: 'addFuelExcelWindow',
    controller: 'emailinfocontroller',
    title: '加气信息导入',
    width: 500,
    height: 300,
    resizable: true,
    modal: true,
    id:'addFuelExcelWindowId',

    bodyStyle : 'background: transparent !important',
    layout : {
        type : 'vbox',
        align : 'stretch'
    },
    items: [
        {
            xtype:'button',
            text:'下载模板',
            margin: '10 0 10 0',
            handler:'downloadExcel' ,
            iconCls :  'icon-export'
        },
        {
            xtype: 'form',
            fileUpload:true,
            reference: 'windowForm',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            border: false,
            bodyPadding: 10,

            fieldDefaults: {
                msgTarget: 'side',
                labelStyle: 'font-weight:bold'
            },

            items: [
                {
                xtype: 'filefield',
                emptyText: '请导入加气文件',
                reference: 'basicFile',
                margin: '10 0 10 0',
                name: 'uploadFile',
                regex: /^.*\.xls$/,
                regexText :'请导入后缀为.xls的excel文件',
                allowBlank: false,
                fieldLabel: '导入文件',
                buttonText: '浏览',
                labelWidth: 80
                }
            ]
        }],

    buttonAlign:'center',
    buttons :[{
        xtype:'button',
        text:'提交',
        iconCls : 'icon-save',
        handler:'addFuelFromExcel'
    },{
        xtype:'button',
        text:'关闭',
        iconCls : 'icon-cancel',
        handler:'closeWindow'
    }]

});