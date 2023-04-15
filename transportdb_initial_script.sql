CREATE DATABASE IF NOT EXISTS `testDB`;

USE  `testdb`;

DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
	`employee_id` int AUTO_INCREMENT,
    `first_name` varchar(50),
    `last_name` varchar(50),
    `email` varchar(50),
    `phone` varchar(15),
    `salary` float,
    PRIMARY KEY(`employee_id`)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `employee` VALUES 
	(1,"Michał","Nowak","michal@gmail.com", "111222333",3500),
    (2,"Paweł","Jarosz","pawel@gmail.com","123123123",4000);

CREATE TABLE `address` (
	`address_id` int AUTO_INCREMENT,
    `address` varchar(50),
	`type` varchar(10),
    `employee_id` int,
    PRIMARY KEY(`address_id`),
	FOREIGN KEY(`employee_id`) REFERENCES `employee` (`employee_id`)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `address` VALUES 
	(1,"some address","some",1),
    (2,"another address","another",1),
    (3,"yet another address","yet",2);
