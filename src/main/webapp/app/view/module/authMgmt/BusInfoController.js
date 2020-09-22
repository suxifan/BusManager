Ext.define('helloext.view.module.authMgmt.BusInfoController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.businfo',
    requires:[

        'helloext.view.module.authMgmt.BusInfoOper'

    ],
    id:'BusInfoControllerId',
    sayHi:function(){
        alert('hi');
    },
    renderBusLineStatus:function(value){
        if(value == '1'){
            return "<span>启用</span>";
        }else{
            return "<span'>停用</span>";
        }
    },

    addBusInfo:function(button){
        var me = this;
        var  mainApp = me.getView().getViewModel().get('mainApp');
        var maincenter = Ext.getCmp('mainView').down('maincenter');
        var busOper = maincenter.getComponent("BusInfoOperId");
        console.log(busOper);
        if(busOper) {
            if(busOper.title=='新增车辆'){

            }else{
                maincenter.remove(busOper);
                busOper.destroy();
                busOper = null;
            }
        }

        if(!busOper) {

            busOper =Ext.create({
                xtype:'businfooper',
                closable: true,
                viewModel:{
                    data:{
                        mainApp:this,
                        title:'新增'
                    }
                }
            });
            maincenter.setActiveTab(maincenter.add(busOper));
            busOper.on('beforeclose', function () { busOper.destroy(); });

        } else {
            maincenter.setActiveTab(busOper);
        }






        //var busViewModel = me.getView().getViewModel();
    },



    queryBusInfo:function(button) {

        var busNum = button.up('toolbar').down('textfield').getValue();
        var store = Ext.getStore('businfostoreId');
        store.proxy.url = './fuelReport/getBusInfo';
        store.proxy.method = 'post';
        var new_params = { busNum : encodeURI(busNum) };
        Ext.apply(store.proxy.extraParams, new_params);
        store.removeAll();
        store.load();
    },

    updateBusInfo:function(button){
        var me = this;


        var model = me.getView().getSelection();
        //var busViewModel = me.getView().getViewModel();
        if(model.length!=1){
            Ext.Msg.alert('提示', '请选择一条记录进行修改！');
            return ;
        }



        var maincenter = Ext.getCmp('mainView').down('maincenter');



        var busOper = maincenter.getComponent("BusInfoOperId");

        if(busOper) {
            if(busOper.title=='修改车辆'){

            }else{
                maincenter.remove(busOper);
                busOper.destroy();
                busOper = null;
            }
        }


        if(!busOper) {
            var busOper =Ext.create({
                xtype:'businfooper',
                closable: true,
                viewModel:{
                    data:{
                        mainApp:this,
                        selectedBusInfo:model[0],
                        title:'修改'
                    }
                }
            });
            maincenter.setActiveTab(maincenter.add(busOper));
            busOper.on('beforeclose', function () { busOper.destroy(); });
        } else {
            maincenter.setActiveTab(busOper);
        }

    },

    deleteBusInfo:function(button){
        var me = this;
        var view = me.getView();
        //alert(view.getSelectionCount());
        var model = view.getSelection();

        var idArray = [];
        Ext.each(model, function (item) {
            idArray.push(item.get('busInfoId'));
        });

        Ext.Msg.confirm('提示', '确定要删除？', function(btn, text){
            if (btn == 'yes'){
                //alert(model[0].get('dobPassword'));
                Ext.Ajax.request({
                    url:'./fuelReport/deleteBusInfo',
                    method:'POST',
                    params:{
                        busInfoId:idArray//model[0].get('lineId')
                    },
                    success:function(response){
                        var json = Ext.util.JSON.decode(response.responseText); ;
                        if(json.success){
                            Ext.Msg.alert('提示', '删除成功！');
                            me.getView().getStore().reload();
                        }else{
                            Ext.Msg.alert('提示', '删除失败！');
                        }

                    },
                    failure:function(response){
                        Ext.Msg.alert('提示', '删除失败！');
                    }
                });
            }
        });

    }

});