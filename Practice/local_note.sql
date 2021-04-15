/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : local_note

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2021-04-16 01:29:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noteTitle` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `noteCont` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `noteLove` int(11) NOT NULL DEFAULT '0',
  `noteAuthor` int(11) NOT NULL,
  `noteLimit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'public',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', '傲慢与偏见读后感', '豆腐干很久，。归还借款了；放工回家看了；‘好可怜了；那么久了', '0', '4', 'public');
INSERT INTO `note` VALUES ('2', '动漫分享', '阿斯顿法国皇家快乐', '0', '1', 'public');
INSERT INTO `note` VALUES ('3', '高数学习笔记', '阿顺帆刚换环境健康快乐来了；；；', '0', '3', 'public');
INSERT INTO `note` VALUES ('4', '喜羊羊与灰太狼回忆', 'sdfghjklasjdslkajfkskdskfk', '0', '4', 'public');
INSERT INTO `note` VALUES ('5', '电影赏析', '重中之重重中之重重中之重', '0', '2', 'private');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'momo', '123456');
INSERT INTO `t_user` VALUES ('2', 'Amy', 'Amy');
INSERT INTO `t_user` VALUES ('3', 'Sam', 'Sam');
INSERT INTO `t_user` VALUES ('4', 'John', 'John');
INSERT INTO `t_user` VALUES ('5', '小明', '111');
INSERT INTO `t_user` VALUES ('6', '容姒', '1234');
INSERT INTO `t_user` VALUES ('8', '小红', '111');
INSERT INTO `t_user` VALUES ('9', '小明', '123456');
INSERT INTO `t_user` VALUES ('10', '容瑕', 'jiade');
INSERT INTO `t_user` VALUES ('11', '李豪', 'huaxue');
