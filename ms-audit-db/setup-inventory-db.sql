-- CREATE USER 'dph'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'inventory_user'@'%' identified by 'N0Passw0rd'; 

CREATE DATABASE IF NOT EXISTS inventory_db;
GRANT ALL PRIVILEGES ON inventory_db.* to 'inventory_user';
GRANT ALL PRIVILEGES ON inventory_db.* to 'dph';

use inventory_db;
CREATE TABLE `MS_ITEM` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) not null,
  `price` decimal(10, 2) not null,
  `quantity` int not null,
  `updated_date` timestamp default current_timestamp NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


insert into MS_ITEM (name, price, quantity)
values ('IPhoneX', 1000.00, 20);

insert into MS_ITEM (name, price, quantity)
values ('IPad', 500.00, 30);

insert into MS_ITEM (name, price, quantity)
values ('IWatch', 200.00, 10);

insert into MS_ITEM (name, price, quantity)
values ('ITube', 200.00, 0);


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
