-- MySQL dump 10.11
--
-- Host: localhost    Database: alpd
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt

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
-- Table structure for table `cust_info`
--

DROP TABLE IF EXISTS `cust_info`;
CREATE TABLE `cust_info` (
  `CUST_REF_ID` int(10) unsigned NOT NULL auto_increment,
  `CUST_ID` varchar(20) NOT NULL,
  `CUST_ENROLLMENT_IN` char(1) NOT NULL,
  `CUST_ENROLL_UPDATE_IN` varchar(45) default NULL,
  `INSERTED_BY` int(10) unsigned default NULL,
  `INSERTED_DT` datetime default NULL,
  `INSERTED_IP` varchar(45) default NULL,
  `UPDATED_BY` int(10) unsigned default NULL,
  `UPDATED_DT` datetime default NULL,
  `UPDATED_IP` varchar(45) default NULL,
  `RECORD_STATUS` char(1) default NULL,
  PRIMARY KEY  (`CUST_REF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cust_info`
--

LOCK TABLES `cust_info` WRITE;
/*!40000 ALTER TABLE `cust_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `cust_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cust_tracking_info`
--

DROP TABLE IF EXISTS `cust_tracking_info`;
CREATE TABLE `cust_tracking_info` (
  `TRACKING_ID` int(10) unsigned NOT NULL auto_increment,
  `CUST_ID` varchar(20) NOT NULL,
  `ENROLL_VALID_TYPE` char(1) default NULL,
  `STATUS_IN` char(1) default NULL,
  `TRACKING_GAL_PATH` varchar(100) default NULL,
  `TRACKING_IMG_PATH` varchar(100) default NULL,
  `DEVICE_TYPE` varchar(45) default NULL,
  `ERROR_CODE` varchar(20) default NULL,
  `ERROR_DESC` varchar(500) default NULL,
  `INSERTED_DT` datetime default NULL,
  `INSERTED_IP` varchar(45) default NULL,
  `UPDATED_DT` datetime default NULL,
  `UPDATED_IP` varchar(45) default NULL,
  `RECORD_STATUS` char(1) default NULL,
  PRIMARY KEY  (`TRACKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cust_tracking_info`
--

LOCK TABLES `cust_tracking_info` WRITE;
/*!40000 ALTER TABLE `cust_tracking_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `cust_tracking_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_values`
--

DROP TABLE IF EXISTS `global_values`;
CREATE TABLE `global_values` (
  `GLOBAL_ID` int(10) unsigned NOT NULL auto_increment,
  `GLOBAL_VAR_NAME` varchar(45) default NULL,
  `GLOBAL_VAR_TYPE` varchar(45) default NULL,
  `GLOBAL_VAR_VALUE` varchar(45) default NULL,
  `ACTIVE_IN` varchar(45) default NULL,
  `INSERTED_DT` datetime default NULL,
  `INSERTED_BY` int(10) unsigned default NULL,
  `UPDATED_DT` datetime default NULL,
  `UPDATED_BY` int(10) unsigned default NULL,
  PRIMARY KEY  (`GLOBAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `global_values`
--

LOCK TABLES `global_values` WRITE;
/*!40000 ALTER TABLE `global_values` DISABLE KEYS */;
INSERT INTO `global_values` VALUES (1,'Authentication Image Count','AIC','3','Y','2013-12-09 13:54:43',NULL,NULL,NULL),(2,'Enrollment Image Count','EIC','5','Y','2013-12-09 13:54:49',NULL,NULL,NULL),(3,'Eye Distance Value','EDV','50','Y','2013-12-09 13:54:58',NULL,NULL,NULL),(4,'Camera Distance Value','CDV','320','Y','2013-12-09 13:55:07',NULL,NULL,NULL),(5,'Threshold Value','THV','7','Y','2013-12-09 13:55:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `global_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking_img_info`
--

DROP TABLE IF EXISTS `tracking_img_info`;
CREATE TABLE `tracking_img_info` (
  `TRACKING_IMG_ID` int(10) unsigned NOT NULL auto_increment,
  `TRACKING_ID` int(10) unsigned NOT NULL,
  `TRACKING_IMG_PATH` varchar(100) default NULL,
  `IMAGE_EYE_DISTANCE` float default NULL,
  `TRACKING_IMG_STATUS` char(1) default NULL,
  `IMG_CAM_DIST_WIDTH` int(10) unsigned default NULL,
  `IMG_CAM_DIST_HEIGHT` int(10) unsigned default NULL,
  `IMG_CAM_RESULT` varchar(25) default NULL,
  `IMG_FACE_DETECT_RESULT` varchar(25) default NULL,
  `IMG_LIVE_RESULT` varchar(25) default NULL,
  `INSERTED_DT` datetime default NULL,
  `INSERTED_IP` varchar(45) default NULL,
  `UPDATED_DT` datetime default NULL,
  `UPDATED_IP` varchar(45) default NULL,
  `RECORD_STATUS` char(1) default NULL,
  PRIMARY KEY  (`TRACKING_IMG_ID`),
  KEY `FK_tracking_img_info_1` (`TRACKING_ID`),
  CONSTRAINT `FK_tracking_img_info_1` FOREIGN KEY (`TRACKING_ID`) REFERENCES `cust_tracking_info` (`TRACKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tracking_img_info`
--

LOCK TABLES `tracking_img_info` WRITE;
/*!40000 ALTER TABLE `tracking_img_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `tracking_img_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking_score`
--

DROP TABLE IF EXISTS `tracking_score`;
CREATE TABLE `tracking_score` (
  `SCORE_ID` int(10) unsigned NOT NULL auto_increment,
  `TRACKING_ID` int(10) unsigned NOT NULL,
  `SRC_PATH` varchar(100) default NULL,
  `DST_PATH` varchar(100) default NULL,
  `SCORE_VALUE` double default NULL,
  `INSERTED_DT` datetime default NULL,
  `INSERTED_IP` varchar(45) default NULL,
  `RECORD_STATUS` char(1) default NULL,
  PRIMARY KEY  (`SCORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tracking_score`
--

LOCK TABLES `tracking_score` WRITE;
/*!40000 ALTER TABLE `tracking_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `tracking_score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-13 15:03:39
