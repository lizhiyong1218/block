/*
MySQL Data Transfer
Source Host: localhost
Source Database: purespringmvc
Target Host: localhost
Target Database: purespringmvc
Date: 2014/11/26 16:53:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_blog_article
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_article`;
CREATE TABLE `t_blog_article` (
  `blog_id` int(11) NOT NULL auto_increment,
  `blog_title` varchar(40) default NULL COMMENT '标题',
  `blog_content` text COMMENT '内容',
  `blog_type` int(11) default NULL COMMENT '类型',
  `author_name` varchar(40) default NULL COMMENT '作者',
  `author_id` int(11) default NULL COMMENT '作者id',
  `blog_keyword` varchar(200) default NULL COMMENT '关键字',
  `blog_status` varchar(2) default NULL COMMENT '状态',
  `blog_desc` varchar(200) default NULL COMMENT '描述',
  `cover` varchar(200) default NULL COMMENT '面封',
  `isHot` char(1) default NULL COMMENT '是否热门',
  `isRecommend` char(1) default NULL COMMENT '是否推荐',
  `pub_time` date default NULL COMMENT '布发时间',
  `create_by` varchar(40) default NULL,
  `create_time` date default NULL,
  `update_by` varchar(40) default NULL,
  `update_time` date default NULL,
  `isAvailable` char(1) default NULL,
  PRIMARY KEY  (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_blog_article` VALUES ('1', 'f', 'd', null, 'd', null, 'd', 'd', 'd', 'resources/images/tip.png', 'd', 'd', null, null, null, null, null, '1');
