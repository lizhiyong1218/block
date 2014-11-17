/*
MySQL Data Transfer
Source Host: 10.1.100.51
Source Database: test_pms
Target Host: 10.1.100.51
Target Database: test_pms
Date: 2014/11/17 17:03:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_pms_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_pms_dictionary`;
CREATE TABLE `t_pms_dictionary` (
  `dictionary_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dictionary_value` varchar(20) DEFAULT NULL COMMENT '字典值',
  `dictionary_label` varchar(20) DEFAULT NULL COMMENT '字典名称',
  `isavailable` char(1) DEFAULT '1' COMMENT '是否可用',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(60) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建创时间',
  `modifi_by` varchar(60) DEFAULT NULL COMMENT '修改人',
  `modifi_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`dictionary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_pms_dictionary` VALUES ('1', 'RMASTATUS', '退换货状态', '1', '退换货属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('2', 'RMATYPE', '退换货类型', '1', '退换货属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('3', 'SHIPVIA', '运输方式', '1', '退换货属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('4', 'ISAVALIABLE', '是否可用', '1', '公共属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('5', 'RMALOCATIONTYPE', '退换货地点', '1', '退换货属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('6', 'CURRENCY', '币种', '1', '公共属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('7', 'WAREHOUSE', '仓库', '1', '公共属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('8', 'OWNER', '货主', '1', '公共属性', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('9', 'PartLostRemind', '物料缺失提醒', '1', '', 'admin', '2014-09-30 10:56:15', 'admin', '2014-09-30 10:56:15');
INSERT INTO `t_pms_dictionary` VALUES ('10', 'ProfitLostRemind', '利润缺失提醒', '1', '', 'admin', '2014-10-14 18:24:13', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('11', 'MailHost', '邮件服务器', '1', '', 'admin', '2014-10-23 20:16:35', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('12', 'POMailSender', 'PO邮件发送人', '1', '', 'admin', '2014-10-23 20:18:30', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('13', 'POMailCcs', 'PO邮件抄送人', '1', '', 'admin', '2014-10-23 20:19:31', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('14', 'RFQMailSender', 'RFQ邮件发送人', '1', '', 'admin', '2014-10-23 20:20:50', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('15', 'RFQMailCcs', 'RFQ邮件抄送人', '1', '', 'admin', '2014-10-23 20:21:53', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('16', 'PartLostMailSender', '物料缺失邮件发送人', '1', '', 'admin', '2014-10-23 20:23:06', 'admin', '2014-10-23 22:54:08');
INSERT INTO `t_pms_dictionary` VALUES ('17', 'ProfitLostMailSender', '利润缺失邮件发送人', '1', '', 'admin', '2014-10-23 20:24:30', 'admin', '2014-10-23 22:54:16');
INSERT INTO `t_pms_dictionary` VALUES ('18', 'IsProduct', '是否PRD', '1', '', 'admin', '2014-10-24 11:03:29', 'admin', '2014-10-24 11:29:08');
INSERT INTO `t_pms_dictionary` VALUES ('19', 'POMailFailSender', 'PO邮件发送失败提醒邮件', '1', '', 'admin', '2014-10-25 14:47:54', 'admin', '2014-10-25 15:37:02');
INSERT INTO `t_pms_dictionary` VALUES ('20', 'POMailFailCcs', 'PO邮件发送失败提醒邮件抄送人', '1', '', 'admin', '2014-10-25 14:49:15', 'admin', '2014-10-25 15:37:10');
INSERT INTO `t_pms_dictionary` VALUES ('21', 'SOToPOFailMailCcs', 'SO生成PO失败邮件抄送人', '1', '', 'admin', '2014-11-04 16:41:11', null, '0000-00-00 00:00:00');
INSERT INTO `t_pms_dictionary` VALUES ('22', 'POToSOFailMailCcs', 'PO转SO失败邮件接收人', '1', '', 'admin', '2014-11-05 09:10:09', null, '0000-00-00 00:00:00');
