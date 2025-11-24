-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 02, 2025 at 05:41 PM
-- Server version: 11.8.3-MariaDB-log
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u550340080_ecommune`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `code` varchar(30) NOT NULL,
  `number` varchar(50) NOT NULL,
  `country_id` int(11) NOT NULL,
  `region_id` int(11) NOT NULL,
  `prefecture_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `section_id` int(11) NOT NULL,
  `road_id` int(11) NOT NULL,
  `longitude` decimal(9,6) NOT NULL,
  `latitude` decimal(9,6) NOT NULL,
  `owner` varchar(150) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `code`, `number`, `country_id`, `region_id`, `prefecture_id`, `branch_id`, `class_id`, `section_id`, `road_id`, `longitude`, `latitude`, `owner`, `created_at`, `updated_at`) VALUES
(1, 'AG-887', '45 TG', 3, 6, 4, 1, 17, 11, 1, -12.941895, 8.940787, '', '2025-09-05 14:41:07', '2025-09-05 14:41:07'),
(2, 'rue-faubourd-bannier', '5', 3, 7, 5, 7, 19, 13, 2, -12.439882, 10.280267, '', '2025-09-05 22:42:51', '2025-09-05 22:42:51'),
(3, 'HHHH', '5', 3, 7, 5, 7, 19, 13, 2, 2.187584, 48.808222, '', '2025-09-09 20:03:53', '2025-09-09 20:03:53');

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `administrative_code` varchar(50) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `region_id` int(11) DEFAULT NULL,
  `prefecture_id` int(11) DEFAULT NULL,
  `school_name` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobileno` varchar(100) NOT NULL,
  `currency` varchar(100) NOT NULL,
  `symbol` varchar(25) NOT NULL,
  `currency_formats` tinyint(4) NOT NULL DEFAULT 1,
  `symbol_position` tinyint(4) NOT NULL DEFAULT 1,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `stu_generate` tinyint(4) NOT NULL DEFAULT 0,
  `stu_username_prefix` varchar(255) NOT NULL,
  `stu_default_password` varchar(255) NOT NULL,
  `grd_generate` tinyint(4) NOT NULL DEFAULT 0,
  `grd_username_prefix` varchar(255) NOT NULL,
  `grd_default_password` varchar(255) NOT NULL,
  `teacher_restricted` tinyint(1) DEFAULT 1,
  `due_days` float NOT NULL DEFAULT 30,
  `due_with_fine` tinyint(4) NOT NULL DEFAULT 1,
  `translation` varchar(255) NOT NULL DEFAULT 'english',
  `timezone` varchar(255) NOT NULL,
  `weekends` varchar(255) NOT NULL DEFAULT '1',
  `reg_prefix_enable` tinyint(1) NOT NULL DEFAULT 0,
  `student_login` tinyint(4) NOT NULL DEFAULT 1,
  `parent_login` tinyint(4) NOT NULL DEFAULT 1,
  `teacher_mobile_visible` tinyint(4) NOT NULL DEFAULT 1,
  `teacher_email_visible` tinyint(4) NOT NULL DEFAULT 1,
  `reg_start_from` tinyint(4) NOT NULL DEFAULT 1,
  `institution_code` varchar(100) DEFAULT NULL,
  `reg_prefix_digit` int(11) NOT NULL,
  `offline_payments` tinyint(1) NOT NULL DEFAULT 1,
  `attendance_type` tinyint(1) NOT NULL DEFAULT 0,
  `show_own_question` tinyint(4) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `unique_roll` tinyint(4) NOT NULL DEFAULT 1,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`id`, `name`, `code`, `administrative_code`, `latitude`, `longitude`, `country_id`, `region_id`, `prefecture_id`, `school_name`, `email`, `mobileno`, `currency`, `symbol`, `currency_formats`, `symbol_position`, `city`, `state`, `address`, `stu_generate`, `stu_username_prefix`, `stu_default_password`, `grd_generate`, `grd_username_prefix`, `grd_default_password`, `teacher_restricted`, `due_days`, `due_with_fine`, `translation`, `timezone`, `weekends`, `reg_prefix_enable`, `student_login`, `parent_login`, `teacher_mobile_visible`, `teacher_email_visible`, `reg_start_from`, `institution_code`, `reg_prefix_digit`, `offline_payments`, `attendance_type`, `show_own_question`, `status`, `unique_roll`, `created_at`, `updated_at`) VALUES
(1, 'Sonfonia', 'SF', '778', 48.8075116, 2.1861039, 3, 7, 5, 'Sonfonia', 'sonfonia@e-commune.net', '+224 620 248 252', 'GNF', 'GNF', 3, 4, 'Conakry', 'Guinée', '', 0, '', '', 0, '', '', 1, 30, 1, 'french', 'Pacific/Midway', '0,6', 0, 1, 1, 1, 1, 1, '', 0, 1, 0, 0, 1, 1, '2025-03-29 12:55:39', '2025-10-06 15:14:10'),
(5, 'coyah', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'coy', 'coyah@e-commune.net', '988', 'GNF', 'GNF', 1, 1, 'coyah', 'Guinée', 'Coyah', 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-08-15 10:09:44', '2025-08-15 10:09:44'),
(9, 'Guendembou', '10000111', '10000111', 8.7665300, -9.9992700, 3, 9, 7, 'Guendembou', 'guendembou@e-commune.net', '777777', 'GNF', 'GNF', 1, 1, '', '', '', 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-09-06 20:21:07', '2025-10-25 21:53:13'),
(13, 'Kindia Centre', 'Kindia', 'Kindia', 48.8575475, 2.3513765, 3, 7, 5, 'Kindia Centre', 'contact@kindia.com', '8888', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-02 13:26:43', '2025-10-02 13:26:43'),
(16, 'Kaloum', 'Kaloum', 'Kaloum', 9.5149641, -13.7084413, 3, 8, 6, 'Kaloum', 'kaloum@e-commune.net', '6555', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:31:05', '2025-10-26 21:31:05'),
(17, 'Dixinn', 'Dixinn', 'Dixinn', 9.5471058, -13.6729364, 3, 8, 6, 'Dixinn', 'dixinn@e-commune.net', '65476545', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:36:18', '2025-10-26 21:36:18'),
(18, 'Lambanyi', 'LAMBAYI', 'LAMBAYI', 9.6445549, -13.6136145, 3, 8, 6, 'Lambanyi', 'lambanyi@e-commune.net', '6554444', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:38:46', '2025-10-26 21:38:46'),
(19, 'Labé Centre', 'LABE', 'LABE', 11.7614835, -12.0118889, 3, 13, 10, 'Labé Centre', 'labe@e-commune.net', '76666666', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:56:06', '2025-10-26 21:56:06'),
(20, 'Mamou Centre', 'MAMOU', 'MAMOU', 10.6619269, -12.1139542, 3, 14, 11, 'Mamou Centre', 'mamou@e-commune.net', '6655555', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:57:29', '2025-10-26 21:57:29'),
(21, 'Kankan Centre', 'KANKAN', 'KANKAN', 10.6248355, -9.3175166, 3, 15, 12, 'Kankan Centre', 'kankan@e-commune.net', '655555', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 21:59:39', '2025-10-26 21:59:39'),
(22, 'Nzérékoré Centre', 'Nzérékoré', 'Nzérékoré', 8.3925728, -8.8572519, 3, 9, 13, 'Nzérékoré Centre', 'nzerekore@e-commune.net', '5566666', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-26 22:03:15', '2025-10-26 22:03:15'),
(23, 'Gaoual Centre', 'GAOUL', 'GAOUL', 11.7540960, -13.2003330, 3, 6, 14, 'Gaoual Centre', 'gaoual@e-commune.net', 'HGGG', 'GNF', 'GNF', 1, 1, '', NULL, NULL, 0, '', '', 0, '', '', 1, 30, 1, 'english', '', '1', 0, 1, 1, 1, 1, 1, NULL, 0, 1, 0, 0, 1, 1, '2025-10-29 22:15:45', '2025-10-29 22:15:45');

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `code` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `code`, `name`, `latitude`, `longitude`, `created_by`, `created_at`, `updated_at`) VALUES
(3, 'GN', 'Guinea', 9.9294122, -11.3539905, 1, '2025-09-05 13:18:56', '2025-09-05 13:22:28'),
(4, 'mali', 'Mali', 16.3700359, -2.2900239, 1, '2025-09-05 22:09:08', '2025-09-05 22:09:08'),
(5, 'FR666', 'France', 48.8082286, 2.1875998, 1, '2025-09-09 20:01:15', '2025-09-09 20:01:15');

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `name_numeric` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `administrative_code` varchar(50) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `name`, `name_numeric`, `created_at`, `updated_at`, `branch_id`, `code`, `administrative_code`, `longitude`, `latitude`, `created_by`) VALUES
(17, 'test class', '2', '2025-07-16 14:11:08', NULL, 1, 'TC', '789', -13.3593750, 9.5913613, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `postal_record`
--

CREATE TABLE `postal_record` (
  `id` int(11) NOT NULL,
  `sender_title` varchar(255) DEFAULT NULL,
  `receiver_title` varchar(255) DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `address` text NOT NULL,
  `date` date NOT NULL,
  `note` text CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `file` varchar(250) NOT NULL,
  `confidential` tinyint(1) NOT NULL DEFAULT 0,
  `created_by` int(11) NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT 1,
  `branch_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prefectures`
--

CREATE TABLE `prefectures` (
  `id` int(11) NOT NULL,
  `code` varchar(5) NOT NULL,
  `administrative_code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `region_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `prefectures`
--

INSERT INTO `prefectures` (`id`, `code`, `administrative_code`, `name`, `region_id`, `country_id`, `created_by`, `latitude`, `longitude`, `created_at`, `updated_at`) VALUES
(5, 'Kindi', 'Kindiaaaa', 'Kindia', 7, 3, 1, 10.0368000, -12.8260215, '2025-09-05 22:10:09', '2025-09-05 22:10:09'),
(6, '10200', '102001', 'Conakry', 8, 3, 1, 9.5170602, -13.6998434, '2025-09-05 22:58:23', '2025-10-26 21:30:08'),
(7, '10011', '10011', 'Gueckédou', 9, 3, 1, 8.6822458, -10.2053675, '2025-09-06 20:18:21', '2025-09-06 20:18:21'),
(8, '23333', '1233', 'Faranah', 12, 3, 1, 10.3143203, -10.8900970, '2025-09-20 06:25:03', '2025-09-20 06:25:03'),
(9, 'COYAH', 'COYAH', 'Coyah', 7, 3, 1, 9.7090410, -13.3889560, '2025-10-26 21:41:43', '2025-10-26 21:41:43'),
(10, 'LABE', 'LABE', 'Labé', 13, 3, 1, 11.7614835, -12.0118889, '2025-10-26 21:45:08', '2025-10-26 21:45:08'),
(11, 'Mamou', 'Mamou', 'Mamou', 14, 3, 1, 10.6619269, -12.1139542, '2025-10-26 21:48:05', '2025-10-26 21:48:05'),
(12, 'KANKA', 'KANKAN', 'Kankan', 15, 3, 1, 10.6248355, -9.3175166, '2025-10-26 21:51:35', '2025-10-26 21:51:35'),
(13, 'Nzéré', 'Nzérékoré', 'Nzérékoré', 9, 3, 1, 8.3925728, -8.8572519, '2025-10-26 22:02:17', '2025-10-26 22:02:17'),
(14, 'Gaoua', 'Gaoual', 'Gaoual', 6, 3, 1, 11.7540960, -13.2003330, '2025-10-29 22:14:21', '2025-10-29 22:14:21');

-- --------------------------------------------------------

--
-- Table structure for table `regions`
--

CREATE TABLE `regions` (
  `id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `code` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `is_capital` tinyint(1) DEFAULT 0,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `regions`
