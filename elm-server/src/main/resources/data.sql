CREATE DATABASE  IF NOT EXISTS `blogdata` ;
USE `blogdata`;

DROP TABLE IF EXISTS `Users`;
CREATE TABLE Users (
                       user_id           INT AUTO_INCREMENT PRIMARY KEY,
                       username         VARCHAR(50) UNIQUE NOT NULL,
                       password         VARCHAR(255) NOT NULL,
                       email            VARCHAR(100) ,
                       avatar           TEXT,
                       register_date     DATETIME,
                       bio              TEXT,
                       update_time       DATETIME,
                       wechat_account    VARCHAR(100),
                       qq_account        VARCHAR(100)
);

-- 创建文章表
DROP TABLE IF EXISTS `Articles`;
CREATE TABLE `Articles` (
                            `article_id` INT AUTO_INCREMENT PRIMARY KEY,
                            `user_id` INT NOT NULL,
                            `title` VARCHAR(255) NOT NULL,
                            `content_path` VARCHAR(255) NOT NULL, -- 文件路径
                            `publish_date` DATETIME NOT NULL,
                            `update_date` DATETIME,
                            `status` ENUM('published', 'draft', 'deleted', 'hidden') NOT NULL,
                            `heat` INT DEFAULT 0,
                            `is_deleted` BOOLEAN DEFAULT 0,
                            FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)
);

-- 创建标签表
DROP TABLE IF EXISTS `Tags`;
CREATE TABLE `Tags` (
                        `tag_id` INT AUTO_INCREMENT PRIMARY KEY,
                        `tag_name` VARCHAR(50) UNIQUE NOT NULL
);

-- 创建文章标签表
DROP TABLE IF EXISTS `ArticleTags`;
CREATE TABLE `ArticleTags` (
                               `article_id` INT,
                               `tag_id` INT,
                               PRIMARY KEY (`article_id`, `tag_id`),
                               FOREIGN KEY (`article_id`) REFERENCES `Articles`(`article_id`) ON DELETE CASCADE,
                               FOREIGN KEY (`tag_id`) REFERENCES `Tags`(`tag_id`) ON DELETE CASCADE
);