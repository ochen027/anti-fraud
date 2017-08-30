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
-- Table structure for table `rolepoint`
--

DROP TABLE IF EXISTS `rolepoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolepoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `flowpointid` varchar(255) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolepoint`
--

LOCK TABLES `rolepoint` WRITE;
/*!40000 ALTER TABLE `rolepoint` DISABLE KEYS */;
INSERT INTO `rolepoint` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19',1),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','e8be3955-29a8-7519-2467-c3d0418df771',1),(3,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','d54e5ad1-50b2-5b47-4f95-09023b4e2a90',2),(4,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','ea63ad61-9911-dd41-9129-f3b027aefe50',0),(5,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','84cf1663-5784-ccdf-aae2-86dc5ffbbcf2',4),(6,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','30102a75-9f3e-e60c-ea6f-597b730af7d5',5),(7,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','8ebf9bac-9571-3401-3d86-a7fa12e033ba',2),(8,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','1ed6dd0f-38fb-26e0-1d30-b08185aed78b',4),(9,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','42e97bb6-ea67-660d-92f9-24619233a9b4',5);
/*!40000 ALTER TABLE `rolepoint` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:57
