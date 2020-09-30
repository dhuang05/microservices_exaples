-- CREATE USER 'dph'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'payment_user'@'%' identified by 'N0Passw0rd'; 

CREATE DATABASE IF NOT EXISTS payment_db;
GRANT ALL PRIVILEGES ON payment_db.* to 'payment_user';
GRANT ALL PRIVILEGES ON payment_db.* to 'dph';


use payment_db;
CREATE TABLE `MS_PAYMENT` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(256) not null,
  `payment_amount` decimal(10, 2) not null,
  `detail` varchar(512),
  `paid_date` timestamp default current_timestamp NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- seata reequired table
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
