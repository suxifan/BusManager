Ext.define('helloext.view.module.reportMgmt.EmailInfoController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.emailinfocontroller',
    requires:[
        'helloext.view.module.reportMgmt.StationNoDataDay',
        'helloext.view.module.reportMgmt.AddFuelExcelWin'

    ],
    id:'emailInfoControllerId' ,


    downloadExcel:function(button){

        Ext.Ajax.request({
            url: './fuelEmail/downloadExcelModel',
            success: function(response){
            }
        });


    },

    showFuelExcelWin:function(button) {
        window.open('/fuelimage/excelmodel.xlsx',"_blank");
    },
    closeWindow:function(button){
        this.getView().close();
    },

    addFuelFromExcel:function(button) {

    var form = this.getView().lookupReference('windowForm').getForm();
    if(form.isValid()) {
        form.submit({
           url:'./fuelEmail/addFuelFromExcel',
            waitMsg: '正在提交...',
            success: function(form, action) {
//                Ext.getStore('busInfoStoreId').reload();
                Ext.MessageBox.alert("信息", "提交成功！");
                Ext.getCmp('addFuelExcelWindowId').close();
            },
            failure:function(form, action) {
                Ext.MessageBox.alert("错误", "导入加气信息失败！");
            }
        });
    }else {
        Ext.MessageBox.alert("警告", "信息填写不正确！");
    }
},
downloadEmail:function(button){
    Ext.getBody().mask("正在连接中...","x-mask-loading");

    Ext.Ajax.request({
        timeout: 240000,
        url: './fuelEmail/downloadEmail',
        success: function(response){
            Ext.getBody().unmask();
            if(response.responseText=="true"){
                Ext.Msg.alert("提示","下载成功!");
            }else{
                Ext.Msg.alert("提示","下载失败,请检查网络!");
            }
        }
    });
},
queryEmailInfo:function(button) {

        var form = this.getView();

        var startDate =  Ext.Date.format(new Date(form.lookupReference('emailStartDate').getValue()),'Y-m-d');
        var endDate =  Ext.Date.format(new Date(form.lookupReference('emailEndDate').getValue()),'Y-m-d');
        var queryType =  form.lookupReference('emailSendType').getValue();

        var store = Ext.getStore('emailInfoStoreId');
        store.proxy.url = './fuelEmail/getEmailInfo';
        store.proxy.method = 'post';


        var new_params= { startDate : encodeURI(startDate) , endDate : encodeURI(endDate),queryType:encodeURI(queryType) };

         Ext.apply(store.proxy.extraParams, new_params);



         store.removeAll();
         store.load();


    },
    queryNoDataDay:function(button){

        var form = this.getView();

        var startDate =  Ext.Date.format(new Date(form.lookupReference('emailStartDate').getValue()),'Y-m-d');
        var endDate =  Ext.Date.format(new Date(form.lookupReference('emailEndDate').getValue()),'Y-m-d');

        var win = new Ext.Window({
            title : '无数据日期统计',
            width : '90%' ,
            height : '90%' ,
            resizable : true,
            autoScroll : true,
            modal : true,
            closable : true,
            layout : {
                type : 'vbox',
                pack : 'start',
                align : 'stretch'
            },
            items : [{
                xtype : 'stationNoDataDay'
            }]
        });

        win.show();


        var  stationNoDataDayStore = Ext.getStore('stationNoDataDayStoreId');

        stationNoDataDayStore.proxy.url = './fuelEmail/getStationNoDataDay';
        stationNoDataDayStore.proxy.method = 'post';
        var new_params = {
            startDate:startDate,
            endDate:endDate
        };


        Ext.apply(stationNoDataDayStore.proxy.extraParams, new_params);
        stationNoDataDayStore.removeAll();
        stationNoDataDayStore.load();




    },


    emailProcessingLogWin:function(value){
        if(value == 2){
            return "<span>查看</span>"
        }else{
            return "<span>无</span>"
        }

    },

    procexsingStateValue:function(value){
        if(value==1||value==2){
            return "<span>已解析</span>";
        }else if(value == -1) {
            return  "<span>未发送</span>";
        }else{
            return  "<span>未解析</span>";
        }
    }

    
});