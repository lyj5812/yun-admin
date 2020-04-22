/*
 Navicat Premium Data Transfer

 Source Server         : yun-mysql-pro
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 114.67.71.138:3306
 Source Schema         : yun-admin

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 25/11/2019 17:47:59
*/
-- 创建数据库
create database `yun-admin` default character set utf8 collate utf8_general_ci;
use yun-admin;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端安全码',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许请求范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端可以使用的授权类型',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'access_token有效时间',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'refresh_token的有效时间值(单位:秒)',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户端详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('admin', NULL, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'admin', 'authorization_code,password,refresh_token,client_credentials', 'https://www.baidu.com/', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('wx_mini_app', NULL, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'app', 'authorization_code,wx_mini_app,refresh_token,client_credentials', 'https://www.baidu.com/', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-default', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '默认 skin-default、蓝色 skin-blue、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '初始化密码 123456');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '云科技', 0, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-05-30 16:58:32', '');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:02:27', '');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '湮灭', '15777777777', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-05-29 21:23:32', '');
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-05-07 17:47:16', '');
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '湮灭', '15888888888', '1264415695@qq.com', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '正常', '0', 'role_status', '', 'primary', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-04-29 23:24:13', '角色正常');
INSERT INTO `sys_dict_data` VALUES (5, 2, '停用', '1', 'role_status', '', 'danger', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-04-29 23:24:39', '角色停用');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'dict_status', '', 'primary', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'dict_status', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (24, 8, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (27, 1, '正常', '0', 'post_status', '', '', 'Y', '0', '0', '1', '2019-06-10 11:29:14', '1', '2019-06-10 11:29:14', '');
INSERT INTO `sys_dict_data` VALUES (28, 2, '停用', '1', 'post_status', '', '', 'Y', '0', '0', '1', '2019-06-10 11:30:21', '1', '2019-06-10 11:30:21', '');
INSERT INTO `sys_dict_data` VALUES (29, 1, 'test', '33', 'sys_notice_status', '', '', 'Y', '0', '1', '1', '2019-06-10 15:10:17', '1', '2019-06-10 15:10:17', 'ces');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', '1', '2018-03-16 11:33:00', '1', '2019-05-02 18:13:20', '用户性别列表', '0');
INSERT INTO `sys_dict_type` VALUES (2, '角色状态', 'role_status', '0', '1', '2018-03-16 11:33:00', '1', '2019-04-29 23:22:33', '角色状态列表', '0');
INSERT INTO `sys_dict_type` VALUES (3, '字典状态', 'dict_status', '0', '1', '2018-03-16 11:33:00', '1', '2019-04-29 15:35:01', '字典状态列表', '0');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '任务状态列表', '0');
INSERT INTO `sys_dict_type` VALUES (5, '系统是否', 'sys_yes_no', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统是否列表', '0');
INSERT INTO `sys_dict_type` VALUES (6, '通知类型', 'sys_notice_type', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '通知类型列表', '0');
INSERT INTO `sys_dict_type` VALUES (7, '通知状态', 'sys_notice_status', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-10 15:14:05', '通知状态列表', '0');
INSERT INTO `sys_dict_type` VALUES (8, '操作类型', 'sys_oper_type', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '操作类型列表', '0');
INSERT INTO `sys_dict_type` VALUES (9, '系统状态', 'sys_common_status', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-10 15:06:32', '登录状态列表', '0');
INSERT INTO `sys_dict_type` VALUES (10, '岗位状态', 'post_status', '0', '1', '2019-06-10 11:28:40', '1', '2019-06-10 11:28:40', '岗位状态', '0');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2018-10-30 10:56:32');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（0目录 1菜单 2按钮）',
  `hidden` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'VUE页面',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1067 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '/system', '0', '0', '', 'fa fa-gear', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '系统管理目录', 'Layout', '0');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '/monitor', '0', '1', '', 'fa fa-video-camera', '1', '2018-03-16 11:33:00', '1', '2019-11-18 13:41:37', '系统监控目录', 'Layout', '0');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '/tool', '0', '0', '', 'fa fa-bars', '1', '2018-03-16 11:33:00', '1', '2019-11-18 14:25:09', '系统工具目录', 'Layout', '0');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1065, 1, '/auth/user', '0', '0', 'system:user:view', 'fa fa-user', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:45:41', '用户管理菜单', '/admin/user/index', '0');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1065, 2, '/auth/role', '0', '0', 'system:role:view', 'fa fa-user-secret', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:47:36', '角色管理菜单', '/admin/role/index', '0');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1065, 3, '/auth/menu', '0', '0', 'system:menu:view', 'fa fa-tasks', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '菜单管理菜单', '/admin/menu/index', '0');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1065, 4, '/auth/dept', '0', '0', 'system:dept:view', 'fa fa-sitemap', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:55:31', '部门管理菜单', '/admin/dept/index', '0');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1065, 5, '/auth/post', '0', '0', 'system:post:view', 'fa fa-vcard-o', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:56:00', '岗位管理菜单', '/admin/post/index', '0');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '0', '0', 'system:dict:view', 'fa fa-suitcase', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:58:36', '字典管理菜单', '/admin/dict/index', '0');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '0', '1', 'system:config:view', 'fa fa-cubes', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:44:29', '参数设置菜单', '/permission/page', '0');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '0', '1', 'system:notice:view', 'fa fa-newspaper-o', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:45:02', '通知公告菜单', '/permission/page', '0');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '0', '1', '', 'fa fa-book', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:45:13', '日志管理菜单', '/nested/menu1/index', '0');
