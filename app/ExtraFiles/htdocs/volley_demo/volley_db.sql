-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 17, 2020 at 10:19 AM
-- Server version: 5.7.26
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `volley_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `volley_tab`;
--

DROP TABLE IF EXISTS `volley_tab`;
CREATE TABLE IF NOT EXISTS `volley_tab`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `designation` text NOT NULL,
  `image` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `volley_tab`;
--

INSERT INTO `volley_tab`(`id`, `name`, `designation`, `image`) VALUES
(1, 'Jitesh Singh', 'CEO', 'js.jpg'),
(2, 'Sudhanshu Singh', 'CHRO', 'ss.jpg'),
(3, 'Utkarsh Tiwari', 'Bussiness Analyst', 'ut.jpg'),
(4, 'Durga Shankar', 'COO', 'ds.jpg'),
(5, 'Geetesh Kumar', 'Web Developer', 'gk.jpg'),
(6, 'Jitu Thakur', 'App Developer', 'jt.jpg'),
(7, 'Pranav Nigam', 'ML Engineer', 'pn.jpg'),
(8, 'Jr Singh', 'Marketing', 'jr.jpg'),
(9, 'Jetha Lal', 'Advertisment', 'jl.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `name`, `username`, `password`) VALUES
(1, 'Erjrsingh', 'Erjitu@gmail.com', '123'),
(2, 'Uttu', 'Uttu@gmail.com', '1234'),
(3, 'Shu', 'Shu@gmail.com', '12345'),
(4, 'DSM', 'DSM@gmail.com', '123456');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
