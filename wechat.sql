/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-03-16 22:59:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for manage_admin
-- ----------------------------
DROP TABLE IF EXISTS `manage_admin`;
CREATE TABLE `manage_admin` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `account` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for sms_send_interval
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_interval`;
CREATE TABLE `sms_send_interval` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `start_day` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开始日期',
  `interval_day` int(2) DEFAULT NULL COMMENT '间隔日期',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='短信发送间隔记录';

-- ----------------------------
-- Table structure for wechat_article_content
-- ----------------------------
DROP TABLE IF EXISTS `wechat_article_content`;
CREATE TABLE `wechat_article_content` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `message_id` char(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消息id',
  `title` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pic_Url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片链接',
  `url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '点击跳转的链接',
  `sort` int(1) DEFAULT '0' COMMENT '排序',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for wechat_menu
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu`;
CREATE TABLE `wechat_menu` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '按钮名称',
  `type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '按钮类型（详见工具包内WxConsts类）',
  `key` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'click等点击类型必须',
  `url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '网页链接（版本低时推小程序打不开的替代网址）',
  `media_id2` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '素材id',
  `app_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '小程序的appid（仅认证公众号可配置）',
  `page_path` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '小程序页面路径',
  `parent_id` char(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级菜单的id',
  `sort_number` int(2) DEFAULT '5' COMMENT '顺序编号，升序',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公众号菜单表';

-- ----------------------------
-- Table structure for wechat_message
-- ----------------------------
DROP TABLE IF EXISTS `wechat_message`;
CREATE TABLE `wechat_message` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '素材名称',
  `msg_type` int(1) NOT NULL COMMENT '消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；',
  `content` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容（文本消息）',
  `media_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒体id（图片消息、语音消息、视频消息、音乐消息）',
  `title` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题（视频消息、音乐消息）',
  `description` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述（视频消息、音乐消息）',
  `music_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '音乐链接（音乐消息）',
  `hq_music_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高质量音乐链接，WIFI环境优先使用该链接播放音乐（音乐消息）',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='微信素材表';

-- ----------------------------
-- Table structure for wechat_official_article
-- ----------------------------
DROP TABLE IF EXISTS `wechat_official_article`;
CREATE TABLE `wechat_official_article` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `media_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信素材id',
  `cover_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面',
  `titles` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for wechat_reply
-- ----------------------------
DROP TABLE IF EXISTS `wechat_reply`;
CREATE TABLE `wechat_reply` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键id',
  `key_word` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关键词',
  `matching_type` int(1) DEFAULT NULL COMMENT '匹配类型（0-精确，1-模糊）',
  `msg_type` int(1) DEFAULT NULL COMMENT '消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；6、图文（文章）',
  `message_id` char(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复消息id，如果是官方文章存mediaId',
  `message_name` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '素材名称',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='关键字回复表';

-- ----------------------------
-- Table structure for wechat_send_record
-- ----------------------------
DROP TABLE IF EXISTS `wechat_send_record`;
CREATE TABLE `wechat_send_record` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `tag` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送对象标签',
  `msg_type` int(11) DEFAULT NULL COMMENT '消息类型。0、文本；1、图片；2、语音；3、视频；4、音乐；5、图文；',
  `media_id` char(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '素材ID',
  `media_name` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '素材名称',
  `success_count` int(11) DEFAULT NULL COMMENT '发送成功数量',
  `fail_count` int(11) DEFAULT NULL COMMENT '发送失败数量',
  `total` int(11) DEFAULT NULL COMMENT '总人数',
  `send_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for wechat_tag
-- ----------------------------
DROP TABLE IF EXISTS `wechat_tag`;
CREATE TABLE `wechat_tag` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `tag_id` bigint(11) DEFAULT NULL COMMENT '微信分配的标签id',
  `tag_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名称',
  `qrcode_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '该标签对应的关注二维码',
  `create_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户标签管理';

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `subscribe` int(1) DEFAULT '0' COMMENT '0-未关注，1-关注',
  `open_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '对应公众号唯一id',
  `nick_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `sex` int(1) DEFAULT '0' COMMENT '0-未知，1-男，2-女',
  `city` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `country` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国家',
  `province` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `language` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '语言',
  `head_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `subscribe_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关注时间',
  `union_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开放平台上绑定的唯一id',
  `remark` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号运营者对粉丝的备注',
  `group_id` int(11) DEFAULT NULL COMMENT '用户所在的分组ID',
  `tag_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户被打上的标签',
  `created_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间（首次关注时间）',
  `update_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新时间（取消关注时间）',
  `debug` int(1) DEFAULT '0' COMMENT '0正常，1调试',
  PRIMARY KEY (`id`),
  KEY `rds_idx_0` (`remark`(191)),
  KEY `rds_idx_1` (`subscribe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='微信公众号用户表';

-- ----------------------------
-- Table structure for wechat_user_statistic
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_statistic`;
CREATE TABLE `wechat_user_statistic` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `subscribe_number` int(11) DEFAULT NULL COMMENT '新增人数',
  `unsubscribe_number` int(11) DEFAULT NULL COMMENT '取关人数',
  `increase_number` int(11) DEFAULT NULL COMMENT '净增人数',
  `record_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户统计表';

-- ----------------------------
-- Table structure for wechat_user_statistic_detail
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_statistic_detail`;
CREATE TABLE `wechat_user_statistic_detail` (
  `id` char(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '记录id',
  `tag_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签',
  `number` int(11) DEFAULT NULL COMMENT '人数',
  `record_time` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='新增标签统计';
