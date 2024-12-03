-- ============================================================================
-- Script for creating the AGP DataBase tables - AGP-DBCreation.sql
-- ============================================================================

-- Droping all tables if exists
DROP TABLE IF EXISTS transport;
DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS relhotelservice;
DROP TABLE IF EXISTS hotel;
DROP TABLE IF EXISTS service;

-- ----------------------------------------------------------------------------

-- Creating Transport table containing all types of transports and their 
-- characteristics
CREATE TABLE transport
(
  name VARCHAR(30) NOT NULL,
  pricePerKm FLOAT NOT NULL,
  speed FLOAT NOT NULL,
  comfort FLOAT NOT NULL,
  PRIMARY KEY(name)
);

-- Creating Site table containing all sites and their characteristics
CREATE TABLE site
(
  name VARCHAR(100) NOT NULL,
  position GEOMETRY NOT NULL,
  price FLOAT NOT NULL,
  isSeaSided BOOLEAN NOT NULL,
  isIntoSea BOOLEAN NOT NULL,
  type VARCHAR(30) NOT NULL,
  PRIMARY KEY(name)
);

-- Creating Hotel table containing all hotels and their characteristics
CREATE TABLE hotel
(
  name VARCHAR(100) NOT NULL,
  position GEOMETRY NOT NULL,
  price FLOAT NOT NULL,
  nbServices INTEGER NOT NULL,
  PRIMARY KEY(name)
);

-- Creating Service table containing all hotels' services and their 
-- characteristics
CREATE TABLE service
(
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY(name)
);

-- Creating RelHotelService table relation between Hotel and Service 
-- tables
CREATE TABLE relhotelservice
(
  nameHotel VARCHAR(100) NOT NULL,
  nameService VARCHAR(50) NOT NULL,
  FOREIGN KEY(nameHotel) REFERENCES hotel(name) ON DELETE CASCADE,
  FOREIGN KEY(nameService) REFERENCES service(name) ON DELETE CASCADE
);

-- ============================================================================
-- Data insertion
-- ============================================================================

-- ---------------------SITE-----------------------------

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Faarumai Waterfalls',ST_GeomFromText('POINT(-17.534299087468145 -149.39944449589822)'),0,false,true,'landscape');

