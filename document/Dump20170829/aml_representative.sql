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
-- Table structure for table `representative`
--

DROP TABLE IF EXISTS `representative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `representative` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `sus_flg_indiv` bit(1) DEFAULT NULL,
  `resi_flg` bit(1) DEFAULT NULL,
  `pep_flg` bit(1) DEFAULT NULL,
  `cust_id` varchar(255) DEFAULT NULL,
  `representative_age` int(11) DEFAULT NULL,
  `representative_birth_date` datetime DEFAULT NULL,
  `representative_city` varchar(255) DEFAULT NULL,
  `representative_country` varchar(255) DEFAULT NULL,
  `representative_id` varchar(255) DEFAULT NULL,
  `representative_id_type` varchar(255) DEFAULT NULL,
  `representative_name` varchar(255) DEFAULT NULL,
  `representative_occupation` varchar(255) DEFAULT NULL,
  `representative_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representative`
--

LOCK TABLES `representative` WRITE;
/*!40000 ALTER TABLE `representative` DISABLE KEYS */;
INSERT INTO `representative` VALUES (1,NULL,NULL,'2017-08-29 16:00:12',NULL,'','\0','\0','\0','10002',37,'1980-01-01 00:00:00','Shanghai','CN','321123198001010000','Resident Identity Card','Tommy Xu','to be confirmed','+86-62221234'),(2,NULL,NULL,'2017-08-29 16:00:12',NULL,'','\0','\0','\0','10003',36,'1981-01-01 00:00:00','Shanghai','CN','123321198101010000','Resident Identity Card','Li Si','to be confirmed','+86-62221234'),(3,NULL,NULL,'2017-08-29 16:00:12',NULL,'','\0','\0','\0','10004',35,'1982-01-01 00:00:00','New York','USA','C1111111','Passport','Obama Clinton','to be confirmed',' +1-212-216-1835'),(4,NULL,NULL,'2017-08-29 16:00:12',NULL,'','\0','\0','\0','10006',34,'1983-01-01 00:00:00','New York','USA','E34567890','passport','AAA','to be confirmed','+1-212-883-1234'),(5,NULL,NULL,'2017-08-29 16:00:12',NULL,'','\0','\0','\0','10008',33,'1984-01-01 00:00:00','New York','USA','997766554','passport','JOHAN WILLEM MAARTEN JANSEN','to be confirmed','N/A');
/*!40000 ALTER TABLE `representative` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:56
