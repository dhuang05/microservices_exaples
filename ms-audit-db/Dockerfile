FROM mysql:8.0

COPY setup-account-db.sql /docker-entrypoint-initdb.d/setup-account-db.sql
COPY setup-payment-db.sql /docker-entrypoint-initdb.d/setup-payment-db.sql
COPY setup-auditlog-db.sql /docker-entrypoint-initdb.d/setup-auditlog-db.sql
COPY setup-inventory-db.sql /docker-entrypoint-initdb.d/setup-inventory-db.sql
COPY setup-sale-db.sql /docker-entrypoint-initdb.d/setup-sale-db.sql
COPY setup-seata-server-db.sql /docker-entrypoint-initdb.d/setup-seata-server-db.sql
COPY setup_xa_permission.sql /docker-entrypoint-initdb.d/setup_xa_permission.sql
