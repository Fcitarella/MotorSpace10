CREATE TABLE utenteregistrato
(
    username      varchar(30) NOT NULL,
    email         varchar(50) NOT NULL,
    password      varchar(30) NOT NULL,
    nome          varchar(30) NOT NULL,
    cognome       varchar(30) NOT NULL,
    datadinascita date        NOT NULL,
    admin         tinyint DEFAULT NULL,
    PRIMARY KEY (username),
    UNIQUE KEY email_UNIQUE (email),
    UNIQUE KEY username_UNIQUE (username)
);

CREATE TABLE amministratore
(
    username           varchar(30) NOT NULL,
    tipoamministratore varchar(30) NOT NULL,
    PRIMARY KEY (username),
    CONSTRAINT amministratore_ibfk_1 FOREIGN KEY (username) REFERENCES utenteregistrato (username)
);

CREATE TABLE cliente
(
    username varchar(30) NOT NULL,
    PRIMARY KEY (username),
    CONSTRAINT cliente_ibfk_1 FOREIGN KEY (username) REFERENCES utenteregistrato (username)
);

CREATE TABLE categoria
(
    id          int AUTO_INCREMENT,
    nome        varchar(30) NOT NULL,
    descrizione varchar(150) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE prodotto
(
    nome        varchar(30) NOT NULL,
    descrizione varchar(50) NOT NULL,
    codice      varchar(30) NOT NULL,
    marca       varchar(30) NOT NULL,
    prezzo      float       NOT NULL,
    categoria   int DEFAULT NULL,
    PRIMARY KEY (codice),

    CONSTRAINT categoria FOREIGN KEY (categoria) REFERENCES categoria (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE recensione
(
    autore   varchar(30)  NOT NULL,
    prodotto varchar(30)  NOT NULL,
    testo    varchar(200) NOT NULL,
    data     date         NOT NULL,
    PRIMARY KEY (autore, prodotto),

    CONSTRAINT recensione_ibfk_1 FOREIGN KEY (autore) REFERENCES cliente (username),
    CONSTRAINT recensione_ibfk_2 FOREIGN KEY (prodotto) REFERENCES prodotto (codice)
);

CREATE TABLE incarrello
(
    username       varchar(30) NOT NULL,
    qt             int         NOT NULL,
    prezzounitario float       NOT NULL,
    prodotto       varchar(30) NOT NULL,
    PRIMARY KEY (username, prodotto),
    CONSTRAINT A FOREIGN KEY (username) REFERENCES utenteregistrato (username),
    CONSTRAINT B FOREIGN KEY (prodotto) REFERENCES prodotto (codice)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ordine
(
    id_ordine      int         NOT NULL AUTO_INCREMENT,
    cliente        varchar(30) NOT NULL,
    dataordine     date        NOT NULL,
    dataspedizione date        NOT NULL,
    dataconsegna   date        NOT NULL,
    PRIMARY KEY (id_ordine),

    CONSTRAINT cliente FOREIGN KEY (cliente) REFERENCES utenteregistrato (username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE indirizzospedizione
(
    ordine  int         NOT NULL,
    via     varchar(30) NOT NULL,
    cap     varchar(5)  NOT NULL,
    città   varchar(30) NOT NULL,
    regione varchar(30) NOT NULL,
    PRIMARY KEY (ordine),
    CONSTRAINT ordineindirizzo FOREIGN KEY (ordine) REFERENCES ordine (id_ordine) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pagamento
(
    ordine                int         NOT NULL,
    datapagamento         varchar(30) NOT NULL,
    tipodicarta           varchar(10) NOT NULL,
    numerodicartaparziale varchar(8)  NOT NULL,
    PRIMARY KEY (ordine),
    CONSTRAINT ordineinpagamento FOREIGN KEY (ordine) REFERENCES ordine (id_ordine) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE lineadordine
(
    ordine         int         NOT NULL,
    prodotto       varchar(30) NOT NULL,
    qt             int         NOT NULL,
    prezzounitario float       NOT NULL,
    PRIMARY KEY (ordine, prodotto),

    CONSTRAINT codiceordine FOREIGN KEY (ordine) REFERENCES ordine (id_ordine) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT codiceprodotto FOREIGN KEY (prodotto) REFERENCES prodotto (codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE prodottoincatalogo
(
    prodotto     varchar(30) NOT NULL,
    acquistabile tinyint(1) NOT NULL,
    PRIMARY KEY (prodotto),
    CONSTRAINT prodottoincatalogo_ibfk_1 FOREIGN KEY (prodotto) REFERENCES prodotto (codice)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE sconto
(
    codicesconto varchar(30) NOT NULL,
    prodotto     varchar(30) NOT NULL,
    valore       int         NOT NULL,
    PRIMARY KEY (codicesconto),

    CONSTRAINT sconto_ibfk_1 FOREIGN KEY (prodotto) REFERENCES prodotto (codice)
);
/*Insert utenteregistrato*/
INSERT INTO utenteregistrato
VALUES ('carmelo45', 'carmelitorusso@gmail.com', 'napoli1980', 'Carmelo', 'Russo', '1945-01-19', NULL);
INSERT INTO utenteregistrato
VALUES ('AMOVIAGGIARE', 'GATTINI@outlook.com', 'VIVALAVITA', 'MONICA', 'DE FALCO', '1958-10-22', NULL);
INSERT INTO utenteregistrato
VALUES ('Teresa00', 'teresitas@gmail.com', 'foglio10', 'Teresa', 'Iazzetta', '1978-01-01', NULL);
INSERT INTO utenteregistrato
VALUES ('Zahokyu1', 'deathnote@gmail.com', 'tiscrivo0', 'Aldo', 'De Falco', '1999-02-03', NULL);
INSERT INTO utenteregistrato
VALUES ('Pasquale88', 'rolex@gmail.com', '777ganggang', 'Pasquale', 'Cesare', '1996-03-06', NULL);
INSERT INTO utenteregistrato
VALUES ('catena2', 'forzadentrodite@gmail.com', 'believeinyou', 'Andrea', 'Alfano', '1970-12-09', NULL);
INSERT INTO utenteregistrato
VALUES ('highwayto', 'musicmylife@gmail.com', 'ilovemusiC', 'Emanuele', 'Iannella', '1989-11-13', NULL);
INSERT INTO utenteregistrato
VALUES ('peppozz', 'napoliunicoamore@gmail.com', 'jammeja', 'Giuseppe', 'Zannetti', '1991-05-12', NULL);
INSERT INTO utenteregistrato
VALUES ('joycle', 'lifeisgood@gmail.com', 'imadandyCANDY', 'Federica', 'Spada', '1996-09-11', NULL);
INSERT INTO utenteregistrato
VALUES ('busyman101', 'workworkwork@gmail.com', 'FigaFattura', 'Antonio', 'Luciano', '2002-01-19', NULL);
INSERT INTO utenteregistrato
VALUES ('anzeen', 'gocopypaste@gmail.com', '8reread8', 'Roberta', 'Rusciano', '2001-01-31', NULL);
INSERT INTO utenteregistrato
VALUES ('francescogallotto', 'francesco1313@gmail.com', 'miaPass123', 'Francesco', 'Sgordi', '1992-12-27', NULL);
INSERT INTO utenteregistrato
VALUES ('aurora200', 'livelife200@gmail.com', 'laluceDentro', 'Aurora', 'De Felice', '1994-11-09', NULL);
INSERT INTO utenteregistrato
VALUES ('ruoteveloci777', 'atuttabirra@gmail.com', 'velocita001725', 'Barbara', 'Nuovo', '1991-08-09', NULL);
INSERT INTO utenteregistrato
VALUES ('adminCatalogo', 'amministrazioneCatalogo@motorspace.com', 'Xert98CVap', 'Amministratore', 'Catalogo',
        '1900-01-01', 1);
INSERT INTO utenteregistrato
VALUES ('adminRecensioni', 'amministrazioneRecensioni@motorspace.com', 'Yerp34GFli', 'Amministratore', 'Recensioni',
        '1900-01-01', 1);
INSERT INTO utenteregistrato
VALUES ('adminSconti', 'amministrazioneSconti@motorspace.com', 'Gity34hbnLS', 'Amministratore', 'Sconti', '1900-01-01',
        1);

/*Insert categoria */
INSERT INTO categoria
VALUES ('1', 'Caschi', 'Esplora la nostra selezione di caschi');
INSERT INTO categoria
VALUES ('2', 'Giacche', 'Esplora la nostra selezioni di giacche');
INSERT INTO categoria
VALUES ('3', 'Pantaloni', 'Esplora la nostra selezione di pantaloni');
INSERT INTO categoria
VALUES ('4', 'Guanti', 'Esplora la nostra selezione di guanti');
INSERT INTO categoria
VALUES ('5', 'Stivali', 'Esplora la nostra selezione di stivali');
INSERT INTO categoria
VALUES ('6', 'Protezioni', 'Esplora la nostra selezione di protezioni');
INSERT INTO categoria
VALUES ('7', 'Batterie', 'Esplora la nostra selezione di batterie');
INSERT INTO categoria
VALUES ('8', 'Motore e trasmissione', 'Esplora la nostra selezione di motori e trasmissioni');
INSERT INTO categoria
VALUES ('9', 'Cavalletti', 'Esplora la nostra selezione di cavalletti');
INSERT INTO categoria
VALUES ('10', 'Manubri, manopole e leve', 'Esplora la nostra selezione di manubri, manopole e leve');
INSERT INTO categoria
VALUES ('11', 'Luci e frecce', 'Esplora la nostra selezione di luci e frecce');
INSERT INTO categoria
VALUES ('12', 'Specchietti', 'Esplora la nostra selezione di specchietti');
INSERT INTO categoria
VALUES ('13', 'Protezioni moto', 'Esplora la nostra selezione di protezioni');
INSERT INTO categoria
VALUES ('14', 'Antifurto e protezione', 'Esplora la nostra selezione di dispositivi antifurto e di protezione');
INSERT INTO categoria
VALUES ('15', 'Ruote', 'Esplora la nostra selezione di ruote');
INSERT INTO categoria
VALUES ('16', 'Coprimoto', 'Esplora la nostra selezione di corpimoto');
INSERT INTO categoria
VALUES ('17', 'Accessori da viaggio', 'Esplora la nostra selezione di accessori da viaggio');

/*Insert prodotto
INSERT INTO prodotto VALUES ('nome', 'descrizione', 'codice', 'marca', 'prezzo', 'categoria'); */
INSERT INTO prodotto
VALUES ('Casco modulare Dubai 504A', 'Perfetto per il viaggio.', '0000000001', 'Duabi', 89.99, 1);
INSERT INTO prodotto
VALUES ('Casco integrale RoadRunner', 'Casco integrale, leggero e pratico.', '0000000002', 'Befast', 69.00, 1);
INSERT INTO prodotto
VALUES ('Casco integrale Spartan', 'Casco integrale in fibra, leggero e comodo.', '0000000003', 'Shark', 365.00, 1);
INSERT INTO prodotto
VALUES ('Casco FF323 ARROW C RACING', 'Casco integrale in carbonio leggero.', '0000000004', 'L32', 367.90, 1);
INSERT INTO prodotto
VALUES ('Giacca moto Albert Avio', 'Giacca da uomo leggera, antivento e traspirante. ', '0000000005', 'Tucano', '69.00',
        '2');
INSERT INTO prodotto
VALUES ('Giacco moto Urbano Febo Toffee', 'Giacca leggera antivento.', '0000000006', 'Tucano', '69.00', '2');
INSERT INTO prodotto
VALUES ('Giacca moto traforata', 'Giacca traforata con tessuti in poliammide.', '0000000007', 'OJ LEVEL', '76.90', '2');

INSERT INTO prodotto
VALUES ('Pantaloni Moto Zero', 'Pantaloni tre strati in 600D.', '0000000008', 'Jollispot', '82.90', '3');
INSERT INTO prodotto
VALUES ('Pantaloni turismo 8827', 'Pantaloni da turismo.', '0000000009', 'Tucano', '79.90', '3');

INSERT INTO prodotto
VALUES ('Guanti moto invernali', 'I guanti moto invernali sono in Windtex.', '0000000010', 'marca', '40.00', '4');
INSERT INTO prodotto
VALUES ('Guanti moto 2 RTX', 'Guanti invernali con membrana Reintex.', '0000000011', 'Macna', '39.95', '4');
INSERT INTO prodotto
VALUES ('Guanti moto Nordic', 'Certificati secondo la normative EN 13594:15 ', '0000000012', 'BEFAST', '39.00', '4');


/*Insert incarrello*/
INSERT INTO incarrello
VALUES ('Zahokyu1', 2, 365.00, '0000000003');
INSERT INTO incarrello
VALUES ('Zahokyu1', 2, 69.00, '0000000005');
INSERT INTO incarrello
VALUES ('aurora200', 1, 76.90, '0000000007');
INSERT INTO incarrello
VALUES ('aurora200', 1, 69.00, '0000000006');
INSERT INTO incarrello
VALUES ('aurora200', '1', 69.00, '0000000005');
INSERT INTO incarrello
VALUES ('catena2', '2', 69.00, '0000000002');
INSERT INTO incarrello
VALUES ('ruoteveloci777', '4', 89.99, '0000000001');
INSERT INTO incarrello
VALUES ('ruoteveloci777', '1', 365.00, '0000000003');
INSERT INTO incarrello
VALUES ('ruoteveloci777', '1', 69.00, '0000000002');
INSERT INTO incarrello
VALUES ('ruoteveloci777', '3', 69.00, '0000000005');
INSERT INTO incarrello
VALUES ('Teresa00', '3', 89.99, '0000000001');
INSERT INTO incarrello
VALUES ('Teresa00', '3', 69.99, '0000000005');

/*Insert ordine
INSERT INTO ordine VALUES ('id', 'cliente', 'dataOrdine', 'dataSpedizione', 'dataConsegna');*/
INSERT INTO ordine
VALUES (1, 'Pasquale88', '2020-01-01', '2020-01-03', '2020-01-05');
INSERT INTO ordine
VALUES (2, 'joycle', '2020-02-03', '2020-02-04', '2020-02-07');
INSERT INTO ordine
VALUES (3, 'AMOVIAGGIARE', '2020-02-03', '2020-02-05', '2020-02-08');


/*Insert indirizzospedizione
INSERT INTO indirizzospedizione VALUES ('ordineint', 'via', 'cap', 'città', 'regione');*/
INSERT INTO indirizzospedizione
VALUES (1, 'via Fiori Rossi', '80010', 'Roma', 'Lazio');
INSERT INTO indirizzospedizione
VALUES (2, 'via otto caprette', '70010', 'Marano di Napoli', 'Campania');
INSERT INTO indirizzospedizione
VALUES (3, 'via della monaca', '60010', 'Salerno', 'Campania');

/*Insert pagamento
INSERT INTO pagamento VALUES ('ordine', 'datadipagamento', 'tipodicarta', 'numerodicartaparziale');*/
INSERT INTO pagamento
VALUES (1, '2020-01-01', 'VISA', '4345');
INSERT INTO pagamento
VALUES (2, '2020-02-03', 'AURA', '1333');
INSERT INTO pagamento
VALUES (3, '2020-02-03', 'MASTERCARD', '7533');

/*Insert lineadordine
INSERT INTO lineadordine VALUES ('ordine', 'prodotto', 'quantità', 'prezzounitario');*/
INSERT INTO lineadordine
VALUES (1, '0000000003', 1, 365);
INSERT INTO lineadordine
VALUES (2, '0000000006', 1, 69);
INSERT INTO lineadordine
VALUES (2, '0000000001', 4, 89.99);
INSERT INTO lineadordine
VALUES (3, '0000000010', 1, 40);


/*Insert prodottoincagtalogo*/
INSERT INTO prodottoincatalogo
VALUES ('0000000001', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000002', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000003', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000004', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000005', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000006', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000007', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000008', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000009', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000010', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000011', '1');
INSERT INTO prodottoincatalogo
VALUES ('0000000012', '1');
