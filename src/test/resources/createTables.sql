-- MySQL Workbench Synchronization
-- Generated: 2016-04-17 17:35
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Sergey

CREATE SCHEMA IF NOT EXISTS `PhonebookTest` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `PhonebookTest`.`Users` (
  `idUser` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `fullName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `PhonebookTest`.`Contacts` (
  `idContact` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NOT NULL,
  `mobilePhone` VARCHAR(45) NOT NULL,
  `homePhone` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `idUser` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idContact`),
  UNIQUE INDEX `idContact_UNIQUE` (`idContact` ASC),
  INDEX `idUser_idx` (`idUser` ASC),
  CONSTRAINT `idUser`
    FOREIGN KEY (`idUser`)
    REFERENCES `PhonebookTest`.`Users` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
