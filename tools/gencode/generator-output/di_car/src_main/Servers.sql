
INSERT INTO t_menu values('t_servers_list','Servers管理', 'system_manager', null,'/servers/list','1','是');
INSERT INTO t_menu values('t_servers_update','修改Servers', 't_servers_list', null,'/servers/update','0','是');
INSERT INTO t_menu values('t_servers_look','查看Servers', 't_servers_list', null,'/servers/look','0','是');
INSERT INTO t_menu values('t_servers_export','导出Servers', 't_servers_list', null,'/servers/list/export','0','是');
INSERT INTO t_menu values('t_servers_delMulti','批量删除Servers', 't_servers_list', null,'/servers/delMulti','0','是');
INSERT INTO t_menu values('t_servers_delete','删除Servers', 't_servers_list', null,'/servers/delete','0','是');
INSERT INTO t_menu values('t_servers_upload','导入Servers', 't_servers_list', null,'/servers/upload','0','是');
INSERT INTO `t_role_menu` VALUES ('t_servers_list_admin', 'admin', 't_servers_list');
INSERT INTO `t_role_menu` VALUES ('t_servers_update_admin', 'admin', 't_servers_update');
INSERT INTO `t_role_menu` VALUES ('t_servers_look_admin', 'admin', 't_servers_look');
INSERT INTO `t_role_menu` VALUES ('t_servers_export_admin', 'admin', 't_servers_export');
INSERT INTO `t_role_menu` VALUES ('t_servers_delMulti_admin', 'admin', 't_servers_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_servers_delete_admin', 'admin', 't_servers_delete');
INSERT INTO `t_role_menu` VALUES ('t_servers_upload_admin', 'admin', 't_servers_upload');
