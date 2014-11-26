/*
MySQL Data Transfer
Source Host: localhost
Source Database: purespringmvc
Target Host: localhost
Target Database: purespringmvc
Date: 2014/11/26 16:53:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_sys_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionary_item`;
CREATE TABLE `t_sys_dictionary_item` (
  `item_id` int(11) NOT NULL auto_increment COMMENT '字典项id',
  `item_value` varchar(200) default NULL,
  `item_label` varchar(500) default NULL COMMENT '名称',
  `order_no` int(11) default NULL COMMENT '排序号',
  `remarks` varchar(200) default NULL COMMENT '备注',
  `create_by` varchar(60) default NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_by` varchar(60) default NULL COMMENT '修改人',
  `modify_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '改修时间',
  `isavailable` char(1) default '1' COMMENT '是否可用',
  `dictionary_value` varchar(20) default NULL COMMENT '数据字典值',
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_sys_dictionary_item` VALUES ('1', '0', '已取消', '9', '取消', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:36:50', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('2', '1', '已完成', '8', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:37:00', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('3', '2', '待供应商换货', '7', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:37:06', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('4', '3', '待供应商退款', '6', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:37:12', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('5', '4', '部分发货', '5', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:37:24', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('6', '5', '待发货', '4', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:39:03', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('7', '6', '已驳回', '2', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:38:27', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('8', '7', '待审核', '3', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:38:41', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('9', '8', '同步失败', '1', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-10-09 09:38:00', '1', 'RMASTATUS');
INSERT INTO `t_sys_dictionary_item` VALUES ('10', '0', '与供应商退货', '1', 'ttt', 'admin', '2014-09-30 10:56:16', 'test', '2014-11-19 16:50:23', '1', 'RMATYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('11', '1', '与供应商换货', '2', '', 'admin', '2014-09-30 10:56:16', 'admin', '2014-09-30 10:56:16', '1', 'RMATYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('12', '2', 'SCRAP', '3', '(退回供应商)', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'RMATYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('13', '1', '快递', '1', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'SHIPVIA');
INSERT INTO `t_sys_dictionary_item` VALUES ('14', '2', '上门自提', '2', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'SHIPVIA');
INSERT INTO `t_sys_dictionary_item` VALUES ('15', '3', '公司配送', '3', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'SHIPVIA');
INSERT INTO `t_sys_dictionary_item` VALUES ('16', '0', '不可用', '1', '用于软删除', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'ISAVALIABLE');
INSERT INTO `t_sys_dictionary_item` VALUES ('17', '1', '可用', '2', '用于软删除', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'ISAVALIABLE');
INSERT INTO `t_sys_dictionary_item` VALUES ('18', '0', '境内退货', '1', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'RMALOCATIONTYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('19', '1', '境外退货', '2', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'RMALOCATIONTYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('20', '2', '境内退境外', '3', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-09-30 10:56:17', '1', 'RMALOCATIONTYPE');
INSERT INTO `t_sys_dictionary_item` VALUES ('21', '1', '人民币', '1', '', 'admin', '2014-09-30 10:56:17', 'admin', '2014-11-04 11:45:48', '1', 'CURRENCY');
INSERT INTO `t_sys_dictionary_item` VALUES ('22', '2', '美元', '2', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-11-04 11:45:53', '1', 'CURRENCY');
INSERT INTO `t_sys_dictionary_item` VALUES ('23', 'HK', '香港仓', '1', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'WAREHOUSE');
INSERT INTO `t_sys_dictionary_item` VALUES ('24', 'HM-BS', '虎门保税仓', '2', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'WAREHOUSE');
INSERT INTO `t_sys_dictionary_item` VALUES ('25', 'HM-WS', '虎门完税仓', '3', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'WAREHOUSE');
INSERT INTO `t_sys_dictionary_item` VALUES ('26', '3000', '中国电子器材技术有限公司', '1', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'OWNER');
INSERT INTO `t_sys_dictionary_item` VALUES ('27', '9000', '中国电子器材国际有限公司', '3', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'OWNER');
INSERT INTO `t_sys_dictionary_item` VALUES ('28', '5000', '深圳市中电国际信息科技有限公司', '2', '', 'admin', '2014-09-30 10:56:18', 'admin', '2014-09-30 10:56:18', '1', 'OWNER');
INSERT INTO `t_sys_dictionary_item` VALUES ('29', 'jacky.zhang@ceacsz.com.cn', '张健', '1', '全球采购部', 'admin', '2014-10-14 18:22:17', 'admin', '2014-11-06 09:28:59', '0', 'PartLostRemind');
INSERT INTO `t_sys_dictionary_item` VALUES ('30', 'anny.liu@ceacsz.com.cn', '刘倩', '2', '产品管理部', 'admin', '2014-10-14 18:22:44', 'admin', '2014-11-06 09:29:07', '0', 'PartLostRemind');
INSERT INTO `t_sys_dictionary_item` VALUES ('31', 'kai.yang@ceacsz.com.cn', 'MAILTOGPSC', '1', 'GPSC', 'admin', '2014-10-14 18:24:13', 'admin', '2014-11-06 09:29:52', '1', 'ProfitLostRemind');
INSERT INTO `t_sys_dictionary_item` VALUES ('32', 'kai.yang@ceacsz.com.cn', 'MAILTOPMSC', '2', 'PMSC', 'admin', '2014-10-14 18:24:13', 'admin', '2014-11-06 09:30:01', '1', 'ProfitLostRemind');
INSERT INTO `t_sys_dictionary_item` VALUES ('33', 'smtpwcom.263xmail.com', '263邮箱', '1', '', 'admin', '2014-10-23 20:16:35', null, '0000-00-00 00:00:00', '1', 'MailHost');
INSERT INTO `t_sys_dictionary_item` VALUES ('34', 'honey.li@ceacsz.com.cn', 'MailSender', '1', '', 'admin', '2014-10-23 20:18:30', 'admin', '2014-10-25 13:47:06', '1', 'POMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('35', 'hongli117!', 'PassWord', '2', '', 'admin', '2014-10-23 20:18:30', 'admin', '2014-10-25 13:47:19', '1', 'POMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('36', 'jacky.zhang@ceacsz.com.cn', '张健', '1', '', 'admin', '2014-10-23 20:19:31', 'admin', '2014-10-25 13:39:30', '0', 'POMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('37', 'kate.lin@ceacsz.com.cn', '林琳', '2', '', 'admin', '2014-10-23 20:19:31', null, '0000-00-00 00:00:00', '1', 'POMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('38', 'jacky.zhang@ceacsz.com.cn', 'MailSender', '1', '', 'admin', '2014-10-23 20:20:50', null, '0000-00-00 00:00:00', '1', 'RFQMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('39', '123.com', 'PassWord', '2', '', 'admin', '2014-10-23 20:20:50', null, '0000-00-00 00:00:00', '1', 'RFQMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('40', 'afei.liu@ceacsz.com.cn', '刘云飞', '1', '', 'admin', '2014-10-23 20:21:53', 'admin', '2014-11-04 15:10:36', '1', 'RFQMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('41', 'iceac@ceacsz.com.cn', 'MailSender', '1', '', 'admin', '2014-10-23 20:23:06', null, '0000-00-00 00:00:00', '1', 'PartLostMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('42', 'iceac@123*', 'PassWord', '2', '', 'admin', '2014-10-23 20:23:06', null, '0000-00-00 00:00:00', '1', 'PartLostMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('43', 'iceac@ceacsz.com.cn', 'MailSender', '1', '', 'admin', '2014-10-23 20:24:30', null, '0000-00-00 00:00:00', '1', 'ProfitLostMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('44', 'iceac@123*', 'PassWord', '2', '', 'admin', '2014-10-23 20:24:30', null, '0000-00-00 00:00:00', '1', 'ProfitLostMailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('45', 'official', '正式', '1', '', 'admin', '2014-10-24 11:03:29', 'admin', '2014-10-24 11:05:08', '0', 'IsOfficial');
INSERT INTO `t_sys_dictionary_item` VALUES ('46', '0', '是否正式环境', '1', '默认为0测试，正式为1', 'admin', '2014-10-24 11:03:29', 'admin', '2014-10-24 11:29:51', '1', 'IsOfficial');
INSERT INTO `t_sys_dictionary_item` VALUES ('47', '1', '是否正式环境', '1', '默认为0测试，1正式', 'admin', '2014-10-24 11:32:02', 'admin', '2014-10-25 16:00:43', '1', 'IsProduct');
INSERT INTO `t_sys_dictionary_item` VALUES ('48', 'kate.lin@ceacsz.com.cn', '林琳', '2', '', 'admin', '2014-10-24 11:39:32', 'admin', '2014-10-25 17:51:04', '0', 'RFQMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('49', 'kevin.zhou@ceacsz.com.cn', '周建华', '3', '', 'admin', '2014-10-24 14:56:42', null, '0000-00-00 00:00:00', '1', 'POMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('50', 'huijun.luo@ceacsz.com.cn', '罗会军', '4', '', 'admin', '2014-10-24 14:57:03', null, '0000-00-00 00:00:00', '1', 'POMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('51', 'honey.li@ceacsz.com.cn', '李红丽', '5', '', 'admin', '2014-10-24 14:58:07', 'admin', '2014-10-25 13:39:25', '0', 'POMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('52', 'iceac@ceacsz.com.cn', 'MailSender', '1', '', 'admin', '2014-10-25 14:47:54', 'admin', '2014-11-04 15:11:34', '1', 'POMailFailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('53', 'iceac@123*', 'PassWord', '2', '', 'admin', '2014-10-25 14:47:54', 'admin', '2014-11-04 15:11:47', '1', 'POMailFailSender');
INSERT INTO `t_sys_dictionary_item` VALUES ('54', 'kate.lin@ceacsz.com.cn', '林琳', '1', '', 'admin', '2014-10-25 14:49:15', null, '0000-00-00 00:00:00', '1', 'POMailFailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('55', 'kevin.zhou@ceacsz.com.cn', '周建华', null, '', 'admin', '2014-10-25 16:13:32', 'admin', '2014-10-25 16:24:07', '1', 'POMailFailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('56', 'meiling.yu@ceacsz.com.cn', '于美玲', null, '', 'admin', '2014-10-26 17:15:36', null, '0000-00-00 00:00:00', '1', 'POMailFailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('57', 'kai.yang@ceacsz.com.cn', '阳凯', '1', '', 'admin', '2014-11-04 16:41:11', 'admin', '2014-11-06 09:30:48', '1', 'SOToPOFailMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('58', 'kate.lin@ceacsz.com.cn', '林琳', '2', '', 'admin', '2014-11-04 16:41:11', 'admin', '2014-11-04 16:41:57', '1', 'SOToPOFailMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('59', 'huijun.luo@ceacsz.com.cn', '罗会军', '1', '', 'admin', '2014-11-05 09:10:09', null, '0000-00-00 00:00:00', '1', 'POToSOFailMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('60', 'kevin.zhou@ceacsz.com.cn', '周建华', '2', '', 'admin', '2014-11-05 09:10:09', null, '0000-00-00 00:00:00', '1', 'POToSOFailMailCcs');
INSERT INTO `t_sys_dictionary_item` VALUES ('61', 'kai.yang@ceacsz.com.cn', '阳凯', '1', '', 'admin', '2014-11-06 09:29:28', null, '0000-00-00 00:00:00', '1', 'PartLostRemind');
