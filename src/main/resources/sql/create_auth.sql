/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2015-6-29 11:47:28                           */
/*==============================================================*/


drop table if exists t_fields;

drop table if exists  t_module_field;

drop table if exists  t_modules;

drop table if exists  t_org_type;

drop table if exists  t_orgs;

drop table if exists  t_role_field;

drop table if exists  t_role_module;

drop table if exists  t_roles;

drop table if exists  t_user_role;

drop table if exists  t_user_type;

drop table if exists  t_users;

drop table if exists  t_bus_line;

/*==============================================================*/
/* Table: t_fields                                              */
/*==============================================================*/
create table t_fields (
   field_id             VARCHAR(32)          not null,
   field_name           VARCHAR(32)          null,
   field_display        VARCHAR(32)          null,
   is_enabled           BOOL                 null,
   constraint PK_T_FIELDS primary key (field_id)
);

comment on table t_fields is
'功能表';

comment on column t_fields.field_id is
'功能编号';

comment on column t_fields.field_name is
'功能名称';

comment on column t_fields.field_display is
'功能显示';

comment on column t_fields.is_enabled is
'是否启用';




/*==============================================================*/
/* Table: t_module_field                                        */
/*==============================================================*/
create table t_module_field (
   module_id            VARCHAR(32)          not null,
   field_id             VARCHAR(32)          not null,
   constraint PK_T_MODULE_FIELD primary key (module_id, field_id)
);

comment on table t_module_field is
't_module_field';

comment on column t_module_field.module_id is
'模块编号';

comment on column t_module_field.field_id is
'功能编号';

/*==============================================================*/
/* Table: t_modules                                             */
/*==============================================================*/
create table t_modules (
   module_id            VARCHAR(32)          not null,
   module_name          VARCHAR(32)          null,
   module_url           VARCHAR(64)          null,
   is_leaf              BOOL                 null,
   is_expanded          BOOL                 null,
   display_index        INT4                 null,
   is_display           BOOL                 null,
   module_desc          VARCHAR(128)         null,
   parent_id            VARCHAR(32)          null,
   sort_no        INT4                 null,
   constraint PK_T_MODULES primary key (module_id)
);

comment on table t_modules is
'模块表';

comment on column t_modules.module_id is
'模块编号';

comment on column t_modules.module_name is
'模块名称';

comment on column t_modules.module_url is
'模块地址';

comment on column t_modules.is_leaf is
'是否为叶子';

comment on column t_modules.is_expanded is
'是否展开';

comment on column t_modules.display_index is
'显示索引';

comment on column t_modules.is_display is
'是否显示';

comment on column t_modules.module_desc is
'模块描述';

comment on column t_modules.parent_id is
'上级模块';

comment on column t_modules.sort_no is
'模块序号';

/*==============================================================*/
/* Table: t_org_type                                            */
/*==============================================================*/
create table t_org_type (
   org_type_id          VARCHAR(32)          not null,
   org_type_name        VARCHAR(32)          null,
   constraint PK_T_ORG_TYPE primary key (org_type_id)
);

comment on table t_org_type is
'组织类型表';

comment on column t_org_type.org_type_id is
'组织类型编号';

comment on column t_org_type.org_type_name is
'组织类型名称';

/*==============================================================*/
/* Table: t_orgs                                                */
/*==============================================================*/
create table t_orgs (
   org_id               VARCHAR(32)          not null,
   org_name             VARCHAR(32)          null,
   org_type_id          VARCHAR(32)          null,
   org_parent_id        VARCHAR(32)          null,
   org_desc             VARCHAR(128)         null,
   is_enabled           BOOL                 null,
   constraint PK_T_ORGS primary key (org_id)
);

comment on table t_orgs is
'组织表';

comment on column t_orgs.org_id is
'机构编号';

comment on column t_orgs.org_name is
'机构名称';

comment on column t_orgs.org_type_id is
'组织类型';

comment on column t_orgs.org_parent_id is
'上级机构';

comment on column t_orgs.org_desc is
'机构描述';

comment on column t_orgs.is_enabled is
'是否启用';

/*==============================================================*/
/* Table: t_role_field                                          */
/*==============================================================*/
create table t_role_field (
   field_id             VARCHAR(32)          not null,
   role_id              VARCHAR(32)          not null,
   constraint PK_T_ROLE_FIELD primary key (field_id, role_id)
);

comment on table t_role_field is
't_role_field';

