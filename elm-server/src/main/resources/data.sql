CREATE DATABASE  IF NOT EXISTS `blogdata` ;
USE `blogdata`;

DROP TABLE IF EXISTS `Users`;
CREATE TABLE Users (
                       UserID           INT AUTO_INCREMENT PRIMARY KEY,
                       Username         VARCHAR(50) UNIQUE NOT NULL,
                       Password         VARCHAR(255) NOT NULL,
                       Email            VARCHAR(100) UNIQUE NOT NULL,
                       Avatar           TEXT,
                       RegisterDate     DATETIME NOT NULL,
                       Bio              TEXT,
                       UpdateTime       DATETIME NOT NULL,
                       WeChatAccount    VARCHAR(100),
                       QQAccount        VARCHAR(100)
);
