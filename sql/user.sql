CREATE DATABASE IF NOT EXISTS my_database;


USE my_database;

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`username`,`password`) VALUES(1,'admin','admin');