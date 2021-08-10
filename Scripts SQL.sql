-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paymybuddy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymybuddy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `paymybuddy` ;

-- -----------------------------------------------------
-- Table `paymybuddy`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`login` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`user` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(50) NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `birthDate` DATE NOT NULL,
  `login_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_login_id_idx` (`login_id` ASC) VISIBLE,
  CONSTRAINT `FK_user_login_id`
    FOREIGN KEY (`login_id`)
    REFERENCES `paymybuddy`.`login` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`contact` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `user_id` SMALLINT NOT NULL,
  `user_contact_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_contact_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_contact_user_contact_id_idx` (`user_contact_id` ASC) VISIBLE,
  CONSTRAINT `FK_contact_user_contact_id`
    FOREIGN KEY (`user_contact_id`)
    REFERENCES `paymybuddy`.`user` (`id`),
  CONSTRAINT `FK_contact_user_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`external_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`external_account` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `IBAN` VARCHAR(50) NOT NULL,
  `user_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `IBAN_UNIQUE` (`IBAN` ASC) VISIBLE,
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_externalaccount_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`internal_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`internal_account` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL(8,2) NOT NULL,
  `user_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_internalaccount_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymybuddy`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`external_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`external_transaction` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `transferredAmount` DECIMAL(8,2) NOT NULL,
  `externalaccount_id` SMALLINT NOT NULL,
  `internalaccount_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_external_id_idx` (`externalaccount_id` ASC) VISIBLE,
  INDEX `FK_internal_id_idx` (`internalaccount_id` ASC) VISIBLE,
  CONSTRAINT `FK_externaltransaction_externalaccount_id`
    FOREIGN KEY (`externalaccount_id`)
    REFERENCES `paymybuddy`.`external_account` (`id`),
  CONSTRAINT `FK_externaltransaction_internalaccount_id`
    FOREIGN KEY (`internalaccount_id`)
    REFERENCES `paymybuddy`.`internal_account` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `paymybuddy`.`internal_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymybuddy`.`internal_transaction` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `transferredAmount` DECIMAL(8,2) NOT NULL,
  `sender_internalaccount_id` SMALLINT NOT NULL,
  `recipient_internalaccount_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_transaction_internal_account_id_idx` (`sender_internalaccount_id` ASC) VISIBLE,
  INDEX `FK_recipient_id_idx` (`recipient_internalaccount_id` ASC) VISIBLE,
  CONSTRAINT `FK_internaltransaction_internalaccount_recipient_id`
    FOREIGN KEY (`recipient_internalaccount_id`)
    REFERENCES `paymybuddy`.`internal_account` (`id`),
  CONSTRAINT `FK_internaltransaction_internalaccount_sender_id`
    FOREIGN KEY (`sender_internalaccount_id`)
    REFERENCES `paymybuddy`.`internal_account` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
