Ext.define('helloext.model.UserAuthManagerMode', {
	//extend: 'helloext.view.module.Base',
	extend: 'Ext.data.Model',
	alias: 'store.userauthmanagermode',
	requires:[
              'helloext.model.UserAuthManagerModeSub'
           ],
    fields: [
	         { name: 'dobUserId', type: 'string' },
	         { name: 'dobPassword', type: 'string' },
	         { name: 'dobAccount', type: 'string' },
	         { name: 'dobRealName', type: 'string' },
	         { name: 'dobSex', type: 'int'},
			 { name: 'dobEmail', type: 'string' },
			 { name: 'dobMobile', type: 'string'},
			 { name: 'dobOfficePhone', type: 'string' },
			 { name: 'dobIdCard', type: 'string'},
			 { name: 'dobErrorCount', type: 'int' },
			 { name: 'dobLastLoginTime', type: 'date' },
			 { name: 'dobLastLoginTimeStr', type: 'string'},
			 { name: 'dobLastLoginIp', type: 'string'},
			 { name: 'dobRemark', type: 'string'},
			 { name: 'orgId', type: 'string'},
			 { name: 'orgName', type: 'string'},
			 { name: 'employeeNum', type: 'string'},
			 { name: 'cardNum', type: 'string'},
			 { name: 'userTypeId', type: 'string'},
			 { name: 'userTypeName', type: 'string'},
			 { name: 'isEnabled', type: 'boolean'},
			// {name:'liHasRoles', type:'string'},
         ]
	});
	
