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
-- Table structure for table `flow_event`
--

DROP TABLE IF EXISTS `flow_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_update_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endpoint` varchar(255) DEFAULT NULL,
  `floweventid` varchar(255) DEFAULT NULL,
  `flowid` varchar(255) DEFAULT NULL,
  `flowpointid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `print` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_event`
--

LOCK TABLES `flow_event` WRITE;
/*!40000 ALTER TABLE `flow_event` DISABLE KEYS */;
INSERT INTO `flow_event` VALUES (1,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','30102a75-9f3e-e60c-ea6f-597b730af7d5','a9a5b044-7af0-a60b-2c94-756a59572e12','954dc267-c3e2-43d1-abdb-a83ca2881875','42e97bb6-ea67-660d-92f9-24619233a9b4','assign','assign(MLROAssign)','WorkflowShape.Connection'),(2,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','ea63ad61-9911-dd41-9129-f3b027aefe50','15e6ce2d-3c60-2dc8-7917-ed1aa171b4c8','954dc267-c3e2-43d1-abdb-a83ca2881875','30102a75-9f3e-e60c-ea6f-597b730af7d5','close','SAR(MLROClose)','WorkflowShape.Connection'),(3,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','1ed6dd0f-38fb-26e0-1d30-b08185aed78b','afda1b0d-9763-1824-f3d6-3b0e00836146','954dc267-c3e2-43d1-abdb-a83ca2881875','30102a75-9f3e-e60c-ea6f-597b730af7d5','return','return(MLROReturn)','WorkflowShape.Connection'),(4,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','42e97bb6-ea67-660d-92f9-24619233a9b4','cbf8ab91-cfe7-302f-dc01-456fad794e3e','954dc267-c3e2-43d1-abdb-a83ca2881875','84cf1663-5784-ccdf-aae2-86dc5ffbbcf2','escalate','escalate(QCEscalate)','WorkflowShape.Connection'),(5,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','ea63ad61-9911-dd41-9129-f3b027aefe50','27cbee12-3cbd-d169-7200-7ccb69372ca7','954dc267-c3e2-43d1-abdb-a83ca2881875','84cf1663-5784-ccdf-aae2-86dc5ffbbcf2','close','close(QCClosed)','WorkflowShape.Connection'),(6,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','84cf1663-5784-ccdf-aae2-86dc5ffbbcf2','dd43a7d7-5dd4-1718-3b56-26595a3eb941','954dc267-c3e2-43d1-abdb-a83ca2881875','1ed6dd0f-38fb-26e0-1d30-b08185aed78b','assign','assign(QcAssign)','WorkflowShape.Connection'),(7,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','d54e5ad1-50b2-5b47-4f95-09023b4e2a90','72ac96cd-b80f-6fb1-db45-66f3305db1dd','954dc267-c3e2-43d1-abdb-a83ca2881875','8ebf9bac-9571-3401-3d86-a7fa12e033ba','assign','assign(L2Assign)','WorkflowShape.Connection'),(8,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','e8be3955-29a8-7519-2467-c3d0418df771','e8568701-4afc-acdd-d535-1c38d793324b','954dc267-c3e2-43d1-abdb-a83ca2881875','f7f837a6-bc31-c39d-6cdb-bcb6ceaa7d19','assign','assign(L1Assign)','WorkflowShape.Connection'),(9,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','ea63ad61-9911-dd41-9129-f3b027aefe50','b8427923-d5cd-aae2-e4d3-009559be6ddb','954dc267-c3e2-43d1-abdb-a83ca2881875','d54e5ad1-50b2-5b47-4f95-09023b4e2a90','close','close(L2Closed)','WorkflowShape.Connection'),(10,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','8ebf9bac-9571-3401-3d86-a7fa12e033ba','257268d7-e9d4-726c-5b9a-b39f1c2acdd2','954dc267-c3e2-43d1-abdb-a83ca2881875','e8be3955-29a8-7519-2467-c3d0418df771','escalate','escalate(L1escalate)','WorkflowShape.Connection'),(11,'sysadmin','2017-08-29 15:59:07','2017-08-29 15:59:07','sysadmin','','','1ed6dd0f-38fb-26e0-1d30-b08185aed78b','285f6eb2-f470-e055-0ba6-aee89d39c099','954dc267-c3e2-43d1-abdb-a83ca2881875','d54e5ad1-50b2-5b47-4f95-09023b4e2a90','escalate','escalate(L2escalate)','WorkflowShape.Connection');
/*!40000 ALTER TABLE `flow_event` ENABLE KEYS */;
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