--

INSERT INTO `regions` (`id`, `country_id`, `code`, `name`, `created_by`, `latitude`, `longitude`, `is_capital`, `created_at`, `updated_at`) VALUES
(6, 3, 'BK', 'Boké', 1, 10.9327963, -14.3000605, 0, '2025-09-05 13:27:13', '2025-09-05 14:16:59'),
(7, 3, 'Kndia', 'Kindia', 1, 10.0368000, -12.8260215, 0, '2025-09-05 22:08:29', '2025-09-05 22:08:29'),
(8, 3, '10', 'Conakry', 1, 9.5170602, -13.6998434, 1, '2025-09-05 22:56:20', '2025-09-05 22:56:20'),
(9, 3, '10001', 'NZérékoré', 1, 8.3925728, -8.8572519, 0, '2025-09-06 20:17:45', '2025-09-06 20:17:45'),
(12, 3, '10200', 'Faranah', 1, 10.3143203, -10.8900970, 0, '2025-09-20 06:23:44', '2025-09-20 06:23:44'),
(13, 3, 'LABE', 'Labé', 1, 11.7614835, -12.0118889, 1, '2025-10-26 21:42:30', '2025-10-26 21:42:30'),
(14, 3, 'MAMOU', 'Mamou', 1, 10.6619269, -12.1139542, 1, '2025-10-26 21:43:07', '2025-10-26 21:43:07'),
(15, 3, 'KANKA', 'Kankan', 1, 10.6248355, -9.3175166, 1, '2025-10-26 21:43:52', '2025-10-26 21:43:52');

