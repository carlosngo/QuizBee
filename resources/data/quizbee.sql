-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema quizbee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quizbee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quizbee` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `quizbee` ;

-- -----------------------------------------------------
-- Table `quizbee`.`quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizbee`.`quiz` (
  `PK_QuizID` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`PK_QuizID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quizbee`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizbee`.`question` (
  `PK_QuestionID` INT(11) NOT NULL AUTO_INCREMENT,
  `FK_QuizID` INT(11) NULL DEFAULT NULL,
  `Prompt` VARCHAR(300) NOT NULL,
  `Answer` INT(11) NOT NULL,
  `ChoiceA` VARCHAR(45) NOT NULL,
  `ChoiceB` VARCHAR(45) NOT NULL,
  `ChoiceC` VARCHAR(45) NOT NULL,
  `ChoiceD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PK_QuestionID`),
  INDEX `Question_QuizID_idx` (`FK_QuizID` ASC) VISIBLE,
  CONSTRAINT `Question_QuizID`
    FOREIGN KEY (`FK_QuizID`)
    REFERENCES `quizbee`.`quiz` (`PK_QuizID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quizbee`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quizbee`.`user` (
  `PK_UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PK_UserID`),
  UNIQUE INDEX `PK_UserID` (`PK_UserID` ASC) VISIBLE,
  UNIQUE INDEX `Username` (`Username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
