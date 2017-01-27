-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 27 jan 2017 om 14:42
-- Serverversie: 10.1.19-MariaDB
-- PHP-versie: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unwdmi`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `members`
--

CREATE TABLE `members` (
  `member_id` int(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `is_admin` int(2) NOT NULL,
  `pw_hash` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `members`
--

INSERT INTO `members` (`member_id`, `username`, `is_admin`, `pw_hash`) VALUES
(1, 'TestUser', 1, '$2y$10$UXUOoPLecD..JrgiYJACFuHj23PYraAxR7cVv1rMf.ueRP9PBX0mK'),
(2, 'UserTest', 0, '$2y$10$zZz/Q0RUAiAkQKGhy.DcieHvt7hi/M8OQwwgyjL11An.g784yRjoa'),
(3, 'UserTest', 0, '$2y$10$Fe396i3Cps7VPSmRFNYHOOu94ipbPoRcq50ejokJoUiam9FrlxeFu'),
(4, '45163', 1, '$2y$10$W/q8whsPt/aCzJ4iTzQXfO3dAHFbSclfydQgrZpSmbddfvRktb.iu'),
(5, '2389472', 1, '$2y$10$jYLAXlxdU4jJExwdayNySOIWP6CYnx28Tr/iDhLa0IHR6ooxRrKXy'),
(6, '46984068', 0, '$2y$10$OHu3gNDrOci0xKAEsLZPhuDkWHYoPY0jHf5w6S8RALane34K0IwiC');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`member_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `members`
--
ALTER TABLE `members`
  MODIFY `member_id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
