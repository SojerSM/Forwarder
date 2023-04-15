CREATE DATABASE IF NOT EXISTS `transportdb`;

USE `transportdb`;

DROP TABLE IF EXISTS `freight`;
DROP TABLE IF EXISTS `driver_data`;
DROP TABLE IF EXISTS `carriage`;
DROP TABLE IF EXISTS `forwarder`;

CREATE TABLE `forwarder` (
	`forwarder_id` int AUTO_INCREMENT NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `dob` date,
    PRIMARY KEY(`forwarder_id`)
) AUTO_INCREMENT = 1, DEFAULT CHARSET = utf8mb4;

INSERT INTO `forwarder` VALUES 
	(1,"Piotr","Nowak","1968-08-10"),
    (2,"Maciej","Chrust","1965-05-19");
    
CREATE TABLE `carriage` (
	`carriage_id` int AUTO_INCREMENT NOT NULL,
    `plates` varchar(10) NOT NULL,
    `price_per_km` double(5,2) NOT NULL,
    `forwarder_id` int,
    PRIMARY KEY(`carriage_id`),
    FOREIGN KEY(`forwarder_id`) REFERENCES `forwarder` (`forwarder_id`)
) AUTO_INCREMENT = 1, DEFAULT CHARSET = utf8mb4;

INSERT INTO `carriage` VALUES 
	(1,"ZS198GT",0.55,1),
    (2,"ZST998Y",0.53,1);
    
CREATE TABLE `driver_data` (
	`carriage_id` int UNIQUE NOT NULL,
	`first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `dob` date,
	`salary` double NOT NULL,
    FOREIGN KEY(`carriage_id`) REFERENCES `carriage` (`carriage_id`)
) DEFAULT CHARSET = utf8mb4;

INSERT INTO `driver_data` VALUES 
	(1, "Marcin", "Nowak", "1976-11-10",5500),
    (2, "Krystian", "Marciniak", "1987-09-12",5200);
    
CREATE TABLE `freight` (
	`freight_id` int AUTO_INCREMENT NOT NULL,
	`distance` int NOT NULL,
    `company_name` varchar(50) NOT NULL,
    `value` double(10,2) NOT NULL,
    `carriage_id` int NOT NULL,
    PRIMARY KEY(`freight_id`),
    FOREIGN KEY(`carriage_id`) REFERENCES `carriage` (`carriage_id`)
) AUTO_INCREMENT = 1, DEFAULT CHARSET = utf8mb4;

INSERT INTO `freight` VALUES
	(1,456,"TSL LOGISTICS", 320,1),
    (2,567,"FERCAM AUSTRIA GMBH", 385,1),
    (3,985,"FERCAM AUSTRIA GMBH", 590,2),
    (4,2521,"ROYAL TRANSPORT", 1420,2),
    (5,789,"NOVA SPEED TRANS", 415,2);
    
    
    
    
    
    
    
