-- Create user information table --

CREATE TABLE `t_user` (
  `id` INT(64) NOT NULL AUTO_INCREMENT COMMENT 'Inner unique ID of user ',
  `user_name` varchar(20) NOT NULL COMMENT 'Nickname of user',
  `sex` INT(32) NOT NULL COMMENT 'sex 1 MAN, 2 FEMALE',
  `note` varchar(45) NOT NULL COMMENT 'comment',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
