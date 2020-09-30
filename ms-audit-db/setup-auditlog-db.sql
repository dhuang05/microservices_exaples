-- CREATE USER 'dph'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'auditlog_user'@'%' identified by 'N0Passw0rd'; 

CREATE DATABASE IF NOT EXISTS auditlog_db;
GRANT ALL PRIVILEGES ON mslog_db.* to 'auditlog_user';
GRANT ALL PRIVILEGES ON mslog_db.* to 'dph';

use auditlog_db;
CREATE TABLE `AUDIT_LOG` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `global_request_id`  varchar(64) not null,
  `entity_name` varchar(64),
  `entity_id` varchar(64),
  `operation_type` varchar(16),
  `nano_stamp` bigint(20) NOT NULL,
  `event_stamp` timestamp default current_timestamp NOT NULL,
  `description`  varchar(256),
  `json_data`  varchar(1024),
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

