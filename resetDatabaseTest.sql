DROP DATABASE `glitch`;

create database Glitch;
use Glitch;

CREATE TABLE Utente (
  username  varchar(20) NOT NULL, 
  email     varchar(40) NOT NULL, 
  cartaDiCredito varchar(16) ,
  password  varchar(15) NOT NULL, 
  nome      char(15) NOT NULL, 
  cognome   char(15) NOT NULL, 
  provincia char(3),
  CAP       int, 
  citta    varchar(11), 
  via       char(20), 
  numero    int, 
  ruolo     char(15), 
  PRIMARY KEY (username, 
  email));
  
  CREATE TABLE `Carta di credito` (
  numeroCarta    varchar(16) , 
  utenteUsername varchar(20) , 
  utenteEmail    varchar(40) , 
  nome           char(15) NOT NULL, 
  cognome        char(15) NOT NULL, 
  scadenza       date NOT NULL, 
  CVV            int NOT NULL, 
  PRIMARY KEY (numeroCarta, 
  utenteUsername, 
  utenteEmail));
CREATE TABLE Prodotto (
  ID            int NOT NULL AUTO_INCREMENT, 
  immagine      blob, 
  prezzo        double NOT NULL, 
  descrizione   blob,  
  PRIMARY KEY (ID));
CREATE TABLE Console (
  prodottoID      int NOT NULL, 
  modello         varchar(15) NOT NULL, 
  casaProduttrice char(15) NOT NULL, 
  PRIMARY KEY (prodottoID));
CREATE TABLE Videogioco (
  prodottoID  int NOT NULL, 
  nome        varchar(40) NOT NULL, 
  genere      char(10) NOT NULL, 
  piattaforma varchar(15) NOT NULL, 
  PRIMARY KEY (prodottoID));
CREATE TABLE Carrello (
  prodottoID     int NOT NULL, 
  utenteUsername varchar(20) NOT NULL, 
  utenteEmail    varchar(40) NOT NULL, 
  quantita      int NOT NULL, 
  PRIMARY KEY (prodottoID, 
  utenteUsername, 
  utenteEmail));
CREATE TABLE Ordine (
   ordineId			int NOT NULL,
   utenteUsername	varchar(20) NOT NULL,
   utenteEmail		varchar(40) NOT NULL,
   nome				varchar(30) NOT NULL,
   quantita			int	NOT NULL,
   prezzo			double	NOT NULL,
  dataOrdinazione   date NOT NULL);
CREATE TABLE Richiesta (
  id             int NOT NULL, 
  utenteEmail    varchar(40) NOT NULL, 
  utenteUsername varchar(20) NOT NULL, 
  destinatario   varchar(25) NOT NULL, 
  descrizione    blob NOT NULL, 
  stato          bit(1) NOT NULL, 
  PRIMARY KEY (id, 
  utenteEmail));
CREATE TABLE Offerta (
  codice    int NOT NULL, 
  sconto    int NOT NULL, 
  categoria char(20) NOT NULL,
  nome      char(15) NOT NULL,
  PRIMARY KEY (codice));

ALTER TABLE Console ADD INDEX FKConsole133890 (prodottoID), ADD CONSTRAINT FKConsole133890 FOREIGN KEY (prodottoID) REFERENCES Prodotto (ID)  on delete cascade
    on update cascade;
ALTER TABLE Videogioco ADD INDEX FKVideogioco351250 (prodottoID), ADD CONSTRAINT FKVideogioco351250 FOREIGN KEY (prodottoID) REFERENCES Prodotto (ID) on delete cascade
    on update cascade;
ALTER TABLE Carrello ADD INDEX FKCarrello618141 (utenteUsername, utenteEmail), ADD CONSTRAINT FKCarrello618141 FOREIGN KEY (utenteUsername, utenteEmail) REFERENCES Utente (username, email) on delete cascade
    on update cascade;
ALTER TABLE Richiesta ADD INDEX FKRichiesta300529 (utenteUsername, utenteEmail), ADD CONSTRAINT FKRichiesta300529 FOREIGN KEY (utenteUsername, utenteEmail) REFERENCES Utente (username, email) on delete cascade
    on update cascade;
ALTER TABLE Carrello ADD INDEX FKCarrello511555 (prodottoID), ADD CONSTRAINT FKCarrello511555 FOREIGN KEY (prodottoID) REFERENCES Prodotto (ID) on delete cascade
    on update cascade;
ALTER TABLE `Carta di credito` ADD INDEX `FKCarta di c550472` (utenteUsername, utenteEmail), ADD CONSTRAINT `FKCarta di c550472` FOREIGN KEY (utenteUsername, utenteEmail) REFERENCES Utente (username, email) on delete cascade
    on update cascade;
Alter table Utente Add Index fkUtente9988(cartaDiCredito), add foreign key  (cartaDiCredito) references `Carta di credito` (numeroCarta)  on delete cascade
    on update cascade;