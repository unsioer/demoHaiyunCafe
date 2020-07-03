CREATE DATABASE IF NOT EXISTS haiyuncafe;
USE haiyuncafe;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(32) NOT NULL,
  `number` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
