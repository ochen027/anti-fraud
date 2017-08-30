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
-- Table structure for table `flow_point`
--

DROP TABLE IF EXISTS `flow_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flowid` varchar(255) DEFAULT NULL,
  `flowpointid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `print` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_point`
--

LOCK TABLES `flow_point` WRITE;
/*!40000 ALTER TABLE `flow_point` DISABLE KEYS */;
INSERT INTO `flow_point` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19','L1 available pool','L1 available pool(L1Pool)','WorkflowShape.WorkflowStart'),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','e8be3955-29a8-7519-2467-c3d0418df771','L1 review','L1 review(l1review)','WorkflowShape.WorkflowProcess'),(3,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','d54e5ad1-50b2-5b47-4f95-09023b4e2a90','l2 review','l2 review(l2Reivew)','WorkflowShape.WorkflowProcess'),(4,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','ea63ad61-9911-dd41-9129-f3b027aefe50','close','close(close)','WorkflowShape.WorkflowEnd'),(5,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','84cf1663-5784-ccdf-aae2-86dc5ffbbcf2','QC review','QC review(QCReview)','WorkflowShape.WorkflowProcess'),(6,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','30102a75-9f3e-e60c-ea6f-597b730af7d5','MLRO review','MLRO review(MLROReview)','WorkflowShape.WorkflowProcess'),(7,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','8ebf9bac-9571-3401-3d86-a7fa12e033ba','L2 available pool','L2 available pool(L2Pool)','WorkflowShape.WorkflowProcess'),(8,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','1ed6dd0f-38fb-26e0-1d30-b08185aed78b','Qc available pool','Qc available pool(QcPool)','WorkflowShape.WorkflowProcess'),(9,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','954dc267-c3e2-43d1-abdb-a83ca2881875','42e97bb6-ea67-660d-92f9-24619233a9b4','MLRO available Pool','MLRO available Pool(MLROPool)','WorkflowShape.WorkflowProcess');
/*!40000 ALTER TABLE `flow_point` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 16:09:58
