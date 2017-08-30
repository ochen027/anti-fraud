-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: aml
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `individual`
--

DROP TABLE IF EXISTS `individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individual` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `sus_flg_indiv` bit(1) DEFAULT NULL,
  `resi_flg` bit(1) DEFAULT NULL,
  `pep_flg` bit(1) DEFAULT NULL,
  `cust_age` int(11) DEFAULT NULL,
  `cust_birth_dt` datetime DEFAULT NULL,
  `cust_ct_no_indiv` varchar(255) DEFAULT NULL,
  `cust_city` varchar(255) DEFAULT NULL,
  `cust_country` varchar(255) DEFAULT NULL,
  `cust_id` varchar(255) DEFAULT NULL,
  `cust_occupation` varchar(255) DEFAULT NULL,
  `cust_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual`
--

LOCK TABLES `individual` WRITE;
/*!40000 ALTER TABLE `individual` DISABLE KEYS */;
INSERT INTO `individual` VALUES (1,NULL,NULL,'2017-08-29 16:00:04',NULL,'','\0','\0','\0',25,'1992-07-01 00:00:00','321123199207010000','Shanghai','CN','10001','Student','+86-16547650900'),(2,NULL,NULL,'2017-08-29 16:00:04',NULL,'','\0','\0','\0',30,'1987-07-01 00:00:00','321123198707010000','Shanghai','CN','10005','Student','+86-13937543557'),(3,NULL,NULL,'2017-08-29 16:00:04',NULL,'','\0','\0','\0',35,'1982-07-01 00:00:00','321123198207010000','Shanghai','CN','10007','Merchant','+86-13937343570'),(4,NULL,NULL,'2017-08-29 16:00:04',NULL,'','\0','\0','\0',40,'1977-07-01 00:00:00','321123197707010000','Shanghai','CN','10009','Student','+86-13937343669'),(5,NULL,NULL,'2017-08-29 16:00:04',NULL,'','\0','\0','',45,'1972-07-01 00:00:00','321123197207010000','Shanghai','CN','10010','Lawyer','+86-15863453669');
/*!40000 ALTER TABLE `individual` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:55
