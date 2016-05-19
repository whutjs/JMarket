DROP TABLE IF EXISTS `t_itemtype`;
CREATE TABLE `t_itemtype` (
  `T_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '物品类别的编号，默认0表示未分类',
  `T_mainclass` varchar(50) NOT NULL DEFAULT '未分类' COMMENT '物品的1级类目，默认为未分类',
  `T_secondclass` varchar(50) NOT NULL DEFAULT '未分类' COMMENT '物品的2级类目，默认为未分类',
  PRIMARY KEY (`T_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品类型表';


INSERT INTO `t_itemtype` VALUES ('0','未分类','未分类'),('1','二手车','普通自行车'),('10','服装包箱','衬衫'),('11','服装包箱','运动鞋'),('12','服装包箱','皮鞋'),('13','服装包箱','双肩包'),('14','二手家电','其他家电'),('15','图书音像','专业书籍'),('16','图书音像','小说/文学'),('17','图书音像','iwatch/ipod/mp3'),('18','生活用品','雨伞'),('19','生活用品','家具'),('2','二手车','山地自行车'),('20','生活用品','茶杯/水杯'),('21','生活用品','其他'),('3','二手手机','苹果'),('4','二手手机','三星'),('5','二手手机','小米'),('6','PC/PAD','苹果'),('7','PC/PAD','Thinkpad'),('8','PC/PAD','索尼'),('9','PC/PAD','联想');


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `U_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户的id',
  `U_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `U_password` varchar(20) NOT NULL DEFAULT '' COMMENT '用户密码',
  `U_sex` varchar(5) DEFAULT NULL COMMENT '用户的性别',
  `U_email` varchar(128) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `U_type` varchar(5) DEFAULT '1' COMMENT '表明用户的类型，普通用户(1)或者管理员(0)',
  `U_qq` varchar(20) DEFAULT NULL COMMENT '用户的qq号码',
  `U_wechat` varchar(128) DEFAULT NULL COMMENT '用户的微信号',
  `U_image` varchar(255) DEFAULT NULL COMMENT '用户头像的图片路径',
  `U_flag` varchar(5) NOT NULL DEFAULT '0' COMMENT '用户的状态，0是未经过邮箱验证的用户，1是邮箱验证过的',
  `U_regtime` datetime DEFAULT NULL COMMENT '用户注册时间',
  `U_phone` varchar(20) DEFAULT NULL COMMENT '用户的手机号码',
  PRIMARY KEY (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


INSERT INTO `t_user` VALUES ('Ulizongyao12','JMUlizongyao12','654321',NULL,'lizongyao12@sjtu.edu.cn','1',NULL,NULL,NULL,'1','2015-12-04 11:44:20',NULL),('Ulzy','JMUlzy','666666',NULL,'lzy@sjtu.edu.cn','1',NULL,NULL,NULL,'1','2015-12-04 02:25:28',NULL),('Utjs','JMUtjs','123456',NULL,'tjs@sjtu.edu.cn','1',NULL,NULL,NULL,'1','2015-12-04 01:15:34',NULL),('Uxueshuai','JMUxueshuai','123456',NULL,'xueshuai@sjtu.edu.cn','1',NULL,NULL,NULL,'1','2015-12-04 12:13:05',NULL),('Uy.d.li','JMUy.d.li','666666',NULL,'y.d.li@sjtu.edu.cn','1',NULL,NULL,NULL,'1','2015-12-04 10:48:27',NULL);


DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `I_id` varchar(50) NOT NULL DEFAULT '' COMMENT '物品编号',
  `I_name` varchar(128) NOT NULL DEFAULT '' COMMENT '物品的名称',
  `I_description` text NOT NULL COMMENT '物品的描述',
  `I_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '物品添加时间',
  `I_flag` varchar(5) NOT NULL DEFAULT '0' COMMENT '物品的状态0表示未售出，1表示已经售出',
  `I_type` varchar(50) NOT NULL DEFAULT '0' COMMENT '这里存放的是物品类型的编号,0表示未分类',
  `I_owner` varchar(50) NOT NULL DEFAULT '0' COMMENT '物品的拥有者',
  `I_price` float NOT NULL DEFAULT '0' COMMENT '商品价格',
  `I_place` varchar(100) DEFAULT NULL COMMENT '商品位置',
  `I_state` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`I_id`),
  KEY `item_user` (`I_owner`) COMMENT '与t_user表的外键',
  CONSTRAINT `item_user` FOREIGN KEY (`I_owner`) REFERENCES `t_user` (`U_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品表';


INSERT INTO `t_item` VALUES ('1451386181149_3712599_Ulizongyao12','3546546143','564687687645458','2015-12-29 18:49:41','0','0','Ulizongyao12',123,'闵行校区',9),('Ibag','双肩包','无印良品','2015-12-20 11:11:11','1','13','Utjs',289,'西21',8),('Ibike','自行车','七成新，白色','2015-12-27 14:23:44','0','1','Ulzy',256,'西21',7),('Ibike1','捷安特自行车','红色，性能佳','2015-12-23 18:17:08','1','2','Utjs',512,'西31',9),('Ibike11','自行车','蓝色，没怎么骑过，要去实习了找人出手','2015-12-29 14:34:57','0','1','Uxueshuai',180,'西32',8),('Ibook','计算机操作系统','机械工业出版社，第五版','2015-12-22 22:28:31','1','15','Utjs',30,'二餐',8),('Ibook1','计算机网络自顶向下方法','考研必备，只要十块钱','2015-12-24 15:31:18','1','15','Uy.d.li',10,'电院3229',9),('Ibook11','thinking in java','10th edition','2015-12-27 20:04:09','0','15','Uxueshuai',40,'电院3229',7),('Ibook111','艺术史','原版彩色','2015-12-29 15:34:07','0','16','Ulizongyao12',99,'西23',9),('Icandle','香薰蜡烛','薰衣草味','2015-12-29 15:37:53','0','0','Uy.d.li',80,'电院3227',9),('Ichair','转椅','黑色，很软','2015-12-29 15:12:28','0','19','Uy.d.li',300,'东21',7),('Ichair1','折叠椅','蓝色，可以放阳台上','2015-12-28 15:13:10','0','19','Ulizongyao12',249,'西22',8),('Icup','hellokitty咖啡杯','大红色hellokitty，限量版！！','2015-12-27 12:33:59','1','20','Uxueshuai',50,'西23',9),('Icup1','保温杯','350ml膳魔师','2015-12-29 10:18:47','0','20','Uy.d.li',150,'东31',9),('Ikeyboard','机械键盘','青轴键盘，女朋友要我卖了TTTTTTT','2015-12-28 15:38:48','0','14','Ulizongyao12',350,'东32',10),('Ilaptop','旧款苹果macair','13年买的，准备换电脑了','2015-12-25 10:17:22','0','6','Utjs',4999,'西22',7),('Ilaptop1','笔记本儿','联想yoga，贱卖','2015-12-22 11:19:23','0','9','Uxueshuai',2099,'西21',5),('Ilaptop11','旧款thinkpad','x60，黑色，比较新','2015-12-29 14:59:17','0','7','Ulizongyao12',3500,'西31',7),('Ilaptop111','一本笔记本','旧款vaio，粉红色','2015-12-29 15:01:07','0','8','Uxueshuai',4000,'西32',5),('Iphone','iphone6','64G，银色，保护完好','2015-12-28 15:39:57','0','3','Utjs',4500,'西32',8),('Ishirt','衬衫','一件黑色的，好看的，衬衫','2015-12-21 15:23:40','1','10','Utjs',100,'西23',8),('Ishirt1','格子衬衫','作为一个交大男怎么可以不穿格子衬衫','2015-12-28 15:25:56','0','10','Uy.d.li',200,'西21',7),('Ishoe','二手AJ','白色，还很新，41码','2015-12-24 15:35:17','1','11','Ulzy',400,'西23',8),('Ishoe1','一双靴子','全新，买小了，八折出','2015-12-27 15:36:43','0','12','Uxueshuai',400,'西23',10),('Iumbrella','一把粉红色的小伞','粉红色爱心图案折叠伞','2015-12-26 08:18:43','1','18','Uy.d.li',20,'东21',8),('Iumbrella1','黑色长柄伞','可以容纳两个人','2015-12-25 15:06:20','1','18','Uy.d.li',50,'东21',8),('Iwatch','iwatch','白色iwatch！','2015-12-29 15:07:12','0','17','Uxueshuai',2500,'西23',9);



DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `M_id` varchar(50) NOT NULL DEFAULT '' COMMENT '留言编号',
  `M_item` varchar(50) NOT NULL DEFAULT '' COMMENT '发表留言的物品编号',
  `M_user` varchar(50) NOT NULL DEFAULT '' COMMENT '留言用户编号',
  `M_parentid` varchar(50) DEFAULT NULL COMMENT '回复消息的父id',
  `M_content` text NOT NULL COMMENT '留言的内容',
  `M_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '留言发表的时间',
  PRIMARY KEY (`M_id`),
  KEY `message_message` (`M_parentid`) COMMENT '与t_message表的外键',
  KEY `message_user` (`M_user`) COMMENT '与t_user表的外键',
  KEY `message_item` (`M_item`) COMMENT '与t_item表的外键',
  CONSTRAINT `message_item` FOREIGN KEY (`M_item`) REFERENCES `t_item` (`I_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_message` FOREIGN KEY (`M_parentid`) REFERENCES `t_message` (`M_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_user` FOREIGN KEY (`M_user`) REFERENCES `t_user` (`U_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';



DROP TABLE IF EXISTS `t_itemphoto`;
CREATE TABLE `t_itemphoto` (
  `P_id` varchar(50) NOT NULL DEFAULT '' COMMENT '物品照片的编号',
  `P_itemId` varchar(50) NOT NULL DEFAULT '0' COMMENT '照片所对应商品的编号',
  `P_path` varchar(255) DEFAULT NULL COMMENT '物品照片的路径',
  PRIMARY KEY (`P_id`),
  KEY `photo_item` (`P_itemId`) COMMENT '与t_item表的外键',
  CONSTRAINT `photo_item` FOREIGN KEY (`P_itemId`) REFERENCES `t_item` (`I_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品照片表';


INSERT INTO `t_itemphoto` VALUES ('1451386181149_3712599_Ulizongyao121213','1451386181149_3712599_Ulizongyao12','');


DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `C_user` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `C_item` varchar(50) DEFAULT NULL COMMENT '物品编号',
  `C_id` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`C_id`),
  KEY `collection_user` (`C_user`) COMMENT '与t_user表的外键',
  KEY `collection_item` (`C_item`) COMMENT '与t_item表的外键',
  CONSTRAINT `collection_item` FOREIGN KEY (`C_item`) REFERENCES `t_item` (`I_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collection_user` FOREIGN KEY (`C_user`) REFERENCES `t_user` (`U_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏表';


