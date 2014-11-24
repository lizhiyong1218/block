/*
MySQL Data Transfer
Source Host: localhost
Source Database: purespringmvc
Target Host: localhost
Target Database: purespringmvc
Date: 2014/11/24 16:55:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_block
-- ----------------------------
DROP TABLE IF EXISTS `t_block`;
CREATE TABLE `t_block` (
  `block_id` int(11) NOT NULL auto_increment,
  `block_title` varchar(40) default NULL COMMENT '标题',
  `block_content` varchar(200) default NULL COMMENT '内容',
  `block_type` int(11) default NULL COMMENT '作者',
  PRIMARY KEY  (`block_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
