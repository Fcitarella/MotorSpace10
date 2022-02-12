DROP DATABASE IF EXISTS MotorSpace;
CREATE DATABASE MotorSpace;
USE MotorSpace;

CREATE TABLE amministratore
(	/*necessaria*/
    id int	AUTO_INCREMENT NOT NULL,
    username      varchar(30) NOT NULL,
    email         varchar(50) NOT NULL,
    password      varchar(30) NOT NULL,
    nome          varchar(30) NOT NULL,
    cognome       varchar(30) NOT NULL,
    codicediaccesso int 	  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY email_UNIQUE (email),
    UNIQUE KEY codice_UNIQUE(codicediaccesso),
    UNIQUE KEY username_UNIQUE (username)
);

CREATE TABLE cliente
(
    /*necessaria*/
    id int	AUTO_INCREMENT NOT NULL,
    username      varchar(30) NOT NULL,
    email         varchar(50) NOT NULL,
    password      varchar(30) NOT NULL,
    nome          varchar(30) NOT NULL,
    cognome       varchar(30) NOT NULL,
    PRIMARY KEY (id,username),
    UNIQUE KEY email_UNIQUE (email),
    UNIQUE KEY username_UNIQUE (username)
);

CREATE TABLE categoria
(
    /*necessaria*/
    id          int NOT NULL AUTO_INCREMENT ,
    nome        varchar(30) NOT NULL,
    descrizione varchar(150) DEFAULT NULL,
    macrocategoria varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE prodotto
(
    /*necessaria*/
    id int	AUTO_INCREMENT NOT NULL,
    nome        varchar(30) NOT NULL,
    descrizione varchar(50) NOT NULL,
    marca       varchar(30) NOT NULL,
    prezzo      float       NOT NULL,
    categoria   int DEFAULT NULL,
    PRIMARY KEY (id),

    CONSTRAINT categoria FOREIGN KEY (categoria) REFERENCES categoria (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE recensione
(
    /*necessaria*/

    autore   varchar(30)  NOT NULL,
    prodotto int  NOT NULL,
    testo    varchar(200) NOT NULL,
    data     date         NOT NULL,
    PRIMARY KEY (autore, prodotto),

    CONSTRAINT recensione_ibfk_1 FOREIGN KEY (autore) REFERENCES cliente (username),
    CONSTRAINT recensione_ibfk_2 FOREIGN KEY (prodotto) REFERENCES prodotto (id)
);


CREATE TABLE ordine
(
    /*necessaria*/
    id_ordine      int         NOT NULL AUTO_INCREMENT,
    cliente        varchar(30) NOT NULL,
    dataordine     date        NOT NULL,
    dataspedizione date        NOT NULL,
    dataconsegna   date        NOT NULL,
    PRIMARY KEY (id_ordine),

    CONSTRAINT cliente FOREIGN KEY (cliente) REFERENCES cliente (username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE indirizzospedizione
(
    /*necessaria*/
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
    /*necessaria*/
    ordine                int         NOT NULL,
    datapagamento         varchar(30) NOT NULL,
    tipodicarta           varchar(10) NOT NULL,
    numerodicartaparziale varchar(8)  NOT NULL,
    PRIMARY KEY (ordine),
    CONSTRAINT ordineinpagamento FOREIGN KEY (ordine) REFERENCES ordine (id_ordine) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE lineadordine
(
    /*necessaria*/
    ordine         int         NOT NULL,
    prodotto       int NOT NULL,
    qt             int         NOT NULL,
    prezzounitario float       NOT NULL,
    PRIMARY KEY (ordine, prodotto),

    CONSTRAINT codiceordine FOREIGN KEY (ordine) REFERENCES ordine (id_ordine) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT codiceprodotto FOREIGN KEY (prodotto) REFERENCES prodotto (id) ON DELETE CASCADE ON UPDATE CASCADE
);
/*Insert categoria */
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Caschi', 'Esplora la nostra selezione di caschi','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Giacche', 'Esplora la nostra selezioni di giacche','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Pantaloni', 'Esplora la nostra selezione di pantaloni','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Guanti', 'Esplora la nostra selezione di guanti','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Stivali', 'Esplora la nostra selezione di stivali','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Protezioni', 'Esplora la nostra selezione di protezioni','Vestiario');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Batterie', 'Esplora la nostra selezione di batterie','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ('Trasmissioni', 'Esplora la nostra selezione di motori e trasmissioni','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Cavalletti', 'Esplora la nostra selezione di cavalletti','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Manubri, manopole e leve', 'Esplora la nostra selezione di manubri, manopole e leve','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Luci e frecce', 'Esplora la nostra selezione di luci e frecce','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ('Specchietti', 'Esplora la nostra selezione di specchietti','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ('Protezioni moto', 'Esplora la nostra selezione di protezioni','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ('Antifurto e protezione', 'Esplora la nostra selezione di dispositivi antifurto e di protezione','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Pneumatici', 'Esplora la nostra selezione di ruote','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ( 'Accessori da viaggio', 'Esplora la nostra selezione di accessori da viaggio','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES ('Parti Freno','Esplora la nostra selezione di accessori da viaggio','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES('Lavaggio e pulizia','Esplora la nostra selezione di accessori per il lavaggio e la pulizia','AccessoriMoto');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES('Carene','Esplora la nostra selezione di carene per la tua moto','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES('Sospensioni','Esplora la nostra selezione di sospensioni per la tua moto','PartiDiRicambio');
INSERT INTO categoria(nome,descrizione,macrocategoria)
VALUES('Parti motore e Filtri','Esplora la nostra selezione di parti motore e filtri','PartiDiRicambio');

/*Insert prodotto
INSERT INTO prodotto VALUES ('nome', 'descrizione', 'codice', 'marca', 'prezzo', 'categoria'); */
INSERT INTO prodotto(nome, descrizione, marca, prezzo, categoria)
VALUES ('Casco modulare Dubai 504A', 'Perfetto per il viaggio.', 'Duabi', 89.99, 1);
INSERT INTO prodotto (nome, descrizione, marca, prezzo, categoria)
VALUES ('Casco integrale RoadRunner', 'Casco integrale, leggero e pratico.', 'Befast', 69.00, 1);
INSERT INTO prodotto (nome, descrizione, marca, prezzo, categoria)
VALUES ('Casco integrale Spartan', 'Casco integrale in fibra, leggero e comodo.', 'Shark', 365.00, 1);
INSERT INTO prodotto (nome, descrizione, marca, prezzo, categoria)
VALUES ('Casco FF323 ARROW C RACING', 'Casco integrale in carbonio leggero.', 'L32', 367.90, 1);
INSERT INTO prodotto (nome, descrizione, marca, prezzo, categoria)
VALUES ('Giacca moto Albert Avio', 'Giacca da uomo leggera, antivento e traspirante. ', 'Tucano', '69.00','2');

insert into cliente( username, email, password, nome, cognome )
values ('Spaghettino', 'dkoo@sddfs.it', 'Ciaone55', 'Rodolfo', 'Signorini');