-- --------------------------------------------------------

--
-- Table structure for table `roads`
--

CREATE TABLE `roads` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `type_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `region_id` int(11) NOT NULL,
  `prefecture_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `section_id` int(11) NOT NULL,
  `geom` linestring NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `roads`
--

INSERT INTO `roads` (`id`, `code`, `name`, `type_id`, `country_id`, `region_id`, `prefecture_id`, `branch_id`, `class_id`, `section_id`, `geom`, `created_by`, `created_at`, `updated_at`) VALUES
(1, 'TRD', 'Test Road', 7, 3, 6, 4, 1, 17, 11, 0xe61000000102000000050000000100000000122bc07de637f24f2c22400000000040f02ac04fca79996bd321400000000040f02ac0c0189021ed7421400100000020012bc018c4254a220021400000000040f02ac086e25aa5d6902040, 1, '2025-09-05 14:40:09', '2025-09-05 14:40:09'),
(2, 'BVD-TELY', 'Boulevard Telly', 7, 3, 7, 5, 7, 19, 13, 0xe61000000102000000050000004b54855ee0892bc0c6bbcdf56a8f25402bdc100f3aea2ac048fd3ef3d4c1254095b57f338e702ac015381dada9a925408d211be2a16b2ac02ce32737395a2540ce0512793f842ac054216f567e452540, 1, '2025-09-05 22:40:49', '2025-09-05 22:40:49'),
(3, 'MARC', 'Marceline', 7, 3, 7, 5, 1, 17, 11, 0xe610000001020000000200000016681b047ae42bc0132941fc95e32340023fdc0fbca32ac0c4980819f3862440, 1, '2025-09-20 06:36:40', '2025-09-20 06:36:40');