INSERT INTO `sys_menu` VALUES (109, '数据监控', 2, 1, 'http://localhost:8011/druid/index.html', '0', '1', 'monitor:data:view', 'brightness_auto', '1', '2018-03-16 11:33:00', '1', '2019-11-14 14:35:00', '在线用户菜单', '/permission/page', '0');
INSERT INTO `sys_menu` VALUES (110, '接口文档', 2, 2, '/monitor/interface', '0', '0', 'monitor:job:view', 'library_books', '1', '2018-03-16 11:33:00', '1', '2019-11-14 11:57:47', '定时任务菜单', 'http://localhost:8050/doc.html', '1');
INSERT INTO `sys_menu` VALUES (111, '注册中心', 2, 3, 'http://47.52.79.108:8848/nacos/index.html#/login', '0', '1', 'monitor:register:view', 'healing', '1', '2018-03-16 11:33:00', '1', '2019-11-14 14:35:12', '数据监控菜单', '/permission/page', '0');
INSERT INTO `sys_menu` VALUES (113, '代码生成', 3, 2, '/tool/gen', '0', '0', 'tool:gen:view', 'fa fa-sticky-note', '1', '2018-03-16 11:33:00', '1', '2019-11-14 12:00:01', '代码生成菜单', '/tool/gen/index', '0');
INSERT INTO `sys_menu` VALUES (114, '系统接口', 3, 3, '/tool/interface', '0', '0', 'tool:swagger:view', 'fa fa-indent', '1', '2018-03-16 11:33:00', '1', '2019-11-14 14:35:57', '系统接口菜单', 'http://localhost:8050/doc.html', '0');
INSERT INTO `sys_menu` VALUES (1000, '用户搜索', 100, 1, '#', '1', '0', 'system:user:search', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 11:33:08', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '1', '0', 'system:user:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '1', '0', 'system:user:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '1', '0', 'system:user:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 16:54:56', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '1', '0', 'system:user:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1005, '重置密码', 100, 5, '#', '1', '0', 'system:user:resetPwd', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1006, '角色搜索', 101, 1, '#', '1', '0', 'system:role:search', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 11:44:16', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1007, '角色新增', 101, 2, '#', '1', '0', 'system:role:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1008, '角色修改', 101, 3, '#', '1', '0', 'system:role:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1009, '角色删除', 101, 4, '#', '1', '0', 'system:role:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 16:55:13', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1010, '角色导出', 101, 4, '#', '1', '0', 'system:role:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1011, '菜单搜索', 102, 1, '#', '1', '0', 'system:menu:search', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 11:46:29', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1012, '菜单新增', 102, 2, '#', '1', '0', 'system:menu:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1013, '菜单修改', 102, 3, '#', '1', '0', 'system:menu:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1014, '菜单删除', 102, 4, '#', '1', '0', 'system:menu:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 16:55:33', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1015, '部门查询', 103, 1, '#', '1', '0', 'system:dept:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1016, '部门新增', 103, 2, '#', '1', '0', 'system:dept:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1017, '部门修改', 103, 3, '#', '1', '0', 'system:dept:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1018, '部门删除', 103, 4, '#', '1', '0', 'system:dept:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 16:55:52', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1019, '岗位搜索', 104, 1, '#', '1', '0', 'system:post:search', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:26:48', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1020, '岗位新增', 104, 2, '#', '1', '0', 'system:post:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1021, '岗位修改', 104, 3, '#', '1', '0', 'system:post:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1022, '岗位删除', 104, 4, '#', '1', '0', 'system:post:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 16:59:54', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1023, '岗位导出', 104, 4, '#', '1', '0', 'system:post:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1024, '字典搜索', 105, 1, '#', '1', '0', 'system:dictType:search', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:21:11', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1025, '字典新增', 105, 2, '#', '1', '0', 'system:dictType:add', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:22:10', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1026, '字典修改', 105, 3, '#', '1', '0', 'system:dictType:edit', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:22:19', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1027, '字典删除', 105, 4, '#', '1', '0', 'system:dictType:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:22:29', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1028, '字典导出', 105, 4, '#', '1', '0', 'system:dictType:export', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-11 13:22:41', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1029, '参数查询', 106, 1, '#', '1', '0', 'system:config:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1030, '参数新增', 106, 2, '#', '1', '0', 'system:config:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1031, '参数修改', 106, 3, '#', '1', '0', 'system:config:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1032, '参数删除', 106, 4, '#', '1', '0', 'system:config:del', '#', '1', '2018-03-16 11:33:00', '1', '2019-06-10 17:00:49', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1033, '参数导出', 106, 4, '#', '1', '0', 'system:config:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1034, '公告查询', 107, 1, '#', '1', '0', 'system:notice:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1035, '公告新增', 107, 2, '#', '1', '0', 'system:notice:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1036, '公告修改', 107, 3, '#', '1', '0', 'system:notice:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1037, '公告删除', 107, 4, '#', '1', '0', 'system:notice:remove', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1038, '操作查询', 500, 1, '#', '1', '0', 'monitor:operlog:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1039, '操作删除', 500, 2, '#', '1', '0', 'monitor:operlog:remove', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1040, '详细信息', 500, 3, '#', '1', '0', 'monitor:operlog:detail', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '1', '0', 'monitor:operlog:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '1', '0', 'monitor:logininfor:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '1', '0', 'monitor:logininfor:remove', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 2, '#', '1', '0', 'monitor:logininfor:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1045, '在线查询', 109, 1, '#', '1', '0', 'monitor:online:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1046, '批量强退', 109, 2, '#', '1', '0', 'monitor:online:batchForceLogout', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1047, '单条强退', 109, 3, '#', '1', '0', 'monitor:online:forceLogout', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1048, '任务查询', 110, 1, '#', '1', '0', 'monitor:job:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1049, '任务新增', 110, 2, '#', '1', '0', 'monitor:job:add', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1050, '任务修改', 110, 3, '#', '1', '0', 'monitor:job:edit', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1051, '任务删除', 110, 4, '#', '1', '0', 'monitor:job:remove', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1052, '状态修改', 110, 5, '#', '1', '0', 'monitor:job:changeStatus', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1053, '任务导出', 110, 5, '#', '1', '0', 'monitor:job:export', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '1');
INSERT INTO `sys_menu` VALUES (1054, '生成查询', 113, 1, '#', '1', '0', 'tool:gen:list', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1055, '生成代码', 113, 2, '#', '1', '0', 'tool:gen:code', '#', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1056, '测试666', 2, 1, '/svgIcons/index', '0', '0', '', 'account_balance', '1', '2019-04-24 15:38:27', '1', NULL, '', '/svg-icons/index', '1');
INSERT INTO `sys_menu` VALUES (1057, '字典项新增', 105, 0, '#', '1', '0', 'system:dictData:add', '#', '1', '2019-06-11 13:32:08', '1', '2019-06-11 13:32:08', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1058, '字典项修改', 105, 0, '#', '1', '0', 'system:dictData:edit', '#', '1', '2019-06-11 13:54:18', '1', '2019-06-11 13:54:18', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1059, '字典项删除', 105, 0, '#', '1', '0', 'system:dictData:del', '#', '1', '2019-06-11 13:55:45', '1', '2019-06-11 13:55:45', '', NULL, '0');
INSERT INTO `sys_menu` VALUES (1060, '第二层', 2, 1, '/system/menu', '0', '0', '', 'android', '1', '2019-06-12 17:10:53', '1', '2019-06-12 17:10:53', '', '/system/menu', '1');
INSERT INTO `sys_menu` VALUES (1061, '第三层', 1060, 1, '/system/menu', '0', '0', '', 'account_balance', '1', '2019-06-12 17:11:32', '1', '2019-06-12 17:11:32', '', '/system/menu', '1');
INSERT INTO `sys_menu` VALUES (1062, '第四层', 1061, 1, '/system/menu', '0', '0', '', 'account_box', '1', '2019-06-12 17:12:03', '1', '2019-06-12 17:12:03', '', '/system/menu', '1');
INSERT INTO `sys_menu` VALUES (1063, '第五层', 1062, 1, '/system/menu', '0', '0', '', 'fa-angellist', '1', '2019-06-12 17:25:56', '1', '2019-06-12 17:25:56', '', '/system/menu', '1');
INSERT INTO `sys_menu` VALUES (1064, '第四层-2', 1061, 1, '/system/menu', '0', '0', '', 'accessible', '1', '2019-06-13 13:41:06', '1', '2019-06-13 13:41:06', '', '/system/menu', '1');
INSERT INTO `sys_menu` VALUES (1065, '权限管理', 0, 1, '/auth', '0', '0', '', 'people', '1', '2019-07-02 21:22:49', '1', '2019-07-02 21:22:49', '', 'Layout', '0');
INSERT INTO `sys_menu` VALUES (1066, '菜单管理二', 1065, 2, '/auth/menu2', '0', '0', '', 'assignment', '1', '2019-11-18 14:14:33', '1', '2019-11-18 14:14:33', '', '/admin/menu2/index', '0');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '定时任务', 2, 'com.ruoyi.web.controller.monitor.SysJobController.changeStatus()', 1, 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2018-10-30 11:05:02');
INSERT INTO `sys_oper_log` VALUES (101, '定时任务', 5, 'com.ruoyi.web.controller.monitor.SysJobController.export()', 1, 'admin', '研发部门', '/monitor/job/export', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"\" ],\r\n  \"methodName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ]\r\n}', 0, NULL, '2018-10-30 11:06:57');
INSERT INTO `sys_oper_log` VALUES (102, '定时任务', 5, 'com.ruoyi.web.controller.monitor.SysJobController.export()', 1, 'admin', '研发部门', '/monitor/job/export', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"\" ],\r\n  \"methodName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ]\r\n}', 0, NULL, '2018-10-30 11:07:12');
INSERT INTO `sys_oper_log` VALUES (103, '定时任务', 5, 'com.ruoyi.web.controller.monitor.SysJobController.export()', 1, 'admin', '研发部门', '/monitor/job/export', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"\" ],\r\n  \"methodName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ]\r\n}', 0, NULL, '2018-10-30 11:07:20');
INSERT INTO `sys_oper_log` VALUES (104, '调度日志', 9, 'com.ruoyi.web.controller.monitor.SysJobLogController.clean()', 1, 'admin', '研发部门', '/monitor/jobLog/clean', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2018-10-30 11:08:32');
INSERT INTO `sys_oper_log` VALUES (105, '用户管理', 5, 'com.ruoyi.web.controller.system.SysUserController.export()', 1, 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', 0, NULL, '2018-10-30 11:08:50');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', '0', '1', '2018-03-16 11:33:00', '1', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-10 14:37:29', '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-10 20:41:55', '');
INSERT INTO `sys_post` VALUES (5, '6666', '天帝', 2, '0', '1', '1', '2019-06-10 13:44:04', '1', '2019-06-10 13:44:12', '66');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '1', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-06-24 23:26:32', '管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', '1', '2018-03-16 11:33:00', '1', '2019-11-25 03:23:00', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 0);
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 108);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 111);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (1, 1017);
INSERT INTO `sys_role_menu` VALUES (1, 1018);
INSERT INTO `sys_role_menu` VALUES (1, 1019);
INSERT INTO `sys_role_menu` VALUES (1, 1020);
INSERT INTO `sys_role_menu` VALUES (1, 1021);
INSERT INTO `sys_role_menu` VALUES (1, 1022);
INSERT INTO `sys_role_menu` VALUES (1, 1023);
INSERT INTO `sys_role_menu` VALUES (1, 1024);
INSERT INTO `sys_role_menu` VALUES (1, 1025);
INSERT INTO `sys_role_menu` VALUES (1, 1026);
INSERT INTO `sys_role_menu` VALUES (1, 1027);
INSERT INTO `sys_role_menu` VALUES (1, 1028);
INSERT INTO `sys_role_menu` VALUES (1, 1029);
INSERT INTO `sys_role_menu` VALUES (1, 1030);
INSERT INTO `sys_role_menu` VALUES (1, 1031);
INSERT INTO `sys_role_menu` VALUES (1, 1032);
INSERT INTO `sys_role_menu` VALUES (1, 1033);
INSERT INTO `sys_role_menu` VALUES (1, 1034);
INSERT INTO `sys_role_menu` VALUES (1, 1035);
INSERT INTO `sys_role_menu` VALUES (1, 1036);
INSERT INTO `sys_role_menu` VALUES (1, 1037);
INSERT INTO `sys_role_menu` VALUES (1, 1045);
INSERT INTO `sys_role_menu` VALUES (1, 1046);
INSERT INTO `sys_role_menu` VALUES (1, 1047);
INSERT INTO `sys_role_menu` VALUES (1, 1048);
INSERT INTO `sys_role_menu` VALUES (1, 1049);
INSERT INTO `sys_role_menu` VALUES (1, 1050);
INSERT INTO `sys_role_menu` VALUES (1, 1051);
INSERT INTO `sys_role_menu` VALUES (1, 1052);
INSERT INTO `sys_role_menu` VALUES (1, 1053);
INSERT INTO `sys_role_menu` VALUES (1, 1054);
INSERT INTO `sys_role_menu` VALUES (1, 1055);
INSERT INTO `sys_role_menu` VALUES (1, 1057);
INSERT INTO `sys_role_menu` VALUES (1, 1058);
INSERT INTO `sys_role_menu` VALUES (1, 1059);
INSERT INTO `sys_role_menu` VALUES (1, 1060);
INSERT INTO `sys_role_menu` VALUES (1, 1061);
INSERT INTO `sys_role_menu` VALUES (1, 1062);
INSERT INTO `sys_role_menu` VALUES (1, 1063);
INSERT INTO `sys_role_menu` VALUES (1, 1064);
INSERT INTO `sys_role_menu` VALUES (2, 0);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1065);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 102);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 1000);
INSERT INTO `sys_role_menu` VALUES (3, 1001);
INSERT INTO `sys_role_menu` VALUES (3, 1002);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);
INSERT INTO `sys_role_menu` VALUES (3, 1011);
INSERT INTO `sys_role_menu` VALUES (3, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1013);
INSERT INTO `sys_role_menu` VALUES (3, 1014);
INSERT INTO `sys_role_menu` VALUES (3, 1015);
INSERT INTO `sys_role_menu` VALUES (3, 1016);
INSERT INTO `sys_role_menu` VALUES (3, 1017);
INSERT INTO `sys_role_menu` VALUES (3, 1018);
INSERT INTO `sys_role_menu` VALUES (3, 1019);
INSERT INTO `sys_role_menu` VALUES (3, 1020);
INSERT INTO `sys_role_menu` VALUES (3, 1021);
INSERT INTO `sys_role_menu` VALUES (3, 1022);
INSERT INTO `sys_role_menu` VALUES (3, 1023);
INSERT INTO `sys_role_menu` VALUES (3, 1024);
INSERT INTO `sys_role_menu` VALUES (3, 1025);
INSERT INTO `sys_role_menu` VALUES (3, 1026);
INSERT INTO `sys_role_menu` VALUES (3, 1027);
INSERT INTO `sys_role_menu` VALUES (3, 1028);
INSERT INTO `sys_role_menu` VALUES (3, 1029);
INSERT INTO `sys_role_menu` VALUES (3, 1030);
INSERT INTO `sys_role_menu` VALUES (3, 1031);
INSERT INTO `sys_role_menu` VALUES (3, 1032);
INSERT INTO `sys_role_menu` VALUES (3, 1033);
INSERT INTO `sys_role_menu` VALUES (3, 1034);
INSERT INTO `sys_role_menu` VALUES (3, 1035);
INSERT INTO `sys_role_menu` VALUES (3, 1036);
INSERT INTO `sys_role_menu` VALUES (3, 1037);
INSERT INTO `sys_role_menu` VALUES (6, 3);
INSERT INTO `sys_role_menu` VALUES (6, 113);
INSERT INTO `sys_role_menu` VALUES (6, 114);
INSERT INTO `sys_role_menu` VALUES (6, 1054);
INSERT INTO `sys_role_menu` VALUES (6, 1055);
INSERT INTO `sys_role_menu` VALUES (7, 1);
INSERT INTO `sys_role_menu` VALUES (7, 2);
INSERT INTO `sys_role_menu` VALUES (7, 100);
INSERT INTO `sys_role_menu` VALUES (7, 101);
INSERT INTO `sys_role_menu` VALUES (7, 102);
INSERT INTO `sys_role_menu` VALUES (7, 103);
INSERT INTO `sys_role_menu` VALUES (7, 104);
INSERT INTO `sys_role_menu` VALUES (7, 105);
INSERT INTO `sys_role_menu` VALUES (7, 106);
INSERT INTO `sys_role_menu` VALUES (7, 107);
INSERT INTO `sys_role_menu` VALUES (7, 108);
INSERT INTO `sys_role_menu` VALUES (7, 109);
INSERT INTO `sys_role_menu` VALUES (7, 110);
INSERT INTO `sys_role_menu` VALUES (7, 111);
INSERT INTO `sys_role_menu` VALUES (7, 1000);
INSERT INTO `sys_role_menu` VALUES (7, 1001);
INSERT INTO `sys_role_menu` VALUES (7, 1002);
INSERT INTO `sys_role_menu` VALUES (7, 1003);
INSERT INTO `sys_role_menu` VALUES (7, 1004);
INSERT INTO `sys_role_menu` VALUES (7, 1005);
INSERT INTO `sys_role_menu` VALUES (7, 1006);
INSERT INTO `sys_role_menu` VALUES (7, 1007);
INSERT INTO `sys_role_menu` VALUES (7, 1008);
INSERT INTO `sys_role_menu` VALUES (7, 1009);
INSERT INTO `sys_role_menu` VALUES (7, 1010);
INSERT INTO `sys_role_menu` VALUES (7, 1011);
INSERT INTO `sys_role_menu` VALUES (7, 1012);
INSERT INTO `sys_role_menu` VALUES (7, 1013);
INSERT INTO `sys_role_menu` VALUES (7, 1014);
INSERT INTO `sys_role_menu` VALUES (7, 1015);
INSERT INTO `sys_role_menu` VALUES (7, 1016);
INSERT INTO `sys_role_menu` VALUES (7, 1017);
INSERT INTO `sys_role_menu` VALUES (7, 1018);
INSERT INTO `sys_role_menu` VALUES (7, 1019);
INSERT INTO `sys_role_menu` VALUES (7, 1020);
INSERT INTO `sys_role_menu` VALUES (7, 1021);
INSERT INTO `sys_role_menu` VALUES (7, 1022);
INSERT INTO `sys_role_menu` VALUES (7, 1023);
INSERT INTO `sys_role_menu` VALUES (7, 1024);
INSERT INTO `sys_role_menu` VALUES (7, 1025);
INSERT INTO `sys_role_menu` VALUES (7, 1026);
INSERT INTO `sys_role_menu` VALUES (7, 1027);
INSERT INTO `sys_role_menu` VALUES (7, 1028);
INSERT INTO `sys_role_menu` VALUES (7, 1029);
INSERT INTO `sys_role_menu` VALUES (7, 1030);
INSERT INTO `sys_role_menu` VALUES (7, 1031);
INSERT INTO `sys_role_menu` VALUES (7, 1032);
INSERT INTO `sys_role_menu` VALUES (7, 1033);
INSERT INTO `sys_role_menu` VALUES (7, 1034);
INSERT INTO `sys_role_menu` VALUES (7, 1035);
INSERT INTO `sys_role_menu` VALUES (7, 1036);
INSERT INTO `sys_role_menu` VALUES (7, 1037);
INSERT INTO `sys_role_menu` VALUES (7, 1045);
INSERT INTO `sys_role_menu` VALUES (7, 1046);
INSERT INTO `sys_role_menu` VALUES (7, 1047);
INSERT INTO `sys_role_menu` VALUES (7, 1048);
INSERT INTO `sys_role_menu` VALUES (7, 1049);
INSERT INTO `sys_role_menu` VALUES (7, 1050);
INSERT INTO `sys_role_menu` VALUES (7, 1051);
INSERT INTO `sys_role_menu` VALUES (7, 1052);
INSERT INTO `sys_role_menu` VALUES (7, 1053);
INSERT INTO `sys_role_menu` VALUES (7, 1056);
INSERT INTO `sys_role_menu` VALUES (8, 1);
INSERT INTO `sys_role_menu` VALUES (8, 100);
INSERT INTO `sys_role_menu` VALUES (8, 101);
INSERT INTO `sys_role_menu` VALUES (8, 102);
INSERT INTO `sys_role_menu` VALUES (8, 103);
INSERT INTO `sys_role_menu` VALUES (8, 104);
INSERT INTO `sys_role_menu` VALUES (8, 105);
INSERT INTO `sys_role_menu` VALUES (8, 106);
INSERT INTO `sys_role_menu` VALUES (8, 107);
INSERT INTO `sys_role_menu` VALUES (8, 108);
INSERT INTO `sys_role_menu` VALUES (8, 1000);
INSERT INTO `sys_role_menu` VALUES (8, 1001);
INSERT INTO `sys_role_menu` VALUES (8, 1002);
INSERT INTO `sys_role_menu` VALUES (8, 1003);
INSERT INTO `sys_role_menu` VALUES (8, 1004);
INSERT INTO `sys_role_menu` VALUES (8, 1005);
INSERT INTO `sys_role_menu` VALUES (8, 1006);
INSERT INTO `sys_role_menu` VALUES (8, 1007);
INSERT INTO `sys_role_menu` VALUES (8, 1008);
INSERT INTO `sys_role_menu` VALUES (8, 1009);
INSERT INTO `sys_role_menu` VALUES (8, 1010);
INSERT INTO `sys_role_menu` VALUES (8, 1011);
INSERT INTO `sys_role_menu` VALUES (8, 1012);
INSERT INTO `sys_role_menu` VALUES (8, 1013);
INSERT INTO `sys_role_menu` VALUES (8, 1014);
INSERT INTO `sys_role_menu` VALUES (8, 1015);
INSERT INTO `sys_role_menu` VALUES (8, 1016);
INSERT INTO `sys_role_menu` VALUES (8, 1017);
INSERT INTO `sys_role_menu` VALUES (8, 1018);
INSERT INTO `sys_role_menu` VALUES (8, 1019);
INSERT INTO `sys_role_menu` VALUES (8, 1020);
INSERT INTO `sys_role_menu` VALUES (8, 1021);
INSERT INTO `sys_role_menu` VALUES (8, 1022);
INSERT INTO `sys_role_menu` VALUES (8, 1023);
INSERT INTO `sys_role_menu` VALUES (8, 1024);
INSERT INTO `sys_role_menu` VALUES (8, 1025);
INSERT INTO `sys_role_menu` VALUES (8, 1026);
INSERT INTO `sys_role_menu` VALUES (8, 1027);
INSERT INTO `sys_role_menu` VALUES (8, 1028);
INSERT INTO `sys_role_menu` VALUES (8, 1029);
INSERT INTO `sys_role_menu` VALUES (8, 1030);
INSERT INTO `sys_role_menu` VALUES (8, 1031);
INSERT INTO `sys_role_menu` VALUES (8, 1032);
INSERT INTO `sys_role_menu` VALUES (8, 1033);
INSERT INTO `sys_role_menu` VALUES (8, 1034);
INSERT INTO `sys_role_menu` VALUES (8, 1035);
INSERT INTO `sys_role_menu` VALUES (8, 1036);
INSERT INTO `sys_role_menu` VALUES (8, 1037);
INSERT INTO `sys_role_menu` VALUES (9, 0);
INSERT INTO `sys_role_menu` VALUES (9, 1);
INSERT INTO `sys_role_menu` VALUES (9, 2);
INSERT INTO `sys_role_menu` VALUES (9, 100);
INSERT INTO `sys_role_menu` VALUES (9, 101);
INSERT INTO `sys_role_menu` VALUES (9, 102);
INSERT INTO `sys_role_menu` VALUES (9, 103);
INSERT INTO `sys_role_menu` VALUES (9, 104);
INSERT INTO `sys_role_menu` VALUES (9, 105);
INSERT INTO `sys_role_menu` VALUES (9, 109);
INSERT INTO `sys_role_menu` VALUES (9, 110);
INSERT INTO `sys_role_menu` VALUES (9, 111);
INSERT INTO `sys_role_menu` VALUES (9, 1000);
INSERT INTO `sys_role_menu` VALUES (9, 1006);
INSERT INTO `sys_role_menu` VALUES (9, 1011);
INSERT INTO `sys_role_menu` VALUES (9, 1015);
INSERT INTO `sys_role_menu` VALUES (9, 1019);
INSERT INTO `sys_role_menu` VALUES (9, 1045);
INSERT INTO `sys_role_menu` VALUES (9, 1046);
INSERT INTO `sys_role_menu` VALUES (9, 1047);
INSERT INTO `sys_role_menu` VALUES (9, 1048);
INSERT INTO `sys_role_menu` VALUES (9, 1049);
INSERT INTO `sys_role_menu` VALUES (9, 1050);
INSERT INTO `sys_role_menu` VALUES (9, 1051);
INSERT INTO `sys_role_menu` VALUES (9, 1052);
INSERT INTO `sys_role_menu` VALUES (9, 1053);
INSERT INTO `sys_role_menu` VALUES (9, 1057);
INSERT INTO `sys_role_menu` VALUES (9, 1060);
INSERT INTO `sys_role_menu` VALUES (9, 1061);
INSERT INTO `sys_role_menu` VALUES (9, 1062);
INSERT INTO `sys_role_menu` VALUES (9, 1063);
INSERT INTO `sys_role_menu` VALUES (9, 1064);
INSERT INTO `sys_role_menu` VALUES (10, 0);
INSERT INTO `sys_role_menu` VALUES (10, 1);
INSERT INTO `sys_role_menu` VALUES (10, 100);
INSERT INTO `sys_role_menu` VALUES (10, 101);
INSERT INTO `sys_role_menu` VALUES (10, 102);
INSERT INTO `sys_role_menu` VALUES (10, 103);
INSERT INTO `sys_role_menu` VALUES (10, 104);
INSERT INTO `sys_role_menu` VALUES (10, 105);
INSERT INTO `sys_role_menu` VALUES (10, 106);
INSERT INTO `sys_role_menu` VALUES (10, 107);
INSERT INTO `sys_role_menu` VALUES (10, 108);
INSERT INTO `sys_role_menu` VALUES (10, 1000);
INSERT INTO `sys_role_menu` VALUES (10, 1001);
INSERT INTO `sys_role_menu` VALUES (10, 1002);
INSERT INTO `sys_role_menu` VALUES (10, 1003);
INSERT INTO `sys_role_menu` VALUES (10, 1004);
INSERT INTO `sys_role_menu` VALUES (10, 1005);
INSERT INTO `sys_role_menu` VALUES (10, 1006);
INSERT INTO `sys_role_menu` VALUES (10, 1007);
INSERT INTO `sys_role_menu` VALUES (10, 1008);
INSERT INTO `sys_role_menu` VALUES (10, 1009);
INSERT INTO `sys_role_menu` VALUES (10, 1010);
INSERT INTO `sys_role_menu` VALUES (10, 1011);
INSERT INTO `sys_role_menu` VALUES (10, 1012);
INSERT INTO `sys_role_menu` VALUES (10, 1013);
INSERT INTO `sys_role_menu` VALUES (10, 1014);
INSERT INTO `sys_role_menu` VALUES (10, 1015);
INSERT INTO `sys_role_menu` VALUES (10, 1016);
INSERT INTO `sys_role_menu` VALUES (10, 1017);
INSERT INTO `sys_role_menu` VALUES (10, 1018);
INSERT INTO `sys_role_menu` VALUES (10, 1019);
INSERT INTO `sys_role_menu` VALUES (10, 1020);
INSERT INTO `sys_role_menu` VALUES (10, 1021);
INSERT INTO `sys_role_menu` VALUES (10, 1022);
INSERT INTO `sys_role_menu` VALUES (10, 1023);
INSERT INTO `sys_role_menu` VALUES (10, 1024);
INSERT INTO `sys_role_menu` VALUES (10, 1025);
INSERT INTO `sys_role_menu` VALUES (10, 1026);
INSERT INTO `sys_role_menu` VALUES (10, 1027);
INSERT INTO `sys_role_menu` VALUES (10, 1028);
INSERT INTO `sys_role_menu` VALUES (10, 1029);
INSERT INTO `sys_role_menu` VALUES (10, 1030);
INSERT INTO `sys_role_menu` VALUES (10, 1031);
INSERT INTO `sys_role_menu` VALUES (10, 1032);
INSERT INTO `sys_role_menu` VALUES (10, 1033);
INSERT INTO `sys_role_menu` VALUES (10, 1034);
INSERT INTO `sys_role_menu` VALUES (10, 1035);
INSERT INTO `sys_role_menu` VALUES (10, 1036);
INSERT INTO `sys_role_menu` VALUES (10, 1037);
INSERT INTO `sys_role_menu` VALUES (10, 1057);
INSERT INTO `sys_role_menu` VALUES (10, 1058);
INSERT INTO `sys_role_menu` VALUES (10, 1059);
INSERT INTO `sys_role_menu` VALUES (10, 1065);
INSERT INTO `sys_role_menu` VALUES (10, 1066);
INSERT INTO `sys_role_menu` VALUES (11, 0);
INSERT INTO `sys_role_menu` VALUES (11, 100);
INSERT INTO `sys_role_menu` VALUES (11, 101);
INSERT INTO `sys_role_menu` VALUES (11, 102);
INSERT INTO `sys_role_menu` VALUES (11, 103);
INSERT INTO `sys_role_menu` VALUES (11, 104);
INSERT INTO `sys_role_menu` VALUES (11, 1000);
INSERT INTO `sys_role_menu` VALUES (11, 1001);
INSERT INTO `sys_role_menu` VALUES (11, 1002);
INSERT INTO `sys_role_menu` VALUES (11, 1003);
INSERT INTO `sys_role_menu` VALUES (11, 1004);
INSERT INTO `sys_role_menu` VALUES (11, 1005);
INSERT INTO `sys_role_menu` VALUES (11, 1006);
INSERT INTO `sys_role_menu` VALUES (11, 1007);
INSERT INTO `sys_role_menu` VALUES (11, 1008);
INSERT INTO `sys_role_menu` VALUES (11, 1009);
INSERT INTO `sys_role_menu` VALUES (11, 1010);
INSERT INTO `sys_role_menu` VALUES (11, 1011);
INSERT INTO `sys_role_menu` VALUES (11, 1012);
INSERT INTO `sys_role_menu` VALUES (11, 1013);
INSERT INTO `sys_role_menu` VALUES (11, 1014);
INSERT INTO `sys_role_menu` VALUES (11, 1015);
INSERT INTO `sys_role_menu` VALUES (11, 1016);
INSERT INTO `sys_role_menu` VALUES (11, 1017);
INSERT INTO `sys_role_menu` VALUES (11, 1018);
INSERT INTO `sys_role_menu` VALUES (11, 1019);
INSERT INTO `sys_role_menu` VALUES (11, 1020);
INSERT INTO `sys_role_menu` VALUES (11, 1021);
INSERT INTO `sys_role_menu` VALUES (11, 1022);
INSERT INTO `sys_role_menu` VALUES (11, 1023);
INSERT INTO `sys_role_menu` VALUES (11, 1065);
INSERT INTO `sys_role_menu` VALUES (11, 1066);
INSERT INTO `sys_role_menu` VALUES (12, 0);
INSERT INTO `sys_role_menu` VALUES (12, 1);
INSERT INTO `sys_role_menu` VALUES (12, 105);
INSERT INTO `sys_role_menu` VALUES (12, 106);
INSERT INTO `sys_role_menu` VALUES (12, 107);
INSERT INTO `sys_role_menu` VALUES (12, 108);
INSERT INTO `sys_role_menu` VALUES (12, 1024);
INSERT INTO `sys_role_menu` VALUES (12, 1025);
INSERT INTO `sys_role_menu` VALUES (12, 1026);
INSERT INTO `sys_role_menu` VALUES (12, 1027);
INSERT INTO `sys_role_menu` VALUES (12, 1028);
INSERT INTO `sys_role_menu` VALUES (12, 1029);
INSERT INTO `sys_role_menu` VALUES (12, 1030);
INSERT INTO `sys_role_menu` VALUES (12, 1031);
INSERT INTO `sys_role_menu` VALUES (12, 1032);
INSERT INTO `sys_role_menu` VALUES (12, 1033);
INSERT INTO `sys_role_menu` VALUES (12, 1034);
INSERT INTO `sys_role_menu` VALUES (12, 1035);
INSERT INTO `sys_role_menu` VALUES (12, 1036);
INSERT INTO `sys_role_menu` VALUES (12, 1037);
INSERT INTO `sys_role_menu` VALUES (12, 1057);
INSERT INTO `sys_role_menu` VALUES (12, 1058);
INSERT INTO `sys_role_menu` VALUES (12, 1059);
INSERT INTO `sys_role_menu` VALUES (13, 0);
INSERT INTO `sys_role_menu` VALUES (13, 1);
INSERT INTO `sys_role_menu` VALUES (13, 105);
INSERT INTO `sys_role_menu` VALUES (13, 106);
INSERT INTO `sys_role_menu` VALUES (13, 107);
INSERT INTO `sys_role_menu` VALUES (13, 108);
INSERT INTO `sys_role_menu` VALUES (13, 1024);
INSERT INTO `sys_role_menu` VALUES (13, 1025);
INSERT INTO `sys_role_menu` VALUES (13, 1026);
INSERT INTO `sys_role_menu` VALUES (13, 1027);
INSERT INTO `sys_role_menu` VALUES (13, 1028);
INSERT INTO `sys_role_menu` VALUES (13, 1029);
INSERT INTO `sys_role_menu` VALUES (13, 1030);
INSERT INTO `sys_role_menu` VALUES (13, 1031);
INSERT INTO `sys_role_menu` VALUES (13, 1032);
INSERT INTO `sys_role_menu` VALUES (13, 1033);
INSERT INTO `sys_role_menu` VALUES (13, 1034);
INSERT INTO `sys_role_menu` VALUES (13, 1035);
INSERT INTO `sys_role_menu` VALUES (13, 1036);
INSERT INTO `sys_role_menu` VALUES (13, 1037);
INSERT INTO `sys_role_menu` VALUES (13, 1057);
INSERT INTO `sys_role_menu` VALUES (13, 1058);
INSERT INTO `sys_role_menu` VALUES (13, 1059);
INSERT INTO `sys_role_menu` VALUES (14, 0);
INSERT INTO `sys_role_menu` VALUES (14, 1);
INSERT INTO `sys_role_menu` VALUES (14, 105);
INSERT INTO `sys_role_menu` VALUES (14, 106);
INSERT INTO `sys_role_menu` VALUES (14, 107);
INSERT INTO `sys_role_menu` VALUES (14, 108);
INSERT INTO `sys_role_menu` VALUES (14, 1024);
INSERT INTO `sys_role_menu` VALUES (14, 1025);
INSERT INTO `sys_role_menu` VALUES (14, 1026);
INSERT INTO `sys_role_menu` VALUES (14, 1027);
INSERT INTO `sys_role_menu` VALUES (14, 1028);
INSERT INTO `sys_role_menu` VALUES (14, 1029);
INSERT INTO `sys_role_menu` VALUES (14, 1030);
INSERT INTO `sys_role_menu` VALUES (14, 1031);
INSERT INTO `sys_role_menu` VALUES (14, 1032);
INSERT INTO `sys_role_menu` VALUES (14, 1033);
INSERT INTO `sys_role_menu` VALUES (14, 1034);
INSERT INTO `sys_role_menu` VALUES (14, 1035);
INSERT INTO `sys_role_menu` VALUES (14, 1036);
INSERT INTO `sys_role_menu` VALUES (14, 1037);
INSERT INTO `sys_role_menu` VALUES (14, 1057);
INSERT INTO `sys_role_menu` VALUES (14, 1058);
INSERT INTO `sys_role_menu` VALUES (14, 1059);
INSERT INTO `sys_role_menu` VALUES (46, 1);
INSERT INTO `sys_role_menu` VALUES (46, 100);
INSERT INTO `sys_role_menu` VALUES (46, 101);
INSERT INTO `sys_role_menu` VALUES (46, 102);
INSERT INTO `sys_role_menu` VALUES (46, 103);
INSERT INTO `sys_role_menu` VALUES (46, 104);
INSERT INTO `sys_role_menu` VALUES (46, 105);
INSERT INTO `sys_role_menu` VALUES (46, 106);
INSERT INTO `sys_role_menu` VALUES (46, 107);
INSERT INTO `sys_role_menu` VALUES (46, 108);
INSERT INTO `sys_role_menu` VALUES (46, 1000);
INSERT INTO `sys_role_menu` VALUES (46, 1001);
INSERT INTO `sys_role_menu` VALUES (46, 1002);
INSERT INTO `sys_role_menu` VALUES (46, 1003);
INSERT INTO `sys_role_menu` VALUES (46, 1004);
INSERT INTO `sys_role_menu` VALUES (46, 1005);
INSERT INTO `sys_role_menu` VALUES (46, 1006);
INSERT INTO `sys_role_menu` VALUES (46, 1007);
INSERT INTO `sys_role_menu` VALUES (46, 1008);
INSERT INTO `sys_role_menu` VALUES (46, 1009);
INSERT INTO `sys_role_menu` VALUES (46, 1010);
INSERT INTO `sys_role_menu` VALUES (46, 1011);
INSERT INTO `sys_role_menu` VALUES (46, 1012);
INSERT INTO `sys_role_menu` VALUES (46, 1013);
INSERT INTO `sys_role_menu` VALUES (46, 1014);
INSERT INTO `sys_role_menu` VALUES (46, 1015);
INSERT INTO `sys_role_menu` VALUES (46, 1016);
INSERT INTO `sys_role_menu` VALUES (46, 1017);
INSERT INTO `sys_role_menu` VALUES (46, 1018);
INSERT INTO `sys_role_menu` VALUES (46, 1019);
INSERT INTO `sys_role_menu` VALUES (46, 1020);
INSERT INTO `sys_role_menu` VALUES (46, 1021);
INSERT INTO `sys_role_menu` VALUES (46, 1022);
INSERT INTO `sys_role_menu` VALUES (46, 1023);
INSERT INTO `sys_role_menu` VALUES (46, 1024);
INSERT INTO `sys_role_menu` VALUES (46, 1025);
INSERT INTO `sys_role_menu` VALUES (46, 1026);
INSERT INTO `sys_role_menu` VALUES (46, 1027);
INSERT INTO `sys_role_menu` VALUES (46, 1028);
INSERT INTO `sys_role_menu` VALUES (46, 1029);
INSERT INTO `sys_role_menu` VALUES (46, 1030);
INSERT INTO `sys_role_menu` VALUES (46, 1031);
INSERT INTO `sys_role_menu` VALUES (46, 1032);
INSERT INTO `sys_role_menu` VALUES (46, 1033);
INSERT INTO `sys_role_menu` VALUES (46, 1034);
INSERT INTO `sys_role_menu` VALUES (46, 1035);
INSERT INTO `sys_role_menu` VALUES (46, 1036);
INSERT INTO `sys_role_menu` VALUES (46, 1037);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `real_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `password` varchar(96) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（1男 0女）',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `ma_open_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '超级管理员', '00', '1264415695@qq.com', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', NULL, '0', '17681239053', '1', 'http://thirdwx.qlogo.cn/mmopen/vi_32/AotJDTonyzTnmknJnk3C22uvKvKC8hTegMmbpj8sS8Gh5LC4O8ibfMMP3zvvv5gDD0M4m8TAdBCab8XL4V6bQvg/132', '0', '', NULL, '1', '2017-06-20 15:12:16', '1', '2019-06-05 22:53:00', '', NULL);
INSERT INTO `sys_user` VALUES (2, 106, 'test', '测试管理员', '00', '1264415695@qq.com', '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', NULL, '0', '17681233341', '0', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', '0', '', NULL, '1', '2017-06-26 17:31:41', '1', '2019-06-11 23:22:21', '测试', NULL);
INSERT INTO `sys_user` VALUES (4, 103, 'system', '系统用户', '00', '111@qq.com', '$2a$10$REVknxz6yMeeI/P8V9q0Vensou24cQXJIeQFCJS0HOOgMR.bwSOju', '', '0', '17681234441', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', '0', '', NULL, '1', '2019-11-25 03:02:39', '1', '2019-11-25 03:22:38', '测试', NULL);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('6ded82f2-c046-4224-b025-848576fb39d4', 'admin', '研发部门', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', 'on_line', '2018-10-30 10:56:23', '2018-10-30 11:08:47', 1800000);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);
INSERT INTO `sys_user_post` VALUES (2, 3);
INSERT INTO `sys_user_post` VALUES (2, 4);
INSERT INTO `sys_user_post` VALUES (3, 1);
INSERT INTO `sys_user_post` VALUES (3, 2);
INSERT INTO `sys_user_post` VALUES (4, 3);
INSERT INTO `sys_user_post` VALUES (6, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 1);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (4, 1);
INSERT INTO `sys_user_role` VALUES (4, 2);

-- ----------------------------
-- Table structure for sys_wx_mini_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_wx_mini_user`;
CREATE TABLE `sys_wx_mini_user`  (
  `wx_mini_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `open_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT 'opneId',
  `subscribe` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否关注公众号0 未关注 1关注',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '市',
  `county` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '区',
  `email` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '邮箱',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`wx_mini_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_wx_mini_user
-- ----------------------------
INSERT INTO `sys_wx_mini_user` VALUES (7, '流年不夏半殇', 'https://wx.qlogo.cn/mmopen/vi_32/RVh7FDY1VnmXlKDlGJibV1E4F4YZy7aicQ2wdaTriaFC7SAfuGXGsNIKoVgiaWZPnV0hwLVKhMjgfY17g9U6YdVKGg/132', '', 'okUIE5pAFKKs2AZBPvvaWn4YL6Pc', '', 'Anhui', 'Bengbu', '0', '0', '1', NULL, '0', NULL, NULL, NULL, '2019-11-20 14:56:52', '');

SET FOREIGN_KEY_CHECKS = 1;
