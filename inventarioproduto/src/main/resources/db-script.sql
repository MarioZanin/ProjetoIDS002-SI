-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projeto_inventprodut
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `projeto_inventprodut`;

CREATE SCHEMA IF NOT EXISTS `projeto_inventprodut` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `projeto_inventprodut`;

-- -----------------------------------------------------
-- Table `projeto_inventprodut`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_inventprodut`.`produto`;

CREATE TABLE IF NOT EXISTS `projeto_inventprodut`.`produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nm_nome` VARCHAR(255) NOT NULL,
  `ds_descricao` TEXT NULL,
  `qt_Minima` INT NOT NULL,
  `qt_Maxima` INT NOT NULL,
  `pr_preco` DECIMAL NOT NULL,
  `ct_categoria` VARCHAR(255) NOT NULL,
  `qt_quantidade` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `projeto_inventprodut`.`tb_fornecedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_inventprodut`.`fornecedor`;

CREATE TABLE IF NOT EXISTS `projeto_inventprodut`.`fornecedor` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nm_fornecedor` VARCHAR(255) NOT NULL,
  `cd_cnpj` VARCHAR(14) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `data_entrada` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `projeto_inventprodut`.`tb_movimentacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_inventprodut`.`movimentacao`;

CREATE TABLE IF NOT EXISTS `projeto_inventprodut`.`movimentacao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `produto_id` BIGINT NOT NULL,
  `quantidade` INT NOT NULL,
  `data_movimentacao` DATETIME NOT NULL,
  `fornecedor_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_idx` (`produto_id` ASC) VISIBLE,
  INDEX `fk_fornecedor_idx` (`fornecedor_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto`
    FOREIGN KEY (`produto_id`)
    REFERENCES `projeto_inventprodut`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedor`
    FOREIGN KEY (`fornecedor_id`)
    REFERENCES `projeto_inventprodut`.`fornecedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;