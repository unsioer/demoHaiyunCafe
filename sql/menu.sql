CREATE DATABASE IF NOT EXISTS haiyuncafe;
USE haiyuncafe;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `picturepath` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(1, '拿铁', 100, 18, '饮料', 'img/拿铁.jpg');
INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(2, '抹茶蛋糕', 66, 21, '小吃', 'img/抹茶蛋糕.jpg');
INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(3, '意式肉酱面', 200, 25, '主食', 'img/意式肉酱面.jpg');
INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(4, '慕斯蛋糕', 88, 19, '小吃', 'img/慕斯蛋糕.jpg');
INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(5, '柠檬水', 250, 10, '饮料', 'img/柠檬水.jpg');
INSERT INTO `menu`(`id`, `itemname`, `number`, `price`, `type`, `picturepath`)
  VALUES(6, '提拉米苏', 300, 17, '小吃', 'img/提拉米苏.jpg');