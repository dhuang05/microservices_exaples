# **Example of Microservices using Zuul, Eureka, Kafka, Spring Cloud/Boot and Global TX**

By Daping Huang dhuang05@gmail.com

**How to run the example**

1. Download the codes from [https://github.com/dhuang05/microservices\_exaples](https://github.com/dhuang05/microservices_exaples)
2. Download and install docker and make sure it is running.
3. Run &quot;_docker run -d -p 9411:9411 openzipkin/zipkin --name zipkin_&quot; to install zipkin for recording microservices call-graphs.
4. Go to _ms-lab-db_ folder, then run &quot;_docker-compose up -d –build_&quot;, this will install database and populate database schema.
5. Start registration and discovery service for microservices, go to _eureka-server_ folder, run _&quot;../gradlew build bootRun_&quot;.
6. Start gateway and load balancer, go to _zuul-server_ folder, run _&quot;../gradlew build bootRun_&quot;.
7. Start global transaction server, go to _seata-server_ folder, run _&quot;./bin/seata-server.sh_&quot;.
8. Start Kafka messaging server, go to _kafka-server_, run _&quot; __./bin/__ zookeeper __-server-start.sh ./__ config __/zookeeper.properties_&quot; and _&quot;./bin/__ kafka __-server-start.sh ./__ config__/server.properties_&quot;.
9. Launch all microservices, for each of below microservice, go to the folder, and run _&quot;../gradlew build bootRun_&quot;. The folders are &quot;_account-api_&quot;, &quot;_audit-log-api_&quot;, &quot;_inventory-api_&quot;, &quot;_payment-booking-api_&quot;, &quot;_order-api_&quot; and &quot;_sale-system_&quot;.
10. Check Eureka server make sure all miscroservices are up &quot;_seata&quot;, &quot;zuul_ &quot; and _account-service_&quot;, &quot;_audit-log-service_&quot;, &quot;_inventory-service_&quot;, &quot;_payment-booking-service_&quot;, &quot;_order-service_&quot; and &quot;_sale-system_&quot;.
11. Download Postman, then import test collection from &quot;_dph-micro-services-test.postman\_collection.json_&quot;. To test, run &quot;_order_&quot; test case, if it get response without error, the system is up and running.
