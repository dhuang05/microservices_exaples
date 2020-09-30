#under eureka folder
docker run -d -p 9411:9411 openzipkin/zipkin --name zipkin

#under ms-lab-db folder
docker-compose up -d --build 