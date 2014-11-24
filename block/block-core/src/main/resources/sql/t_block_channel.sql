/*
MySQL Data Transfer
Source Host: localhost
Source Database: purespringmvc
Target Host: localhost
Target Database: purespringmvc
Date: 2014/11/24 16:55:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_block_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_block_channel`;
CREATE TABLE `t_block_channel` (
  `channel_id` int(11) NOT NULL auto_increment COMMENT '栏目id',
  `channel_parent_id` int(11) default NULL COMMENT '父栏目id',
  `channel_name` varchar(40) default NULL COMMENT '栏目名称(唯一)',
  `channel_showname` varchar(40) default NULL COMMENT '显示名称',
  `channel_logo` varchar(400) default NULL COMMENT '图标',
  `channel_desc` varchar(400) default NULL COMMENT '述描',
  `crate_by` varchar(40) default NULL,
  `create_time` date default NULL,
  `update_by` varchar(40) default NULL,
  `update_time` date default NULL,
  `isAvailable` char(1) default NULL,
  PRIMARY KEY  (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
