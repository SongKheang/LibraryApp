-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2023 at 04:22 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `adminID` varchar(50) NOT NULL,
  `adminName` varchar(50) NOT NULL,
  `adminPhone` varchar(50) NOT NULL,
  `adminPassword` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`adminID`, `adminName`, `adminPhone`, `adminPassword`) VALUES
(' hg', 'hg', ' hg', ' hg'),
('123', 'Jork', '123', '123'),
('e20200469', 'VEN Dara', '0963180249', '123');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `bookID` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `quality` enum('old','meduim','new','unreadable') DEFAULT NULL,
  `bookshelf` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `remain` int(11) DEFAULT NULL,
  `bookCoverPath` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`bookID`, `title`, `author`, `year`, `category`, `page`, `quality`, `bookshelf`, `quantity`, `remain`, `bookCoverPath`) VALUES
(20, 'The old man and the sea', 'Ernest Hemmingway', 1856, 'Novel', 360, 'old', 'A10-01', 7, 5, '/BookCover/one piece 1.jpg'),
(22, 'History Of Angkor', 'David Chandier', 2012, 'History', 200, 'new', 'A12-10', 2, 1, '/BookCover/a_history_of_cambodia_chandler_david_p_0.jpg'),
(30, 'Music Theory', 'Jonh Jamsion', 2020, 'Thoery', 100, 'meduim', 'B12-10', 10, 9, '/BookCover/41gUCpWSwoL.jpg'),
(31, 'Albert: Father of Modern Science', 'Jam chaler', 2020, 'Science', 400, 'new', 'A01-02', 30, 30, '/BookCover/51jiJbifP5L.jpg'),
(32, 'World Of Science', 'Tony Stark', 1995, 'Science', 500, 'meduim', 'B12-45', 50, 49, '/BookCover/71KmjQb+UvL._AC_UF1000,1000_QL80_.jpg'),
(34, 'The Jazz Theory', 'Mark Levine', 2021, 'Science', 300, 'new', 'C19-07', 50, 49, '/BookCover/1883217040.jpg'),
(35, 'A History Of Cambodia - Fourth Edition', 'David Chanler', 2002, 'History', 400, 'new', 'C12-04', 100, 99, '/BookCover/a_history_of_cambodia_chandler_david_p_0.jpg'),
(36, 'A Record Of Cambodia', 'Perter harris', 2002, 'History', 500, 'new', 'C12-04', 12, 12, '/BookCover/a_record_of_cambodia.jpg'),
(37, 'មាលាដូងចិត្ត២❤️🤣', 'ញ៉ុក ថែម', 1995, 'Novel', 122, 'unreadable', 'A01-02', 10, 47, '/BookCover/mea_lea_dung_jit.jpg'),
(38, 'Man of the Time', 'Tesla', 2002, 'Science', 100, 'meduim', 'B11-02', 100, 99, '/BookCover/cvr9780743215367_9780743215367_hr.jpg'),
(39, 'Earth 2100', 'Septimus Serverus', 2022, 'Thoery', 500, 'new', 'A12-05', 70, 68, '/BookCover/earth2100.jpg'),
(40, 'Naruto - Jiraiya Tale', 'Misikiko Ovasaki', 2010, 'Comic', 200, 'new', 'A12-04', 50, 48, '/BookCover/Jiraiya Tale.jpg'),
(41, 'Naruto Vol01', 'Misikiko Ovasaki', 2002, 'Comic', 100, 'new', 'A12-05', 100, 99, '/BookCover/narutoVol1.jpg'),
(43, 'រាមកេរី៍', 'Unknown', 1990, 'Science', 120, 'old', 'A12', 5, 4, '/BookCover/ramayana.jpg'),
(44, 'One Piece vol1', 'Kavasaki', 2002, 'Comedy', 100, 'new', 'C12', 10, 10, '/BookCover/one piece 1.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `borroweroutside`
--

CREATE TABLE `borroweroutside` (
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `bookID` int(11) NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `isReturned` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borroweroutside`
--

INSERT INTO `borroweroutside` (`name`, `phone`, `bookID`, `borrowDate`, `returnDate`, `isReturned`) VALUES
('Dara', '123', 15, '2023-06-21', '2023-06-23', 1),
('Dara', '123', 14, '2023-06-21', '2023-06-23', 1),
('Dara', '123', 11, '2023-06-21', '2023-06-23', 1),
('Dara', '123', 16, '2023-06-21', '2023-06-23', 0),
('Dara', '123', 15, '2023-06-21', '2023-06-23', 1),
('Dara', '0963180249', 14, '2023-06-21', '2023-06-24', 1),
('Dara', '0963180249', 16, '2023-06-22', '2023-06-24', 0),
('Dara', '0963180249', 15, '2023-06-22', '2023-06-24', 0),
('Dara', '0963180249', 11, '2023-06-22', '2023-06-24', 1),
('Dara', '0963180249', 14, '2023-06-22', '2023-06-24', 1),
('Dara', '0134341243', 15, '2023-06-22', '2023-06-24', 0),
('Dara', '0134341243', 14, '2023-06-22', '2023-06-24', 1),
('ffff', '6556', 23, '2023-06-22', '2023-06-24', 1),
('ffff', '6556', 22, '2023-06-22', '2023-06-24', 1),
('ffff', '6556', 20, '2023-06-22', '2023-06-24', 1),
('qq', 'qq', 26, '2023-06-22', '2023-06-23', 0),
('qq', 'qq', 22, '2023-06-22', '2023-06-24', 1),
('Dara', '143434', 26, '2023-06-22', '2023-06-21', 0),
('111', '111', 26, '2023-06-22', '2023-07-05', 0),
('111', '111', 24, '2023-06-22', '2023-07-05', 0),
('111', '111', 23, '2023-06-22', '2023-07-05', 0),
('111', '111', 20, '2023-06-22', '2023-07-05', 0),
('111', '111', 27, '2023-06-22', '2023-06-29', 0),
('Dara', '0482095435', 34, '2023-06-27', '2023-06-29', 0),
('Dara', '0482095435', 33, '2023-06-27', '2023-06-29', 0),
('Dara', '0482095435', 32, '2023-06-27', '2023-06-29', 0),
('Dara', '0482095435', 35, '2023-06-27', '2023-06-29', 0),
('Dara', '0482095435', 37, '2023-06-27', '2023-06-29', 0),
('Jonh', '092812527', 39, '2023-06-27', '2023-06-29', 0),
('Dara', '048294392', 37, '2023-06-27', '2023-06-29', 0),
('GG', '33333', 43, '2023-06-29', '2023-07-08', 0),
('GG', '33333', 40, '2023-06-29', '2023-07-08', 0),
('GG', '33333', 38, '2023-06-29', '2023-07-08', 0),
('GG', '33333', 37, '2023-06-29', '2023-07-08', 0),
('GG', '33333', 30, '2023-06-29', '2023-06-30', 0);

-- --------------------------------------------------------

--
-- Table structure for table `borrowlist`
--

CREATE TABLE `borrowlist` (
  `bookID` int(11) DEFAULT NULL,
  `borrower` varchar(50) DEFAULT NULL,
  `borrowDate` date DEFAULT curdate(),
  `returnDate` date DEFAULT NULL,
  `isReturned` enum('1','0') DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrowlist`
--

INSERT INTO `borrowlist` (`bookID`, `borrower`, `borrowDate`, `returnDate`, `isReturned`) VALUES
(20, 'e20200810', '2023-06-22', '2023-06-23', '1'),
(20, 'e20200810', '2023-06-22', '2023-06-23', '1'),
(20, 'e20200810', '2023-06-22', '2023-06-24', '0'),
(NULL, 'e20200810', '2023-06-22', '2023-06-24', '0'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(22, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(22, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(22, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(20, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-24', '1'),
(NULL, NULL, '2023-06-22', '2023-06-30', '1'),
(NULL, NULL, '2023-06-22', '2023-06-30', '1'),
(22, NULL, '2023-06-22', '2023-06-30', '1'),
(NULL, NULL, '2023-06-22', '2023-06-30', '1'),
(20, NULL, '2023-06-22', '2023-06-30', '1'),
(NULL, NULL, '2023-06-23', '2023-06-20', '1'),
(NULL, NULL, '2023-06-23', '2023-06-20', '1'),
(NULL, NULL, '2023-06-23', '2023-06-24', '1'),
(NULL, 'e20200469', '2023-06-24', '2023-06-30', '0'),
(NULL, 'e20200469', '2023-06-24', '2023-06-30', '0'),
(NULL, 'e20200469', '2023-06-24', '2023-06-30', '0'),
(NULL, '123', '2023-06-24', '2023-07-01', '0'),
(NULL, '123', '2023-06-24', '2023-07-01', '1'),
(NULL, '123', '2023-06-24', '2023-07-01', '1'),
(NULL, '123', '2023-06-24', '2023-07-01', '1'),
(22, '123', '2023-06-24', '2023-07-01', '1'),
(22, 'e20200958', '2023-06-24', '2023-07-01', '0'),
(43, '456', '2023-06-29', '2023-07-04', '1'),
(41, '456', '2023-06-29', '2023-07-04', '0'),
(40, '456', '2023-06-29', '2023-07-04', '0'),
(39, '456', '2023-06-29', '2023-07-04', '0'),
(38, '456', '2023-06-29', '2023-07-04', '1');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `studentID` varchar(50) NOT NULL,
  `studentName` varchar(50) NOT NULL,
  `studentPhone` varchar(50) NOT NULL,
  `studentPassword` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`studentID`, `studentName`, `studentPhone`, `studentPassword`) VALUES
('123', 'Hello Yes', '123', '123'),
('456', '456', '456', '456'),
('e20200460', 'Dara', 'e20200469', 'e20200469'),
('e20200469', 'VEN Dara', '0963180249', 'e20200469'),
('e20200810', 'ser long', '011285218', '123'),
('e20200958', 'Ty Sopheaktra', '092815263', 'Tra092815263');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminID`),
  ADD UNIQUE KEY `adminPhone` (`adminPhone`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`bookID`);

--
-- Indexes for table `borrowlist`
--
ALTER TABLE `borrowlist`
  ADD KEY `bookID` (`bookID`),
  ADD KEY `borrower` (`borrower`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentID`),
  ADD UNIQUE KEY `studentPhone` (`studentPhone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowlist`
--
ALTER TABLE `borrowlist`
  ADD CONSTRAINT `borrowlist_ibfk_3` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `borrowlist_ibfk_4` FOREIGN KEY (`borrower`) REFERENCES `students` (`studentID`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