-- --------------------------------------------------------

--
-- Table structure for table `road_types`
--

CREATE TABLE `road_types` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `road_types`
--

INSERT INTO `road_types` (`id`, `name`, `created_at`) VALUES
(6, 'Avenue', '2025-09-05 14:22:45'),
(7, 'Boulevard', '2025-09-05 14:22:59'),
(8, 'Rue', '2025-09-05 22:11:37'),
(9, 'Route', '2025-09-05 23:05:58'),
(10, 'Chemin', '2025-09-09 20:07:46');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `capacity` varchar(20) DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `administrative_code` varchar(50) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`id`, `name`, `capacity`, `branch_id`, `code`, `administrative_code`, `longitude`, `latitude`, `created_by`) VALUES
(11, 'Foulamadina', '20', 1, 'FLM', '998', -13.5811996, 9.6717722, NULL),
(12, 'dadia', '1', 4, 'dadia', 'didai', -13.1866612, 10.2046504, NULL),
(13, 'Linsan 1', '', 7, 'linsan', 'Linsan098383', -12.4398815, 10.2802667, NULL),
(14, 'Permanence', '', 8, '100202', '100202', -13.6027087, 9.5940153, NULL);

-- --------------------------------------------------------

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `fk_addresses_country` (`country_id`),
  ADD KEY `fk_addresses_region` (`region_id`),
  ADD KEY `fk_addresses_prefecture` (`prefecture_id`),
  ADD KEY `fk_addresses_city` (`branch_id`),
  ADD KEY `fk_addresses_district` (`class_id`),
  ADD KEY `fk_addresses_borough` (`section_id`),
  ADD KEY `fk_addresses_road` (`road_id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`id`);
  
--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `fk_country_staff` (`created_by`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_rms_1` (`branch_id`);

--
-- Indexes for table `postal_record`
--
ALTER TABLE `postal_record`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prefectures`
--
ALTER TABLE `prefectures`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD UNIQUE KEY `administrative_code` (`administrative_code`),
  ADD KEY `fk_pref_region` (`region_id`),
  ADD KEY `fk_pref_staff` (`created_by`);

--
-- Indexes for table `regions`
--
ALTER TABLE `regions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `fk_region_country` (`country_id`),
  ADD KEY `fk_region_staff` (`created_by`);

--
-- Indexes for table `roads`
--
ALTER TABLE `roads`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `borough_id` (`section_id`);

--
-- Indexes for table `road_types`
--
ALTER TABLE `road_types`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
  
--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
  
--
-- AUTO_INCREMENT for table `postal_record`
--
ALTER TABLE `postal_record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prefectures`
--
ALTER TABLE `prefectures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `regions`
--
ALTER TABLE `regions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `roads`
--
ALTER TABLE `roads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `road_types`
--
ALTER TABLE `road_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_rms_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
