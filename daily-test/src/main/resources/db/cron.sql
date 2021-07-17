-- CREATE DATABASE `socks`;
-- USE `SOCKS`;
DROP DATABASE IF EXISTS `socks`;
DROP TABLE IF EXISTS `cron`;
CREATE TABLE `cron`  (
 `cron_id` int4 NOT NULL PRIMARY KEY,
 `cron` varchar(30) NOT NULL,
 `cron_name` varchar(64) NOT NULL
);
INSERT INTO `cron` VALUES ('0', '0/5 * * * * ?','test');
INSERT INTO `cron` VALUES ('1', '0 0 23 * * ?','spliteData');
INSERT INTO `cron` VALUES ('2', '0 0 1 * * ?','initStockTableData');