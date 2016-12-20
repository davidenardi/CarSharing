-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Dic 20, 2016 alle 11:34
-- Versione del server: 10.1.13-MariaDB
-- Versione PHP: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsharing`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `auto`
--

CREATE TABLE `auto` (
  `Targa` varchar(10) COLLATE utf8_bin NOT NULL,
  `Marca` varchar(50) COLLATE utf8_bin NOT NULL,
  `Modello` varchar(50) COLLATE utf8_bin NOT NULL,
  `Costo_Giornaliero` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `auto`
--

INSERT INTO `auto` (`Targa`, `Marca`, `Modello`, `Costo_Giornaliero`) VALUES
('AA222DS', 'FIAT', '500', '27.00'),
('AB009FG', 'SEAT', 'IBIZA', '25.00'),
('BB333EE', 'FORD', 'ESPACE', '50.00'),
('BC111KL', 'SEAT', 'LEON', '30.00'),
('CD969RB', 'ALFA ROMEO', 'GIULIA', '50.00'),
('ZI888LI', 'TOYOTA', 'YARIX', '20.00');

-- --------------------------------------------------------

--
-- Struttura della tabella `noleggi`
--

CREATE TABLE `noleggi` (
  `codice_noleggio` int(11) NOT NULL,
  `auto` varchar(10) COLLATE utf8_bin NOT NULL,
  `socio` varchar(16) COLLATE utf8_bin NOT NULL,
  `inzio` date NOT NULL,
  `fine` date NOT NULL,
  `auto_restituita` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `noleggi`
--

INSERT INTO `noleggi` (`codice_noleggio`, `auto`, `socio`, `inzio`, `fine`, `auto_restituita`) VALUES
(1, 'AB009FG', 'RSSMRA19T54A000Z', '2015-12-23', '2016-01-03', 1),
(2, 'AB009FG', 'RSSMRA19T54A000Z', '2016-04-15', '2016-04-30', 1),
(3, 'BB333EE', 'NCLGO68B80E111T', '2016-01-20', '2016-01-25', 1),
(4, 'BB333EE', 'RSSMRA19T54A000Z', '2016-10-03', '2016-11-01', 1),
(5, 'BB333EE', 'VRDNNA41C66S456W', '2016-12-02', '2016-12-10', 0),
(6, 'AA222DS', 'RSSMRA19T54A000Z', '2017-02-10', '2017-02-15', 0),
(7, 'ZI888LI', 'RSSMRA19T54A000Z', '2016-12-12', '2016-12-19', 1),
(8, 'CD969RB', 'RSSMRA19T54A000Z', '2015-07-13', '2015-08-13', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `soci`
--

CREATE TABLE `soci` (
  `cf` varchar(16) COLLATE utf8_bin NOT NULL,
  `Cognome` varchar(50) COLLATE utf8_bin NOT NULL,
  `Nome` varchar(50) COLLATE utf8_bin NOT NULL,
  `Indirizzo` varchar(50) COLLATE utf8_bin NOT NULL,
  `Telefono` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `soci`
--

INSERT INTO `soci` (`cf`, `Cognome`, `Nome`, `Indirizzo`, `Telefono`) VALUES
('BNCLGO68B80E111T', 'BIANCHI', 'OLGA', 'VIA XXIV GIUGNO, 100/A ROMA', ''),
('DMALDA18D91A000A', 'ADAMI', 'ALDO', 'VICOLO ITALIA, 120 TREVISO', '333889900112'),
('RSSLCA21A78A000Q', 'ROSSI', 'LUCA', 'VIALE ROMANO, 17 MILANO', '34789891234'),
('RSSMRA19T54A000Z', 'ROSSI', 'MARIO', 'VIA DEL SOLE, 41 TREVISO', '34511223344'),
('VRDNNA41C66S456W', 'VERDI', 'ANNA', 'VIA PIAVE, 18 TREVISO', '34511223344');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`Targa`);

--
-- Indici per le tabelle `noleggi`
--
ALTER TABLE `noleggi`
  ADD PRIMARY KEY (`codice_noleggio`);

--
-- Indici per le tabelle `soci`
--
ALTER TABLE `soci`
  ADD PRIMARY KEY (`cf`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `noleggi`
--
ALTER TABLE `noleggi`
  MODIFY `codice_noleggio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
