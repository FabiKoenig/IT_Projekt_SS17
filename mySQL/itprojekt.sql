-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Jun 2017 um 17:01
-- Server-Version: 10.1.21-MariaDB
-- PHP-Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `itprojekt`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ausschreibung`
--

CREATE TABLE `ausschreibung` (
  `Ausschreibung_Id` int(11) NOT NULL,
  `Bezeichnung` varchar(30) NOT NULL,
  `Bewerbungsfrist` date NOT NULL,
  `Ausschreibungstext` text NOT NULL,
  `Ausschreibungsstatus` varchar(30) NOT NULL,
  `Ausschreibender_Id` int(11) NOT NULL,
  `Partnerprofil_Id` int(11) NOT NULL,
  `Projekt_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `beteiligung`
--

CREATE TABLE `beteiligung` (
  `Beteiligung_Id` int(11) NOT NULL,
  `Umfang` int(11) NOT NULL,
  `Startdatum` date NOT NULL,
  `Enddatum` date NOT NULL,
  `Bewertung_Id` int(11) DEFAULT NULL,
  `Beteiligter_Id` int(11) NOT NULL,
  `Projekt_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bewerbung`
--

CREATE TABLE `bewerbung` (
  `Bewerbung_Id` int(11) NOT NULL,
  `Bewerbungstext` text NOT NULL,
  `Erstellungsdatum` date NOT NULL,
  `Organisationseinheit_Id` int(11) NOT NULL,
  `Ausschreibung_Id` int(11) NOT NULL,
  `Bewerbungsstatus` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bewertung`
--

CREATE TABLE `bewertung` (
  `Bewertung_Id` int(11) NOT NULL,
  `Stellungnahme` text NOT NULL,
  `Wert` double NOT NULL,
  `Bewerbung_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `eigenschaft`
--

CREATE TABLE `eigenschaft` (
  `Eigenschaft_Id` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Wert` varchar(30) NOT NULL,
  `Partnerprofil_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `organisationseinheit`
--

CREATE TABLE `organisationseinheit` (
  `Organisationseinheit_Id` int(11) NOT NULL,
  `Strasse` varchar(30) DEFAULT NULL,
  `Hausnummer` varchar(5) DEFAULT NULL,
  `PLZ` int(11) DEFAULT NULL,
  `Ort` varchar(30) DEFAULT NULL,
  `Partnerprofil_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `partnerprofil`
--

CREATE TABLE `partnerprofil` (
  `Partnerprofil_Id` int(11) NOT NULL,
  `Erstellungsdatum` date NOT NULL,
  `Aenderungsdatum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `Person_Id` int(11) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `Anrede` varchar(5) NOT NULL,
  `Vorname` varchar(30) NOT NULL,
  `Nachname` varchar(30) NOT NULL,
  `Unternehmen_Id` int(11) DEFAULT NULL,
  `Team_Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projekt`
--

CREATE TABLE `projekt` (
  `Projekt_Id` int(11) NOT NULL,
  `Startdatum` date NOT NULL,
  `Enddatum` date NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Beschreibung` text NOT NULL,
  `Projektleiter_Id` int(11) NOT NULL,
  `Projektmarktplatz_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projektmarktplatz`
--

CREATE TABLE `projektmarktplatz` (
  `Projektmarktplatz_Id` int(11) NOT NULL,
  `Bezeichnung` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

CREATE TABLE `team` (
  `Team_Id` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Unternehmen_Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teilnahme`
--

CREATE TABLE `teilnahme` (
  `Person_Id` int(11) NOT NULL,
  `Projektmarktplatz_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `unternehmen`
--

CREATE TABLE `unternehmen` (
  `Unternehmen_Id` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `ausschreibung`
--
ALTER TABLE `ausschreibung`
  ADD PRIMARY KEY (`Ausschreibung_Id`),
  ADD KEY `Partnerprofil_Id` (`Partnerprofil_Id`),
  ADD KEY `Ausschreibender_Id` (`Ausschreibender_Id`),
  ADD KEY `Projekt_Id` (`Projekt_Id`);

--
-- Indizes für die Tabelle `beteiligung`
--
ALTER TABLE `beteiligung`
  ADD PRIMARY KEY (`Beteiligung_Id`),
  ADD KEY `Projekt_Id` (`Projekt_Id`),
  ADD KEY `Beteiligter_Id` (`Beteiligter_Id`),
  ADD KEY `Bewertung_Id` (`Bewertung_Id`);

--
-- Indizes für die Tabelle `bewerbung`
--
ALTER TABLE `bewerbung`
  ADD PRIMARY KEY (`Bewerbung_Id`),
  ADD KEY `Ausschreibung_Id` (`Ausschreibung_Id`),
  ADD KEY `Bewerber_Id` (`Organisationseinheit_Id`);

--
-- Indizes für die Tabelle `bewertung`
--
ALTER TABLE `bewertung`
  ADD PRIMARY KEY (`Bewertung_Id`),
  ADD KEY `Bewerbung_Id` (`Bewerbung_Id`);

--
-- Indizes für die Tabelle `eigenschaft`
--
ALTER TABLE `eigenschaft`
  ADD PRIMARY KEY (`Eigenschaft_Id`),
  ADD KEY `Partnerprofil_Id` (`Partnerprofil_Id`);

--
-- Indizes für die Tabelle `organisationseinheit`
--
ALTER TABLE `organisationseinheit`
  ADD PRIMARY KEY (`Organisationseinheit_Id`),
  ADD KEY `Partnerprofil_Id` (`Partnerprofil_Id`);

--
-- Indizes für die Tabelle `partnerprofil`
--
ALTER TABLE `partnerprofil`
  ADD PRIMARY KEY (`Partnerprofil_Id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`Person_Id`),
  ADD KEY `Organisationseinheit_Id` (`Person_Id`),
  ADD KEY `Unternhemen_Id` (`Unternehmen_Id`),
  ADD KEY `Team_Id` (`Team_Id`);

--
-- Indizes für die Tabelle `projekt`
--
ALTER TABLE `projekt`
  ADD PRIMARY KEY (`Projekt_Id`),
  ADD KEY `Projektleiter_Id` (`Projektleiter_Id`),
  ADD KEY `Ausschreibung_Id` (`Projektmarktplatz_Id`);

--
-- Indizes für die Tabelle `projektmarktplatz`
--
ALTER TABLE `projektmarktplatz`
  ADD PRIMARY KEY (`Projektmarktplatz_Id`);

--
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`Team_Id`),
  ADD KEY `Organisationseinheit_Id` (`Team_Id`),
  ADD KEY `Unternhemen_Id` (`Unternehmen_Id`);

--
-- Indizes für die Tabelle `teilnahme`
--
ALTER TABLE `teilnahme`
  ADD PRIMARY KEY (`Person_Id`,`Projektmarktplatz_Id`),
  ADD KEY `Primärschlüssel_von_Projektmarktplatz` (`Projektmarktplatz_Id`);

--
-- Indizes für die Tabelle `unternehmen`
--
ALTER TABLE `unternehmen`
  ADD PRIMARY KEY (`Unternehmen_Id`),
  ADD KEY `Unternehmen_Id` (`Unternehmen_Id`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `ausschreibung`
--
ALTER TABLE `ausschreibung`
  ADD CONSTRAINT `Fremdschlüssel_Ausschreibender` FOREIGN KEY (`Ausschreibender_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Fremdschlüssel_Partnerprofil` FOREIGN KEY (`Partnerprofil_Id`) REFERENCES `partnerprofil` (`Partnerprofil_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ausschreibung_ibfk_1` FOREIGN KEY (`Projekt_Id`) REFERENCES `projekt` (`Projekt_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `beteiligung`
--
ALTER TABLE `beteiligung`
  ADD CONSTRAINT `beteiligung_ibfk_1` FOREIGN KEY (`Bewertung_Id`) REFERENCES `bewertung` (`Bewertung_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `beteiligung_ibfk_2` FOREIGN KEY (`Beteiligter_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `beteiligung_ibfk_3` FOREIGN KEY (`Projekt_Id`) REFERENCES `projekt` (`Projekt_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `bewerbung`
--
ALTER TABLE `bewerbung`
  ADD CONSTRAINT `Fremdschlüssel_Ausschreibung` FOREIGN KEY (`Ausschreibung_Id`) REFERENCES `ausschreibung` (`Ausschreibung_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `bewerbung_ibfk_1` FOREIGN KEY (`Organisationseinheit_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `bewertung`
--
ALTER TABLE `bewertung`
  ADD CONSTRAINT `bewertung_ibfk_1` FOREIGN KEY (`Bewerbung_Id`) REFERENCES `bewerbung` (`Bewerbung_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `eigenschaft`
--
ALTER TABLE `eigenschaft`
  ADD CONSTRAINT `eigenschaft_ibfk_1` FOREIGN KEY (`Partnerprofil_Id`) REFERENCES `partnerprofil` (`Partnerprofil_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `organisationseinheit`
--
ALTER TABLE `organisationseinheit`
  ADD CONSTRAINT `organisationseinheit_ibfk_1` FOREIGN KEY (`Partnerprofil_Id`) REFERENCES `partnerprofil` (`Partnerprofil_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`Person_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `person_ibfk_2` FOREIGN KEY (`Unternehmen_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `person_ibfk_3` FOREIGN KEY (`Team_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `projekt`
--
ALTER TABLE `projekt`
  ADD CONSTRAINT `projekt_ibfk_1` FOREIGN KEY (`Projektleiter_Id`) REFERENCES `person` (`Person_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `projekt_ibfk_2` FOREIGN KEY (`Projektmarktplatz_Id`) REFERENCES `projektmarktplatz` (`Projektmarktplatz_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `team_ibfk_1` FOREIGN KEY (`Team_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `team_ibfk_2` FOREIGN KEY (`Unternehmen_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `teilnahme`
--
ALTER TABLE `teilnahme`
  ADD CONSTRAINT `Primärschlüssel_von_Person` FOREIGN KEY (`Person_Id`) REFERENCES `person` (`Person_Id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `Primärschlüssel_von_Projektmarktplatz` FOREIGN KEY (`Projektmarktplatz_Id`) REFERENCES `projektmarktplatz` (`Projektmarktplatz_Id`) ON UPDATE CASCADE;

--
-- Constraints der Tabelle `unternehmen`
--
ALTER TABLE `unternehmen`
  ADD CONSTRAINT `unternehmen_ibfk_1` FOREIGN KEY (`Unternehmen_Id`) REFERENCES `organisationseinheit` (`Organisationseinheit_Id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
