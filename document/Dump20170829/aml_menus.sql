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
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `menu_desc` varchar(255) DEFAULT NULL,
  `menu_ico` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_parentid` int(11) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Dashboard',0,'dashboard()'),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Alerts',0,NULL),(3,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'My Alerts',2,'myAlert()'),(4,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Available Alerts(L1)',2,'available({id:\"L1Pool\"})'),(5,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Suppressed Alerts',2,'suppressedAlert'),(6,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Closed Alerts',2,'closedAlert()'),(7,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Create Alert',2,'CreateAlert()'),(9,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Query',0,NULL),(10,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Reports',0,'reports()'),(11,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'System',0,NULL),(12,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Scenario',11,'rulesManager()'),(13,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Workflow',11,'IndexWorkflow()'),(14,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Batch',11,'transBatch()'),(15,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Document',11,'documentExample()'),(18,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Alert Query',9,'alertQuery()'),(19,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Case Query',9,'caseQuery()'),(23,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Import',11,'ImportData()'),(24,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'User Management',11,'userManagement()'),(25,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Risk Country',11,'riskCountry()'),(26,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Watch List',11,'watchList()'),(27,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'User Group',11,'userGroup()'),(28,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'User Role',11,'roleList()'),(29,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Available Alerts(L2)',2,'available({id:\"L2Pool\"})'),(30,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Available Alerts(QC)',2,'available({id:\"QcPool\"})'),(31,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Available Alerts(MLRO)',2,'available({id:\"MLROPool\"})'),(32,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Menu',11,'menuList()'),(33,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Role Menu',11,'roleMenu()'),(34,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','',NULL,NULL,'Report Test',11,'reportTest()');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:59
