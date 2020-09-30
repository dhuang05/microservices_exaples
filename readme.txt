How to run:

1.	Download the codes from https://github.com/dhuang05/microservices_exaples
2.	Download and install docker and make sure it is running.
3.	Run “docker run -d -p 9411:9411 openzipkin/zipkin --name zipkin” to install zipkin for recording microservices call-graphs.
4.	Go to ms-lab-db folder, then run “docker-compose up -d –build”, this will install database and populate database schema.
5.	Start registration and discovery service for microservices, go to eureka-server folder, run “../gradlew build bootRun”.
6.	Start gateway and load balancer, go to zuul-server folder, run “../gradlew build bootRun”.
7.	Start global transaction server, go to seata-server folder, run “./bin/seata-server.sh”.
8.	Start Kafka messaging server, go to kafka-server, run “./bin/zookeeper-server-start.sh ./config/zookeeper.properties” and “./bin/kafka-server-start.sh ./config/server.properties”.
9.	Launch all microservices, for each of below microservice, go to the folder, and run “../gradlew build bootRun”. The folders are “account-api”, “audit-log-api”, “inventory-api”, “payment-booking-api”, “order-api” and “sale-system”.
10.	Check Eureka server make sure all miscroservices are up “seata”, “zuul ” and account-service”, “audit-log-service”, “inventory-service”, “payment-booking-service”, “order-service” and “sale-system”.
11.	Download Postman, then import test collection from “dph-micro-services-test.postman_collection.json”. To test, run “order” test case, if it get response without error, the system is up and running.
