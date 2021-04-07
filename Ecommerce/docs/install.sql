/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(70) NOT NULL,
  `iid` int(11) NOT NULL,
  `price` decimal(6,2) DEFAULT '0.00',
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `iid` (`iid`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`iid`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `session_id`, `iid`, `price`, `quantity`) VALUES
	(7, '9EFC7B1C23E920C82D04FC08689979FE', 1, 12.00, 5),
	(8, '9EFC7B1C23E920C82D04FC08689979FE', 2, 23.00, 1),
	(11, 'B196EF9590C5DB2843875DF716654DCB', 1, 12.00, 1),
	(12, 'B196EF9590C5DB2843875DF716654DCB', 2, 23.00, 2),
	(19, '18D65B0B261C676242C1FA40D2C52AF2', 4, 190.00, 1),
	(54, '41D4EBE7F40B87B941CC138FA602B8E7', 4, 190.00, 1),
	(55, '41D4EBE7F40B87B941CC138FA602B8E7', 3, 3.00, 1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `title`) VALUES
	(1, 'Test');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon` varchar(6) NOT NULL,
  `discount` smallint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `coupon` (`coupon`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` (`id`, `coupon`, `discount`) VALUES
	(1, 'abc', 10),
	(2, 'z3456', 30),
	(3, 'z3457', 80),
	(4, 'z3458', 45);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(70) NOT NULL,
  `description` text,
  `price` decimal(6,2) DEFAULT '0.00',
  `quantity` int(11) DEFAULT '0',
  `picture` varchar(100) DEFAULT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id`, `title`, `description`, `price`, `quantity`, `picture`, `cid`) VALUES
	(1, 'tv', 'aaa', 12.00, 66, 'item001.jpg', 1),
	(2, 'monitor', 'vvvvv', 23.00, 24, 'item006.jpg', 1),
	(3, 'libro', 'vvvvv', 3.00, 23, 'item004.jpg', 1),
	(4, 'cellulare samsung', 'cellulare samsung', 190.00, 53, 'item003.jpg', 1),
	(5, 'pomodori', 'pomodori', 1.00, 731, 'item002.jpg', 1),
	(6, 'set di pentole', 'set di pentole', 23.99, 100, 'item005.jpg', 1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL,
  `password` varchar(70) NOT NULL,
  `name` varchar(70) NOT NULL,
  `surname` varchar(70) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(70) NOT NULL,
  `did` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `did` (`did`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`did`) REFERENCES `discount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `password`, `name`, `surname`, `phone`, `address`, `did`) VALUES
	(1, 'fprotano@yahoo.com', 'demo', 'demo', 'demo', '11', 'dd', 1),
	(2, 'mario.rossi@gmail.com', 'demo', 'mario', 'rossi', '+396713349', 'via delle vie', 1),
	(3, 'mario.rossi1@gmail.com', 'demo', 'mario1', 'rossi1', '+396713349', 'via delle vie', 1),
	(4, 'franco.protano@email.it', 'demo', 'franco', 'protan', '', '', 1),
	(5, 'mario.tozzi@gmail.com', 'demo', 'mario', 'tozzi', '', '', 2),
	(6, 'pippo.pippo@gmail.com', 'demo', 'pippo', 'pippo', '', '', 2),
	(7, 'paolo.depaoli@gmail.com', 'demo', 'paolo', 'de paoli', '', '', 4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `discount` smallint(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`),
  KEY `uid` (`uid`),
  CONSTRAINT `user_order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` (`id`, `order_no`, `uid`, `discount`) VALUES
	(1, 'abc', 1, 1),
	(7, '202104030823', 1, 1),
	(8, '202104030825', 1, 1),
	(9, '202104030943', 1, 1),
	(16, '202104051135', 1, 1),
	(17, '202104050348', 5, 2),
	(18, '202104050359', 6, 2),
	(19, '202104050415', 7, 3),
	(20, '202104050428', 7, 3),
	(21, '202104050430', 4, 1),
	(22, '202104050432', 4, 2),
	(23, '202104050435', 4, 1),
	(25, '202104050436', 4, 12),
	(26, '202104050443', 7, 80),
	(27, '202104050445', 7, 45);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `user_order_row` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iid` int(11) NOT NULL,
  `uoid` int(11) NOT NULL,
  `price` decimal(6,2) DEFAULT '0.00',
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `iid` (`iid`),
  KEY `user_order_row_ibfk_1` (`uoid`),
  CONSTRAINT `user_order_row_ibfk_1` FOREIGN KEY (`uoid`) REFERENCES `user_order` (`id`),
  CONSTRAINT `user_order_row_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `user_order_row` DISABLE KEYS */;
INSERT INTO `user_order_row` (`id`, `iid`, `uoid`, `price`, `quantity`) VALUES
	(1, 1, 1, 12.00, 1),
	(2, 2, 1, 23.00, 3),
	(8, 1, 7, 12.00, 3),
	(9, 3, 8, 3.00, 1),
	(10, 2, 8, 23.00, 2),
	(11, 1, 8, 12.00, 4),
	(12, 5, 9, 1.00, 10),
	(19, 1, 16, 12.00, 99),
	(20, 1, 17, 12.00, 1),
	(21, 5, 17, 1.00, 19),
	(22, 5, 18, 1.00, 100),
	(23, 2, 18, 23.00, 2),
	(24, 4, 18, 190.00, 1),
	(25, 3, 19, 3.00, 1),
	(26, 4, 19, 190.00, 1),
	(27, 2, 19, 23.00, 1),
	(28, 1, 19, 12.00, 1),
	(29, 2, 20, 23.00, 1),
	(30, 4, 21, 190.00, 1),
	(31, 3, 21, 3.00, 1),
	(32, 2, 21, 23.00, 1),
	(33, 5, 21, 1.00, 1),
	(34, 4, 22, 190.00, 1),
	(35, 2, 22, 23.00, 2),
	(36, 5, 22, 1.00, 11),
	(37, 4, 23, 190.00, 11),
	(38, 2, 25, 23.00, 2),
	(39, 4, 26, 190.00, 1),
	(40, 2, 26, 23.00, 1),
	(41, 5, 26, 1.00, 19),
	(42, 4, 27, 190.00, 1),
	(43, 2, 27, 23.00, 1),
	(44, 5, 27, 1.00, 19);
/*!40000 ALTER TABLE `user_order_row` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
