-- ----------------------------
-- Records of t_fields
-- ----------------------------
INSERT INTO "public"."t_fields" VALUES ('auth_bus_line_module_add', '增加', null, 't');
INSERT INTO "public"."t_fields" VALUES ('auth_bus_line_module_delete', '删除', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_bus_line_module_update', '修改', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_org_module_add', '增加', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_org_module_delete', '删除', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_org_module_update', '修改', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_role_module_add', '增加', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_role_module_delete', '删除', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_role_module_update', '修改', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_user_module_add', '增加', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_user_module_delete', '删除', '', 't');
INSERT INTO "public"."t_fields" VALUES ('auth_user_module_update', '修改', '', 't');
INSERT INTO "public"."t_fields" VALUES ('gas_gun_mgmt_module_add', '增加', null, 't');
INSERT INTO "public"."t_fields" VALUES ('gas_gun_mgmt_module_delete', '删除', null, 't');
INSERT INTO "public"."t_fields" VALUES ('gas_gun_mgmt_module_update', '修改', null, 't');
INSERT INTO "public"."t_fields" VALUES ('gas_station_info_module_add', '增加', null, 't');
INSERT INTO "public"."t_fields" VALUES ('gas_station_info_module_delete', '删除', null, 't');
INSERT INTO "public"."t_fields" VALUES ('gas_station_info_module_update', '修改', null, 't');
INSERT INTO "public"."t_fields" VALUES ('param_setting_update', '修改', null, 't');
INSERT INTO "public"."t_fields" VALUES ('device_mgmt_module_add', '增加', null, 't');
INSERT INTO "public"."t_fields" VALUES ('device_mgmt_module_update', '修改', null, 't');
INSERT INTO "public"."t_fields" VALUES ('device_mgmt_module_delete', '删除', null, 't');
-- ----------------------------
-- Records of t_module_field
-- ----------------------------
INSERT INTO "public"."t_module_field" VALUES ('auth_bus_line_module', 'auth_bus_line_module_add');
INSERT INTO "public"."t_module_field" VALUES ('auth_bus_line_module', 'auth_bus_line_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('auth_bus_line_module', 'auth_bus_line_module_update');
INSERT INTO "public"."t_module_field" VALUES ('auth_org_module', 'auth_org_module_add');
INSERT INTO "public"."t_module_field" VALUES ('auth_org_module', 'auth_org_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('auth_org_module', 'auth_org_module_update');
INSERT INTO "public"."t_module_field" VALUES ('auth_role_module', 'auth_role_module_add');
INSERT INTO "public"."t_module_field" VALUES ('auth_role_module', 'auth_role_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('auth_role_module', 'auth_role_module_update');
INSERT INTO "public"."t_module_field" VALUES ('auth_user_module', 'auth_user_module_add');
INSERT INTO "public"."t_module_field" VALUES ('auth_user_module', 'auth_user_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('auth_user_module', 'auth_user_module_update');
INSERT INTO "public"."t_module_field" VALUES ('gas_gun_mgmt_module', 'gas_gun_mgmt_module_add');
INSERT INTO "public"."t_module_field" VALUES ('gas_gun_mgmt_module', 'gas_gun_mgmt_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('gas_gun_mgmt_module', 'gas_gun_mgmt_module_update');
INSERT INTO "public"."t_module_field" VALUES ('gas_station_info_module', 'gas_station_info_module_add');
INSERT INTO "public"."t_module_field" VALUES ('gas_station_info_module', 'gas_station_info_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('gas_station_info_module', 'gas_station_info_module_update');
INSERT INTO "public"."t_module_field" VALUES ('sys_device_mgmt_module', 'device_mgmt_module_add');
INSERT INTO "public"."t_module_field" VALUES ('sys_device_mgmt_module', 'device_mgmt_module_update');
INSERT INTO "public"."t_module_field" VALUES ('sys_device_mgmt_module', 'device_mgmt_module_delete');
INSERT INTO "public"."t_module_field" VALUES ('sys_param_set_module', 'param_setting_update');



-- ----------------------------
-- Records of t_modules
-- ----------------------------
INSERT INTO "public"."t_modules" VALUES ('auth_bus_line_module', '线路管理', '', 't', 't', null, 't', '', 'auth_module', '4');
INSERT INTO "public"."t_modules" VALUES ('auth_module', '权限管理', '', 't', 't', null, 't', '', '', '0');
INSERT INTO "public"."t_modules" VALUES ('auth_org_module', '组织机构管理', '', 't', 't', null, 't', '', 'auth_module', '1');
INSERT INTO "public"."t_modules" VALUES ('auth_role_module', '角色管理', '', 't', 't', null, 't', '', 'auth_module', '3');
INSERT INTO "public"."t_modules" VALUES ('auth_user_module', '用户管理', '', 't', 't', null, 't', '', 'auth_module', '2');
INSERT INTO "public"."t_modules" VALUES ('gas_gun_mgmt_module', '气枪管理', null, 't', 't', null, 't', null, 'gas_station_module', '2');
INSERT INTO "public"."t_modules" VALUES ('gas_station_info_module', '负责人管理', null, 't', 't', null, 't', null, 'gas_station_module', '1');
INSERT INTO "public"."t_modules" VALUES ('gas_station_module', '加气站管理', null, 't', 't', null, 't', null, null, '3');
INSERT INTO "public"."t_modules" VALUES ('report_addfuel_manytimes_module', '多次加气统计', null, 't', 't', null, 't', null, 'report_module', '7');
INSERT INTO "public"."t_modules" VALUES ('report_bus_fuel_month_module', '分公司单车油气月终汇总', null, 't', 't', null, 't', null, 'report_module', '4');
INSERT INTO "public"."t_modules" VALUES ('report_fleet_fuel_month_module', '分公司车队油气月终汇总', null, 't', 't', null, 't', null, 'report_module', '3');
INSERT INTO "public"."t_modules" VALUES ('report_fuel_month_module', '天然气材料月终汇总', null, 't', 't', null, 't', null, 'report_module', '2');
INSERT INTO "public"."t_modules" VALUES ('report_group_fuel_month_module', '集团油气月终汇总', null, 't', 't', null, 't', null, 'report_module', '1');
INSERT INTO "public"."t_modules" VALUES ('report_modify_addfueldata_module', '加气数据修改明细表', null, 't', 't', null, 't', null, 'report_module', '6');
INSERT INTO "public"."t_modules" VALUES ('report_module', '报表管理', null, 't', 't', null, 't', null, null, '1');
INSERT INTO "public"."t_modules" VALUES ('report_subGroup_fuel_day_module', '分公司油气明细日表', null, 't', 't', null, 't', null, 'report_module', '5');
INSERT INTO "public"."t_modules" VALUES ('sys_device_mgmt_module', '设备管理', null, 't', 't', null, 't', null, 'sys_mgmt_module', '0');
INSERT INTO "public"."t_modules" VALUES ('sys_mgmt_module', '系统管理', null, 't', 't', null, 't', null, null, '2');
INSERT INTO "public"."t_modules" VALUES ('sys_param_set_module', '参数设置', null, 't', 't', null, 't', null, 'sys_mgmt_module', '1');
INSERT INTO "public"."t_modules" VALUES ('report_modify_check_module', '加气修改记录审核', null, 't', 't', null, 't', null, 'report_module', '8');

INSERT INTO "public"."t_modules" VALUES ('gas_class_stat_module', '加气站班次日汇总', null, 't', 't', null, 't', null, 'report_module', '12');
INSERT INTO "public"."t_modules" VALUES ('report_new_bus_gas_module', '新车加气明细表', null, 't', 't', null, 't', null, 'report_module', '11');


-- ----------------------------
-- Records of t_org_type
-- ----------------------------
INSERT INTO "public"."t_org_type" VALUES ('1', '普通组织');
INSERT INTO "public"."t_org_type" VALUES ('2', '合作单位');
INSERT INTO "public"."t_org_type" VALUES ('3', '加气站');
INSERT INTO "public"."t_org_type" VALUES ('4', '加油站');
INSERT INTO "public"."t_org_type" VALUES ('5', '公交分公司');
INSERT INTO "public"."t_org_type" VALUES ('6', '车队');



-- ----------------------------
-- Records of t_orgs
-- ----------------------------
INSERT INTO "public"."t_orgs" VALUES ('1', '公交集团', '1', '', '总公司', 't');



-- ----------------------------
-- Records of t_role_field
-- ----------------------------
INSERT INTO "public"."t_role_field" VALUES ('auth_bus_line_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_bus_line_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_bus_line_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_org_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_org_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_org_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_role_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_role_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_role_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_user_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_user_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('auth_user_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_gun_mgmt_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_gun_mgmt_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_gun_mgmt_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_station_info_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_station_info_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('gas_station_info_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('device_mgmt_module_add', '1');
INSERT INTO "public"."t_role_field" VALUES ('device_mgmt_module_update', '1');
INSERT INTO "public"."t_role_field" VALUES ('device_mgmt_module_delete', '1');
INSERT INTO "public"."t_role_field" VALUES ('param_setting_update', '1');





-- ----------------------------
-- Records of t_role_module
-- ----------------------------

INSERT INTO "public"."t_role_module" VALUES ('auth_bus_line_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('auth_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('auth_org_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('auth_role_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('auth_user_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('gas_gun_mgmt_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('gas_station_info_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('gas_station_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_addfuel_manytimes_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_bus_fuel_month_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_fleet_fuel_month_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_fuel_month_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_group_fuel_month_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_modify_addfueldata_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_subGroup_fuel_day_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('sys_device_mgmt_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('sys_mgmt_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('sys_param_set_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_modify_check_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('gas_class_stat_module', '1');
INSERT INTO "public"."t_role_module" VALUES ('report_new_bus_gas_module', '1');


-- ----------------------------
-- Records of t_roles
-- ----------------------------
INSERT INTO "public"."t_roles" VALUES ('1', '超级管理员', '拥有所有管理权限');
INSERT INTO "public"."t_roles" VALUES ('2', '一般管理员', '拥有部分管理权限');
INSERT INTO "public"."t_roles" VALUES ('3', '一般用户', '拥有访问权限，不拥有管理权限');
INSERT INTO "public"."t_roles" VALUES ('4', '访客', '只具有浏览权限');





-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO "public"."t_user_role" VALUES ('104354a812f14c079385', '1');



-- ----------------------------
-- Records of t_user_type
-- ----------------------------
INSERT INTO "public"."t_user_type" VALUES ('1', '员工');
INSERT INTO "public"."t_user_type" VALUES ('10', '总监');
INSERT INTO "public"."t_user_type" VALUES ('11', '加气司机');
INSERT INTO "public"."t_user_type" VALUES ('2', '加气员');
INSERT INTO "public"."t_user_type" VALUES ('3', '司机');
INSERT INTO "public"."t_user_type" VALUES ('4', '经理');
INSERT INTO "public"."t_user_type" VALUES ('5', '总经理');
INSERT INTO "public"."t_user_type" VALUES ('6', '会计');
INSERT INTO "public"."t_user_type" VALUES ('7', '行政');
INSERT INTO "public"."t_user_type" VALUES ('8', '人事');
INSERT INTO "public"."t_user_type" VALUES ('9', '技术支持');



-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO "public"."t_users" VALUES ('104354a812f14c079385', 'admin', 'c3284d0f94606de1fd2af172aba15bf3', 'e1', 'card1', '9', '网管', '1', 'admin@163.com', '53435534', '153884938433', '43455655534344', '0', '2015-07-27', '', null, 't', '1');