DROP DATABASE IF EXISTS MotorSpace;
CREATE DATABASE MotorSpace;
USE MotorSpace;

CREATE TABLE `utenteregistrato` (
                                    `username` varchar(30) NOT NULL,
                                    `email` varchar(50) NOT NULL,
                                    `password` varchar(30) NOT NULL,
                                    `nome` varchar(30) NOT NULL,
                                    `cognome` varchar(30) NOT NULL,
                                    `datadinascita` date NOT NULL,
                                    `admin` tinyint DEFAULT NULL,
                                    PRIMARY KEY (`username`),
                                    UNIQUE KEY `email_UNIQUE` (`email`),
                                    UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `amministratore` (
                                  `username` varchar(30) NOT NULL,
                                  `tipoamministratore` varchar(30) NOT NULL,
                                  PRIMARY KEY (`username`),
                                  CONSTRAINT `amministratore_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utenteregistrato` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `cliente` (
                           `username` varchar(30) NOT NULL,
                           PRIMARY KEY (`username`),
                           CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`username`) REFERENCES `utenteregistrato` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `categoria` (
                             `id` int NOT NULL,
                             `nome` varchar(30) NOT NULL,
                             `descrizione` varchar(150) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `prodotto` (
                            `nome` varchar(30) NOT NULL,
                            `descrizione` varchar(50) NOT NULL,
                            `codice` varchar(30) NOT NULL,
                            `marca` varchar(30) NOT NULL,
                            `prezzo` float NOT NULL,
                            `categoria` int DEFAULT NULL,
                            PRIMARY KEY (`codice`),
                            KEY `categoria` (`categoria`),
                            FULLTEXT KEY `nome` (`nome`,`descrizione`),
                            CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `recensione` (
                              `autore` varchar(30) NOT NULL,
                              `prodotto` varchar(30) NOT NULL,
                              `testo` varchar(200) NOT NULL,
                              `data` date NOT NULL,
                              PRIMARY KEY (`autore`,`prodotto`),
                              KEY `prodotto` (`prodotto`),
                              CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`autore`) REFERENCES `cliente` (`username`),
                              CONSTRAINT `recensione_ibfk_2` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `incarrello` (
                              `username` varchar(30) NOT NULL,
                              `qt` int NOT NULL,
                              `prezzounitario` float NOT NULL,
                              `prodotto` varchar(30) NOT NULL,
                              PRIMARY KEY (`username`,`prodotto`),
                              KEY `prodotto` (`prodotto`),
                              CONSTRAINT `incarrello_ibfk_1` FOREIGN KEY (`username`) REFERENCES `cliente` (`username`),
                              CONSTRAINT `incarrello_ibfk_2` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `ordine` (
                          `id_ordine` varchar(30) NOT NULL,
                          `cliente` varchar(30) NOT NULL,
                          `dataordine` date NOT NULL,
                          `dataspedizione` date NOT NULL,
                          `dataconsegna` date NOT NULL,
                          PRIMARY KEY (`id_ordine`),
                          KEY `cliente_idx` (`cliente`),
                          CONSTRAINT `cliente` FOREIGN KEY (`cliente`) REFERENCES `utenteregistrato` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `indirizzospedizione` (
                                       `ordine` varchar(30) NOT NULL,
                                       `via` varchar(30) NOT NULL,
                                       `cap` varchar(5) NOT NULL,
                                       `città` varchar(30) NOT NULL,
                                       `regione` varchar(30) NOT NULL,
                                       PRIMARY KEY (`ordine`),
                                       CONSTRAINT `indirizzospedizione_ibfk_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `pagamento` (
                             `ordine` varchar(30) NOT NULL,
                             `datapagamento` varchar(30) NOT NULL,
                             `tipodicarta` varchar(10) NOT NULL,
                             `numerodicartaparziale` varchar(8) NOT NULL,
                             PRIMARY KEY (`ordine`),
                             CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `lineadordine` (
                                `ordine` varchar(30) NOT NULL,
                                `prodotto` varchar(30) NOT NULL,
                                `qt` int NOT NULL,
                                `prezzounitario` float NOT NULL,
                                PRIMARY KEY (`ordine`,`prodotto`),
                                KEY `prodotto` (`prodotto`),
                                CONSTRAINT `lineadordine_ibfk_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`id_ordine`),
                                CONSTRAINT `lineadordine_ibfk_2` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `prodottoincatalogo` (
                                      `prodotto` varchar(30) NOT NULL,
                                      `acquistabile` tinyint(1) NOT NULL,
                                      `prezzo` float NOT NULL,
                                      PRIMARY KEY (`prodotto`),
                                      CONSTRAINT `prodottoincatalogo_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `sconto` (
                          `codicesconto` varchar(30) NOT NULL,
                          `prodotto` varchar(30) NOT NULL,
                          `valore` int NOT NULL,
                          PRIMARY KEY (`codicesconto`),
                          KEY `prodotto` (`prodotto`),
                          CONSTRAINT `sconto_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci