

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `img_insert_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `img_insert_tab`;
--

DROP TABLE IF EXISTS `img_insert_tab`;
CREATE TABLE IF NOT EXISTS `img_insert_tab`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `designation` text NOT NULL,
  `image` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `img_insert_tab`;
--

INSERT INTO `img_insert_tab`(`id`, `name`, `designation`, `image`) VALUES
(1, 'Jitesh Singh', 'CEO', 'js.jpg'),
(2, 'Sudhanshu Singh', 'CHRO', 'ss.jpg'),
(3, 'Utkarsh Tiwari', 'Bussiness Analyst', 'ut.jpg'),
(4, 'Durga Shankar', 'COO', 'ds.jpg'),
(5, 'Geetesh Kumar', 'Web Developer', 'gk.jpg'),
(6, 'Jitu Thakur', 'App Developer', 'jt.jpg'),
(7, 'Pranav Nigam', 'ML Engineer', 'pn.jpg'),
(8, 'Jr Singh', 'Marketing', 'jr.jpg'),
(9, 'Jetha Lal', 'Advertisment', 'jl.jpg');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