comment on column t_role_field.field_id is
'功能编号';

comment on column t_role_field.role_id is
'角色编号';

/*==============================================================*/
/* Table: t_role_module                                         */
/*==============================================================*/
create table t_role_module (
   module_id            VARCHAR(32)          not null,
   role_id              VARCHAR(32)          not null,
   constraint PK_T_ROLE_MODULE primary key (module_id, role_id)
);

comment on table t_role_module is
't_role_module';

comment on column t_role_module.module_id is
'模块编号';

comment on column t_role_module.role_id is
'角色编号';

/*==============================================================*/
/* Table: t_roles                                               */
/*==============================================================*/
create table t_roles (
   role_id              VARCHAR(32)          not null,
   role_name            VARCHAR(32)          null,
   role_desc            VARCHAR(32)          null,
   constraint PK_T_ROLES primary key (role_id)
);

comment on table t_roles is
'角色表';

comment on column t_roles.role_id is
'角色编号';

comment on column t_roles.role_name is
'角色名称';

comment on column t_roles.role_desc is
'角色描述';

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role (
   user_id              VARCHAR(32)          not null,
   role_id              VARCHAR(32)          not null,
   constraint PK_T_USER_ROLE primary key (user_id, role_id)
);

comment on table t_user_role is
't_user_role';

comment on column t_user_role.user_id is
'用户编号';

comment on column t_user_role.role_id is
'角色编号';

/*==============================================================*/
/* Table: t_user_type                                           */
/*==============================================================*/
create table t_user_type (
   user_type_id         VARCHAR(32)          not null,
   user_type_name       VARCHAR(32)          null,
   constraint PK_T_USER_TYPE primary key (user_type_id)
   
);

comment on table t_user_type is
'用户类型表';

comment on column t_user_type.user_type_id is
'用户类型编号';

comment on column t_user_type.user_type_name is
'用户类型名称';

/*==============================================================*/
/* Table: t_users                                               */
/*==============================================================*/
create table t_users (
   user_id              VARCHAR(32)          not null,
   account              VARCHAR(32)          null,
   password             VARCHAR(32)          null,
   employee_num         VARCHAR(32)          null,
   card_num             VARCHAR(32)          null,
   user_type_id         VARCHAR(32)          null,
   real_name            VARCHAR(32)          null,
   sex                  INT2                 null,
   email                VARCHAR(32)          null,
   office_phone         VARCHAR(32)          null,
   mobile               VARCHAR(32)          null,
   id_card              VARCHAR(32)          null,
   error_count          INT4                 null,
   last_login_time      DATE                 null,
   last_login_ip        VARCHAR(32)          null,
   remark               VARCHAR(128)         null,
   is_enabled           BOOL                 null,
   org_id               VARCHAR(32)          null,
   constraint PK_T_USERS primary key (user_id)
);

comment on table t_users is
'用户表';

comment on column t_users.user_id is
'用户编号';

comment on column t_users.account is
'账户';

comment on column t_users.password is
'密码';

comment on column t_users.employee_num is
'员工号';

comment on column t_users.card_num is
'卡号';

comment on column t_users.user_type_id is
'用户类型';

comment on column t_users.real_name is
'真实姓名';

comment on column t_users.sex is
'性别';

comment on column t_users.email is
'电子邮箱';

comment on column t_users.office_phone is
'办公室电话';

comment on column t_users.mobile is
'手机';

comment on column t_users.id_card is
'身份证号';

comment on column t_users.error_count is
'错误登录次数';

comment on column t_users.last_login_time is
'上次登录时间';

comment on column t_users.last_login_ip is
'上次登录IP';

comment on column t_users.remark is
'备注';

comment on column t_users.is_enabled is
'是否启用';

comment on column t_users.org_id is
'所属机构';

create table t_bus_line (
line_id varchar(32) not null,
line_name varchar(32) null,
line_alias varchar(32) null,
line_org_id varchar(32) null,
line_status int4 null,
created timestamp(6) null,
constraint PK_T_BUS_LINE primary key (line_id)
);
comment on table t_bus_line is '线路';
comment on column t_bus_line.line_id is '线路标识';
comment on column t_bus_line.line_name is '线路名称';
comment on column t_bus_line.line_alias is '线路别名';
comment on column t_bus_line.line_org_id is '线路所属机构';
comment on column t_bus_line.line_status is '线路状态';
comment on column t_bus_line.created is '创建时间';
