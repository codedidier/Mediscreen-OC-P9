-- -----------------------------------------------------
-- Schema Mediscreen
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mediscreen` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

USE `mediscreen` ;

-- -----------------------------------------------------
-- Table `mediscreen`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mediscreen`.`patient` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(15) NULL,
  PRIMARY KEY (`id`));