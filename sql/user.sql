CREATE DATABASE IF NOT EXISTS haiyuncafe;
USE haiyuncafe;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `authority` varchar(15) check(Authority='administrator' or Authority='user'),
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`username`,`password`,`authority`) VALUES(1,'admin','admin','administrator');
INSERT INTO `user`(`id`,`username`,`password`,`authority`,`email`,`phone`) VALUES(2,'test','test','user','test@example.com','13000000000');
INSERT INTO `user`(`id`,`username`,`password`,`authority`,`email`,`phone`) VALUES(3,'test2','test2','user','test2@example.com','13000000001');