INSERT INTO site (name, position, price, isSeaSided,isIntoSea, type) 
VALUES ('The hole of the blower of Arahoho',ST_GeomFromText('POINT(-17.52309604876949 -149.39084679133384)'),0,true,false,'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Vaipahi Water Garden',ST_GeomFromText('POINT(-17.759566491900213 -149.39043465258078)'),10.5,true,false, 'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Caves of Maraa',ST_GeomFromText('POINT(-17.74388480165823 -149.5665692876325)'),0,true,false,'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Marae Arahurahu',ST_GeomFromText('POINT(-17.696063577238373 -149.57883459911776)'),15,false,false,'cultural');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Discovering sharks',ST_GeomFromText('POINT(-17.456574296746947 -149.31397240132767)'),29.95,true,true,'seascape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Discovering whales',ST_GeomFromText('POINT(-17.724270670522404 -149.6189774668673)'),15.5,true,true,'seascape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Discovering turtles',ST_GeomFromText('POINT(-17.960453875371762 -149.0911025984783)'),10.30,true,true,'seascape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Swim with dolphins',ST_GeomFromText('POINT(-17.39139740872396 -149.17915960960655)'),24.95,true,true,'seascape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Discovering corals',ST_GeomFromText('POINT(-17.519846402141187 -149.59078761447128)'),9.99,true,true,'seascape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Museum of Tahiti and the Islands',ST_GeomFromText('POINT(-17.631295511309307 -149.61402083604008)'),25,true,false,'cultural');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Tomb of King Pomare V',ST_GeomFromText('POINT(-17.5156885858618 -149.52641025756506)'),0,true,false,'cultural');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Mount Thabor',ST_GeomFromText('POINT(-17.53696862686786 -149.51576725257917)'),14.99,false,false,'cultural');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Mount Orohena',ST_GeomFromText('POINT(-17.61409472290844 -149.48325618992)'),0,false,false,'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Belvedere of Taravao',ST_GeomFromText('POINT(-17.774233964311822 -149.25703257193737)'),0,false,false,'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Tautira',ST_GeomFromText('POINT(-17.743580299698984 -149.16345908970604)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Taharuu Beach',ST_GeomFromText('POINT(-17.76879406754628 -149.4805172513238)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Marae Fare Hape',ST_GeomFromText('POINT(-17.63616457242306 -149.43661345812876)'),0,false,false,'landscape');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Papara Shellfish Museum',ST_GeomFromText('POINT(-17.746655470456705 -149.50643700013995)'),19.99,true,false,'cultural');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Pointe Venus Beach',ST_GeomFromText('POINT(-17.491570039090675 -149.49460688854305)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Pension Te Miti ',ST_GeomFromText('POINT(-17.639899287522354 -149.58578423804053)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of The Tahiti by Pearl Resorts',ST_GeomFromText('POINT(-17.496571663785666 -149.51123249737392)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of NINAMU PEARL RESORT TAHITI',ST_GeomFromText('POINT(-17.608923200043634 -149.58958702648238)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Punatea Village',ST_GeomFromText('POINT(-17.725808241598717 -149.2723443828701)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Villa Mitirapa',ST_GeomFromText('POINT(-17.729821229721793 -149.329937256952)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of InterContinental Resort Tahiti',ST_GeomFromText('POINT(-17.568507454766408 -149.61952189878602)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Hilton Hotel Tahiti',ST_GeomFromText('POINT(-17.54336644179948 -149.58727693562028)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Fare Arearea',ST_GeomFromText('POINT(-17.650300655464356 -149.30566672674084)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Manava Suite Resort Tahiti',ST_GeomFromText('POINT(-17.588241147366617 -149.61434482615275)'),0,true,false,'beach');

INSERT INTO site (name, position, price, isSeaSided, isIntoSea, type) 
VALUES ('Beach of Rava Lodge',ST_GeomFromText('POINT(-17.615449256223137 -149.6070568591527)'),0,true,false,'beach');

-- ---------------------HOTEL-----------------------------
INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('Pension Te Miti',ST_GeomFromText('POINT(-17.639899287522354 -149.58578423804053)'),50,0);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('The Tahiti by Pearl Resorts',ST_GeomFromText('POINT(-17.496571663785666 -149.51123249737392)'),433.50,7);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('NINAMU PEARL RESORT TAHITI',ST_GeomFromText('POINT(-17.608923200043634 -149.58958702648238)'),105,2);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('Punatea Village',ST_GeomFromText('POINT(-17.725808241598717 -149.2723443828701)'),70,1);

INSERT INTO hotel(name,position,price,nbServices)
VALUES ('Villa Mitirapa',ST_GeomFromText('POINT(-17.729821229721793 -149.329937256952)'),229.99,5);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('InterContinental Resort Tahiti',ST_GeomFromText('POINT(-17.568507454766408 -149.61952189878602)'),235,5);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('Hilton Hotel Tahiti',ST_GeomFromText('POINT(-17.54336644179948 -149.58727693562028)'),351,6);

INSERT INTO hotel(name,position,price,nbServices)
VALUES ('Fare Arearea',ST_GeomFromText('POINT(-17.650300655464356 -149.30566672674084)'),122,3);

INSERT INTO hotel(name,position,price,nbServices) 
VALUES ('Manava Suite Resort Tahiti',ST_GeomFromText('POINT(-17.588241147366617 -149.61434482615275)'),186,4);

INSERT INTO hotel(name,position,price,nbServices)
VALUES ('Rava Lodge',ST_GeomFromText('POINT(-17.615449256223137 -149.6070568591527)'),90,2);

-- ---------------------TRANSPORT-----------------------------
INSERT INTO transport(name, pricePerKm, speed, comfort)
VALUES('on foot',0,6.4,0);

INSERT INTO transport(name, pricePerKm, speed, comfort)
VALUES('bus',0.5,45,2);

INSERT INTO transport(name, pricePerKm, speed, comfort)
VALUES('boat',1.5,65.5,4);

-- ---------------------SERVICE-----------------------------

INSERT INTO service(name) VALUES ('swimming pool');
INSERT INTO service(name) VALUES ('gym');
INSERT INTO service(name) VALUES ('sauna');
INSERT INTO service(name) VALUES ('restaurant');
INSERT INTO service(name) VALUES ('massage');
INSERT INTO service(name) VALUES ('casino');
INSERT INTO service(name) VALUES ('room service');

-- ----------------------RELHOTELSERVICE----------------------------
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','gym');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','sauna');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','massage');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','casino');
INSERT INTO relhotelservice(nameHotel, nameService) VALUES ('The Tahiti by Pearl Resorts','room service');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('NINAMU PEARL RESORT TAHITI','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('NINAMU PEARL RESORT TAHITI','restaurant');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Punatea Village','restaurant');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Villa Mitirapa','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Villa Mitirapa','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Villa Mitirapa','room service');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Villa Mitirapa','sauna');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Villa Mitirapa','gym');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('InterContinental Resort Tahiti','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('InterContinental Resort Tahiti','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('InterContinental Resort Tahiti','room service');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('InterContinental Resort Tahiti','sauna');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('InterContinental Resort Tahiti','gym');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','room service');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','sauna');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','gym');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Hilton Hotel Tahiti','massage');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Fare Arearea','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Fare Arearea','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Fare Arearea','massage');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Manava Suite Resort Tahiti','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Manava Suite Resort Tahiti','swimming pool');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Manava Suite Resort Tahiti','massage');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Manava Suite Resort Tahiti','room service');

INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Rava Lodge','restaurant');
INSERT INTO relhotelservice(nameHotel, nameService)VALUES('Rava Lodge','swimming pool');

-- ============================================================================
-- End Of File
-- ============================================================================