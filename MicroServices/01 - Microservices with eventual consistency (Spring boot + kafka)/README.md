# Microservices with eventual consistency
Samples micro services with spring boot and kafka which use eventual consistency.

# How to setup

## Install and configure third party applications

### Mysql / MariaDB
- Install mysql database
- Create databases for micro services (order_service and customer_service)
>Note: see application.properties files
- Add username and password to application.properties files
```yml
spring.datasource.url=jdbc:mysql://localhost:3306/{database-name}?autoReconnect=true&useSSL=false
spring.datasource.username={username}
spring.datasource.password={password}
```

### Apache Kafka
- Download [apache kafka](https://kafka.apache.org/downloads)
- Go to kafka installed location
- Start inbuilt zookeeper
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
- Start kafka
```
bin/kafka-server-start.sh config/server.properties
```
- Create topics (order and customer topics)
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic customer
```

## Clone and build project
- Clone samples repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "MicroServices/01 - Microservices with eventual consistency (Spring boot + kafka)" folder
- Run micro services (Run both projects)
```
cd customer-service
mvn spring-boot:run
```
```
cd order-service
mvn spring-boot:run
```

# Use api
## End points available
- http://localhost:8081/customers - Add new customer
```json
{
  "customerName": "test",
  "creditLimit": 10000
}
```
- http://localhost:8081/customers/{id} - Get customer using id
- http://localhost:8082/orders - Add new order
```json
{
  "customerId": 1,
  "orderTotal": 2000
}
```
- http://localhost:8082/orders/{id} - Get order by id

>Note: All the requests must use JSON data type