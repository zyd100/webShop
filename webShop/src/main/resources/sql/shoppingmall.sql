-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: shoppingmall
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `shoppingmall`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `shoppingmall` /*!40100 DEFAULT CHARACTER SET gb2312 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shoppingmall`;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL COMMENT '用户名',
  `product_id` bigint(10) NOT NULL COMMENT '产品序号',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `star` int(1) NOT NULL COMMENT '好评度（1-5星）',
  `audit` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312 COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (7,'testId',7,'testcomment',3,1,'2020-04-01 10:15:12'),(8,'testId',7,'五星好评，实惠便宜',5,1,'2020-04-01 11:41:30');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `contact_name` varchar(45) NOT NULL DEFAULT '""' COMMENT '联系人',
  `contact_mobile` varchar(45) NOT NULL DEFAULT '""' COMMENT '联系电话',
  `contact_address` varchar(255) NOT NULL DEFAULT '""' COMMENT '寄送地址',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '默认地址0/其他地址1',
  PRIMARY KEY (`id`),
  KEY `contact_ibfk_1_idx` (`user_name`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312 COMMENT='联系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (9,'testId','nametest','12345678','testaddress',1);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `order_num` varchar(32) NOT NULL COMMENT '订单编号',
  `update_user_name` varchar(45) NOT NULL COMMENT '更新者',
  `status` int(11) NOT NULL COMMENT '状态（未付款0/已下单1/已寄送2/已寄到3/购物车4）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312 COMMENT='订单历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `order_num` varchar(32) NOT NULL COMMENT '订单编号',
  `user_name` varchar(16) NOT NULL COMMENT '用户名',
  `price` float DEFAULT NULL COMMENT '合计金额',
  `status` int(11) NOT NULL COMMENT '状态（未付款0/已下单1/已寄送2/已寄到3/购物车4/收藏夹5）',
  `contact_name` varchar(45) DEFAULT NULL COMMENT '联系人',
  `contact_mobile` varchar(45) DEFAULT NULL COMMENT '联系电话',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '寄送地址',
  `message` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_num_UNIQUE` (`order_num`),
  KEY `order_info_ibfk_1_idx` (`user_name`),
  CONSTRAINT `order_info_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=gb2312 COMMENT='订单信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES (16,'3a50ea39c626f330fb66887bdf1c5e3d','testId',11000,4,NULL,NULL,NULL,NULL,'2020-03-28 03:10:27'),(17,'9e8984babd1684cabc6dda3193c83c5e','testId',0,5,NULL,NULL,NULL,NULL,'2020-03-28 03:14:40');
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `order_num` varchar(32) NOT NULL COMMENT '订单编号',
  `user_name` varchar(16) NOT NULL COMMENT '购买人',
  `product_id` bigint(10) NOT NULL COMMENT '产品序号',
  `product_name` varchar(100) NOT NULL COMMENT '产品名',
  `price` float NOT NULL COMMENT '单价',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `order_item_ibfk_1_idx` (`order_num`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_num`) REFERENCES `order_info` (`order_num`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=gb2312 COMMENT='订单条目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (26,'3a50ea39c626f330fb66887bdf1c5e3d','testId',7,'电脑产品',1000,10),(27,'9e8984babd1684cabc6dda3193c83c5e','testId',7,'电脑产品',1000,1),(28,'3a50ea39c626f330fb66887bdf1c5e3d','testId',7,'电脑产品',1000,1),(41,'9e8984babd1684cabc6dda3193c83c5e','testId',7,'电脑产品',1000,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `product_name` varchar(100) NOT NULL COMMENT '产品名',
  `explain` varchar(255) DEFAULT NULL COMMENT '产品概述',
  `category_id` int(11) NOT NULL COMMENT '类别序号',
  `shop_price` float NOT NULL COMMENT '店内价格',
  `price` float DEFAULT NULL COMMENT '市场价格',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_name` varchar(16) DEFAULT NULL COMMENT '创建者用户名',
  `update_user_name` varchar(16) DEFAULT NULL COMMENT '更新者用户名',
  PRIMARY KEY (`id`),
  KEY `product_ibfk_1_idx` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312 COMMENT='产品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (7,'电脑产品',NULL,1,1000,899,600,NULL,'2020-03-22 08:28:46','2020-04-01 09:44:41','admin',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `category_name` varchar(100) NOT NULL COMMENT '类别名',
  `sort_order` int(5) DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=gb2312 COMMENT='产品类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'电脑',6,'电脑硬件等',NULL,'2020-03-31 06:28:22'),(3,'其他',999,'其他类别',NULL,'2020-03-23 07:50:57'),(4,'休闲食品',4,NULL,NULL,'2020-03-26 01:32:21'),(5,'罐装食品',5,NULL,NULL,'2020-03-26 01:32:21'),(6,'酒水饮料',6,NULL,NULL,'2020-03-26 01:32:21'),(7,'奶制品',7,NULL,NULL,'2020-03-26 01:32:21'),(8,'粮油',8,NULL,NULL,'2020-03-26 01:32:21'),(9,'厨房调料',9,NULL,NULL,'2020-03-26 01:32:21'),(10,'女装',10,NULL,NULL,'2020-03-26 01:32:21'),(11,'男装',11,NULL,NULL,'2020-03-26 01:32:21'),(12,'童装',12,NULL,NULL,'2020-03-26 01:32:21'),(13,'内衣裤',13,NULL,NULL,'2020-03-26 01:32:21'),(14,'运动类',14,NULL,NULL,'2020-03-26 01:32:21'),(15,'配饰',15,NULL,NULL,'2020-03-26 01:32:21'),(16,'男鞋',16,NULL,NULL,'2020-03-26 01:32:21'),(17,'女鞋',17,NULL,NULL,'2020-03-26 01:32:21'),(18,'车饰',18,NULL,NULL,'2020-03-26 01:32:21'),(19,'玩具',19,NULL,NULL,'2020-03-26 01:32:21'),(20,'床品',20,NULL,NULL,'2020-03-26 01:32:21'),(21,'文化体育',21,NULL,NULL,'2020-03-26 01:32:21'),(22,'婴儿专用',22,NULL,NULL,'2020-03-26 01:32:21'),(23,'洗护用品',23,NULL,NULL,'2020-03-26 01:32:21'),(24,'卫生用品',24,NULL,NULL,'2020-03-26 01:32:21'),(25,'居室日用',25,NULL,NULL,'2020-03-26 01:32:21'),(26,'家装软饰',26,NULL,NULL,'2020-03-26 01:32:21'),(27,'数码产品',27,NULL,NULL,'2020-03-26 01:32:21'),(28,'数码配件',28,NULL,NULL,'2020-03-26 01:32:21'),(29,'小家电',29,NULL,NULL,'2020-03-26 01:32:21'),(30,'大家电',30,NULL,NULL,'2020-03-26 01:32:21'),(31,'面部保养',31,NULL,NULL,'2020-03-26 01:32:21'),(32,'彩妆香水',32,NULL,NULL,'2020-03-26 01:32:21'),(33,'箱包皮具',33,NULL,NULL,'2020-03-26 01:32:21'),(34,'医药',34,NULL,NULL,'2020-03-26 05:25:02'),(35,'厨房生鲜',35,NULL,NULL,'2020-03-26 05:25:02'),(36,'保健',36,NULL,NULL,'2020-03-26 05:25:02'),(37,'通信器材',37,NULL,NULL,'2020-03-26 05:25:02'),(38,'运动穿搭',38,NULL,NULL,'2020-03-26 05:25:02'),(39,'生活家电',39,NULL,NULL,'2020-03-26 05:25:02');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_name` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nick_name` varchar(8) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `image` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'admin','21232f297a57a5a743894a0e4a801fc3','admin',NULL,'2020-03-19 06:38:09',NULL),(11,'testId','e10adc3949ba59abbe56e057f20f883e','testnick','1019661738@qq.com','2020-03-19 06:38:43',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `role` varchar(20) NOT NULL COMMENT '权限名',
  `user_name` varchar(16) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  CONSTRAINT `user_name` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312 COMMENT='用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (6,'ADMIN','admin'),(7,'CUSTOM','testId');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-01 19:47:37
