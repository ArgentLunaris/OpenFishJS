CREATE DATABASE `OpenFishJS`;
USE `OpenFishJS`;
CREATE TABLE `Fish`(
	Id int,
    species varchar(50),
    `desc` varchar(50),
    minWeight int,
    maxWeight int,
    wiki varchar(100),
    rarity varchar(50)
);