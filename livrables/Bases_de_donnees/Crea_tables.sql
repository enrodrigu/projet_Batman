-- -----------------------------------------------------
-- Schema info_batman_schema
-- -----------------------------------------------------
USE `info_batman_schema` ;

-- -----------------------------------------------------
-- Table `info_batman_schema`.`DJ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_batman_schema`.`DJ` (
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `nom_scene` VARCHAR(45) NOT NULL,
  `date_naissance` VARCHAR(45) NOT NULL,
  `lieu_residence` VARCHAR(255) NOT NULL,
  `style_musical` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nom_scene`));


-- -----------------------------------------------------
-- Table `info_batman_schema`.`Lieu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_batman_schema`.`Lieu` (
  `nom_lieu` VARCHAR(45) NOT NULL,
  `ville` VARCHAR(45) NOT NULL,
  `pays` VARCHAR(45) NOT NULL,
  `continent` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nom_lieu`, `ville`));


-- -----------------------------------------------------
-- Table `info_batman_schema`.`Evenement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_batman_schema`.`Evenement` (
  `date_debut_evenement` DATETIME NOT NULL,
  `date_fin_evenement` DATETIME NOT NULL,
  `DJ` VARCHAR(45) NOT NULL,
  `lieu_evenement` VARCHAR(45) NOT NULL,
  `ville_evenement` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`date_debut_evenement`, `DJ`),
  INDEX `DJ_idx` (`DJ` ASC) VISIBLE,
  INDEX `lieu_idx` (`lieu_evenement` ASC, `ville_evenement` ASC) VISIBLE,
  CONSTRAINT `DJ`
    FOREIGN KEY (`DJ`)
    REFERENCES `info_batman_schema`.`DJ` (`nom_scene`),
  CONSTRAINT `fk_lieu`
    FOREIGN KEY (`lieu_evenement` , `ville_evenement`)
    REFERENCES `info_batman_schema`.`Lieu` (`nom_lieu` , `ville`));
