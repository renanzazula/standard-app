-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host:     Database: standard-db-app
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caixa` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `data_abertura` date NOT NULL,
  `data_fechamento` date DEFAULT NULL,
  `hora_abertura` time NOT NULL,
  `hora_fechamento` time DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` double DEFAULT '0',
  `total_desconto` double DEFAULT '0',
  `total_vendas` double DEFAULT '0',
  `valor_final` double DEFAULT '0',
  `valor_inicial` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_has_subcategoria`
--

DROP TABLE IF EXISTS `categoria_has_subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_has_subcategoria` (
  `categoria_codigo` bigint NOT NULL,
  `subcategoria_codigo` bigint NOT NULL,
  PRIMARY KEY (`categoria_codigo`,`subcategoria_codigo`),
  KEY `FKd96wgnlj5knwjqa7fw565cb6f` (`subcategoria_codigo`),
  CONSTRAINT `FKd96wgnlj5knwjqa7fw565cb6f` FOREIGN KEY (`subcategoria_codigo`) REFERENCES `subcategoria` (`codigo`),
  CONSTRAINT `FKg8iod4rvlhb8ssh6vdw282bkv` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_has_subcategoria`
--

LOCK TABLES `categoria_has_subcategoria` WRITE;
/*!40000 ALTER TABLE `categoria_has_subcategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria_has_subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_param`
--

DROP TABLE IF EXISTS `config_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_param` (
  `id` varchar(45) NOT NULL,
  `value` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_param`
--

LOCK TABLES `config_param` WRITE;
/*!40000 ALTER TABLE `config_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dominio`
--

DROP TABLE IF EXISTS `dominio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dominio` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dominio`
--

LOCK TABLES `dominio` WRITE;
/*!40000 ALTER TABLE `dominio` DISABLE KEYS */;
/*!40000 ALTER TABLE `dominio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_de_pagamento`
--

DROP TABLE IF EXISTS `forma_de_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_de_pagamento` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `porcentagem_desconto` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_de_pagamento`
--

LOCK TABLES `forma_de_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_de_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_de_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_tipo_medida`
--

DROP TABLE IF EXISTS `itens_tipo_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itens_tipo_medida` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `categoria_codigo` bigint DEFAULT NULL,
  `marca_codigo` bigint DEFAULT NULL,
  `medida_codigo` bigint DEFAULT NULL,
  `subcategoria_codigo` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK2w8y02rdyce862co3hsrddhou` (`categoria_codigo`),
  KEY `FK7qdj4gsx165mjph8ajg8d3toi` (`marca_codigo`),
  KEY `FKrt4q5y4959n38q2ckawen92ik` (`medida_codigo`),
  KEY `FKc3ekbeo0ke4uasxd0ttjxe8tw` (`subcategoria_codigo`),
  CONSTRAINT `FK2w8y02rdyce862co3hsrddhou` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`),
  CONSTRAINT `FK7qdj4gsx165mjph8ajg8d3toi` FOREIGN KEY (`marca_codigo`) REFERENCES `marca` (`codigo`),
  CONSTRAINT `FKc3ekbeo0ke4uasxd0ttjxe8tw` FOREIGN KEY (`subcategoria_codigo`) REFERENCES `subcategoria` (`codigo`),
  CONSTRAINT `FKrt4q5y4959n38q2ckawen92ik` FOREIGN KEY (`medida_codigo`) REFERENCES `medida` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_tipo_medida`
--

LOCK TABLES `itens_tipo_medida` WRITE;
/*!40000 ALTER TABLE `itens_tipo_medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `itens_tipo_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_failure`
--

DROP TABLE IF EXISTS `login_failure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_failure` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `source_ip` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yuqycsl6io9aivn03yr2hiia` (`user_id`),
  CONSTRAINT `FK7yuqycsl6io9aivn03yr2hiia` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_failure`
--

LOCK TABLES `login_failure` WRITE;
/*!40000 ALTER TABLE `login_failure` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_failure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_success`
--

DROP TABLE IF EXISTS `login_success`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_success` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `source_ip` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_success`
--

LOCK TABLES `login_success` WRITE;
/*!40000 ALTER TABLE `login_success` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_success` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medida`
--

DROP TABLE IF EXISTS `medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medida` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medida`
--

LOCK TABLES `medida` WRITE;
/*!40000 ALTER TABLE `medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `bar_code` varchar(255) NOT NULL,
  `data_hora_cadastro` datetime DEFAULT NULL,
  `desconto` double NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `foto` blob,
  `nome` varchar(45) NOT NULL,
  `peso` double NOT NULL,
  `porcentagem` int NOT NULL,
  `porcentagem_desconto` int NOT NULL,
  `preco` double DEFAULT NULL,
  `preco_custo` double NOT NULL,
  `preco_oferta` double NOT NULL,
  `preco_venda` double NOT NULL,
  `status` varchar(255) NOT NULL,
  `categoria_codigo` bigint NOT NULL,
  `fornecedor_codigo` bigint NOT NULL,
  `marca_codigo` bigint NOT NULL,
  `medida_codigo` bigint NOT NULL,
  `subcategoria_codigo` bigint NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKtfuf17yvliycysg3vt5h0sp2v` (`categoria_codigo`),
  KEY `FKpvyafr9m7vpu95rd3uq7fja5g` (`fornecedor_codigo`),
  KEY `FKc1yyrbyl61fympj6ams5ou9qm` (`marca_codigo`),
  KEY `FKiwd9fe0gfcp0rrts6ifn8mw1b` (`medida_codigo`),
  KEY `FKnd8vfywu4t6sl6yy4614nuuc` (`subcategoria_codigo`),
  CONSTRAINT `FKc1yyrbyl61fympj6ams5ou9qm` FOREIGN KEY (`marca_codigo`) REFERENCES `marca` (`codigo`),
  CONSTRAINT `FKiwd9fe0gfcp0rrts6ifn8mw1b` FOREIGN KEY (`medida_codigo`) REFERENCES `medida` (`codigo`),
  CONSTRAINT `FKnd8vfywu4t6sl6yy4614nuuc` FOREIGN KEY (`subcategoria_codigo`) REFERENCES `subcategoria` (`codigo`),
  CONSTRAINT `FKpvyafr9m7vpu95rd3uq7fja5g` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`),
  CONSTRAINT `FKtfuf17yvliycysg3vt5h0sp2v` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_has_itens_tipo_medida`
--

DROP TABLE IF EXISTS `produto_has_itens_tipo_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto_has_itens_tipo_medida` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  `itens_tipo_medida_codigo` bigint DEFAULT NULL,
  `produto_codigo` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK8ia63yfyhfqlbwb77mkssssgd` (`itens_tipo_medida_codigo`),
  KEY `FKqjya8flllhojml0d9mnevq0rq` (`produto_codigo`),
  CONSTRAINT `FK8ia63yfyhfqlbwb77mkssssgd` FOREIGN KEY (`itens_tipo_medida_codigo`) REFERENCES `itens_tipo_medida` (`codigo`),
  CONSTRAINT `FKqjya8flllhojml0d9mnevq0rq` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_has_itens_tipo_medida`
--

LOCK TABLES `produto_has_itens_tipo_medida` WRITE;
/*!40000 ALTER TABLE `produto_has_itens_tipo_medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto_has_itens_tipo_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_has_itens_tipo_medida_has_dominio`
--

DROP TABLE IF EXISTS `produto_has_itens_tipo_medida_has_dominio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto_has_itens_tipo_medida_has_dominio` (
  `produto_has_itens_tipo_medida_codigo` bigint NOT NULL,
  `dominio_codigo` bigint NOT NULL,
  PRIMARY KEY (`produto_has_itens_tipo_medida_codigo`,`dominio_codigo`),
  KEY `FK7k3c99iu4qy82w8dmy0vwy5u4` (`dominio_codigo`),
  CONSTRAINT `FK2f8eg726fks5l5ml3gvisqob7` FOREIGN KEY (`produto_has_itens_tipo_medida_codigo`) REFERENCES `produto_has_itens_tipo_medida` (`codigo`),
  CONSTRAINT `FK7k3c99iu4qy82w8dmy0vwy5u4` FOREIGN KEY (`dominio_codigo`) REFERENCES `dominio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_has_itens_tipo_medida_has_dominio`
--

LOCK TABLES `produto_has_itens_tipo_medida_has_dominio` WRITE;
/*!40000 ALTER TABLE `produto_has_itens_tipo_medida_has_dominio` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto_has_itens_tipo_medida_has_dominio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recebimento`
--

DROP TABLE IF EXISTS `recebimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recebimento` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `caixa_codigo` bigint DEFAULT NULL,
  `cliente_codigo` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKkd71q7664qvorwu0e9ilwhetp` (`caixa_codigo`),
  KEY `FK7gm81ocgo627b5pjl316rs28u` (`cliente_codigo`),
  CONSTRAINT `FK7gm81ocgo627b5pjl316rs28u` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `FKkd71q7664qvorwu0e9ilwhetp` FOREIGN KEY (`caixa_codigo`) REFERENCES `caixa` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recebimento`
--

LOCK TABLES `recebimento` WRITE;
/*!40000 ALTER TABLE `recebimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `recebimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retirada`
--

DROP TABLE IF EXISTS `retirada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retirada` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` double NOT NULL,
  `caixa_codigo` bigint NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK4p04dhtoghxk1dlcqsgc8668e` (`caixa_codigo`),
  CONSTRAINT `FK4p04dhtoghxk1dlcqsgc8668e` FOREIGN KEY (`caixa_codigo`) REFERENCES `caixa` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retirada`
--

LOCK TABLES `retirada` WRITE;
/*!40000 ALTER TABLE `retirada` DISABLE KEYS */;
/*!40000 ALTER TABLE `retirada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_authority` (
  `role_id` bigint NOT NULL,
  `authority_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `FKqbri833f7xop13bvdje3xxtnw` (`authority_id`),
  CONSTRAINT `FK2052966dco7y9f97s1a824bj1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKqbri833f7xop13bvdje3xxtnw` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategoria` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credential_non_expired` bit(1) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_pass`
--

DROP TABLE IF EXISTS `user_pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_pass` (
  `user_id` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK48occyjanogoyt1252y5b2tfx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_pass`
--

LOCK TABLES `user_pass` WRITE;
/*!40000 ALTER TABLE `user_pass` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_session` (
  `user_id` varchar(64) NOT NULL,
  `active_sessions` varchar(1024) DEFAULT NULL,
  `application` varchar(1) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_session`
--

LOCK TABLES `user_session` WRITE;
/*!40000 ALTER TABLE `user_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `hora` datetime DEFAULT NULL,
  `pagamento` double DEFAULT NULL,
  `quantidade` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `total_apagar` double DEFAULT NULL,
  `troco` double DEFAULT NULL,
  `valor_pago` double DEFAULT NULL,
  `valor_pendente` double DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `caixa_codigo` bigint NOT NULL,
  `cliente_codigo` bigint NOT NULL,
  `formas_de_pagamento_codigo` bigint NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKcpovrb0jietoi8io1hu7y3w1i` (`caixa_codigo`),
  KEY `FK7fxgfskuip18oucp6s7p0iygu` (`cliente_codigo`),
  KEY `FKs2fb3901rfrvqljjqftv59ulw` (`formas_de_pagamento_codigo`),
  CONSTRAINT `FK7fxgfskuip18oucp6s7p0iygu` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `FKcpovrb0jietoi8io1hu7y3w1i` FOREIGN KEY (`caixa_codigo`) REFERENCES `caixa` (`codigo`),
  CONSTRAINT `FKs2fb3901rfrvqljjqftv59ulw` FOREIGN KEY (`formas_de_pagamento_codigo`) REFERENCES `forma_de_pagamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda_has_item_produto`
--

DROP TABLE IF EXISTS `venda_has_item_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda_has_item_produto` (
  `codigo` bigint NOT NULL AUTO_INCREMENT,
  `alterado_por` bigint DEFAULT NULL,
  `criado_por` bigint DEFAULT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `hora_criacao` time DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  `produto_has_itens_tipo_medida_codigo` bigint DEFAULT NULL,
  `venda_codigo` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK3koi1qe1xc9m4grgfpebvxutw` (`produto_has_itens_tipo_medida_codigo`),
  KEY `FKngotbekt6jdb6l2qnionsxnrk` (`venda_codigo`),
  CONSTRAINT `FK3koi1qe1xc9m4grgfpebvxutw` FOREIGN KEY (`produto_has_itens_tipo_medida_codigo`) REFERENCES `produto_has_itens_tipo_medida` (`codigo`),
  CONSTRAINT `FKngotbekt6jdb6l2qnionsxnrk` FOREIGN KEY (`venda_codigo`) REFERENCES `venda` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda_has_item_produto`
--

LOCK TABLES `venda_has_item_produto` WRITE;
/*!40000 ALTER TABLE `venda_has_item_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda_has_item_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'standard-db-app'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-21  0:04:35
