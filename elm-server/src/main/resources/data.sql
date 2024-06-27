CREATE DATABASE  IF NOT EXISTS `blogdata` ;
USE `blogdata`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
                       user_id              INT AUTO_INCREMENT PRIMARY KEY,
                       username             VARCHAR(50) UNIQUE NOT NULL,
                       password             VARCHAR(255) NOT NULL,
                       email                VARCHAR(100) UNIQUE NOT NULL,
                       avatar               TEXT,
                       register_date        DATETIME NOT NULL,
                       last_login_time      DATETIME DEFAULT NULL,
                       bio                  TEXT,
                       update_time          DATETIME NOT NULL,
                       wechat_account       VARCHAR(100),
                       qq_account           VARCHAR(100),
                       is_logged_out        INT DEFAULT 0 CHECK (is_logged_out IN (0, 1))
);
