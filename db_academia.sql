
CREATE SCHEMA IF NOT EXISTS `db_academia` DEFAULT CHARACTER SET utf8 ;
USE `db_academia` ;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_estado` (
  `id_estado` INT(11) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_cidade` (
  `id_cidade` INT(11) NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(45) NOT NULL,
  `tb_estado_id_estado` INT(11) NOT NULL,
  PRIMARY KEY (`id_cidade`),
  INDEX `fk_tb_cidade_tb_estado1_idx` (`tb_estado_id_estado` ASC),
  CONSTRAINT `fk_tb_cidade_tb_estado1`
    FOREIGN KEY (`tb_estado_id_estado`)
    REFERENCES `db_academia`.`tb_estado` (`id_estado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_turma` (
  `id_turma` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `dt_inicio` DATE NOT NULL,
  `dt_fim` DATE NOT NULL,
  `tb_pessoa_id_pessoa` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_turma`),
  UNIQUE INDEX `id_turma_UNIQUE` (`id_turma` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


/*INDEX `fk_tb_turma_tb_pessoa1_idx` (`tb_pessoa_id_pessoa` ASC),
  CONSTRAINT `fk_tb_turma_tb_pessoa1`
    FOREIGN KEY (`tb_pessoa_id_pessoa`)
    REFERENCES `db_academia`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)*/

-- -----------------------------------------------------
-- Table `db_academia`.`tb_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_pessoa` (
  `id_pessoa` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'C�DIGO DA PESSOA',
  `nm_pessoa` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL COMMENT 'NOME DA PESSOA',
  `fl_sexo` ENUM('M', 'F') CHARACTER SET 'latin1' COLLATE 'latin1_german1_ci' NOT NULL COMMENT 'INFORMAR M OU F',
  `dt_nascimento` DATE NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
  `foto` LONGBLOB NULL DEFAULT NULL COMMENT 'foto',
  `tipo` ENUM('ADM', 'EST', 'PF') CHARACTER SET 'latin1' NOT NULL COMMENT 'DESCRI��O DO nivel ',
  `cpf` VARCHAR(14) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `dataCadastro` DATE NULL DEFAULT NULL COMMENT 'data cadastro',
  `tb_turma_id_turma` INT(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  INDEX `fk_tb_pessoa_tb_turma1_idx` (`tb_turma_id_turma` ASC),
  CONSTRAINT `fk_tb_pessoa_tb_turma1`
    FOREIGN KEY (`tb_turma_id_turma`)
    REFERENCES `db_academia`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_contato` (
  `id_contato` INT(11) NOT NULL AUTO_INCREMENT,
  `tele1` VARCHAR(45) NOT NULL,
  `tele2` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `tb_pessoa_id_pessoa` INT(11) NOT NULL,
  PRIMARY KEY (`id_contato`),
  UNIQUE INDEX `tb_pessoa_id_pessoa_UNIQUE` (`tb_pessoa_id_pessoa` ASC),
  INDEX `fk_tb_contato_tb_pessoa1_idx` (`tb_pessoa_id_pessoa` ASC),
  CONSTRAINT `fk_tb_contato_tb_pessoa1`
    FOREIGN KEY (`tb_pessoa_id_pessoa`)
    REFERENCES `db_academia`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_endereco` (
  `id_endereco` INT(11) NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(140) NOT NULL,
  `numero` INT(11) NOT NULL,
  `complemento` VARCHAR(140) NULL DEFAULT NULL,
  `cep` VARCHAR(15) NOT NULL,
  `tb_pessoa_id_pessoa` INT(11) NOT NULL,
  `tb_cidade_id_cidade` INT(11) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_tb_endereco_tb_pessoa1_idx` (`tb_pessoa_id_pessoa` ASC),
  INDEX `fk_tb_endereco_tb_cidade1_idx` (`tb_cidade_id_cidade` ASC),
  CONSTRAINT `fk_tb_endereco_tb_cidade1`
    FOREIGN KEY (`tb_cidade_id_cidade`)
    REFERENCES `db_academia`.`tb_cidade` (`id_cidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_endereco_tb_pessoa1`
    FOREIGN KEY (`tb_pessoa_id_pessoa`)
    REFERENCES `db_academia`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_evento` (
  `id_Evento` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `dateInicil` DATETIME NOT NULL,
  `dateFim` DATETIME NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
  `subTitulo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Evento`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_evento_has_tb_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_evento_has_tb_turma` (
  `tb_evento_id_Evento` INT(11) NOT NULL,
  `tb_turma_id_turma` INT(11) NOT NULL,
  PRIMARY KEY (`tb_evento_id_Evento`, `tb_turma_id_turma`),
  INDEX `fk_tb_evento_has_tb_turma_tb_turma1_idx` (`tb_turma_id_turma` ASC),
  INDEX `fk_tb_evento_has_tb_turma_tb_evento1_idx` (`tb_evento_id_Evento` ASC),
  CONSTRAINT `fk_tb_evento_has_tb_turma_tb_evento1`
    FOREIGN KEY (`tb_evento_id_Evento`)
    REFERENCES `db_academia`.`tb_evento` (`id_Evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_evento_has_tb_turma_tb_turma1`
    FOREIGN KEY (`tb_turma_id_turma`)
    REFERENCES `db_academia`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `db_academia`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'C�DIGO DO USU�RIO',
  `mat_login` VARCHAR(9) NOT NULL COMMENT 'LOGIN DO USU�RIO PARA ACESSO AO SISTEMA',
  `mat_senha` VARCHAR(250) NOT NULL COMMENT 'SENHA DO USU�RIO PARA ACESSO AO SISTEMA',
  `tb_pessoa_id_pessoa` INT(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `mat_login_UNIQUE` (`mat_login` ASC),
  INDEX `fk_tb_usuario_tb_pessoa1_idx` (`tb_pessoa_id_pessoa` ASC),
  CONSTRAINT `fk_tb_usuario_tb_pessoa1`
    FOREIGN KEY (`tb_pessoa_id_pessoa`)
    REFERENCES `db_academia`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `db_academia`.`tb_turma` 
ADD INDEX `fk_tb_turma_tb_pessoa1_idx` (`tb_pessoa_id_pessoa` ASC);
 
ALTER TABLE `db_academia`.`tb_turma` 
	ADD CONSTRAINT `fk_tb_turma_tb_pessoa1`
   		FOREIGN KEY (`tb_pessoa_id_pessoa`)
    	REFERENCES `db_academia`.`tb_pessoa` (`id_pessoa`)
    	ON DELETE NO ACTION
    	ON UPDATE NO ACTION;
    
ALTER TABLE `db_academia`.`tb_pessoa` 
	CHANGE COLUMN `foto` `foto` VARCHAR(50) NULL DEFAULT NULL COMMENT 'foto' ;
	
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_diasSemana` (
  `id_diasSemana` SMALLINT(5) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_diasSemana`),
 dom TINYINT NULL DEFAULT 0,
  seg TINYINT NULL DEFAULT 0,
  ter TINYINT NULL DEFAULT 0,
  qua TINYINT NULL DEFAULT 0,
  qui TINYINT NULL DEFAULT 0,
  sex TINYINT NULL DEFAULT 0,
  sab TINYINT NULL DEFAULT 0)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
ALTER TABLE `db_academia`.`tb_turma` 
ADD COLUMN `id_diasSemana` SMALLINT(5) NOT NULL AFTER `tb_pessoa_id_pessoa`,
ADD INDEX `fk_tb_turma_tb_diassemana1_idx` (`id_diasSemana` ASC);
;

ALTER TABLE `db_academia`.`tb_turma` 
ADD CONSTRAINT `fk_tb_turma_tb_diassemana1`
  FOREIGN KEY (`id_diasSemana`)
  REFERENCES `db_academia`.`tb_diassemana` (`id_diasSemana`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
    
CREATE TABLE IF NOT EXISTS `db_academia`.`tb_aula` ( id_aula INT UNSIGNED NOT NULL AUTO_INCREMENT,
          dt_aula DATE NOT NULL,
          hr_inicio TIME NOT NULL,
          hr_fim TIME NOT NULL,
          PRIMARY KEY (id_aula)
        )
        ENGINE = InnoDB
        DEFAULT CHARACTER SET = utf8 
            
ALTER TABLE `db_academia`.`tb_aula` 
ADD COLUMN `tb_turma_id_turma` INT(11) NOT NULL AFTER `hr_fim`,
ADD INDEX `fk_tb_aula_tb_turma1_idx` (`tb_turma_id_turma` ASC);
;

ALTER TABLE `db_academia`.`tb_aula` 
ADD CONSTRAINT `fk_tb_aula_tb_turma1`
  FOREIGN KEY (`tb_turma_id_turma`)
  REFERENCES `db_academia`.`tb_turma` (`id_turma`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;        
  
ALTER TABLE `db_academia`.`tb_aula` 
DROP FOREIGN KEY `fk_tb_aula_tb_turma1`;

ALTER TABLE `db_academia`.`tb_aula` 
CHANGE COLUMN `tb_turma_id_turma` `id_turma` INT(11) NOT NULL ,
ADD INDEX `fk_tb_aula_tb_turma1_idx` (`id_turma` ASC),
DROP INDEX `fk_tb_aula_tb_turma1_idx` ;
;

ALTER TABLE `db_academia`.`tb_aula` 
ADD CONSTRAINT `fk_tb_aula_tb_turma1`
  FOREIGN KEY (`id_turma`)
  REFERENCES `db_academia`.`tb_turma` (`id_turma`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;  

