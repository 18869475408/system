-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: users
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminPassword` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ynuadmin',
  `adminName` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('ynuadmin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customs`
--

DROP TABLE IF EXISTS `customs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customs` (
  `customerID` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL DEFAULT 'null',
  `customerPassword` varchar(255) NOT NULL DEFAULT '',
  `customerGrade` varchar(255) NOT NULL DEFAULT 'null',
  `registrationTime` datetime NOT NULL DEFAULT '2021-10-01 00:00:00',
  `costTotal` double NOT NULL DEFAULT '0',
  `customerEmail` varchar(255) NOT NULL DEFAULT 'null',
  `customerPhoneNumber` char(11) NOT NULL DEFAULT 'null',
  PRIMARY KEY (`customerID`,`customerName`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customs`
--

LOCK TABLES `customs` WRITE;
/*!40000 ALTER TABLE `customs` DISABLE KEYS */;
INSERT INTO `customs` VALUES (1,'ynuer1','Zqw123456789@','金牌客户','2023-08-03 23:00:00',10049.5,'1341234567@qq.com','18812345678'),(2,'ynuer2','Zqw123456789@','金牌客户','2023-01-01 14:00:00',5107.5,'3393747954@qq.com','15012345678'),(3,'ynuer3','Zqw123456789@','银牌客户','2023-06-01 10:00:00',1630.5,'1711234567@qq.com','13812345678'),(73,'ynuer4','Zqw123456789!','金牌客户','2023-09-06 16:12:06',15180.5,'1881234444@qq.com','18812344444');
/*!40000 ALTER TABLE `customs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `goodsName` varchar(255) NOT NULL DEFAULT 'null',
  `goodsID` int(10) NOT NULL AUTO_INCREMENT,
  `goodsManufacture` varchar(255) DEFAULT 'null',
  `goodsDateOfProduction` date NOT NULL DEFAULT '2023-08-30',
  `goodsTypes` varchar(255) DEFAULT 'null',
  `goodsImportPrice` double unsigned DEFAULT '0',
  `goodsExitPrice` double unsigned NOT NULL DEFAULT '0',
  `goodsCount` int(32) NOT NULL,
  PRIMARY KEY (`goodsID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES ('冰红茶',1,'康师傅','2023-08-02','N5S3',2.5,3,4500),('大理牧场',2,'云南大理','2023-05-03','N5S3SV',4,5,569),('东方树叶',3,'农夫山泉','2023-08-30','N5S36',3,4,3560),('百事可乐',4,'百事可乐','2023-08-16','TFY3',3,3.5,5088);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopcar`
--

DROP TABLE IF EXISTS `shopcar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopcar` (
  `customerID` int(10) DEFAULT NULL,
  `ownGoodsID` int(10) DEFAULT NULL,
  `ownGoodsName` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'null',
  `ownGoodsManufacturer` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'null',
  `ownGoodsDateOfProduction` date DEFAULT '2022-10-01',
  `ownGoodsType` varchar(10) COLLATE utf8_unicode_ci DEFAULT 'null',
  `ownGoodsPrice` double DEFAULT '10',
  `ownGoodsCount` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopcar`
--

LOCK TABLES `shopcar` WRITE;
/*!40000 ALTER TABLE `shopcar` DISABLE KEYS */;
INSERT INTO `shopcar` VALUES (1,4,'百事可乐','农夫山泉','2022-08-16','TFY3',3.5,0),(2,1,'冰红茶','康师傅','2022-08-02','N5S3',3,16),(2,3,'东方树叶','农夫山泉','2023-08-30','N5S36',4,10),(2,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,10),(1,2,'大理牧场','云南大理','2023-05-03','N5S3SV',5,0),(1,1,'冰红茶','康师傅','2023-08-02','N5S3',3,0),(43,1,'冰红茶','康师傅','2023-08-02','N5S3',3,3),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,0),(73,2,'大理牧场','云南大理','2023-05-03','N5S3SV',5,0),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,0);
/*!40000 ALTER TABLE `shopcar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppinghistory`
--

DROP TABLE IF EXISTS `shoppinghistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppinghistory` (
  `customerID` int(10) NOT NULL,
  `GoodsID` int(11) DEFAULT NULL,
  `GoodsName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Manufacturer` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DateOfProduction` date DEFAULT NULL,
  `Type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Count` int(11) DEFAULT NULL,
  `PurchaseTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppinghistory`
--

LOCK TABLES `shoppinghistory` WRITE;
/*!40000 ALTER TABLE `shoppinghistory` DISABLE KEYS */;
INSERT INTO `shoppinghistory` VALUES (1,2,'大理牧场','云南大理','2023-08-02','N5S3SV',3,3,'2023-08-15 21:53:06'),(1,4,'百事可乐','农夫山泉','2023-08-16','TFY3',3.5,4,'2023-08-15 08:54:19'),(2,1,'冰红茶','康师傅','2023-08-02','N5S3',3,5,'2023-08-24 22:56:05'),(3,1,'冰红茶','康师傅','2023-08-02','N5S3',3,5,'2023-08-17 15:59:38'),(1,2,'大理牧场','云南大理','2023-05-03','N5S3SV',5,30,'2023-08-17 16:49:11'),(1,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,1,'2023-08-17 16:49:24'),(3,1,'冰红茶','康师傅','2023-08-02','N5S3',3,20,'2023-08-17 20:20:18'),(3,3,'东方树叶','农夫山泉','2023-08-30','N5S36',4,10,'2023-08-17 20:29:11'),(3,3,'东方树叶','农夫山泉','2023-08-30','N5S36',4,40,'2023-08-17 20:41:11'),(3,3,'东方树叶','农夫山泉','2023-08-30','N5S36',4,20,'2023-08-17 20:47:39'),(3,3,'东方树叶','农夫山泉','2023-08-30','N5S36',4,10,'2023-08-17 20:48:59'),(1,1,'冰红茶','康师傅','2023-08-02','N5S3',3,2,'2023-08-20 00:36:07'),(1,1,'冰红茶','康师傅','2023-08-02','N5S3',3,1,'2023-08-20 00:40:21'),(43,1,'冰红茶','康师傅','2023-08-02','N5S3',3,3,'2023-08-26 13:27:50'),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,500,'2023-09-06 16:14:04'),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,300,'2023-09-06 16:20:06'),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,200,'2023-09-06 21:27:54'),(73,2,'大理牧场','云南大理','2023-05-03','N5S3SV',5,420,'2023-09-06 21:29:39'),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,13,'2023-09-06 21:32:06'),(73,1,'冰红茶','康师傅','2023-08-02','N5S3',3,1,'2023-09-06 21:35:48'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,1,'2023-09-06 21:43:10'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,2,'2023-09-06 21:47:03'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,1,'2023-09-06 21:49:13'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,3,'2023-09-06 21:55:27'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,3,'2023-09-06 21:59:34'),(73,4,'百事可乐','百事可乐','2023-08-16','TFY3',3.5,1,'2023-09-06 22:03:58'),(73,2,'大理牧场','云南大理','2023-05-03','N5S3SV',5,2000,'2023-09-06 22:08:43');
/*!40000 ALTER TABLE `shoppinghistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-28 14:59:21
