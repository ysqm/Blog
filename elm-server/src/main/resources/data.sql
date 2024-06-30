CREATE DATABASE  IF NOT EXISTS `blogdata` ;
USE `blogdata`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
                       user_id              INT AUTO_INCREMENT PRIMARY KEY,
                       username             VARCHAR(50) UNIQUE NOT NULL,
                       password             VARCHAR(255) NOT NULL,
                       email                VARCHAR(100) UNIQUE NOT NULL,
                       avatar               TEXT,
                       create_time          DATETIME NOT NULL,
                       last_login_time      DATETIME DEFAULT NULL,
                       bio                  TEXT,
                       update_time          DATETIME NOT NULL,
                       wechat_account       VARCHAR(100),
                       qq_account           VARCHAR(100),
                       is_logged_out        INT DEFAULT 0 CHECK (is_logged_out IN (0, 1))
);

DROP TABLE IF EXISTS `Articles`;
CREATE TABLE Articles (
                         article_id         INT AUTO_INCREMENT PRIMARY KEY,
                         user_id            INT,
                         title              VARCHAR(255) NOT NULL,
                         content            TEXT NOT NULL,
                         publish_date       DATETIME NOT NULL,
                         update_date        DATETIME NULL,
                         status             ENUM('published', 'draft', 'deleted', 'hidden') NOT NULL,
                         heat               INT DEFAULT 0,
                         FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS `Comments`;
CREATE TABLE Comments (
                         comment_id         INT AUTO_INCREMENT PRIMARY KEY,
                         article_id         INT NOT NULL,
                         user_id            INT NOT NULL,
                         content            TEXT NOT NULL,
                         comment_date       DATETIME NOT NULL,
                         parent_comment_id  INT NULL,
                         FOREIGN KEY (article_id) REFERENCES Articles(article_id),
                         FOREIGN KEY (user_id) REFERENCES Users(user_id),
                         FOREIGN KEY (parent_comment_id) REFERENCES Comments(comment_id)
);

DROP TABLE IF EXISTS `Tags`;
CREATE TABLE Tags (
                     tag_id         INT AUTO_INCREMENT PRIMARY KEY,
                     tag_name       VARCHAR(50) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS `ArticleTags`;
CREATE TABLE ArticleTags (
                            article_id      INT NOT NULL,
                            tag_id          INT NOT NULL,
                            PRIMARY KEY (article_id, tag_id),
                            FOREIGN KEY (article_id) REFERENCES Articles(article_id),
                            FOREIGN KEY (tag_id) REFERENCES Tags(tag_id)
);

DROP TABLE IF EXISTS `LikeDislikes`;
CREATE TABLE LikeDislikes (
                            record_id           INT AUTO_INCREMENT PRIMARY KEY,
                            user_id             INT NOT NULL,
                            target_id           INT NOT NULL,
                            record_type         ENUM('article', 'comment') NOT NULL,
                            record_status       ENUM('like', 'dislike') NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES Users(user_id),
                            FOREIGN KEY (target_id) REFERENCES Articles(article_id),
                            FOREIGN KEY (target_id) REFERENCES Comments(comment_id)
);

DROP TABLE IF EXISTS `Follows`;
CREATE TABLE Follows (
                             user_id                INT NOT NULL,
                             followed_user_id       INT NOT NULL,
                             PRIMARY KEY (user_id, followed_user_id),
                             FOREIGN KEY (user_id) REFERENCES Users(user_id),
                             FOREIGN KEY (followed_user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS `Templates`;
CREATE TABLE Templates (
                          template_id       INT AUTO_INCREMENT PRIMARY KEY,
                          template_name     VARCHAR(100) NOT NULL,
                          template_content  TEXT NOT NULL
);

DROP TABLE IF EXISTS `UserTemplates`;
CREATE TABLE UserTemplates (
                               user_id      INT NOT NULL,
                               template_id  INT NOT NULL,
                               PRIMARY KEY (user_id, template_id),
                               FOREIGN KEY (user_id) REFERENCES Users(user_id),
                               FOREIGN KEY (template_id) REFERENCES Templates(template_id)
);

DROP TABLE IF EXISTS `CommunityArticles`;
CREATE TABLE CommunityArticles (
                                 article_id     INT NOT NULL,
                                 display_type   ENUM('heat', 'other') NOT NULL,
                                 admin_id       INT NOT NULL,
                                 PRIMARY KEY (article_id),
                                 FOREIGN KEY (article_id) REFERENCES Articles(article_id),
                                 FOREIGN KEY (admin_id) REFERENCES UserS(user_id)
);

DROP TABLE IF EXISTS `Drafts`;
CREATE TABLE Drafts (
                       draft_id     INT AUTO_INCREMENT PRIMARY KEY,
                       user_id      INT NOT NULL,
                       title        VARCHAR(255) NOT NULL,
                       content      TEXT,
                       save_date    DATETIME NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS `TaskTimes`;
CREATE TABLE TaskTimes (
                                task_id         INT AUTO_INCREMENT PRIMARY KEY,
                                article_id      INT NOT NULL,
                                publish_time    DATETIME NOT NULL,
                                status          ENUM('pending', 'completed', 'failed') NOT NULL,
                                FOREIGN KEY (article_id) REFERENCES Articles(article_id)
);

DROP TABLE IF EXISTS `Notifications`;
CREATE TABLE Notifications (
                              notification_id   INT AUTO_INCREMENT PRIMARY KEY,
                              user_id           INT NOT NULL,
                              message           TEXT NOT NULL,
                              is_read           BOOLEAN DEFAULT FALSE,
                              create_date       DATETIME NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS `Favorites`;
CREATE TABLE Favorites (
                          favorite_id       INT AUTO_INCREMENT PRIMARY KEY,
                          user_id           INT NOT NULL,
                          article_id        INT NOT NULL,
                          create_date       DATETIME NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES Users(user_id),
                          FOREIGN KEY (article_id) REFERENCES Articles(article_id)
);

DROP TABLE IF EXISTS `Reports`;
CREATE TABLE Reports (
                        report_id               INT AUTO_INCREMENT PRIMARY KEY,
                        reporter_id             INT NOT NULL,
                        reported_content_id     INT NOT NULL,
                        content_type            ENUM('article', 'comment') NOT NULL,
                        reason                  TEXT NOT NULL,
                        report_date             DATETIME NOT NULL,
                        status ENUM('pending', 'reviewed', 'resolved') NOT NULL,
                        FOREIGN KEY (reporter_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS `AdminLogs`;
CREATE TABLE AdminLogs (
                           log_id               INT AUTO_INCREMENT PRIMARY KEY,
                           admin_id             INT NOT NULL,
                           action               VARCHAR(255) NOT NULL,
                           action_date          DATETIME NOT NULL,
                           FOREIGN KEY (admin_id) REFERENCES Users(user_id)
);