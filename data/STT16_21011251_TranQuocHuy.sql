-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.5.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlysanpham
CREATE DATABASE IF NOT EXISTS `quanlysanpham` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `quanlysanpham`;

-- Dumping structure for table quanlysanpham.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlysanpham.category: ~2 rows (approximately)
INSERT INTO `category` (`id`, `name`) VALUES
	(1, 'Sách giấy'),
	(2, 'Sách điện tử');

-- Dumping structure for table quanlysanpham.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3046kvjyysq288vy3lsbtc9nw` (`role_id`),
  CONSTRAINT `FK3046kvjyysq288vy3lsbtc9nw` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlysanpham.employee: ~2 rows (approximately)
INSERT INTO `employee` (`id`, `role_id`, `name`, `password`) VALUES
	(1, 1, 'ADMIN', '$2a$12$JrKXR9S8EgACvUQBBBIr.eXuoOV2jHJXa11Xq9.GDqet7e1KWkIqW'),
	(2, 2, 'USER', '$2a$12$c9x6MjofiTb/ydyRT8bTQObcfuU7Z16/1HieharlKoJFw/bxd75mm');

-- Dumping structure for table quanlysanpham.product
CREATE TABLE IF NOT EXISTS `product` (
  `categoryid` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `price` double NOT NULL,
  `register_date` date DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ort9abhumpx4t2mlngljr1vi` (`categoryid`),
  CONSTRAINT `FK4ort9abhumpx4t2mlngljr1vi` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlysanpham.product: ~5 rows (approximately)
INSERT INTO `product` (`categoryid`, `id`, `is_active`, `price`, `register_date`, `code`, `description`, `image`, `name`) VALUES
	(1, 1, b'1', 15.1, '2024-12-19', 'SG01255500', 'Hay', '1.png', 'Sách giấy 2024'),
	(1, 2, b'0', 12.2, '2024-12-20', 'SG01255511', 'Hay quá trời', '2.png', 'Sách giấy 2025'),
	(2, 3, b'1', 15, '2024-12-19', 'SDT01255522', 'OKE', '3.png', 'Sách điện tư 2024'),
	(2, 4, b'0', 12, '2024-12-20', 'SDT01255533', 'GOOD', '4.png', 'Sách điện tư 2025'),
	(1, 5, b'1', 15, '2024-12-20', 'SG01255501', 'QUÁ HAY', '1.png', 'Sach giao khoa 105');

-- Dumping structure for table quanlysanpham.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlysanpham.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
