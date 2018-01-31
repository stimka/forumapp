-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gameforum
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gameforum
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gameforum` DEFAULT CHARACTER SET utf8 ;
USE `gameforum` ;

-- -----------------------------------------------------
-- Table `gameforum`.`section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`section` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gameforum`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `sex` ENUM('M', 'F', 'N') NULL DEFAULT 'N',
  `birthday` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `city` VARCHAR(50) NULL DEFAULT NULL,
  `register_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_date` DATE NULL DEFAULT NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `iduser_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gameforum`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`topic` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `section_id` INT(11) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NOT NULL,
  `views` INT(11) NOT NULL DEFAULT '0',
  `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` TIMESTAMP NULL DEFAULT NULL,
  `is_closed` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idthread_UNIQUE` (`id` ASC),
  INDEX `fk_idsection_index` (`section_id` ASC),
  INDEX `fk_iduser_index` (`user_id` ASC),
  CONSTRAINT `fk_topics_idsection`
    FOREIGN KEY (`section_id`)
    REFERENCES `gameforum`.`section` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_topics_iduser`
    FOREIGN KEY (`user_id`)
    REFERENCES `gameforum`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gameforum`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`post` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `topic_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `content` TEXT NOT NULL,
  `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_iduser_index` (`user_id` ASC),
  INDEX `fk_idtopic_index` (`topic_id` ASC),
  CONSTRAINT `fk_posts_idtopic`
    FOREIGN KEY (`topic_id`)
    REFERENCES `gameforum`.`topic` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_posts_iduser`
    FOREIGN KEY (`user_id`)
    REFERENCES `gameforum`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 79
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gameforum`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gameforum`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gameforum`.`user_has_role` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_idrole_index` (`role_id` ASC),
  INDEX `fk_iduser_index` (`user_id` ASC),
  CONSTRAINT `fk_rolesofusers_idrole`
    FOREIGN KEY (`role_id`)
    REFERENCES `gameforum`.`user_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_rolesofusers_iduser`
    FOREIGN KEY (`user_id`)
    REFERENCES `gameforum`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
