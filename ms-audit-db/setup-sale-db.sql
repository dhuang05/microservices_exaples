-- CREATE USER 'dph'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'sale_user'@'%' identified by 'N0Passw0rd'; 

CREATE DATABASE IF NOT EXISTS sale_db;
GRANT ALL PRIVILEGES ON sale_db.* to 'sale_user';
GRANT ALL PRIVILEGES ON sale_db.* to 'dph';

use sale_db;
CREATE TABLE `MS_ORDER` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_account_id` bigint(20) not null,
  `item_Id` bigint(20) not null,
  `quantity` int not null,
  `ordered_date` timestamp default current_timestamp NOT NULL,
  PRIMARY KEY (`order_id`)
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
