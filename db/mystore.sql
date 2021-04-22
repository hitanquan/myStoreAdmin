CREATE DATABASE mystore;

CREATE TABLE `t_admin` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id' ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名' ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22
ROW_FORMAT=COMPACT;



CREATE TABLE `t_category` (
`cid`  int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id' ,
`cname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称' ,
PRIMARY KEY (`cid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=7
ROW_FORMAT=COMPACT;


CREATE TABLE `t_goods` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id，自增主键' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称' ,
`price`  decimal(10,0) NULL DEFAULT NULL COMMENT '商品价格' ,
`image`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片，存的名称' ,
`gdesc`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述' ,
`is_hot`  int(2) NULL DEFAULT NULL COMMENT '是否热门，1表示热门，0表示不热门' ,
`cid`  int(11) NULL DEFAULT NULL COMMENT '商品分类id' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`cid`) REFERENCES `t_category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `fk_cid` (`cid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=24
ROW_FORMAT=COMPACT;

INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (3, '谭权', '1234');
INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (6, 'lisi', 'lisi');
INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (7, '王五', 'wangwu');
INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (10, '田七', 'tianqi');
INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (12, '哈哈', '1222');
INSERT INTO `t_admin` (`id`, `username`, `password`) VALUES (19, 'wangwu', '1234');

INSERT INTO `t_category` (`cid`, `cname`) VALUES (1, '女性服装');
INSERT INTO `t_category` (`cid`, `cname`) VALUES (2, '零食');
INSERT INTO `t_category` (`cid`, `cname`) VALUES (3, '数码产品');
INSERT INTO `t_category` (`cid`, `cname`) VALUES (4, '男性服装');


INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (1, '梵希蔓短袖衬衣女2018新款夏季气质韩版通勤', 159, 'goods_001.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (2, '姿忆秀五分袖宽松衬衫女2018夏装新款竖条纹\r\n', 88, 'goods_002.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (3, '哇哈哈', 3, 'goods_001.png', '哇哈哈哈哈', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (4, '可口可乐', 5, 'goods_001.png', '可口可口乐', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (5, '百事可乐', 5, 'goods_001.png', '你喝吗哈哈哈哈', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (6, '威龙', 5, 'goods_001.png', '哈哈哈哈哈', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (7, '小王子', 5, 'goods_001.png', '哈哈哈哈', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (8, '华为手机', 5000, 'goods_001.png', '买卖啊妈妈妈妈', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (9, '行李箱', 300, 'goods_001.png', '嘻嘻嘻嘻嘻', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (10, '连衣裙', 199, 'goods_001.png', '哈哈哈哈哈哈', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (11, '梵希蔓短袖雪纺衬衫短款2018夏季新款女装韩\r\n', 176, 'goods_003.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (12, '2018夏季新款雪纺衬衫女上衣职业OL短袖衬衣韩版修身休闲', 159, 'goods_004.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (13, '电脑椅家用电竞椅 人体工学椅子座椅网布转椅可躺老板椅办公椅', 269, 'goods_005.png', '', 1, 2);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (14, '电脑椅家用座椅办公椅主播椅子游戏椅网吧电竞椅可躺午休', 699, 'goods_006.png', '', 1, 3);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (15, '百图 立领条纹衬衫女套头短袖百搭雪纺衫上\r\n', 179, 'goods_007.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (16, '不锈钢卫浴室柜组合小户型挂墙式洗手洗脸盆池卫生间厕所洗漱台盆', 305, 'goods_008.png', '', 1, 2);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (17, '北欧双盆浴室柜组合简约卫浴柜镜柜卫生间洗手盆洗脸盆洗漱台盆柜', 900, 'goods_009.png', '', 1, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (18, '简约现代浴室柜洗手盆柜组合洗脸卫生间小户型厕所洗漱台卫浴吊柜', 948, 'goods_10.png', '', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (19, '三只鸟旅行箱拉杆箱女铝框20寸行李箱万向轮24寸学生密码箱登机箱', 268, 'goods_11.png', '', 0, 3);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (20, '全铝镁铝合金拉杆箱金属旅行箱子男女万向轮行李箱商务学生登机箱', 375, 'goods_12.png', '', 0, 4);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (21, '床上双人电脑桌 台式电脑桌家用笔记本电脑桌 ', 89, 'goods_13.png', '', 0, 1);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (22, '男童鞋儿童运动鞋2018春秋新款夏季网面休闲透气小白网鞋女童鞋子', 79, 'goods_14.png', '', 0, 4);
INSERT INTO `t_goods` (`id`, `name`, `price`, `image`, `gdesc`, `is_hot`, `cid`) VALUES (23, '大码宽松休闲立领短袖纯棉衬衫格子上衣衬衣\r\n', 149, 'goods_15.png', '', 0, 4);
