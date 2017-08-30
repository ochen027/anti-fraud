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
-- Table structure for table `rolemenu`
--

DROP TABLE IF EXISTS `rolemenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolemenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolemenu`
--

LOCK TABLES `rolemenu` WRITE;
/*!40000 ALTER TABLE `rolemenu` DISABLE KEYS */;
INSERT INTO `rolemenu` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,6),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,6),(3,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',3,6),(4,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',4,6),(5,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',5,6),(6,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,6),(7,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',7,6),(8,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',8,6),(9,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,6),(10,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,6),(11,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',11,6),(12,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',12,6),(13,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',13,6),(14,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',14,6),(15,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',15,6),(16,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',16,6),(17,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',17,6),(18,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',18,6),(19,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',19,6),(20,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',20,6),(21,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',21,6),(23,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',23,6),(24,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',24,6),(25,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',25,6),(26,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',26,6),(27,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',27,6),(28,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',28,6),(29,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',29,6),(30,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',30,6),(31,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',31,6),(32,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',32,6),(33,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',33,6),(100001,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,1),(100002,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,1),(100003,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',3,1),(100004,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',4,1),(100006,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,1),(100007,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',7,1),(100009,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,1),(100010,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,1),(110001,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,2),(110002,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,2),(110003,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',3,2),(110006,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,2),(110009,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,2),(110010,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,2),(110029,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',29,2),(110030,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',30,4),(110031,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',31,5),(120001,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,3),(120002,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,3),(120004,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',4,3),(120005,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',5,3),(120006,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,3),(120009,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,3),(120010,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,3),(130001,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,4),(130002,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,4),(130003,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',3,4),(130005,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',5,4),(130006,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,4),(130009,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,4),(130010,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,4),(140001,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',1,5),(140002,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',2,5),(140003,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',3,5),(140005,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',5,5),(140006,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',6,5),(140009,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',9,5),(140010,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',10,5);
/*!40000 ALTER TABLE `rolemenu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:10:00
