

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema court_reservation_manager
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `learn_english` ;

-- -----------------------------------------------------
-- Schema court_reservation_manager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `learn_english` DEFAULT CHARACTER SET utf8 ;
USE `learn_english` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `best_score` VARCHAR(45) NOT NULL,
  `account_created` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- Table `booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_history` ;

CREATE TABLE IF NOT EXISTS `game_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `game_time` TIMESTAMP NOT NULL,
  `game_score` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_game_history_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE INDEX `fk_game_history_user_idx` ON `game_history` (`user_id` ASC) VISIBLE;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT "ROLE_USER",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_01` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`)
  ON DELETE CASCADE ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
