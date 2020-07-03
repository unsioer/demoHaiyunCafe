CREATE DATABASE IF NOT EXISTS haiyuncafe;


USE haiyuncafe;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `authority` varchar(15) check(Authority='administrator' or Authority='user'),
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`username`,`password`) VALUES(1,'admin','admin');

CREATE TABLE IF NOT EXISTS `menu` (
    `meal_name` varchar(20) PRIMARY KEY,
    `type` varchar(5) check(Type='饮料' or Type='小吃' or Type='主食'),
    `stock` int(5),
    `price` int(5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `orders`(
    `id` varchar(20) REFERENCES `user`(`id`),
    `meal_name` varchar(20) REFERENCES `menu`(`meal_name`)
);