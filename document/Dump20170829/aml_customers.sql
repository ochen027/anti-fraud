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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `cust_first_nm` varchar(255) DEFAULT NULL,
  `full_nm` varchar(255) DEFAULT NULL,
  `cust_id` varchar(255) DEFAULT NULL,
  `cust_last_nm` varchar(255) DEFAULT NULL,
  `last_upd_by` varchar(255) DEFAULT NULL,
  `cust_middle_nm` varchar(255) DEFAULT NULL,
  `cust_open_br` varchar(255) DEFAULT NULL,
  `cust_risk_level` varchar(255) DEFAULT NULL,
  `cust_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,NULL,NULL,'2017-08-29 15:59:56',NULL,'','San','Zhang San','10001','Zhang','20001','','100034','L','individual'),(2,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Alibaba International Trading Co.,Ltd','Alibaba International Trading Co.,Ltd','10002','','20001','','100034','L','company'),(3,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Bright Dairy & Food Co.,Ltd','Bright Dairy & Food Co.,Ltd','10003','','20001','','100034','M','company'),(4,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Jingdong International Trading Co.,Ltd','Jingdong International Trading Co.,Ltd','10004','','20001','','100034','M','company'),(5,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Wu','Wang Wu','10005','Wang','20001','','100034','H','individual'),(6,NULL,NULL,'2017-08-29 15:59:56',NULL,'','ABC Company','ABC Company','10006','','20001','','100034','H','company'),(7,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Lin-kai','Zhou Lin-kai','10007','Zhou','20001','','100034','L','individual'),(8,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Pepsi Company','Pepsi Company','10008','','20001','','100034','L','company'),(9,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Xin Qiang','Wang Xin Qiang','10009','Wang','20001','','100034','L','individual'),(10,NULL,NULL,'2017-08-29 15:59:56',NULL,'','Liang','Wu Liang','10010','Wu','20001','','100034','L','individual');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:53
