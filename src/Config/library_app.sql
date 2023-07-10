-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2023 at 01:42 PM
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
('e12345', 'ABC', '097', '123'),
('e20200460', 'e20200469', '0963180240', 'e20200469'),
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
(20, 'The old man and the sea', 'Ernest Hemmingway', 1856, 'Novel', 360, 'old', 'A10-01', 7, 6, '/BookCover/one piece 1.jpg'),
(22, 'History Of Angkor', 'David Chandier', 2012, 'History', 200, 'meduim', 'A12-10', 2, 3, '/BookCover/a_history_of_cambodia_chandler_david_p_0.jpg'),
(34, 'The Jazz Theory', 'Mark Levine', 2021, 'Science', 300, 'new', 'C19-07', 50, 51, '/BookCover/1883217040.jpg'),
(35, 'A History Of Cambodia - Fourth Edition', 'David Chanler', 2002, 'History', 400, 'new', 'C12-04', 100, 99, '/BookCover/a_history_of_cambodia_chandler_david_p_0.jpg'),
(36, 'A Record Of Cambodia', 'Perter harris', 2002, 'History', 500, 'new', 'C12-04', 12, 12, '/BookCover/a_record_of_cambodia.jpg'),
(39, 'Earth 2100', 'Septimus Serverus', 2022, 'Thoery', 500, 'new', 'A12-05', 70, 68, '/BookCover/earth2100.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `borroweroutside`
--

CREATE TABLE `borroweroutside` (
  `name` varchar(50) NOT NULL,
  `bookID` int(11) DEFAULT NULL,
  `phone` varchar(50) NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate` date NOT NULL,
  `isReturned` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borroweroutside`
--

INSERT INTO `borroweroutside` (`name`, `bookID`, `phone`, `borrowDate`, `returnDate`, `isReturned`) VALUES
('Dara', 22, '099999', '2023-07-02', '2023-07-04', 1),
('Dara', NULL, '099999', '2023-07-02', '2023-07-04', 1),
('Dara', NULL, '033333', '2023-07-02', '2023-07-05', 1),
('Dara', 36, '033333', '2023-07-02', '2023-07-05', 1),
('gg', NULL, '07634234', '2023-07-04', '2023-07-05', 1),
('yehh', NULL, 'yehh', '2023-07-04', '2023-07-04', 1),
('Hello', NULL, 'hello', '2023-07-03', '2023-07-04', 1),
('Hello', 35, 'hello', '2023-07-06', '2023-07-06', 1),
('Jam Bond', NULL, '03344', '2023-07-03', '2023-07-08', 1),
('Jam Bond', NULL, '03344', '2023-07-03', '2023-07-08', 1),
('Jam Bond', 34, '03344', '2023-07-03', '2023-07-08', 1),
('Jam Bond', NULL, '03344', '2023-07-03', '2023-07-09', 1),
('Jam Bond', 36, '2222', '2023-07-03', '2023-07-12', 1),
('HHH', 35, 'qqq', '2023-07-03', '2023-07-05', 1),
('a', 35, 'a', '2023-07-01', '2023-07-13', 1),
('hh', 34, 'hh', '2023-07-07', '2023-07-15', 1),
('Jams Bond', 35, '012345', '2023-07-06', '2023-07-08', 0),
('Jams Bond', NULL, '012345', '2023-07-06', '2023-07-08', 0),
('Dara', 34, '0963180249', '2023-07-06', '2023-07-07', 1),
('Dara', NULL, '0963180249', '2023-07-06', '2023-07-07', 0),
('Dara', NULL, '0963180249', '2023-07-06', '2023-07-07', 0),
('Dara', 22, '0963180249', '2023-07-06', '2023-07-07', 0),
('Jonh Wick', NULL, '09999', '2023-07-06', '2023-07-15', 1),
('aa', NULL, 'aa', '2023-07-10', '2023-07-11', 1),
('dd', NULL, 'dd', '2023-07-10', '2023-07-11', 1),
('hello', NULL, 'hello', '2023-07-10', '2023-07-12', 1),
('hello', 39, 'hello', '2023-07-10', '2023-07-11', 1);

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
(20, 'e20200810', '2023-06-22', '2023-06-24', '1'),
(22, '123', '2023-06-24', '2023-07-01', '1'),
(22, 'e20200958', '2023-06-24', '2023-07-01', '1'),
(NULL, '456', '2023-06-29', '2023-07-04', '1'),
(NULL, '456', '2023-06-29', '2023-07-04', '1'),
(NULL, '456', '2023-06-29', '2023-07-04', '1'),
(39, '456', '2023-06-29', '2023-07-04', '1'),
(NULL, '456', '2023-06-29', '2023-07-04', '1'),
(NULL, '123', '2023-07-03', '2023-07-03', '1'),
(20, 'e20201030', '2023-07-03', '2023-07-04', '1'),
(NULL, 'e20201030', '2023-07-03', '2023-07-04', '1'),
(NULL, '123', '2023-07-10', '2023-07-13', '0'),
(39, '123', '2023-07-10', '2023-07-13', '0'),
(NULL, '123', '2023-07-10', '2023-07-18', '1');

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
('124', '123', '124', '123'),
('456', '456', '456', '456'),
('e20200460', 'Dara', 'e20200469', 'e20200469'),
('e20200469', 'VEN Dara', '0963180249', 'e20200469'),
('e20200470', 'VEN Dara', '0963180240', '123'),
('e20200810', 'ser long', '011285218', '123'),
('e20200958', 'Ty Sopheaktra', '092815263', 'Tra092815263'),
('e20201030', 'Kheang', '0333333', '345'),
('ff', 'ff', 'ff', 'ff'),
('gg', 'gg', 'gg', 'gg');

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
-- Indexes for table `borroweroutside`
--
ALTER TABLE `borroweroutside`
  ADD KEY `bookID` (`bookID`);

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
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borroweroutside`
--
ALTER TABLE `borroweroutside`
  ADD CONSTRAINT `borroweroutside_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`) ON DELETE SET NULL ON UPDATE CASCADE;

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
