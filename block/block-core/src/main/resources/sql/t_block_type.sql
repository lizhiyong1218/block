/*
MySQL Data Transfer
Source Host: localhost
Source Database: purespringmvc
Target Host: localhost
Target Database: purespringmvc
Date: 2014/11/24 16:55:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_block_type
-- ----------------------------
DROP TABLE IF EXISTS `t_block_type`;
CREATE TABLE `t_block_type` (
  `type_id` int(11) NOT NULL auto_increment,
  `type_name` varchar(20) default NULL COMMENT '类型名称',
  `type_desc` varchar(200) default NULL COMMENT '类型描述',
  `crate_by` varchar(40) default NULL COMMENT '创建人',
  `create_time` date default NULL COMMENT '创建时间',
  `update_by` varchar(40) default NULL COMMENT '修改人',
  `update_time` date default NULL COMMENT '修改时间',
  `isAvailable` char(1) default NULL COMMENT '是否可用',
  PRIMARY KEY  (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
