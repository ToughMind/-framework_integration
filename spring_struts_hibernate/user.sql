/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost
 Source Database       : framework_integration

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : utf-8

 Date: 05/22/2017 20:46:54 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '状态',
  `createTime` bigint(20) unsigned DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `money` double(10,0) DEFAULT NULL COMMENT '资产',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('9', 'good', '255', '0', '2017-05-18 09:00:22', '9999999999', '9999999999'), ('10', 'good', '255', '0', '2017-05-18 09:00:32', '9999999999', '9999999999'), ('11', 'good', '255', '0', '2017-05-18 09:00:38', '9999999999', '9999999999'), ('12', 'good', '255', '0', '2017-05-18 09:00:39', '9999999999', '9999999999');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
