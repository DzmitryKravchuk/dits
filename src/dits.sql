DROP DATABASE IF EXISTS dits_test;

CREATE DATABASE dits_test DEFAULT CHARACTER SET 'utf8';

USE dits_test;

CREATE TABLE `Role`
(
    `roleId` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `user`   BINARY              NOT NULL,
    `tutor`  BINARY              NOT NULL,
    `admin`  BINARY              NOT NULL
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