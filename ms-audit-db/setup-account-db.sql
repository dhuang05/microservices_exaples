-- CREATE USER 'dph'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'account_user'@'%' identified by 'N0Passw0rd'; 

CREATE DATABASE IF NOT EXISTS account_db;
GRANT ALL PRIVILEGES ON account_db.* to 'account_user';
GRANT ALL PRIVILEGES ON account_db.* to 'dph';

use account_db;

CREATE TABLE `MS_ACCOUNT` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) not null,
  `balance` decimal(10, 2) not null,
 `updated_date` timestamp default current_timestamp NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into MS_ACCOUNT (name, balance)
values ('Daping', 60000);

insert into MS_ACCOUNT (name, balance)
values ('Paul', 1700);

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
