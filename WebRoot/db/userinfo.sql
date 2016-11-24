/*
Navicat MySQL Data Transfer

Source Server         : 本地服务器
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : deepin

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-05-27 15:04:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` varchar(36) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userPwd` varchar(36) NOT NULL,
  `userStatus` int(11) NOT NULL,
  `userTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userRemark` varchar(500) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `unique_name` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('37202483597929407', '雪茄', 'e10adc3949ba59abbe56e057f20f883e', '0', '2016-05-27 15:00:38', '管理员什么都没留下！');
INSERT INTO `userinfo` VALUES ('43540348092168289', '指尖丶绕情缘', 'e10adc3949ba59abbe56e057f20f883e', '0', '2016-05-17 16:14:04', '管理员什么都没留下！');
INSERT INTO `userinfo` VALUES ('70330361157804499', '花易落轻雾', 'e10adc3949ba59abbe56e057f20f883e', '0', '2016-05-17 16:18:47', '管理员什么都没留下！');
INSERT INTO `userinfo` VALUES ('73182972626214855', '颠覆玉米', 'e10adc3949ba59abbe56e057f20f883e', '0', '2016-05-17 16:36:50', '管理员什么都没留下！');
INSERT INTO `userinfo` VALUES ('96604407252123649', '旧梦丶毁佳人', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-05-12 20:15:55', '管理员什么都没留下！');
