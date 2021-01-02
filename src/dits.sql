DROP DATABASE IF EXISTS dits_test;

CREATE DATABASE dits_test DEFAULT CHARACTER SET 'utf8';

USE dits_test;

CREATE TABLE `Role`
(
    `roleId` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`   varchar(255)        NOT NULL
);

CREATE TABLE `User`
(
    `userId`    integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `firstName` varchar(255)        NOT NULL,
    `lastName`  varchar(255)        NOT NULL,
    `login`     varchar(255)        NOT NULL,
    `password`  varchar(255)        NOT NULL,
    `nameRole`  varchar(255)        NOT NULL
);

CREATE TABLE `user_2_role`
(
    `roleId` integer NOT NULL,
    `userId` integer NOT NULL,
    PRIMARY KEY (`roleId`, `userId`),
    FOREIGN KEY (`roleId`) REFERENCES `Role` (`roleId`) ON DELETE CASCADE,
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
);

CREATE TABLE `Topic`
(
    `topicId`     integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`        varchar(255)        NOT NULL,
    `description` varchar(255)        NOT NULL
);

CREATE TABLE `Test`
(
    `testId`      integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`        varchar(255)        NOT NULL,
    `description` varchar(255)        NOT NULL,
    `topicId`     integer             NOT NULL,
    FOREIGN KEY (`topicId`) REFERENCES `Topic` (`topicId`)
);

CREATE TABLE `Question`
(
    `questionId`  integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `description` varchar(255)        NOT NULL,
    `testId`      integer             NOT NULL,
    FOREIGN KEY (`testId`) REFERENCES `Test` (`testId`) ON DELETE CASCADE
);

CREATE TABLE `Answer`
(
    `answerId`    integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `description` varchar(255)        NOT NULL,
    `correct`     BOOLEAN             NOT NULL,
    `questionId`  integer             NOT NULL,
    FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`)
);

CREATE TABLE `Literature`
(
    `literatureId` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `description`  varchar(255)        NOT NULL,
    `questionId`   integer             NOT NULL,
    FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`)
);

CREATE TABLE `Link`
(
    `linkId`       integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `link`         varchar(255)        NOT NULL,
    `literatureId` integer             NOT NULL,
    FOREIGN KEY (`literatureId`) REFERENCES `Literature` (`literatureId`)
);

CREATE TABLE `Statistic`
(
    `statisticId` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `date`        DATE                NOT NULL,
    `correct`     BOOLEAN             NOT NULL,
    `questionId`  integer             NOT NULL,
    `userId`      integer             NOT NULL,
    FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`) ON DELETE CASCADE,
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
);

LOCK TABLES `topic` WRITE;
INSERT INTO `topic`
VALUES (1, 'First topic', 'First topic'),
       (2, 'Second topic', 'Second topic');
UNLOCK TABLES;

LOCK TABLES `test` WRITE;
INSERT INTO `test`
VALUES (1, 'First test', 'First test', '1'),
       (2, 'Second test', 'Second test', '2');
UNLOCK TABLES;

LOCK TABLES `role` WRITE;
INSERT INTO `role`
VALUES (1, 'admin'),
       (2, 'tutor'),
       (3, 'user');
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO `user`
VALUES (1, 'Name1', 'SurName1', 'login1', 'pass1','admin'),
       (2, 'Name2', 'SurName2', 'login2', 'pass2','tutor'),
       (3, 'Name3', 'SurName3', 'login3', 'pass3','user');
UNLOCK TABLES;

LOCK TABLES `user_2_role` WRITE;
INSERT INTO `user_2_role`
VALUES (1, 1),
       (2, 2),
       (3, 3);
UNLOCK TABLES;

