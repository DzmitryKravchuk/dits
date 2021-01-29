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
    `password`  varchar(255)        NOT NULL
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
    FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`) ON DELETE CASCADE
);

CREATE TABLE `Literature`
(
    `literatureId` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `description`  varchar(255)        NOT NULL,
    `questionId`   integer             NOT NULL,
    FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`) ON DELETE CASCADE
);

CREATE TABLE `Link`
(
    `linkId`       integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `link`         varchar(255)        NOT NULL,
    `literatureId` integer             NOT NULL,
    FOREIGN KEY (`literatureId`) REFERENCES `Literature` (`literatureId`) ON DELETE CASCADE
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
VALUES (1, 'Java core', 'Java core description'),
       (2, 'Collections', 'Collections descriptions');
UNLOCK TABLES;

LOCK TABLES `test` WRITE;
INSERT INTO `test`
VALUES (1, 'Java core test', 'check general understanding', '1'),
       (2, 'Java Collection Framework test', 'check knowledge about collections', '2');
UNLOCK TABLES;

LOCK TABLES `role` WRITE;
INSERT INTO `role`
VALUES (1, 'admin'),
       (2, 'tutor'),
       (3, 'user');
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO `user`
VALUES (1, 'Василий', 'Теркин', 'admin', '$2a$10$fe/bp6YbQ36czsAiL01qHOnxQRLbPUlgyQqJ4DHCwZ6.jxZzZQUCy'),
       (2, 'Евгений', 'Онегин', 'tutor', '$2a$10$vLBocq7lXsPKhv/0GIGLA.pCICtGYrzHOFP6ExaxmQuUV9zJmhyuS'),
       (3, 'Василиса', 'Премудрая', 'user1', '$2a$10$bvs5FwXrBwvwpEEGYxLwouhf3eOvs.2GpKJj8M2uFPNqFcFNcyM8G'),
       (4, 'Иван', 'Дурак', 'user', '$2a$10$xoxO6lYd6vRBRJZCQ9VKDOfJYW0g7jECnLRbexp8CXVAtWVPEe8Ma');
UNLOCK TABLES;

LOCK TABLES `user_2_role` WRITE;
INSERT INTO `user_2_role`
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4);
UNLOCK TABLES;

LOCK TABLES `question` WRITE;
INSERT INTO `question`
VALUES (1, 'What do you know about arrays & primitive types', '1'),
       (2, 'What is autoboxing', '1'),
       (3, 'What kind of interfaces JCF contains', '2'),
       (4, 'What is the difference between ArrayList & LinkedList', '2');
UNLOCK TABLES;

LOCK TABLES `answer` WRITE;
INSERT INTO `answer`
VALUES (1, 'Массив представляет собой набор данных определенного типа', true, '1'),
       (2, 'Массив это второе название коллекции', false, '1'),
       (3, 'Размер массива можно менять', false, '1'),
       (4, 'Autoboxing - представляет собой автоматическое приведение примитивного типа к классу оболочке', true, '2'),
       (5, 'Autoboxing - пример {Integer a = new Integer (2); int i=1+a}', false, '2'),
       (6, 'Autoboxing не возможно с типом char-Character', false, '2'),
       (7, 'List, Set, Queue', true, '3'),
       (8, 'Map, Container, SortedContainer', false, '3'),
       (9, 'String, StringBuffer, StringBuilder', false, '3'),
       (10, 'ArrayList - строится на основе массива,LinkedList - двусвязанный список', true, '4'),
       (11, 'ArrayList - потокобезопасный,LinkedList - непотокобезопасный', false, '4'),
       (12, 'ArrayList и LinkedList два названия для одного списка', false, '4');
UNLOCK TABLES;

LOCK TABLES `literature` WRITE;
INSERT INTO `literature`
VALUES (1, 'About arrays & primitive types', '1'),
       (2, 'About autoboxing', '2'),
       (3, 'About kinds of interfaces JCF contains', '3'),
       (4, 'About ArrayList & LinkedList', '4');
UNLOCK TABLES;

LOCK TABLES `link` WRITE;
INSERT INTO `link`
VALUES (1, 'https://docs.google.com/document/linc1', '1'),
       (2, 'https://docs.google.com/document/linc2', '2'),
       (3, 'https://docs.google.com/document/linc3', '3'),
       (4, 'https://docs.google.com/document/linc4', '4');
UNLOCK TABLES;

